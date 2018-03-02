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
    
    // this method will convert String variable into a integer value, then return it.
    public int getVPageNum(){
        int tempInt = Integer.parseInt(vPageNum, 16);
        return tempInt;
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
