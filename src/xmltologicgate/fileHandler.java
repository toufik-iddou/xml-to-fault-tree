/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmltologicgate.components.ComponentSelective;
import xmltologicgate.components.MyComponent;
import xmltologicgate.components.blocComponent;
import xmltologicgate.components.logicalComponent;


/**
 *
 * @author iddou
 */
public final class fileHandler {
    Document doc;
    blocComponent component;
    int maxy=0;
    int maxx=0;
    ArrayList<Integer> maxIndexes;
    public fileHandler(String path){
         try   
{  
    maxIndexes= new ArrayList<>();
    maxIndexes.add(1);
File file = new File(path);  
DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
DocumentBuilder db = dbf.newDocumentBuilder();  
doc = db.parse(file);  
doc.getDocumentElement().normalize();  
String targetName = doc.getDocumentElement().getNodeName();
//System.out.println("Root element: " +targetName );
Node mainNode = doc.getElementsByTagName(targetName).item(0);
 Element element = (Element) mainNode;
String label = element.getAttribute("label");
int level = 0;
component = (blocComponent) ComponentSelective.getComponentByName(targetName,new position(0, 0),label);
component.child = (logicalComponent) (getChildren(mainNode,level,0).get(0));
}   
catch (  IOException | ParserConfigurationException | SAXException e)   
{  
System.out.print(e.getMessage());
} 
    }

ArrayList<MyComponent> getChildren(Node topnode,int level,int Imin){
ArrayList<MyComponent> components= new ArrayList<>();
NodeList children = topnode.getChildNodes();


for (int itr = 0; itr < children.getLength(); itr++)   
{  
Node node = children.item(itr);  
if(!node.getNodeName().equals("#text")){  
////System.out.println("\nNode Name :" + node.getNodeName());
String targetName= node.getNodeName();
Element element = (Element) node;
String label = element.getAttribute("label");

int iIndex = Imin+components.size();
int jIndex = level+1;
MyComponent localComponent = ComponentSelective.getComponentByName(targetName,new position(iIndex,jIndex ),label);
if(node.getChildNodes().getLength()>1){
    if(localComponent instanceof blocComponent){
    blocComponent blockcomponent = (blocComponent)localComponent;
    blockcomponent.child = (logicalComponent) (getChildren(node,level+1,iIndex).get(0));
    components.add(blockcomponent);
    setMaxIndex(level+1, iIndex);
    }
    else if(localComponent instanceof logicalComponent){
        for(int i =level+1;i<maxIndexes.size();i++){
            if(iIndex<maxIndexes.get(i)){
            iIndex=maxIndexes.get(i);
            }
        }
        logicalComponent logicalcomponent = (logicalComponent)localComponent;
        logicalcomponent.pos.i = iIndex++;
        setMaxIndex(level+1, iIndex);
    logicalcomponent.children = getChildren(node,level+1,logicalcomponent.pos.i);
    
    components.add(logicalcomponent);
    }
}else{
    components.add(localComponent);
    setMaxIndex(level+1, iIndex);
}
}
} 
return components; 
}
void setMaxIndex(int level,int iIndex){
    for(int i =maxIndexes.size()-1 ; i<level ; i++){
    maxIndexes.add(0);

        }
    maxIndexes.set(level,iIndex+1);
    printMaxIndexes();
}
void printMaxIndexes(){
//System.out.println(maxIndexes.toString());
}
}
