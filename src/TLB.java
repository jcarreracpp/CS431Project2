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
    //THIS will be a one dimensional array of TLB entries. ONLY 8 ENTRIES.
    private String vPageNum;
    private int validBit;
    private int refBit;
    private int dirtyBit;
    private String pageFrameNum;
    
    public TLB(){
        
    }
    
    public boolean lookup(){
        return false;
    }
    
    public void setVPageNum(String vPageNum){
        this.vPageNum = vPageNum;
    }
    
    public void setValidBit(int validBit){
        this.validBit = validBit;
    }
    
    public void setRefBit(int refBit){
        this.refBit = refBit;
    }
    
    public void setDirtyBit(int dirtyBit){
        this.dirtyBit = dirtyBit;
    }
    
    public void setPageFrameNum(String pageFrameNum){
        this.pageFrameNum = pageFrameNum;
    }
    
}
