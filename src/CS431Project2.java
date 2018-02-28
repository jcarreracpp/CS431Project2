/**
 *
 * @author Jacob
 */
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CS431Project2 {
    public static void main(String[] args) throws FileNotFoundException {
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
        
        HardDisk hData = new HardDisk();
        VirtualPageTable [] pageData = new VirtualPageTable[256];
        TLB [] tlbData = new TLB[256];
        
        pw.write(sb.toString());
        pw.close();
        
    }
}
