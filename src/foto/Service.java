/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foto;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import usp.ws.foto.FotoBean;
import usp.ws.foto.FotoSIBService;
import usp.ws.foto.FotoWS;
import usp.ws.foto.WSException;
import java.io.IOException;
        
/**
 *
 * @author schneider
 */
public class Service {
    
    final private FotoWS ws;
    final private String username;
    final private String password;
    
    /**
     * Class constructor.
     * @param username String
     * @param password String
     */
    Service(String username, String password)
    {
        
        FotoSIBService service = new FotoSIBService();
        this.ws = service.getFotoSIBPort();
        
        this.username = username;
        this.password = password;
        
    }
    
    /**
     * Set the Request Headers, adding Username and Password Headers.
     */
    private void setHeaders()
    {
       
       List<Header> list = new ArrayList<>();
       list.add(Headers.create(new QName("username"), this.username));
       list.add(Headers.create(new QName("password"), this.password));
       WSBindingProvider bp = (WSBindingProvider)this.ws;
       
       bp.setOutboundHeaders(list);
       
    }
    
    private String mountPath(String path, String code)
    {
        
        Path filePath = new Path(path, code);
        return filePath.getPaht();
        
    }
    
    /**
     * Save a given image on the file system.
     * @param img Image
     * @param path String
     */
    private void saveImage(Image img, String path)
    {
        
        BufferedImage bi = (BufferedImage)img;
        File f = new File(path);
        try {
            ImageIO.write(bi, "jpeg", f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Save a list of images on the file system.
     * @param fotoList List<FotoBean>
     * @param path String
     */
    private void listImages(List<FotoBean> fotoList, String path)
    {
        
        try {
            if(fotoList.size() > 0){
                Path filePath = new Path(path, "0");
                for(FotoBean foto:fotoList){
                    byte[] handler = foto.getFotoPessoa();
                    filePath.setCode(Integer.toString(foto.getCodigoPessoa()));
                    OutputStream out = new FileOutputStream(filePath.getPaht());
                    out.write(handler);
                    out.close();
                    System.out.println(foto.getCodigoPessoa());
                }
                System.out.println("Número de fotos obtifdas: "+fotoList.size());
            } else {
                System.out.println("Nenhuma foto foi obtida");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
    }
    
    
    /**
     * Connect to the webservice.
     */
    public void connect()
    {
        this.setHeaders();
    }
    
    /**
     * Save a image on the file system. 
     * @param code int
     * @param path String
     */
    public void getPhoto(int code, String path)
    {
        try {
            Image photo = this.ws.obterFotoCartao(code);
            String formatedPath = this.mountPath(path, Integer.toString(code));
            this.saveImage(photo, formatedPath);
        } catch(WSException e){
            System.out.println(e.getFaultInfo());
        }
        
    }
    
    /**
     * Get a list of Photos between an interval.
     * @param codeIni int
     * @param codeEnd int
     * @param path String
     */
    public void getPhotoByInterval(int codeIni, int codeEnd, String path){
        
        try {
            
            List<FotoBean> fotoList = this.ws.listarFotoCartao(codeIni, codeEnd);
            this.listImages(fotoList, path);
  
        } catch (WSException e) {
            
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * Get a list of Updated Photos since a given date.
     * @param date String
     * @param path String
     */
    public void getUpdatedPhoto(String date, String path)
    {
        
        try {
            List<FotoBean> fotoList = this.ws.listarFotoCartaoAtualizado(date);    
            this.listImages(fotoList, path);
        } catch(WSException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
