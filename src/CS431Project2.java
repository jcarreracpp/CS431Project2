/**
 *
 * @author Jacob
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


public class CS431Project2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CPU cpu = CPU.getInstance();
        cpu.initCPU();
        //cpu.doThing();
        cpu.readInstructions();
//        // variable declarations
//        int userInt = 0;
//        String [] fileNames = {"test_files\\test_1.txt", "test_files\1\test_2.txt", "test_files\\test_3.txt", "test_files\\test_4.txt"};
//        String inFile;
//        String line = null;
//        FileReader fileReader;
//        
//        // object declarations
//        HardDisk hData = HardDisk.getInstance();
//        //VirtualPageTable [] pageData = new VirtualPageTable[256];
//        //TLB [] tlbData = new TLB[8];
//        //OperatingSystem os = OperatingSystem.getInstance();
//        //os.initOS();
//        
//        //csv file creation
//        PrintWriter pw = new PrintWriter(new File("result.csv"));
//        StringBuilder sb = new StringBuilder();
//        sb.append("Address" + ",");
//        sb.append("R/W,");
//        sb.append("Value,");
//        sb.append("Soft,");
//        sb.append("Hard,");
//        sb.append("Hit,");
//        sb.append("Evicted_pg#,");
//        sb.append("Dirty_Evicted_Page\n");
//        
//       
//        
//        
//        
//        bReader.close();
//        pw.write(sb.toString());
//        pw.close();
//        
    }
    
    public static int display(){
        int userInt = 0;
        Scanner kbd = new Scanner(System.in);
        do{
            System.out.println("Please choose the input test file number: ");
            System.out.println("1. test_1.txt");
            System.out.println("2. test_2.txt");
            System.out.println("3. test_3.txt");
            System.out.println("4. test_4.txt");
            userInt = kbd.nextInt();
            if(userInt < 1 || userInt > 4){
                System.out.println("Please Enter a Value between 1 and 4");
            }
        } while(userInt < 1 && userInt > 4);
        
        return userInt;
    }
    
    
}