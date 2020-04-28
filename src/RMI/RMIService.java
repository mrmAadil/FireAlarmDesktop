/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author moham
 */
public interface RMIService extends Remote {
    
    public String getSensorReadings() throws RemoteException;
    public boolean addSensor(String sensorID, int floor, int room)throws RemoteException;
}
