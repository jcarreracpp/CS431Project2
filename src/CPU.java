
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
    private PrintWriter pw;
    private StringBuilder sb;
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
    
    public void readInstructions(String input) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("test_files\\"+input);
        pw = new PrintWriter(new File("resultOf-"+input.substring(0, 6)+".csv")); 
        sb = new StringBuilder();
        sb = initialCSV(sb);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int acquisition = 0;
        int[] holdResult;
        int[] result;
        int pgData = 0;
        while((line = br.readLine()) != null){
            mmu.setMODE(Integer.parseInt(line));
            line = br.readLine();
            if(mmu.getMode() == 0){
                holdResult = parseToMMU(line);
                sb.append(holdResult[0]).append(",");
                sb.append("0,");
                acquisition = mmu.queryAcquisition(holdResult);
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                System.out.println("ACQUISITION RESULT: "+ acquisition);
                if(acquisition == 2){
                    result = os.readHardMiss(holdResult);
                    sb.append(result[0]+",");
                    sbAcquisitionWrite(acquisition);
                    sb.append(result[1]+","+result[2]+"\n");
                }else{
                    pgData = mmu.pageForRead(holdResult);
                    sb.append(pgData).append(",");
                    sbAcquisitionWrite(acquisition);
                    sb.append("-1,-1\n");
                }
                System.out.println("Result: "+pgData+"\n");
            }else{
                holdResult = parseToMMU(line);
                sb.append(holdResult[0]).append(",");
                sb.append("1,");
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                line = br.readLine();
                sb.append(Integer.parseInt(line)).append(",");
                sbAcquisitionWrite(acquisition);
                System.out.println("Value: "+ line);
                
                if(acquisition == 2){
                    result = os.writeHardMiss(holdResult, Integer.parseInt(line));
                    sb.append(result[0]+","+result[1]+"\n");
                }else{
                    mmu.dataForWrite(holdResult, Integer.parseInt(line));
                    sb.append("-1,-1\n");
                }
            }
            os.clockTick();
        }
        pw.write(sb.toString());
        pw.close();
    }
    
    public void sbAcquisitionWrite(int i){
        if(i == 2){
            sb.append("0,1,0,");
        }else if(i == 1){
            sb.append("1,0,0,");
        }else{
            sb.append("0,0,1,");
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
    
    public static StringBuilder initialCSV(StringBuilder stringB){
        stringB.append("Address" + ",");
        stringB.append("R/W,");
        stringB.append("Value,");
        stringB.append("Soft,");
        stringB.append("Hard,");
        stringB.append("Hit,");
        stringB.append("Evicted_pg#,");
        stringB.append("Dirty_Evicted_Page\n");
        return stringB;
    }
}
