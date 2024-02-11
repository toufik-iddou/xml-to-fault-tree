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
abstract public class MyComponent {
    public static position maxPosition=new position(0, 0);
    public double probability = 1;
    public position pos;
    public String image="rectangle.png";
    public MyComponent(position pos){
        
                
        this.pos = pos;

    }
    
    public double getPropbability(){
        return this.probability;
    }
    
    @Override
    public String toString(){
    return this.image;
    }
    
    abstract public double calculateProbability();
}
