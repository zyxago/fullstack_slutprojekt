package nu.te4.fullstack_slutprojekt.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
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

    public List<Recipe> getRecipes() {
        LOGGER.debug("Getting recipes.");
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM recipe");
            ResultSet data = stmt.executeQuery();
            while (data.next()) {
                List<Ingredient> ingredientList = new ArrayList<>();
                List<String> categoryList = new ArrayList<>();
                List<String> instructionList = new ArrayList<>();
                RecipeBuilder recipeBuilder = new RecipeBuilder()
                        .setId(data.getInt("id"))
                        .setWriterId(data.getInt("writer_id"))
                        .setImage(data.getString("img"))
                        .setLikes(data.getInt("likes"))
                        .setRepports(data.getInt("repports"))
                        .setInformation(data.getString("info"));

                CallableStatement cs = conn.prepareCall("CALL recipe_attached_data(?)");
                cs.setInt(1, data.getInt("id"));

                if (cs.execute()) {
                    //Get ingredient list
                    ResultSet resData = cs.getResultSet();
                    while (resData.next()) {
                        ingredientList.add(new Ingredient(
                                resData.getString("ingredient"),
                                resData.getInt("amount"),
                                resData.getString("messurment")));
                    }
                    recipeBuilder.setIngredients(ingredientList);
                    resData.close();

                    if (cs.getMoreResults()) {
                        //Get category list
                        resData = cs.getResultSet();
                        while (resData.next()) {
                            categoryList.add(resData.getString("category"));
                        }
                        recipeBuilder.setCategories(categoryList);
                        resData.close();

                        if (cs.getMoreResults()) {
                            //Get instruction list
                            resData = cs.getResultSet();
                            while (resData.next()) {
                                instructionList.add(resData.getString("instruction_text"));
                            }
                            recipeBuilder.setInstructions(instructionList);
                            resData.close();
                        }
                    }
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
        try (Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement stmt = conn.prepareStatement("");
        } catch (Exception e){
            LOGGER.error("Error in RecipeBean.addRecipe: " + e.getMessage());
        }
        return 0;
    }

    public int modifyRecipe(int id) {
        return 0;
    }

    public int likeRecipe() {
        return 0;
    }

    public int repportRecipe() {
        return 0;
    }

    public int removeRecipe(int id) {
        return 0;
    }

}
