package nu.te4.fullstack_slutprojekt.beans;

import nu.te4.fullstack_slutprojekt.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Stateless
public class StatsBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatsBean.class);

    public int insertStats(){
        try (Connection connection = new ConnectionFactory().getConnection()){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO stats VALUES(null, 0, 0)", Statement.RETURN_GENERATED_KEYS);
            if(stmt.executeUpdate() > 0){
                try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (Exception e){
            LOGGER.error("Error in StatsBean.insertStats: " + e.getMessage());
        }
        return 0;
    }
}
