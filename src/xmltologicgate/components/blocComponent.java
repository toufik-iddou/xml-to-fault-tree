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
abstract public class blocComponent extends MyComponent{
    public logicalComponent child; 
    public String lable;
    public blocComponent(position pos,String lable) {
        super(pos);
        this.lable = lable;
    }
    
    public ArrayList<ArrayList<MyComponent>> getListchildrenWithLevel(){
    ArrayList<ArrayList<MyComponent>> componentsList = new ArrayList<>();
    ArrayList<MyComponent> components = new ArrayList<>();
    components.add(this.child);
      return componentsList;     
    }
    public blocComponent changeProbability(double probability,position pos){
        try{
        if(this.pos.equals(pos)){
            this.probability = probability;    
        }else{
            if(this.child!=null){
         this.child = ((logicalComponent) this.child).changeProbability(probability, pos);
           }
        }
        }catch(Exception ex){
        System.out.println(String.valueOf(pos.i) +";"+String.valueOf(pos.j));
        System.out.println(String.valueOf(this.pos.i) +";"+String.valueOf(this.pos.j));
        
        System.out.println(ex);
        }
        
        return this;
    }
    
    public double calculateProbability(){
        if(this.child != null){
            this.probability= this.child.calculateProbability();
        }
        return this.probability;
    }
    
}
