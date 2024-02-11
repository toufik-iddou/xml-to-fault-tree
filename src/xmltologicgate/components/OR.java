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
public class OR extends logicalComponent{ 
        public OR(position pos) {
        super(pos);
        super.image = "or.png";;
    }
    public OR() {
        super(new position(0, 0));
        super.image = "or.png";
    }
        
    static public double calculat(Double[] probs){
        double total = 1;
        for(double prob : probs){
            total *=(1-prob);
        }
        return 1-total;
    }

    
    public double calculateProbability(){
               int len=this.children.size();
        Double[] props = new Double[len];
        for(int i =0;i<len;i++){
            props[i]=this.children.get(i).calculateProbability();
        }
        probability=OR.calculat(props);
    return probability;
    }    
}
