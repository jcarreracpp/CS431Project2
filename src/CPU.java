
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
public class CPU {
    private static CPU singleton;
    private static MMU mmu;
    private static OperatingSystem os;
    //Will call MMU and TLB frequently. Address width is 12 bits.
    
    private CPU(){}
    
    public static CPU getInstance(){
        if(singleton == null){
            singleton = new CPU();
            System.out.println("CPU online.");
        }
        return singleton;
    }
    
    public static void initCPU() throws IOException{
        mmu = MMU.getInstance();
        mmu.initMMU();
        os = OperatingSystem.getInstance();
        os.initOS();
    }
    
    public void doThing(){
        System.out.println("Accessing data at page FF, value FF: "+mmu.readHDD("FF","FF"));
    }
}
