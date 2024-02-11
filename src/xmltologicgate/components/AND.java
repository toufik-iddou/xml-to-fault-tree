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
public class AND extends logicalComponent {
    public double probability=1;    
    public AND(position pos) {
        super(pos);
        super.image = "and.png";
    }
    public AND() {
        super(new position(0, 0));
        super.image = "and.png";
    }
    
    
    
     
    static public double calculat(Double[] probs){
        double total = 1;
        for(double prob : probs){
            total *=prob;
        }
        return total;
    }

    
    public double calculateProbability(){
        int len=this.children.size();
        Double[] props = new Double[len];
        for(int i =0;i<len;i++){
            props[i]=this.children.get(i).calculateProbability();
        }
        probability=AND.calculat(props);
    return probability;
    }    
    
}
