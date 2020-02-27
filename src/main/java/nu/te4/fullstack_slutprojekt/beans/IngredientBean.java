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
     *
     * @param resData
     * @return
     */
    public List<Ingredient> getIngredients(ResultSet resData) {
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            while (resData.next()) {
                ingredientList.add(new Ingredient(
                        resData.getString("ingredient"),
                        resData.getInt("amount"),
                        resData.getString("messurment")));
            }
        } catch (Exception e) {
            LOGGER.error("Error in IngredientBean.getIngredients: " + e.getMessage());
        }
        return ingredientList;
    }

    private int mapIngredientToRecipe(int id, Ingredient ingredient) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ingredient_recipe VALUES(?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, ingredient.getName());
            stmt.setInt(3, ingredient.getAmount());
            stmt.setString(4, ingredient.getMessurment());
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in IngredientBean.addIngredientList: " + e.getMessage());
        }
        return 0;
    }

    /**
     *
     * @param id
     * @param ingredientList
     */
    public void addIngredientList(int id, List<Ingredient> ingredientList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addIngredient(?)");//Lägg till ingrediens via function som returnerar 1 om den lyckades 0 om fail
            for (Ingredient ingredient : ingredientList) {
                stmt.setString(1, ingredient.getName());
                stmt.executeQuery();
                mapIngredientToRecipe(id, ingredient);
            }
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addIngredientList: " + e.getMessage());
        }
    }
}
