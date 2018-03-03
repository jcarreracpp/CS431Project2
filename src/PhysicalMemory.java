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
    private int[][] ram = new int[16][256];
    private static int entryCount;
    //THIS WILL BE A TWO DIMENSIONAL ARRAY to simulate page-frame #.
    //ADDRESS WIDTH IS 12 BITS.
    //Dimension.
    public PhysicalMemory(){
        entryCount = 0;
    }
    
    public void storeData(){
        
    }
    
    public int getEntryCount(){
        return entryCount;
    }
}
