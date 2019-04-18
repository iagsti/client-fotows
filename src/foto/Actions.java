/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foto;

/**
 *
 * @author schneider
 */
public class Actions {
    private String action;
    private final String[] enabledActions;
    private final String[] args;
    
    Actions(String[] args)
    {
        
        String[] enabledActionsList = {"foto-atualizada", "foto", "foto-por-intervalo"};
        this.enabledActions = enabledActionsList;
        this.action = "No action";
        this.args = args;
        this.checkAction();
        
    }
    
    /**
     * Check if the given action is an enabled action.
     * @param action
     * @return 
     */
    private void checkAction()
    {
        
        for (String enabledAction : this.enabledActions) {
            if (enabledAction.equals(this.args[0])) {
                this.action = enabledAction;
            }
        }
    }
    
    /**
     * Get the validated action.
     * @return 
     */
    public String getAction()
    {   
        return this.action;
    }
}
