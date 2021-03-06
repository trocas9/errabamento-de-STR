
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

public class Cylinder_B extends Conveyor_Cylinder{

    public Cylinder_B(String _name, Mechanism ___mech, Cylinder[] slaves){
        super(_name, ___mech,slaves);
        state[0]= true;
        for(int i=1;i<mechanism.PKG_NUMBER;i++){
            state[i]=false;
        }
    }
    @Override
    public void run(){
        while(keepWorking){
            try {
                Integer d = (Integer) mailbox.take();
                while(mechanism.read_Cylinder_B_sensor()==0);
                if(d == 1){
                    for(int i=0;i<slave.length;i++){
                        slave[i].stopIt();
                    }
                    mechanism.cylinder_A_stop();
                    mechanism.conveyorStop();
                    mechanism.cylinder_B_goto(1);  
                    mechanism.cylinder_B_goto(0);
                    mechanism.conveyorMove();
                    for(int i=0;i<slave.length;i++){
                        slave[i].resumeCyl();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder_A.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    public boolean can_recieve_part(int i){
        if(state[i-1]){
            return true;
        }
        return false;
    }

    @Override
    public void get_piece() {

    }

    public void update_part(int i){
        if(i==1){
            if(state[2]){
                this.increment_sequence();
                state[2]=false;
            }
            else{
                state[1]= false;
                state[2]=true;
            }
        }
        if(i==2)
            state[1]= true;
    }

    @Override
    public void resumeCyl() {
        //todo
    }
}
  


