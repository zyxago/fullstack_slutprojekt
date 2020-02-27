package nu.te4.fullstack_slutprojekt.beans;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBean.class);

    /**
     *
     * @param resData
     * @return
     */
    public List<String> getCategoryList(ResultSet resData) {
        List<String> categoryList = new ArrayList<>();
        try {
            while (resData.next()) {
                categoryList.add(resData.getString("category"));
            }
        } catch (Exception e) {
            LOGGER.error("Error in CategoryBean.getCategoryList: " + e.getMessage());
        }
        return categoryList;
    }

    private int mapCategoryToRecipe(int id, String category) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO category_recipe VALUES(?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, category);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in CategoryBean.mapCategoryToRecipe: " + e.getMessage());
        }
        return 0;
    }

    /**
     *
     * @param id
     * @param categoryList
     */
    public void addCategoryList(int id, List<String> categoryList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addCategory(?)");
            for (String category : categoryList) {
                stmt.setString(1, category);
                stmt.executeQuery();
                mapCategoryToRecipe(id, category);
            }
        } catch (Exception e) {
            LOGGER.error("Error in CategoryBean.addCategoryList: " + e.getMessage());
        }
    }
}
