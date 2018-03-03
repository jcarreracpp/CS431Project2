
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class OperatingSystem {
    private static OperatingSystem singleton;
    //Uses the clock algorithm for page replacement.
    //This will reset the R-bit every 20 instructions.
    private OperatingSystem(){}
    
    public static OperatingSystem getInstance(){
        if(singleton == null){
            singleton = new OperatingSystem();
            System.out.println("OS online.\n\tOS clock cycle rate: 20");
        }
        return singleton;
    }
    
    public void initOS(){
    }
}
