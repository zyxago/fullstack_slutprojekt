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

    /**
     *
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
                stmt.setString(4, ingredient.getMessurment());
                stmt.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("Error in RecipeBean.addIngredientList: " + e.getMessage());
        }
    }
}
