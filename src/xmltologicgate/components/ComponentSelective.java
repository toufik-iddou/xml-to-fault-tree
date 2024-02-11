/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate.components;

import xmltologicgate.position;

/**
 *
 * @author iddou
 */
public class ComponentSelective {
   
    public static MyComponent getComponentByName(String name){
        switch (name){
            case "rectangle":
                return new rectangleBloc();
            case "circle":
                return new circularBloc();
            case "or":
                return new OR();
            case "nor":
                return new NOR();
            case "and":
                return new AND();
            case "nand":
                return new NAND();
            case "xor":
                return new XOR();
            case "xnor":
                return new XNOR();
            case "not":
                return new NOT();
            default:
                 return new rectangleBloc();
        }
    }
    
    
    public static MyComponent getComponentByName(String name,position pos,String lable){
        

        switch (name){
            case "rectangle":
                return new rectangleBloc(pos,lable);
            case "circle":
                return new circularBloc(pos,lable);
            case "or":
                return new OR(pos);
            case "nor":
                return new NOR(pos);
            case "and":
                return new AND(pos);
            case "nand":
                return new NAND(pos);
            case "xor":
                return new XOR(pos);
            case "xnor":
                return new XNOR(pos);
            case "not":
                return new NOT(pos);
            default:
                 return new rectangleBloc(pos,lable);
        }
    }
    
}
