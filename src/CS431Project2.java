
/**
 *
 * @author Jacob & Jorge
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class CS431Project2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        CPU cpu = CPU.getInstance();
        cpu.initCPU();
        cpu.readInstructions(args[0]);    
    }
}
