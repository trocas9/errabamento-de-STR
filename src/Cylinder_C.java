
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
public class Cylinder_C extends Conveyor_Cylinder{
 

  
    public Cylinder_C(String _name, Mechanism ___mech,Cylinder[] slaves){
        super(_name,___mech,slaves);
        state[0]= true;
        for(int i=1;i<mechanism.PKG_NUMBER;i++){
            state[i]=false;
        }
    }
    public void run(){
        while(keepWorking){
            try {
                Integer d = (Integer) mailbox.take();
                while(mechanism.read_Cylinder_C_sensor()==0);
                if(d.intValue()== 1){
                    for(int i=0;i<slave.length;i++){
                        slave[i].stopIt();
                    }
                    mechanism.conveyorStop();
                    mechanism.cylinder_C_goto(1);  
                    mechanism.cylinder_C_goto(0);
                    mechanism.conveyorMove();
                    for(int i=0;i<slave.length;i++){
                        slave[i].resumeCyl();
                    }
                }
                else
                    mechanism.turnOnLight();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder_A.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.sleep(110);
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
        //TODO
    }

    public void update_part(int i){
        if(i==1){
            if(state[3]){
                this.increment_sequence();
                state[3]=false;
            }
            else{
                state[1]=false;
                state[3]=true;
            }
        }
        if(i==3)
            state[1]= true;
    }

    @Override
    public void resumeCyl() {
        //todo
    }
}
