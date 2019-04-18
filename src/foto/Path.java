/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foto;

import java.util.Properties;

/**
 *
 * @author schneider
 */
public class Path {
 
    private final String path;
    private String code;
    private final String slash;
    
     /**
     * Class constructor.
     * @param path String
     */
    Path(String path, String code)
    {
        this.path = path;
        this.code = code;
        
        if("Win".equals(this.getCurrentOs()))
        {
            this.slash = "\\";
        }else{
            this.slash = "/";
        }   
    }
    
    /**
     * Get the current operating system name.
     * @return String
     */
    private String getCurrentOs()
    {
        
        Properties props = System.getProperties();
        String os = props.getProperty("os.name");
        return  os.subSequence(0, 3).toString();
        
    }
    
    /**
     * Format the Path.
     */
    private String formatPath()
    {
        String filePath = this.path;
        char lastChar = filePath.charAt(filePath.length() -1);
        if(Character.toString(lastChar).equals(this.slash)){
            filePath = filePath.concat("F1-"+this.code+".jpg");
        } else {
            filePath = filePath.concat(this.slash+"F1-"+this.code+".jpg");
        }
      
        return filePath;
    }
    
    /**
     * Return the path.
     * @return String
     */
    public String getPaht()
    {
        
        return this.formatPath();
        
    }
    
    /**
     * Set the Code parameter.
     * @param code 
     */
    public void setCode(String code)
    {
        
        this.code = code;
        
    }
    
}
