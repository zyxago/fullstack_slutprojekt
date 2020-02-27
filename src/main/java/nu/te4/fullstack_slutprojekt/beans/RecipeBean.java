package nu.te4.fullstack_slutprojekt.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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

    /**
     * @return
     */
    public List<Recipe> getRecipes() {
        LOGGER.debug("Getting recipes.");
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM recipe");
            ResultSet data = stmt.executeQuery();
            CallableStatement cs = conn.prepareCall("CALL recipe_attached_data(?)");
            while (data.next()) {
                RecipeBuilder recipeBuilder = new RecipeBuilder()
                        .setId(data.getInt("id"))
                        .setWriterId(data.getInt("writer_id"))
                        .setImage(data.getBlob("img"))
                        .setLikes(data.getInt("likes"))
                        .setRepports(data.getInt("repports"))
                        .setInformation(data.getString("info"));

                cs.setInt(1, data.getInt("id"));
                if (cs.execute()) {
                    ResultSet resData = cs.getResultSet();
                    recipeBuilder.setIngredients(ingredientBean.getIngredients(resData));
                    resData.close();

                    resData = cs.getResultSet();
                    recipeBuilder.setCategories(categoryBean.getCategoryList(resData));
                    resData.close();

                    resData = cs.getResultSet();
                    recipeBuilder.setInstructions(instructionBean.getInstructionList(resData));
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

    public int addRecipe(Recipe recipe) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO recipe VALUES(null, ?, ?, ?, ?)");
            stmt.setInt(1, recipe.getWriterId());
            stmt.setBlob(2, recipe.getImage());
            stmt.setString(3, recipe.getInformation());
            ingredientBean.addIngredientList(recipe.getId(), recipe.getIngredients());
            categoryBean.addCategoryList(recipe.getId(), recipe.getCategories());
            instructionBean.addInstructionList(recipe.getId(), recipe.getInstructions());
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addRecipe: " + e.getMessage());
        }
        return 0;
    }

    public int modifyRecipe(int id, Recipe recipe) {
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
        return 0;
    }

}
