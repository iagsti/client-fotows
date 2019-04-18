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
public class Parameters
{
    protected String username;
    protected String password;
    protected String filePath;
    protected String dateUpdate;
    protected int[] codeInterval;
    protected int code;
    
   /**
     * Class contructor.
     * Set the entring parameters.
     */
    Parameters(String[] args)
    {
        this.codeInterval = new int[2];
        
        for (String arg : args) {
            String[] argsSplit = arg.split("=");
            switch(argsSplit[0]){           
                case "--username":
                    this.username = argsSplit[1];
                    break;
                case "--password": 
                    this.password = argsSplit[1];
                    break;
                case "--diretorio-fotos": 
                    this.filePath = argsSplit[1];
                    break;
                case "--data-atualizacao": 
                    this.dateUpdate = argsSplit[1];
                    break;
                case "--codigo-inicial":
                    this.codeInterval[0] = Integer.parseInt(argsSplit[1]);
                    break;
                case "--codigo-final":
                    this.codeInterval[1] = Integer.parseInt(argsSplit[1]);
                    break;
                case "--codigo":
                    this.code = Integer.parseInt(argsSplit[1]);
                    break;           
            }
        }
       
    }
    
    /**
     * Return the username.
     * @return 
     */
    public String getUsername(){
            return this.username;
    }
    
    /**
     * Return the password.
     * @return 
     */
    public String getPassword(){
            return this.password;
    }

    /**
     * Return the file path.
     * @return 
     */
    public String getFilePath(){
            return this.filePath;
    }

    /**
     * Return the update date.
     * @return 
     */
    public String getDateUpdate(){
            return this.dateUpdate;
    }
    
    /**
     * Return the code interval
     * @return 
     */
    public int[] getCodeInterval(){
        return this.codeInterval;
    }
    
    /**
     * Return the code.
     * @return 
     */
    public int getCode(){
        return this.code;
    }
}
