
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel-Loureiro
 */
public class ThreadFeeder extends Thread {

    private boolean finished = false;

// final here ensures the mailbox cannot be initialized again
  static final BlockingQueue mailbox = new ArrayBlockingQueue(100);
  private static final Semaphore newPartSemaphore = new Semaphore(0);
  Mechanism mechanism;

  public ThreadFeeder(Mechanism ___m) {
    mechanism = ___m;
  }

  static public BlockingQueue getMailbox() {
    return mailbox;
  }

  static public Semaphore getSemaphore() {
    return newPartSemaphore;
  }

  public void finishWork() {
    finished = true;
  }
  
  public void run() {
    while (!finished) {
      //wait for notification of new part in feeder
      //this might be a semaphore triggered from a GUI button
      // then, advance cylinder_A (with its messageQueue)
      // this can be done the following way:
      // BloquingQueue mbx = Cylinder_A.getMailbox();
      // mbx.put(1); mbx.put(0);
      // And then start identifying.      
      // After identification
      // send message to the other threads ThreadStation_1 and ThreadStation_2

    }
  }    
    
}
