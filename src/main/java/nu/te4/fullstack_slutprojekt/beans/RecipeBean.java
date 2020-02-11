package nu.te4.fullstack_slutprojekt.beans;

import java.util.List;
import javax.ejb.Stateless;
import nu.te4.fullstack_slutprojekt.entities.Recipe;

/**
 *
 * @author erikh
 */
@Stateless
public class RecipeBean {
    
    public List<Recipe> getRecipies(){
        return null;
    }
    
    public int addRecipe(Recipe recipe){
        return 0;
    }
    
    public int modifyRecipe(int id){
        return 0;
    }
    
    public int removeRecipe(int id){
        return 0;
    }
    
}
