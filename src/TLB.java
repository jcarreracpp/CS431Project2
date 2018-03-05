/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class TLB {
    private static TLB singleton;
    private static TLBEntry[] entries = new TLBEntry[8];
    //THIS will be a one dimensional array of TLB entries. ONLY 8 ENTRIES.

    
    private TLB(){}
    
    public static TLB getInstance(){
        if(singleton == null){
            singleton = new TLB();
            initEntries();
            System.out.println("TLB online.\n\tTLB entry count: 8");
        }
        return singleton;
    }
    
    public static void initEntries(){
        for(int i = 0; i < entries.length; i++){
            entries[i] = new TLBEntry();
        }
    }
    
    
    
    public void addReadEntry(int vPageNum, int pFrameNum){
        for(int i = entries.length-2; i >= 0; i--){
            entries[i+1] = entries[i];
        }
        entries[0] = new TLBEntry(vPageNum, pFrameNum);
    }
    
    public void addWrittenEntry(int vPageNum, int pFrameNum) {
        for(int i = entries.length-2; i >= 0; i--){
            entries[i+1] = entries[i];
        }
        entries[0] = new TLBEntry(vPageNum, pFrameNum);
        entries[0].setDirtyBit(1);
    }
    
    public void collapseToEnd(){
        int pullbackCounter = 0;
        boolean missedSpot = false;
        for(int i = entries.length-1; i >= 0; i--){
            if(entries[i].getVPageNum() != -1){
                for(int j = i; j >=0; j--){
                    
                }
            }else{
                pullbackCounter++;
            }
        }
    }
    
    public void derefEntries(){
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getRefBit() == 1){
                entries[i].setRefBit(0);
            }
        }
    }
    
    public void nukeDerefEntries(){
        for(int i = 0; i < entries.length; i++){
            if( entries[i].getRefBit() == 0){
                entries[i] = new TLBEntry();
            }
        }
    }
    
    public boolean tlbEntryExists(int vpn){
        boolean result = false;
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getVPageNum() == vpn){
                result = true;
            }
        }
        return result;
    }
    
    public int getPageFrame(int vpn){
        int result = -1;
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getVPageNum() == vpn)
                result = entries[i].getPageFrameNum();
        }
        return result;
    }

    void refreshRBit(int input) {
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getVPageNum() == input){
                entries[i].setValidBit(1);
            }
        }
    }

    void setDBit(int input) {
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getVPageNum() == input){
                entries[i].setValidBit(1);
                entries[i].setDirtyBit(1);
            }
        }
    }
    public void displayTLB(){
        for(int i = 0; i < entries.length; i++){
            System.out.println("TLB ENTRY "+i+": vpn: "+entries[i].getVPageNum()+", vb: "
            +entries[i].getValidBit()+", rb: "+entries[i].getRefBit()+", db: "+
            entries[i].getDirtyBit()+", pfn: "+entries[i].getPageFrameNum());
        }
    }
}
