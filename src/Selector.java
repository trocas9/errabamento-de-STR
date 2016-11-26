
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
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
public class Selector extends Thread{
    //instance variables
    BlockingQueue mailbox ;
    Mechanism m;
    Conveyor_Cylinder cylinders [];
    Cylinder A;
    boolean keepWorking = true;
    
    //constructor
    public Selector(Mechanism m){
        this.m=m;
        IdentifyPart p = new IdentifyPart("indentify", m);
        mailbox=p.getMailbox();
        A=new Cylinder_A("Put Cilinder", m);
        Cylinder a [] = new Cylinder[1];
        a[0] = A;
        cylinders= new Conveyor_Cylinder[2];
        cylinders[0]=new Cylinder_B("Cylinder B",m,a);
        cylinders[1]=new Cylinder_C("Cylinder C",m,a);
    }
    
    public void stopIt() {
        keepWorking = false;
    }
    //run
    @Override
    public void run(){
        int i=0;
        this.cylinders[0].start();
        this.cylinders[1].start();
        
        while (keepWorking){
            try {
                i =((Integer) mailbox.take()).intValue();
                int o= checker(i);
                if(o!=-1){
                    cylinders[o-1].mailbox.add((Integer)1);
                    cylinders[o-1].update_part(i);
                    for(int j=0;j<o-1;j++){
                        cylinders[j].mailbox.add((Integer)0);
                    }
                }
                else for(int j=0;j<m.CYLINDER_NUMBER;j++){
                    cylinders[j].mailbox.add((Integer)0);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Selector.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotAllowedPartException ex) {
                Logger.getLogger(Selector.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        this.cylinders[0].stopIt();
        this.cylinders[1].stopIt();
        //this.cylinders[2].stopIt();
    }
    
    public  int checker(int i){
        if(m.verBotao_lixo() ){//TODO mudar estado do botao
            return -1;
        }
        if(m.verBotao_1()  && cylinders[0].can_recieve_part(i)){//TODO mudar estado do botao
            return 1;
        }
        if(m.verBotao_2() && cylinders[1].can_recieve_part(i)){//TODO mudar estado do botao
            return 2;
        }
        int k=0,can[]= new int [m.CYLINDER_NUMBER];

        for(int j=0;j<m.CYLINDER_NUMBER;j++){
           if(cylinders[j].can_recieve_part(i)){
                can[k]=j;
                k++;
           }
        }
        if(k==0)
            return -1;
        int o= ThreadLocalRandom.current().nextInt(0, k);
        return can[o];
    }
}
