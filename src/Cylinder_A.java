/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Miguel-Loureiro
 */
public class Cylinder_A extends Cylinder{

    private int direction;

    public Cylinder_A(String _name, Mechanism ___mech) {
        super(_name, ___mech);
        direction=0;
    }


    public void run(){
        while(keepWorking){
            try {
                Integer d = (Integer) mailbox.take();
                direction = d;
                mechanism.cylinder_A_goto(d);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder_A.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopIt() {
        mechanism.cylinder_A_stop();
    }
    public void resumeCyl(){
        switch (direction){
        case 0: mechanism.cylinder_A_movLeft();
        break;
        case 1: mechanism.cylinder_A_movRight();
        break;
    }
    }
}