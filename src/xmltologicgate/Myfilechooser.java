/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltologicgate;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 *
 * @author iddou
 */
public class Myfilechooser {
    public Myfilechooser(){}
    public String getFile(){
        JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose an XML file");
            fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));

                        int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                return  selectedFile.getAbsolutePath();
            } else {
               throw new NullPointerException("file not find");
            }
    }
}
