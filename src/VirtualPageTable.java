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
    private PageTableEntry[] ptentries = new PageTableEntry[256];
    
    
    private VirtualPageTable(){ }
    
    public static VirtualPageTable getInstance(){
        if(singleton == null){
            singleton = new VirtualPageTable();
            System.out.println("VPT online.\n\tVPT entry count: 256");
        }
        return singleton;
    }
    
    public boolean ptEntryExists(int pte){
        boolean result = false;
        if(ptentries[pte].getPageFrameNum() != null)
            result = true;
        return result;
    }
 
    
}