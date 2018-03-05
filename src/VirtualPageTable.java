
/**
 *
 * @author Jorge & Jacob
 */
public class VirtualPageTable {

    //THIS WILL BE AN ARRAY OF PAGE TABLE ENTRIES, so one dimensional array.
    //PAGE OFFSET IS 8 BITS.
    private static VirtualPageTable singleton;
    private static PageTableEntry[] ptentries = new PageTableEntry[256];

    private VirtualPageTable() {
    }

    public static VirtualPageTable getInstance() {
        if (singleton == null) {
            singleton = new VirtualPageTable();
            initPageTableEntries();
            System.out.println("VPT online.\n\tVPT entry count: 256");
        }
        return singleton;
    }

    public static void initPageTableEntries() {
        for (int i = 0; i < ptentries.length; i++) {
            ptentries[i] = new PageTableEntry();
        }
    }

    public void replaceEntry(int[] input, int dbit, int pageframe) {
        ptentries[input[0]].setPageFrameNum(pageframe);
        ptentries[input[0]].setDirtyBit(dbit);
        ptentries[input[0]].setValidBit(1);
        ptentries[input[0]].setRefBit(1);
    }

    public int returnFrameLocationAt(int input) {
        return ptentries[input].getPageFrameNum();
    }

    public boolean ptEntryExists(int pte) {
        boolean result = false;
        if (ptentries[pte].getValidBit() > 0) {
            result = true;
        }
        return result;
    }

    public int getIDFromFrameNumber(int input) {
        int result = -1;
        for (int i = 0; i < 256; i++) {
            if (ptentries[i].getPageFrameNum() == input) {
                result = i;
            }
        }
        return result;
    }

    public void refreshRBit(int i) {
        ptentries[i].setRefBit(1);
    }

    public void setDBit(int i) {
        ptentries[i].setRefBit(1);
        ptentries[i].setDirtyBit(1);
    }

    public int getDBit(int i) {
        return ptentries[i].getDirtyBit();
    }

    public void desetRBit(int i) {
        ptentries[i].setRefBit(0);
    }

    public void nukeEntry(int i) {
        if (ptentries[i].getRefBit() == 0) {
            ptentries[i] = new PageTableEntry();
        }
    }
}
