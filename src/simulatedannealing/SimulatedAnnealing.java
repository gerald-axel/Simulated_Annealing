/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Gerald Alba
 */
public class SimulatedAnnealing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xls");
        file.setFileFilter(filter);
        file.showOpenDialog(null);*/
        
        ReadExcel read = new ReadExcel();
        //read.setInputFile(file.getSelectedFile().getAbsolutePath());
        read.read();
        
        Algorithm alg = new Algorithm();
        alg.Anneal();
    }
    
}
