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
    
    public void setSingleValu(int address, int offset, int inputVal){
        ram[address][offset] = inputVal;
    }
    
     public boolean ramEntryExists(int address, int offset, HardDisk harddrive){
        boolean result = false;
        int checkCounter = 0;
        for(int i = 0; i < 16; i++){
            //checks if there's the input address is exists on the main memory 
            // checks the 5 elements of the ram array to check the ram[i][j] == harddrive[address][j]
            for(int j = 0; j < 10; j++){
                if(ram[i][j] == harddrive.readValue(address, j)){
                    checkCounter ++;
                }
            }
            if(checkCounter == 10){
                return true;
            }
            checkCounter = 0;
        }
        return result;
    }
    
}
