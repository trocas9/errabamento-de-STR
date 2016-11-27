
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
public class Mechanism {
    
Hardware hard;
public final int  PKG_NUMBER = 3;
public final int  CYLINDER_NUMBER = 2;

public Mechanism(Hardware h){
	hard = h;
}
public void conveyorMove(){
     int x = hard.safe_read_port(2);
     x = hard.setBitValue(x, 2 , true);
     hard.safe_write_port(2,x);
}

public void conveyorStop(){
    int x = hard.safe_read_port(2);
    x = hard.setBitValue(x, 2 , false);
    hard.safe_write_port(2,x);    
};

public int cylind_A_getPosition() {
    int x = hard.safe_read_port(0);
    if(hard.getBitValue(x, 6))
	  return 0;
    if(hard.getBitValue(x, 5))
	  return 1;
    else
        return -1;
}

public int cylind_B_getPosition() {
    int x = hard.safe_read_port(0);
    if(!hard.getBitValue(x, 4))
	  return 0;
    if(!hard.getBitValue(x, 3))
	  return 1;
    else
        return -1;
}

public int cylind_C_getPosition() {
    
    int x = hard.safe_read_port(0);
    if(!hard.getBitValue(x, 2))
	  return 0;
    if(!hard.getBitValue(x, 1))
	  return 1;
    else
        return -1;
}

public boolean cylind_A_isAtPosition (int pos) { 
     return (pos==cylind_A_getPosition());  
}

public boolean cylind_B_isAtPosition (int pos) { 
     return (pos==cylind_B_getPosition());  
}

public boolean ConveyorIsMoving(){
    int x = hard.safe_read_port(2);
    return hard.getBitValue(x, 2);
}

public boolean cylind_C_isAtPosition (int pos) { 
     return (pos==cylind_C_getPosition());  
}

public void cylinder_A_movLeft() {

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 0, true);
        x = hard.setBitValue(x, 1, false);
        hard.safe_write_port(2, x);
    
};
public void cylinder_A_movRight() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 1, true);
        x = hard.setBitValue(x, 0, false);
        hard.safe_write_port(2, x);
    
};
public void cylinder_A_stop() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 1, false);
        x = hard.setBitValue(x, 0, false);
        hard.safe_write_port(2, x);    
    
};

public void cylinder_B_movUp(){
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 4, false);
        x = hard.setBitValue(x, 3, true);
        hard.safe_write_port(2, x);

};
public void cylinder_B_movDown() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 3, false);
        x = hard.setBitValue(x, 4, true);
        hard.safe_write_port(2, x);
    
};
public void cylinder_B_stop(){

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 3, false);
        x = hard.setBitValue(x, 4, false);
        hard.safe_write_port(2, x);
    
};

public void cylinder_C_movUp(){
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 6, false);
        x = hard.setBitValue(x, 5, true);
        hard.safe_write_port(2, x);

};

public void cylinder_C_movDown() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 5, false);
        x = hard.setBitValue(x, 6, true);
        hard.safe_write_port(2, x);
    
};
public void cylinder_C_stop(){

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 5, false);
        x = hard.setBitValue(x, 6, false);
        hard.safe_write_port(2, x);
    
};

public void cylinder_A_goto(int desired_pos)  //0, 1
  {
    //move only if it is callibrated
    int actual = this.cylind_A_getPosition();
    if(this.cylind_A_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_A_movRight();
      else if(actual > desired_pos)
           this.cylinder_A_movLeft();
      while(this.cylind_A_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_A_stop(); 
    }    
  }
 


public void cylinder_B_goto(int desired_pos){

    //move only if it is callibrated
    int actual = this.cylind_B_getPosition();
    if(this.cylind_B_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_B_movDown();
      else if(actual > desired_pos)
           this.cylinder_B_movUp();
      while(this.cylind_B_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_B_stop();      
    }    
    
};

public void cylinder_C_goto(int desired_pos){

    //move only if it is callibrated
    int actual = this.cylind_C_getPosition();
    if(this.cylind_C_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_C_movDown();
      else if(actual > desired_pos)
           this.cylinder_C_movUp();
      while(this.cylind_C_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_C_stop();      
    }    
    
};

    public boolean verBotao_lixo() {//TODO
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 2);
        x = hard.setBitValue(x, 2 , false);
        hard.safe_write_port(1, x);
        return x1;
    }

    public boolean verBotao_1() {//TODO
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 4);
        x = hard.setBitValue(x, 4, false);
        hard.safe_write_port(1, x);
        return x1;
    }

    public boolean verBotao_2() {//TODO
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 3);
        x = hard.setBitValue(x, 3 , false);
        hard.safe_write_port(1, x);
        return x1;
    }
    
    public int readSendor1 (){
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 6);
        if(x1)
            return 1;
        else
            return 0;
     
}

    public int readSendor2(){
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 5);
        if(x1)
            return 1;
        else
            return 0;

    }

    public int read_Cylinder_B_sensor(){
        int x = hard.safe_read_port(0);
        boolean x1 = hard.getBitValue(x, 0);

        if(x1)
            return 1;
        else
            return 0;

    }
    
    public int read_Cylinder_C_sensor(){
        int x = hard.safe_read_port(1);
        boolean x1 = hard.getBitValue(x, 7);

        if(x1)
            return 1;
        else
            return 0;

    }
    
    public void turnOnLight(){
        int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 7, true);
        hard.safe_write_port(2, x);       
    }
    
    public void turnOFFLight(){
        int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 7, false);
        hard.safe_write_port(2, x);         
    }
    
    
    

}
