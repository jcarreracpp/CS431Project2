
import java.io.IOException;

/**
 *
 * @author Jorge & Jacob
 */
public class MMU {

    private static MMU singleton;
    private static HardDisk hd;
    private static PhysicalMemory pm;
    private static TLB tlb;
    private static VirtualPageTable vpt;
    private int mode = 0;

    //THIS WILL USE FIFO for the replacement algorithm.
    private MMU() {
    }

    public static MMU getInstance() {
        if (singleton == null) {
            singleton = new MMU();
            System.out.println("MMU online.");
        }
        return singleton;
    }

    public static void initMMU() throws IOException {
        hd = HardDisk.getInstance();
        pm = PhysicalMemory.getInstance();
        tlb = TLB.getInstance();
        vpt = VirtualPageTable.getInstance();
    }

    public static int readHDD(String a, String v) {
        return hd.readValue(a, v);
    }

    public void setMODE(int i) {
        if (i == 1) {
            mode = 1;
        } else {
            mode = 0;
        }
    }

    public void printMode() {
        if (mode == 0) {
            System.out.println("READ");
        } else {
            System.out.println("WRITE");
        }
    }

    public int queryAcquisition(int[] input) {
        int result = 0;
        if (queryTLB(input[0])) {
            result = 0;
        } else if (queryVPT(input[0])) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }

    public int pageForRead(int[] input) {
        int hold = -1;
        //tlb.displayTLB();
        if (queryTLB(input[0])) {
            //HIT
            System.out.println("READ: HIT");
            vpt.replaceEntry(input, 0, tlb.getPageFrame(input[0]));
            tlb.refreshRBit(input[0]);
            return pm.getValue(tlb.getPageFrame(input[0]), input[1]);
        } else {
            //SOFT MISS
            //So also returns the read value here, but before must also
            //put the virtual page table intothe TLB and physical memory.
            System.out.println("READ: SOFT MISS");
            hold = vpt.returnFrameLocationAt(input[0]);
            vpt.replaceEntry(input, 0, tlb.getPageFrame(input[0]));
            tlb.addReadEntry(input[0], hold);
            return pm.getValue(tlb.getPageFrame(input[0]), input[1]);
        }
    }

    public void dataForWrite(int[] input, int data) {
        int hold = -1;
        //tlb.displayTLB();
        if (queryTLB(input[0])) {
            //HIT
            System.out.println("WRITE: HIT");
            vpt.replaceEntry(input, 1, tlb.getPageFrame(input[0]));
            tlb.setDBit(input[0]);
            pm.writeValue(tlb.getPageFrame(input[0]), input[1], data);
            //return pm.getValue(tlb.getPageFrame(input[0]), input[1]);
        } else {
            //SOFT MISS
            //So also returns the read value here, but before must also
            //put the virtual page table intothe TLB and physical memory.
            System.out.println("WRITE: SOFT MISS");
            hold = vpt.returnFrameLocationAt(input[0]);
            vpt.replaceEntry(input, 1, tlb.getPageFrame(input[0]));
            tlb.addWrittenEntry(input[0], hold);
            pm.writeValue(tlb.getPageFrame(input[0]), input[1], data);
        }
    }

    //Redundant method wrapper, make it look nicer in here.
    public boolean queryTLB(int page) {
        return tlb.tlbEntryExists(page);
    }

    //Also redundant wrapper, twice as nice.
    public boolean queryVPT(int page) {
        return vpt.ptEntryExists(page);
    }

    public int getMode() {
        return mode;
    }
}
