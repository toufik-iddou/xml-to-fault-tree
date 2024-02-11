/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate.components;

import java.util.ArrayList;
import xmltologicgate.position;

/**
 *
 * @author iddou
 */
abstract public class logicalComponent extends MyComponent {
    public ArrayList<MyComponent> children;
    public logicalComponent(position pos) {
        super(pos);
        children = new ArrayList<>();
    }
    public logicalComponent changeProbability(double probability,position pos){
        ArrayList<MyComponent> children= new ArrayList<>();
        if(this.pos.equals(pos)){
            this.probability = probability;
        }else{
        for(MyComponent component : this.children){
            if(component instanceof logicalComponent){
                
                children.add(((logicalComponent) component).changeProbability(probability, pos));
            }else if(component instanceof blocComponent){
                
             children.add(((blocComponent)component).changeProbability(probability, pos));
            }
        }
        }
        this.children = children;
        return this;
    }
    
    abstract public double calculateProbability();

}





