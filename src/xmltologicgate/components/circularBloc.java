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
public class circularBloc extends blocComponent{
    public circularBloc(position pos,String lable) {
        super(pos,lable);
        super.image = "circle.png";
    }
        public circularBloc() {
        super(new position(0, 0),"");
        super.image = "circle.png";
    }

}
