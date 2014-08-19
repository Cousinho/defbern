package Util;

import javax.swing.table.DefaultTableModel;

//modelo de tabla, las tablas que usen este modelo no seran editables
public class TablaModelo extends DefaultTableModel {
       
    public TablaModelo(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
     public TablaModelo() {
    }
   
       public boolean isCellEditable (int filas,int Columnas){
			return false;	
	}
         
}
