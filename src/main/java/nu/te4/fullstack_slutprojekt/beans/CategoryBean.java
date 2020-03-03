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

    /**
     * @param id
     * @param categoryList
     */
    public void addCategoryList(int id, List<String> categoryList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addCategory(?, ?)");
            for (String category : categoryList) {
                stmt.setString(1, category);
                stmt.setInt(2, id);
                stmt.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("Error in CategoryBean.addCategoryList: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param categories
     */
    public void updateCategoryList(int id, List<String> categories) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM category_recipe WHERE recipe_id = ?");
            stmt.setInt(1, id);
            stmt.executeQuery();
            addCategoryList(id, categories);
        } catch (Exception e) {
            LOGGER.error("Error in CategoryBean.updateCategoryList: " + e.getMessage());
        }
    }
}
