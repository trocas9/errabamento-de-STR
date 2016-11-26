
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


    public void run(){
      int x1_prev = 0;
      int x1_act = 0;
      int x2_prev = 0;
      int x2_act = 0;
      int xB_prev = 0;
      int xB_act = 0;
      int soma = 0;

      while(keepWorking){

          x1_prev = mechanism.readSendor1();
          x2_prev = mechanism.readSendor2();
          xB_prev = mechanism.read_Cylinder_B_sensor();

          if (x1_prev == 0 && x1_act == 1)
              soma++;
          if (x2_prev == 0 && x2_act == 1)
              soma++;
          if (xB_prev == 0 && xB_act == 1){
              soma++;
              System.out.printf("Soma: "+soma+"\n");
              /*try{this.mailbox.put(soma);}
              catch (InterruptedException ex){
              Logger.getLogger(Trabalho2.class.getName()).log(Level.SEVERE, null, ex);*/                   
           }


          x1_act = mechanism.readSendor1();
          x2_act = mechanism.readSendor2();
          xB_act = mechanism.read_Cylinder_B_sensor();
      }

    }
  }  
