package nu.te4.fullstack_slutprojekt.beans;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.rowset.serial.SerialBlob;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import nu.te4.fullstack_slutprojekt.entities.Comment;
import nu.te4.fullstack_slutprojekt.entities.Ingredient;
import nu.te4.fullstack_slutprojekt.entities.Recipe;
import nu.te4.fullstack_slutprojekt.entities.RecipeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erikh
 */
@Stateless
public class RecipeBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeBean.class);

    @EJB
    IngredientBean ingredientBean;
    @EJB
    CategoryBean categoryBean;
    @EJB
    InstructionBean instructionBean;
    @EJB
    StatsBean statsBean;

    public List<Recipe> getRecipes() {
        LOGGER.debug("Getting recipes.");
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM recipe_full");
            ResultSet data = stmt.executeQuery();
            CallableStatement cs = conn.prepareCall("CALL recipe_attached_data(?)");
            while (data.next()) {
                RecipeBuilder recipeBuilder = new RecipeBuilder()
                        .setId(data.getInt("id"))
                        .setWriterId(data.getInt("writer_id"))
                        .setLikes(data.getInt("likes"))
                        .setRepports(data.getInt("repports"))
                        .setInformation(data.getString("info"))
                        .setTitle(data.getString("title"));

                cs.setInt(1, data.getInt("id"));
                if (cs.execute()) {
                    ResultSet resData = cs.getResultSet();
                    recipeBuilder.setIngredients(ingredientBean.getRecipeIngredients(resData));
                    resData.close();
                    cs.getMoreResults();
                    resData = cs.getResultSet();
                    recipeBuilder.setCategories(categoryBean.getRecipeCategoryList(resData));
                    resData.close();
                    cs.getMoreResults();
                    resData = cs.getResultSet();
                    recipeBuilder.setInstructions(instructionBean.getRecipeInstructionList(resData));
                    resData.close();
                }
                recipeList.add(recipeBuilder.build());
            }
            data.close();
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.getRecipes: " + e.getMessage());
        }
        return recipeList;
    }

    public InputStream getImage(int id) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT img FROM recipe WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet data = stmt.executeQuery();
            if (data.next()) {
                return data.getBlob("img").getBinaryStream();
            }
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.getImage: " + e.getMessage());
        }
        return null;
    }

    public int addRecipe(Recipe recipe) {
        int recipeId = 0;
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO recipe VALUES(null, ?, ?, null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, recipe.getTitle());
            stmt.setInt(2, recipe.getWriterId());
            stmt.setString(3, recipe.getInformation());
            stmt.setInt(4, statsBean.insertStats());
            if (stmt.executeUpdate() > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        recipeId = generatedKeys.getInt(1);
                        ingredientBean.addIngredientList(recipeId, recipe.getIngredients());
                        categoryBean.addCategoryList(recipeId, recipe.getCategories());
                        instructionBean.addInstructionList(recipeId, recipe.getInstructions());
                    } else {
                        throw new Exception("Failed to get id of newly generated recipe");
                    }
                }
            }
            return recipeId;
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addRecipe: " + e.getMessage());
        }
        return 0;
    }

    public int modifyRecipe(Recipe recipe) {
        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement stmt = conn.prepareStatement("UPDATE recipe SET title = ?, info = ? WHERE id = ?");
            stmt.setString(1, recipe.getTitle());
            stmt.setString(2, recipe.getInformation());
            stmt.setInt(3, recipe.getId());
            instructionBean.updateInstructionList(recipe.getId(), recipe.getInstructions());
            ingredientBean.updateIngredientList(recipe.getId(), recipe.getIngredients());
            categoryBean.updateCategoryList(recipe.getId(), recipe.getCategories());
        } catch (Exception e){
            LOGGER.error("Error in RecipeBean.modifyRecipe: " + e.getMessage());
        }
        return 0;
    }

    public int likeRecipe(int id) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE stats SET likes = likes+1 WHERE id IN (SELECT stats_id FROM recipe WHERE id = ?)");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.likeRecipe: " + e.getMessage());
            return 0;
        }
    }

    public int repportRecipe(int id) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE stats SET repports = repports+1 WHERE id IN (SELECT stats_id FROM recipe WHERE id = ?)");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.repportRecipe: " + e.getMessage());
            return 0;
        }
    }

    public int removeRecipe(int id) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM recipe WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addImage: " + e.getMessage());
            return 0;
        }
    }

    public int addImage(int id, InputStream image) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE recipe SET img = ? WHERE id = ?");
            stmt.setBlob(1, image);
            stmt.setInt(2, id);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addImage: " + e.getMessage());
            return 0;
        }
    }
}
