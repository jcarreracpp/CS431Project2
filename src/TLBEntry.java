/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
class TLBEntry {
    private String vPageNum;
    private int validBit;
    private int refBit;
    private int dirtyBit;
    private String pageFrameNum;
    
    public TLBEntry(){
        vPageNum = null;
        validBit = -1;
        refBit = -1;
        dirtyBit = -1;
        pageFrameNum = null;
    }
    public TLBEntry(String vpn, int vb, int rb, int db, String pfn){
        vPageNum = vpn;
        validBit = vb;
        refBit = rb;
        dirtyBit = db;
        pageFrameNum = pfn;
    }
    
    public void setVPageNum(String vPageNum){
        this.vPageNum = vPageNum;
    }
    
    public String getVPageNum(){
        return vPageNum;
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
    
    public String getPageFrameNum(){
        return pageFrameNum;
    }
}
