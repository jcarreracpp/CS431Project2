/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class HardDisk {
    private int[][] harddrive = new int[256][256];
    private HardDisk singleton;
    
    public HardDisk getInstance(){
        if(singleton == null){
            singleton = new HardDisk();
            fillDrive();
        }
        return singleton;
    }
    
    private void fillDrive(){
        
    }
}
