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
            System.out.println("TLB online.\n\tTLB entry count: 8");
        }
        return singleton;
    }
    
    public boolean tlbEntryExists(int vpn){
        boolean result = false;
        for(int i = 0; i < entries.length; i++){
            if(entries[i].getVPageNum() == vpn)
                result = true;
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
