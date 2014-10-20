package Util;


import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoFecha extends DefaultTableCellRenderer { 
    public FormatoFecha() { super(); 
    } @Override public void setValue(Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        setText((value == null) ? "" : sdf.format(value)); } 
}