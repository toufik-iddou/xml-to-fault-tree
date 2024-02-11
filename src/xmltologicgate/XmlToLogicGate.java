/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate;

import java.awt.Color;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author iddou
 */
public class XmlToLogicGate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Myfilechooser fc = new Myfilechooser();
        String filePath = "";
        
        try{
        filePath = fc.getFile();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }
            
            
        fileHandler fh = new fileHandler(filePath);
        int height = fh.maxIndexes.size();
        int width = 0;
        for(int i : fh.maxIndexes){
        if(width<i){
        width=i;}}

        JFrame form  = new JFrame();

        form.setLayout(null);
        form.setBackground(Color.WHITE);
        MyDraw draw = new MyDraw(form,width,height,fh.component);
        draw.border = 1;
        draw.drawSchame();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setExtendedState(form.getExtendedState() | JFrame.MAXIMIZED_BOTH);        
        form.setVisible(true);
    } 
}
