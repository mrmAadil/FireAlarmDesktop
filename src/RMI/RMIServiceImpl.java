/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.management.remote.rmi.RMIServer;
import javax.swing.Timer;


public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {
    
    private static HttpURLConnection connection;
    
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.bind("FireAlarmService", new RMIServiceImpl());
        
            System.out.println("!!!!   Start the Server   !!!!");
            
            Timer timer = new Timer(0,null);
            
            timer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
            
            timer.setRepeats(true);
            timer.setDelay(15000);
            timer.start();
    }
        
    //Constructor
    RMIServiceImpl() throws RemoteException{
        super();
    }
        
    /*
        Read all sensor Details
    */
    @Override
    public String getSensorReadings() throws RemoteException {
        
      BufferedReader reader;
      String line = "";
      StringBuffer response = new StringBuffer();
      
      try{         
          
          URL url = new URL("http://localhost:8080/firealarm/sensors");
          connection = (HttpURLConnection) url.openConnection();
          
          //Request Setup
	   connection.setRequestMethod("GET");
	   connection.setConnectTimeout(5000);
	   connection.setReadTimeout(5000);
           
           int status = connection.getResponseCode();
	   System.out.println(status);
           
           //check the status
	            if(status > 200){
	                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                reader.close();
	            }else {
	                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                reader.close();
	            }
                    
                   
      }catch(Exception ex){
          ex.printStackTrace();
      }finally{
        connection.disconnect();
    }
        return line;
}
    

    /*
        Adding sensors into the system
    */
    @Override
    public boolean addSensor(String sensorID, int floor, int room) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
