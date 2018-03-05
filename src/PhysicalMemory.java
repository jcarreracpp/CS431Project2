
/**
 *
 * @author Jorge & Jacob
 */
public class PhysicalMemory {

    private static int[][] ram = new int[16][256];
    private int[] fresh = new int[16];
    private static PhysicalMemory singleton;

    //THIS WILL BE A TWO DIMENSIONAL ARRAY to simulate page-frame #.
    //ADDRESS WIDTH IS 12 BITS.
    //Dimension.
    private PhysicalMemory() {
    }

    public static PhysicalMemory getInstance() {
        if (singleton == null) {
            singleton = new PhysicalMemory();
        }
        return singleton;
    }

    public void addPage(int location, int[] input) {
        for (int i = 0; i < input.length; i++) {
            ram[location][i] = input[i];
            markInUse(location);
        }
    }

    public int ramFirstOpenSpace() {
        boolean found = false;
        int result = -1;
        for (int i = 0; i < fresh.length; i++) {
            if (fresh[i] == 0 && !found) {
                result = i;
                found = true;
                fresh[i] = 1;
            }
        }
        return result;
    }

    public void deref(int i) {
        fresh[i] = 0;
    }

    public void markInUse(int i) {
        fresh[i] = 1;
    }

    public void writeValue(int page, int index, int value) {
        ram[page][index] = value;
    }

    public int getValue(int page, int index) {
        return ram[page][index];
    }

    public int[] getPage(int page) {
        return ram[page];
    }
}
