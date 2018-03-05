
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
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
    public static PrintWriter pw;
    public static StringBuilder sb;
    
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
        pw = new PrintWriter(new File("result.csv")); 
        sb = new StringBuilder();
        sb = initialCSV(sb);
        
    }
    
    public void doThing(){
        System.out.println("Accessing data at page FF, value FF: "+mmu.readHDD("FF","FF"));
    }
    
    public void readInstructions() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("test_files\\test_1.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        String tempLine = null;
        int[] holdResult;
        int pgData = 0;
        while((line = br.readLine()) != null){
            mmu.setMODE(Integer.parseInt(line));
            line = br.readLine();
            if(mmu.getMode() == 0){
                holdResult = parseToMMU(line);
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                pgData = mmu.pageForRead(holdResult);
                System.out.println("Result: "+pgData+"\n");
                
                sb = writeCSV(sb, line, mmu.getMode(), pgData, mmu.getHitMissVal(), mmu.getFrameNum(), mmu.returnDirtyBit());
                
                
            }else{
                holdResult = parseToMMU(line);
                System.out.println("Page: "+ holdResult[0] + ", Index: "+ holdResult[1]);
                tempLine = line; //copy address value into a temp String variable
                line = br.readLine(); 
                System.out.println("Value: "+ line +"\n");
                mmu.dataForWrite(holdResult, Integer.parseInt(line));
                
                sb = writeCSV(sb, tempLine, mmu.getMode(), Integer.parseInt(line), mmu.getHitMissVal(), mmu.getFrameNum(), mmu.returnDirtyBit());
            }
            os.clockTick();
        }
        
        pw.write(sb.toString());
        pw.close();
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
        sb.append("Address" + ",");
        sb.append("R/W,");
        sb.append("Value,");
        sb.append("Soft,");
        sb.append("Hard,");
        sb.append("Hit,");
        sb.append("Evicted_pg#,");
        sb.append("Dirty_Evicted_Page\n");
        return stringB;
    }
    
    //writing to csv file when it's reading
    public static StringBuilder writeCSV(StringBuilder stringB, String address, int readWriteVal, int value, int hitVal, int evictPageNum, int dirtyEvictPage){
        int tempInt;
        
        //writing address into csv
        sb.append(address + ","); //writing address into csv

//writing        
        if(readWriteVal == 0) //writing Read/Write Val.
            sb.append("0 ,");
        else
            sb.append("1 ,");
        
//writing the actual value from the hard disk
        sb.append(value + ",");

//writing the hit or miss value.
        tempInt = hitVal;
        switch(tempInt){
            // HIT
            case 0:
                sb.append("0 ,");
                sb.append("0 ,");
                sb.append("1 ,");
                sb.append("N/A ,");
                break;
            // SOFT MISS
            case 1:
                sb.append("1 ,");
                sb.append("0 ,");
                sb.append("0 ,");
                sb.append(evictPageNum + ",");
                break;
            // HARD MISS
            case 2:
                sb.append("0 ,");
                sb.append("1 ,");
                sb.append("0 ,");
                sb.append(evictPageNum + ",");
                break;
        }
        
        
        //writng the dirty evict Page #
        sb.append(dirtyEvictPage + "\n");
        
        
        return stringB;
    }
}
