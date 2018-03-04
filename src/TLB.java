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
    
    public void addEntry(int vPageNum, int pFrameNum){
        for(int i = entries.length-2; i >= 0; i--){
            entries[i+1] = entries[i];
        }
        entries[0] = new TLBEntry(vPageNum, pFrameNum);
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
    
    public TLBEntry getEntry(String vpn){
        TLBEntry hold = new TLBEntry();
        for(int i = 0; i < entries.length; i++){
            if(vpn.equals(entries[i].getVPageNum()))
                hold = entries[i];
        }
        return hold;
    }
}
