
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jorge & Jacob
 */
public class HardDisk {

    private static int[][] harddrive = new int[256][256];
    private static HardDisk singleton;
    private static char[] hexGuide = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private HardDisk() {
    }

    public static HardDisk getInstance() throws FileNotFoundException, IOException {
        if (singleton == null) {
            singleton = new HardDisk();
            fillDrive();
            System.out.println("HDD online.\n\tHDD entry count: 256");
        }
        return singleton;
    }

    private static void fillDrive() throws FileNotFoundException, IOException {
        for (int i = 0; i < 256; i++) {
            int counter = 0;
            String pgid = hexify(i);
            pgid = "page_files\\" + pgid;
            pgid = pgid.concat(".pg");
            //System.out.println(pgid);
            FileReader fr = new FileReader(pgid);
            BufferedReader br = new BufferedReader(fr);
            while ((pgid = br.readLine()) != null) {
                harddrive[i][counter] = Integer.parseInt(pgid);
                counter++;
            }
            br.close();
            fr.close();
        }
    }

    public void display() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                System.out.println(harddrive[i][j]);
            }
        }
    }

    public int[] returnPage(int i) {
        int[] result = harddrive[i];
        return result;
    }

    public int readValue(String address, String value) {
        return harddrive[decimalize(address)][decimalize(value)];
    }

    public void writeToHDD(int page, int input[]) {
        for (int i = 0; i < 256; i++) {
            harddrive[page][i] = input[i];
        }
    }

    private static String hexify(int i) {
        String temp = "";
        char lead = hexGuide[(i / 16)];
        temp += lead;
        lead = hexGuide[(i % 16)];
        temp += lead;
        return temp;
    }

    private static int decimalize(String i) {
        char lead = i.charAt(0);
        char back = i.charAt(1);
        int result = 0;
        for (int j = 0; j < hexGuide.length; j++) {
            if (lead == hexGuide[j]) {
                result = (result + (j * 16));
            }
            if (back == hexGuide[j]) {
                result = (result + (j));
            }
        }
        return result;
    }
}
