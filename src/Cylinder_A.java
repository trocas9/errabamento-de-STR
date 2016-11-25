/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Miguel-Loureiro
 */
public class Cylinder_A extends Thread{
    String threadName;
    Mechanism mechanism;
    boolean keepWorking = true;
    BlockingQueue mailbox = new ArrayBlockingQueue(5);
  
    public void stopIt() {
        keepWorking = false;
    }
    public BlockingQueue getMailbox() {
        return mailbox;
    }
    public Cylinder_A(String _name, Mechanism ___mech){
        threadName = _name;
        mechanism  = ___mech;
    }
    public void run(){
        while(keepWorking){
            try {
                Integer d = (Integer) mailbox.take();
                mechanism.cylinder_A_goto(d);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder_A.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}