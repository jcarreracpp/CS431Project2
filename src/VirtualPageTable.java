/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class VirtualPageTable {
    //THIS WILL BE AN ARRAY OF PAGE TABLE ENTRIES, so one dimensional array.
    //PAGE OFFSET IS 8 BITS.
    private static VirtualPageTable singleton;
    private static PageTableEntry[] ptentries = new PageTableEntry[256];
    
    private VirtualPageTable(){ }
    
    public static VirtualPageTable getInstance(){
        if(singleton == null){
            singleton = new VirtualPageTable();
            initPageTableEntries();
            System.out.println("VPT online.\n\tVPT entry count: 256");
        }
        return singleton;
    }
    
    public static void initPageTableEntries(){
        for(int i = 0; i < ptentries.length; i++){
            ptentries[i] = new PageTableEntry();
        }
    }
    
    public void replaceEntry(int[] input, int pageframe){
        ptentries[input[0]].setPageFrameNum(pageframe);
        ptentries[input[0]].setDirtyBit(0);
        ptentries[input[0]].setValidBit(1);
        ptentries[input[0]].setRefBit(1);
    }
    
    public int returnFrameLocationAt(int input){
        return ptentries[input].getPageFrameNum();
    }
    
    public boolean ptEntryExists(int pte){
        boolean result = false;
        if(ptentries[pte].getPageFrameNum() != -1){
            result = true;
        }
        return result;
    }
}