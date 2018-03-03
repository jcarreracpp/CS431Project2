
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class MMU {
    private static HardDisk hd;
    private static MMU singleton;
    private static TLB tlb;
    private static VirtualPageTable vpt;
    //THIS WILL USE FIFO for the replacement algorithm.
    private MMU(){}
    
    public static MMU getInstance(){
        if(singleton == null){
            singleton = new MMU();
            System.out.println("MMU online.");
        }
        return singleton;
    }
    
    public static void initMMU() throws IOException{
        hd = HardDisk.getInstance();
        tlb = TLB.getInstance();
        vpt = VirtualPageTable.getInstance();
    }
    
    public static int readHDD(String a, String v){
        return hd.readValue(a, v);
    }
    
}
