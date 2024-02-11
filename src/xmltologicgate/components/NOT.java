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
public class NOT extends logicalComponent {
        
    public NOT(position pos) {
        super(pos);
        super.image = "not.png";
    }
        public NOT() {
        super(new position(0, 0));
        super.image = "not.png";
    }
        
     static public double calculat(double prob){
        return 1-prob;
    }

    
    public double calculateProbability(){
        probability=NOT.calculat(this.children.get(0).probability);
    return probability;
    }    
}
