
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
 * @author Cavaco
 */
public abstract class Cylinder extends Thread{
    String threadName;
    Mechanism mechanism;
    boolean keepWorking;
    BlockingQueue mailbox;
    int direction;
  
    public void stopIt() {
        keepWorking = false;
    }
    public abstract void  resumeCyl();
    public BlockingQueue getMailbox() {
        return mailbox;
    }
    public Cylinder(String _name, Mechanism ___mech){
        threadName = _name;
        mechanism  = ___mech;
        mailbox = new ArrayBlockingQueue(5);
        keepWorking  = true;
        direction=0;
    }

}
