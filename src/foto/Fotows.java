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
public class Fotows {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        String username = parameters.getUsername();
        String password = parameters.getPassword();
        
        Actions actions = new Actions(args);
        String action = actions.getAction();
        
        Service service = new Service(username, password);
        service.connect();
        
        switch(action) {
            case "foto-atualizada":
                service.getUpdatedPhoto(parameters.getDateUpdate(), parameters.getFilePath());
                break;
            case "foto":
                service.getPhoto(parameters.getCode(), parameters.getFilePath());
                break;
            case "foto-por-intervalo":
                int intervalIni = parameters.getCodeInterval()[0];
                int intervalEnd = parameters.getCodeInterval()[1];
                service.getPhotoByInterval(intervalIni, intervalEnd, parameters.getFilePath());
                break;
        }
    }
    
}
