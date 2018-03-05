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
        cpu.readInstructions("test_3.txt");
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
//        //beginning of the actual program
//        userInt = display(); // asking user to choose the input file
//        fileReader = new FileReader(fileNames[userInt - 1]);
//        BufferedReader bReader = new BufferedReader(fileReader);
//        String address = null;
//        String offset = null;
//        String writeVal = null;
//        int pgData = 0;
//        
//        //reading text file
//        while((line = bReader.readLine()) != null){
//            System.out.println(line);
//            if(Integer.parseInt(line) == 0){ //Read
//                System.out.println("Read");
//                line = bReader.readLine();
//                address = line.substring(0, 2);
//                offset = line.substring(2);
//                
//                //Psudo-Code
//                /*
//                if not found in TLB{
//                    if not found in VPT{
//                        HARD MISS
//                        pgData = hData.getValue(Integer.parseInt(address, 16), Integer.parseInt(offset, 16));
//                       
//                        if main memory is full{
//                            FIFO?
//                        }
//                        save data into the Main Memory
//                        
//                        if Virtual Page Table is full{
//                            Clock
//                        }
//                        save data into the Virtual Page Table
//                
//                        if TLB is full{
//                            FIFO
//                        }
//                        Save data into the TLB
//                
//                        break;
//                    }
//                    SOFT MISS
//                    get the virtual Page table value
//                    save it to the TLB
//                }
//                Record Values into CSV File
//                */
//                
//              
//                System.out.println(address + "   " + offset); //wrote to check the address and offset are right
//            }
//            else{
//                System.out.println("Write");
//                line = bReader.readLine();
//                address = line.substring(0, 2);
//                offset = line.substring(2);
//                pgData = Integer.parseInt(bReader.readLine());
//                
//                //check TLB
//                
//                //check VirtualPageTable
//                //if not found, hard miss
//
//                //store value into the main memory
//                //store them into Virtual Page Table
//                //store into TLB
//                
//                //record values into the csv file.
//                
//                
//                System.out.println(address + "   " + offset); //wrote to check the address and offset are right
//                
//            }
//        }
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