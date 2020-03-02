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
                instructionList.add(resData.getString("instruction_text"));
            }
        } catch (Exception e) {
            LOGGER.error("Error in InstructionBean.getInstructionList: " + e.getMessage());
        }
        return instructionList;
    }

    /**
     *
     * @param id
     * @param instructionList
     */
    public void addInstructionList(int id, List<String> instructionList) {
        try (Connection conn = new ConnectionFactory().getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT addInstruction(?, ?, ?)");
            for (String instruction : instructionList) {
                stmt.setInt(1, id);
                stmt.setInt(2, instructionList.indexOf(instruction));
                stmt.setString(3, instruction);
                stmt.executeQuery();
            }
        } catch (Exception e) {
            LOGGER.error("Error in InstructionBean.addInstructionList: " + e.getMessage());
        }
    }
}
