
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    
    public void readInstructions() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("test_files\\test_1.txt");
        BufferedReader br = new BufferedReader(fr);
        String address = null;
        String offset = null;
        String writeVal = null;
        String line = null;
        int[] holdResult;
        int pgData = 0;
        while((line = br.readLine()) != null){
            mmu.setMODE(Integer.parseInt(line));
            mmu.printMode();
            line = br.readLine();
            if(mmu.getMode() == 0){
                holdResult = parseToMMU(line);
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                System.out.println();
            }else{
                holdResult = parseToMMU(line);
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                line = br.readLine();
                System.out.println("Value: "+ line +"\n");
            }            
        }
    }
    
    public int[] parseToMMU(String i){
        int[] result = new int[2];
        String j = i.substring(2);
        i = i.substring(0, 1);
        result[0] = Integer.decode("0x"+i);
        result[1] = Integer.decode("0x"+j);
        return result;
    }
}
