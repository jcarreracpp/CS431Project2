/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class PhysicalMemory {
    private static int[][] ram = new int[16][256];
    private static PhysicalMemory singleton;
    //THIS WILL BE A TWO DIMENSIONAL ARRAY to simulate page-frame #.
    //ADDRESS WIDTH IS 12 BITS.
    //Dimension.
    private PhysicalMemory(){}
    
    public static PhysicalMemory getInstance(){
        if(singleton == null){
            singleton = new PhysicalMemory();
        }
        return singleton;
    }
    
    public int getValue(int page, int index){
        return ram[page][index];
    }
    
     public boolean ramEntryExists(int address, int offset){
        boolean result = false;
        for(int i = 0; i < 16; i++){
            //checks if there's the same address exists.
        }
        return result;
    }
    
}
