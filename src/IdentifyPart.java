
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
    
    String threadName;
    Mechanism mechanism;
    boolean keepWorking = true;
    BlockingQueue mailbox = new ArrayBlockingQueue(5);
    
    public void stopIt(){
        keepWorking = false;
    }
    
    public BlockingQueue getMailbox() {
        return mailbox;
    }
  
    public IdentifyPart(String _name, Mechanism ___mech){
      threadName = _name;
      mechanism  = ___mech;

    }

    @Override
    public void run(){
        int x1_prev = 0;
        int x1_act;
        int x2_prev = 0;
        int x2_act;
        int xB_prev = 0;
        int xB_act;
        int soma_act = 1;

        while(keepWorking){ 
            
            
            x1_act = mechanism.readSendor1();
            if (x1_prev == 0 && x1_act == 1){
                soma_act++;
                //System.out.printf("Soma1: "+soma_act+"\n");
            }
            x2_act = mechanism.readSendor2();
            if (x2_prev == 0 && x2_act == 1){
                soma_act++;
                //System.out.printf("Soma2: "+soma_act+"\n");
            }
            xB_act = mechanism.read_Cylinder_B_sensor();
            if(xB_act == 1 && xB_prev == 0){
                //System.out.printf("Soma: "+soma_act+"\n");
                try{this.mailbox.put(soma_act);}
                catch (InterruptedException ex){
                Logger.getLogger(Trabalho2.class.getName()).log(Level.SEVERE, null, ex);}
                soma_act=1;                         
            }
                                          
            x1_prev = x1_act;
            x2_prev = x2_act;
            xB_prev = xB_act;
            
            
            
        }

    }
}  
