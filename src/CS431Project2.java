/**
 *
 * @author Jacob
 */
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.Console;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class CS431Project2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // variable declarations
        int userInt = 0;
        String [] fileNames = {"inFiles\\test_1.txt", "inFiles\\test_2.txt", "inFiles\\test_3.txt", "inFiles\\test_4.txt"};
        String inFile;
        FileReader fileReader;
        
        // object declarations
        HardDisk hData = new HardDisk();
        VirtualPageTable [] pageData = new VirtualPageTable[256];
        TLB [] tlbData = new TLB[256];
        OperatingSystem os = OperatingSystem.getInstance();
        os.initOS();
        
        //csv file creation
        PrintWriter pw = new PrintWriter(new File("result.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Address" + ",");
        sb.append("R/W,");
        sb.append("Value,");
        sb.append("Soft,");
        sb.append("Hard,");
        sb.append("Hit,");
        sb.append("Evicted_pg#,");
        sb.append("Dirty_Evicted_Page\n");
        
        //beginning of the actual program
        userInt = display(); // asking user to choose the input file
        fileReader = new FileReader(fileNames[userInt - 1]);
        BufferedReader bReader = new BufferedReader(fileReader);
        while(bReader.readLine() != null){
            // read data
        }
        
        
        
        
        bReader.close();
        pw.write(sb.toString());
        pw.close();
        
    }
    
    public static int display(){
        int userInt = 0;
        Scanner kbd = new Scanner(System.in);
        while(userInt > 0 && userInt < 5){
            System.out.println("Please choose the input test file number: ");
            System.out.println("1. test_1.txt");
            System.out.println("2. test_2.txt");
            System.out.println("3. test_3.txt");
            System.out.println("4. test_4.txt");
            userInt = kbd.nextInt();
            if(userInt < 1 || userInt > 4){
                System.out.println("Please Enter a Value between 1 and 4");
            }
        }
        
        return userInt;
    }
    
    
}
