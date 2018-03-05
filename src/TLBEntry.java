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
    private int vPageNum;
    private int validBit;
    private int refBit;
    private int dirtyBit;
    private int pageFrameNum;
    
    public TLBEntry(){
        vPageNum = -1;
        validBit = -1;
        refBit = -1;
        dirtyBit = -1;
        pageFrameNum = -1;
    }
    public TLBEntry(int vpn, int pfn){
        vPageNum = vpn;
        pageFrameNum = pfn;
        validBit = 1;
        refBit = 1;
        dirtyBit = 0;
    }
    
    public TLBEntry(int vpn, int vb, int rb, int db, int pfn){
        vPageNum = vpn;
        validBit = vb;
        refBit = rb;
        dirtyBit = db;
        pageFrameNum = pfn;
    }
    
    public void setVPageNum(int vPageNum){
        this.vPageNum = vPageNum;
    }
    
    public int getVPageNum(){
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
    
    public void setPageFrameNum(int pageFrameNum){
        this.pageFrameNum = pageFrameNum;
    }
    
    public int getPageFrameNum(){
        return pageFrameNum;
    }
}
