
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel-Loureiro
 */
public class IdentifyPart extends Thread{
    
    
    
    public void stopIt(){
    keepWorking = false;
  }
  
  public Cylinder_B(String _name, Mechanism ___mech){
    threadName = _name;
    mechanism  = ___mech;

  }
  public void run()
  {
    while(keepWorking){
        System.out.println("My first thread: "+ threadName);
        Thread.yield();
    }

}
    
}
