/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cavaco
 */
public abstract class Conveyor_Cylinder extends Cylinder{
    boolean state [];
    private int sequence_number;
    Cylinder[] slave;
    public Conveyor_Cylinder(String _name, Mechanism ___mech,Cylinder [] slaves) {
        super(_name, ___mech);
        sequence_number=0;
        state= new boolean [___mech.PKG_NUMBER];
        slave=slaves;
    }
    public int get_sequence_number(){
        return sequence_number;
    }
    public void increment_sequence(){
        sequence_number++;
    }
    public abstract boolean can_recieve_part(int i);
    public abstract void get_piece();
    public abstract void update_part(int i)throws NotAllowedPartException;
    
}
