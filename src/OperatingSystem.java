
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
public class OperatingSystem {
    private static OperatingSystem singleton;
    private static HardDisk hd;
    private static PhysicalMemory pm;
    private static TLB tlb;
    private static VirtualPageTable vpt;
    private int clockCounter = 0;
    //Uses the clock algorithm for page replacement.
    //This will reset the R-bit every 5 instructions.
    private OperatingSystem(){}
    
    public static OperatingSystem getInstance(){
        if(singleton == null){
            singleton = new OperatingSystem();
            System.out.println("OS online.\n\tOS clock cycle rate: 20");
        }
        return singleton;
    }
    
    public void initOS() throws IOException{
        hd = HardDisk.getInstance();
        pm = PhysicalMemory.getInstance();
        tlb = TLB.getInstance();
        vpt = VirtualPageTable.getInstance();
    }

    public void clockTick() {
        clockCounter++;
        if(clockCounter >=5){
            clockCounter = 0;
            sweep();
        }
    }

    private void sweep() {
        tlbSweep();
        vptSweep();
    }

    private void tlbSweep() {
        tlb.nukeDerefEntries();
        tlb.derefEntries();
    }

    private void vptSweep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
