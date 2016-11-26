
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.BlockingQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel-Loureiro
 */
public class Trabalho2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            //System.out.println("Hello world...");
            Hardware h = new Hardware();
            h.create_di(0);
            h.create_di(1);
            h.create_di(2);

            Mechanism mech = new Mechanism(h);

            Cylinder_A cylA = new Cylinder_A("thread A",mech );
            IdentifyPart part = new IdentifyPart("thread Part", mech);
            cylA.start();
            part.run();

            BlockingQueue mbxCylA = cylA.getMailbox();


            mech.conveyorMove();

            mbxCylA.put(1);  // goto(1);
            mbxCylA.put(0);  // goto(0);

            
            System.in.read();
                        

            mech.conveyorStop();

            System.out.println("Mesmo Depois");
            //int x = mech.partIdentify();
            //System.out.printf("part identified is %d", x);

            //press enter to stop them
            part.stopIt();
            cylA.stopIt();
            System.out.println("Mesmo Mesmo Depois");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Trabalho2.class.getName()).log(Level.SEVERE, null, ex);
        }
    //mech.cylind_A.goto(1);
      System.out.println("Fare Well");
    }    
}