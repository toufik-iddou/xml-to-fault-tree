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
public class rectangleBloc extends blocComponent{
    
    public rectangleBloc(position pos,String lable) {
        super(pos,lable);
    }
    
        public rectangleBloc(){
        super(new position(0, 0),"");
    }
    
}
