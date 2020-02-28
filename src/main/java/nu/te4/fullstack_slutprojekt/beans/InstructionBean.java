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
public class InstructionBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstructionBean.class);

    /**
     *
     * @param resData
     * @return
     */
    public List<String> getInstructionList(ResultSet resData) {
        List<String> instructionList = new ArrayList<>();
        try {
            while (resData.next()) {
                instructionList.add(resData.getString("instruction"));
            }
        } catch (Exception e) {
            LOGGER.error("Error in InstructionBean.getInstructionList: " + e.getMessage());
        }
        return instructionList;
    }

    private int mapInstructionToRecipe(int recipeId, String instruction, int instructionId, int order) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO instruction_recipe VALUES(?, ?, ?, ?)");
            stmt.setInt(1, instructionId);
            stmt.setInt(2, recipeId);
            stmt.setInt(3, order);
            stmt.setString(4, instruction);
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Error in InstructionBean.mapInstructionToRecipe: " + e.getMessage());
        }
        return 0;
    }

    /**
     *
     * @param id
     * @param instructionList
     */
    public void addInstructionList(int id, List<String> instructionList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addInstruction(?)");
            for (String instruction : instructionList) {
                stmt.setString(1, instruction);
                ResultSet data = stmt.executeQuery();
                if (data.next()) {
                    mapInstructionToRecipe(id, instruction, data.getInt(1), instructionList.indexOf(instruction));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error in InstructionBean.addInstructionList: " + e.getMessage());
        }
    }
}
