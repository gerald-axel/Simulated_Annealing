/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

/**
 *
 * @author Gerald Alba
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

    private String inputFile;
    public static double [][]matriz;

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void read() throws IOException {
        File inputWorkbook = new File("C:\\Users\\Gerald Alba\\Desktop\\probarSimAnn2.xls");
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            int columns = sheet.getColumns();
            int rows = sheet.getRows();
            matriz = new double[rows][columns];
            Algorithm.C =  new int[rows];
            
            for (int j = 0; j < rows; j++) {
                for (int i = 0; i < columns; i++) {
                    Cell cell = sheet.getCell(i, j);
                    CellType type = cell.getType();
                    /*if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                + cell.getContents());
                    }*/

                    if (type == CellType.NUMBER) {
                        matriz[j][i] = Integer.parseInt(cell.getContents());
                    }
                }
                
                Algorithm.C[j] = j;
            }
            //System.out.println(Arrays.toString(matriz[0]));
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

}
