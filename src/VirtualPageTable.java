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
    private int validBit;
    private int refBit;
    private int dirtyBit;
    private String pageFrameNum;
    
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
    
     
    public void setValidBit(int validBit){
        this.validBit = validBit;
    }
    
    public int getValidBit(){
        return this.validBit;
    }
    
    public void setRefBit(int refBit){
        this.refBit = refBit;
    }
    
    public int getRefBit(){
        return this.refBit;
    }
    
    public void setDirtyBit(int dirtyBit){
        this.dirtyBit = dirtyBit;
    }
    
    public int getDirtyBit(){
        return this.dirtyBit;
    }
    
    public void setPageFrameNum(String pageFrameNum){
        this.pageFrameNum = pageFrameNum;
    }
    
    // this method will convert String variable into a integer value, then return it.
    public int getPageFrameNum(){
        int tempInt = Integer.parseInt(pageFrameNum, 16);
        return tempInt;
    }
    
}