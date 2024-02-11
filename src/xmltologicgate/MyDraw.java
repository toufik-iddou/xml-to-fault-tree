/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import xmltologicgate.components.MyComponent;
import xmltologicgate.components.blocComponent;
import xmltologicgate.components.circularBloc;
import xmltologicgate.components.logicalComponent;

/**
 *
 * @author iddou
 */
public class MyDraw {
    
    private JFrame form;
    public  Graphics2D g2d;
    private blocComponent bcomponent;
    public MyDraw(JFrame form){
    this.form = form;
    }
     public MyDraw(JFrame form,int pnlWidth,int pnlHeight,blocComponent component){
    this.form = form;
    this.pnlWidth = pnlWidth*hpading+200;
    this.pnlHeight =pnlHeight*vpading+20;
    this.bcomponent = component;
    }
    
    public int border = 1;
    public Color color = Color.black;
    public int hpading = 110;
    //public int hpading = 180;
    public int vpading = 80;
    //public int vpading = 80;
    public int scale = 50;
    //public int width = 166;
    public int width = 100;
    public int height = 74;
    private int maxLableLength = 28;
    public int pnlWidth = 300;
    public int pnlHeight =300;
    JPanel pnl;
   
    
        public void drawSchame(){
            
        pnl = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g2d= (Graphics2D) g;
            g2d.setStroke(new BasicStroke(border));
            g2d.setColor(color);
            drawComponent(bcomponent);
         
        }
        };
        JScrollPane sp = new JScrollPane();
        pnl.setLayout(null);
        sp.add(pnl);
        
        
        pnl.setBackground(Color.WHITE);
        pnl.setSize(pnlWidth,pnlHeight);
        savePanelAsImage(pnl);
        
        form.add(pnl);
        form.setBackground(Color.WHITE);
    }
    
    void drawComponent(MyComponent component){
        JButton btn =new JButton();
        btn.setLayout(null);
        btn.setSize(100, 70);
        btn.setMargin(new Insets(1,1,1,1));
        btn.setLocation(component.pos.i*hpading+1, component.pos.j*vpading+1);
        File image = new File(component.image);
        try{
        Image img =ImageIO.read(image);
        this.pnl.add(btn);
        if(component instanceof blocComponent){
            //create button  ************
            JLabel lbl =new JLabel(String.valueOf(((blocComponent) component).probability)  );
            lbl.setSize(50, 20);
            btn.setText(((blocComponent) component).lable);
            btn.add(lbl);
            lbl.setLocation(3, 0);
            btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                 if(((blocComponent) component).child==null){
                 String prob = JOptionPane.showInputDialog("enter the probability");
                     double probability =Float.parseFloat(prob);
                 if(probability>=0 &&  probability<=1){
                     form.remove(pnl);
                     try{
                     bcomponent = ((blocComponent) bcomponent).changeProbability(probability, component.pos);
                     bcomponent.calculateProbability();
                     }
                     catch(Exception ex){
                     System.err.println(ex);
                     }
                    drawSchame();
                     SwingUtilities.updateComponentTreeUI(form);
                 }else{
                 JOptionPane.showMessageDialog(null,"the prbebilty should be number between 0 and 1");
                 }
                }
                }
                catch(Exception ex){
                }
            }
        });
         //drawLable((blocComponent) component);
        if(((blocComponent) component).child!=null){
            drawComponent(((blocComponent) component).child);
           drawConnection(component.pos.i*hpading+width/2,component.pos.j*vpading+height,((blocComponent) component).child.pos.i*hpading+width/2,((blocComponent) component).child.pos.j*vpading);
        }
        }
        else if(component instanceof logicalComponent){
            ImageIcon icon = new ImageIcon(((logicalComponent) component).image);
            btn.setIcon(icon);
            btn.setBorderPainted(false);
            btn.setBackground(Color.WHITE);
        for (MyComponent comp :((logicalComponent) component).children){
            drawComponent(comp);
            drawConnection(component.pos.i*hpading+width/2,component.pos.j*vpading+height,comp.pos.i*hpading+width/2,comp.pos.j*vpading);      
        }
        }
        if(component instanceof circularBloc){
         ImageIcon icon = new ImageIcon(((circularBloc)component).image);
         JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            lbl.setSize(100,70);
            btn.add(lbl);
            btn.setBorderPainted(false);
            btn.setBackground(Color.WHITE);
        }
        }
        catch(Exception ex ){
        }
    }
    void drawConnection(int x1,int y1,int x2,int y2){
        int y3=y1;
     
          this.g2d.drawLine(x1,y1-5,x1,y1);   
     this.g2d.drawLine(x1,y1,x2,y1);
     
     this.g2d.drawLine(x2,y1,x2,y2);
    }
    void drawLable(blocComponent component){
        String lable = component.lable;
        int length = lable.length();
        int xspace = length>maxLableLength?maxLableLength:length;
        int yspace = 0;
        if(length>28){
        lable=lable.substring(0,25)+"...";
        }
    this.g2d.drawString(lable, component.pos.i*hpading+(int)(width/2-xspace*2.7), component.pos.j*vpading+height/2-yspace*4);
    }
    String lableFormater(String lable){
         int length = lable.length();
         String newLable ="";
        for(int i =0;i<length/maxLableLength;i++){
            int min = i*maxLableLength;
            int max = (i+1)*maxLableLength;
            if(max>length){
            max=length;
            }
        newLable+=lable.substring(min, max)+"\n";
        System.err.println(newLable);
        }
        return newLable;
    }
      private void savePanelAsImage(JPanel panel) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        panel.printAll(g2d);
        g2d.dispose();
        try {
            File outputFile = new File("panel_image.png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("Image saved to: " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
}
}
