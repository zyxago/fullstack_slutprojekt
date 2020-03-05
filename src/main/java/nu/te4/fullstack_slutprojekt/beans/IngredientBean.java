package nu.te4.fullstack_slutprojekt.beans;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import nu.te4.fullstack_slutprojekt.entities.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class IngredientBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientBean.class);

    /**
     * @param resData
     * @return
     */
    public List<Ingredient> getRecipeIngredients(ResultSet resData) {
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            while (resData.next()) {
                ingredientList.add(new Ingredient(
                        resData.getString("ingredient"),
                        resData.getInt("amount"),
                        resData.getString("measurement")));
            }
        } catch (Exception e) {
            LOGGER.error("Error in IngredientBean.getRecipeIngredients: " + e.getMessage());
        }
        return ingredientList;
    }

    /**
     * @param id
     * @param ingredientList
     */
    public void addIngredientList(int id, List<Ingredient> ingredientList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addIngredient(?, ?, ?, ?)");
            for (Ingredient ingredient : ingredientList) {
                stmt.setInt(1, id);
                stmt.setString(2, ingredient.getName());
                stmt.setInt(3, ingredient.getAmount());
                stmt.setString(4, ingredient.getMeasurement());
                stmt.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("Error in IngredientBean.addIngredientList: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param ingredientList
     */
    public void updateIngredientList(int id, List<Ingredient> ingredientList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM ingredient_recipe WHERE recipe_id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            addIngredientList(id, ingredientList);
        } catch (Exception e) {
            LOGGER.error("Error in IngredientBean.updateIngredientList: " + e.getMessage());
        }
    }

    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ingredient");
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                ingredients.add(data.getString(1));
            }
        } catch (Exception e){
            LOGGER.error("Error in IngredientBean.getIngredients: " + e.getMessage());
        }
        return ingredients;
    }

    public List<String> getMeasurement() {
        List<String> measurement = new ArrayList<>();
        try(Connection conn = new ConnectionFactory().getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM measurement");
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                measurement.add(data.getString(1));
            }
        } catch (Exception e){
            LOGGER.error("Error in IngredientBean.getMeasurement: " + e.getMessage());
        }
        return measurement;
    }
}
