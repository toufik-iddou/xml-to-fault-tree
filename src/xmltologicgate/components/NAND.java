/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate.components;

import xmltologicgate.components.logicalComponent;
import java.util.ArrayList;
import xmltologicgate.position;

/**
 *
 * @author iddou
 */
public class NAND extends logicalComponent {
        
    public NAND(position pos) {
        super(pos);
        super.image = "nand.png";
    }
    
    public NAND() {
        super(new position(0, 0));
        super.image = "nand.png";
    }
    
    public double calculateProbability(){
                int len=this.children.size();
        Double[] props = new Double[len];
        for(int i =0;i<len;i++){
            props[i]=this.children.get(i).calculateProbability();
        }
        probability=NOT.calculat(AND.calculat(props));
    return probability;
    }    
}
