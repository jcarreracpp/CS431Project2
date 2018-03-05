
import java.io.IOException;

/**
 *
 * @author Jorge & Jacob
 */
public class OperatingSystem {

    private static Clock pointer;
    private static OperatingSystem singleton;
    private static HardDisk hd;
    private static PhysicalMemory pm;
    private static TLB tlb;
    private static VirtualPageTable vpt;
    private int clockCounter = 0;

    //Uses the clock algorithm for page replacement.
    //This will reset the R-bit every 5 instructions.
    private OperatingSystem() {
    }

    public static OperatingSystem getInstance() {
        if (singleton == null) {
            singleton = new OperatingSystem();
            initClock();
            System.out.println("OS online.\n\tOS clock cycle rate: 20");
        }
        return singleton;
    }

    public void initOS() throws IOException {
        hd = HardDisk.getInstance();
        pm = PhysicalMemory.getInstance();
        tlb = TLB.getInstance();
        vpt = VirtualPageTable.getInstance();
    }

    public void clockTick() {
        clockCounter++;
        if (clockCounter >= 4) {
            clockCounter = 0;
            derefAllEntries();
        }
    }

    public static void initClock() {
        pointer = new Clock(0, null);
        Clock cursor = pointer;
        for (int i = 1; i < 16; i++) {
            Clock newHand = new Clock(i, null);
            cursor.setNext(newHand);
            cursor = newHand;
        }
        cursor.setNext(pointer);
    }

    public void displayClock() {
        Clock cursor = pointer;
        for (int i = 0; i < 32; i++) {
            System.out.println(cursor.getID());
            cursor = cursor.getNext();
        }
    }

    //Hard miss for reading instruction, calls page replacement.
    public int[] readHardMiss(int[] input) {
        //int hold = -1;
        //hold = pm.ramFirstOpenSpace();
        //tlb.displayTLB();
        System.out.println("READ HARD MISS FOR " + input[0]);
        int[] result = new int[3];
        result[1] = -1;
        result[2] = -1;
        while (pointer.getRef() != 0) {
            pm.deref(pointer.getID());
            pointer.deRef();
            pointer = pointer.getNext();
        }
        //displayClock();
        result[1] = vpt.getIDFromFrameNumber(pointer.getID());
        result[2] = pageReplacement(input[0]);
        vpt.replaceEntry(input, 0, pointer.getID());
        tlb.addReadEntry(input[0], pointer.getID());
        result[0] = pm.getValue(tlb.getPageFrame(input[0]), input[1]);
        return result;
    }

    //Hard miss for writing instruction, calls page replacement.
    public int[] writeHardMiss(int[] input, int value) {
        //tlb.displayTLB();
        System.out.println("WRITE HARD MISS FOR " + input[0]);
        int[] result = new int[2];
        result[1] = -1;
        while (pointer.getRef() != 0) {
            pm.deref(pointer.getID());
            pointer.deRef();
            pointer = pointer.getNext();
        }

        //displayClock();
        System.out.println("");
        result[0] = vpt.getIDFromFrameNumber(pointer.getID());
        result[1] = pageReplacement(input[0]);
        vpt.replaceEntry(input, 1, pointer.getID());
        tlb.addReadEntry(input[0], pointer.getID());
        pm.writeValue(pointer.getID(), input[1], value);
        return result;
    }

    //Replaces a page, if DBit is set, saves page to HD before evicting.
    private int pageReplacement(int i) {
        int dbitSet = 0;
        if (vpt.getDBit(i) == 1) {
            hd.writeToHDD(i, pm.getPage(pointer.getID()));
            dbitSet = 1;
        }
        pm.addPage(pointer.getID(), hd.returnPage(i));
        pm.markInUse(pointer.getID());
        pointer.setRef();
        return dbitSet;
    }

    private void derefAllEntries() {
        for (int i = 0; i < 256; i++) {
            vpt.nukeEntry(i);
            vpt.desetRBit(i);
        }
        tlb.nukeDerefEntries();
        tlb.derefEntries();
    }
}
