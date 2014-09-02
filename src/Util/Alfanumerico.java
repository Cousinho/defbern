package Util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//clase alfanumerico recibe un campo de texto y un número maximo de caracteres
//esta clase solo permite insertar números y letras
public class Alfanumerico extends PlainDocument
{
   private JTextField editor;
    
   private int numeroMaximoCaracteres;

    public Alfanumerico(JTextField editor, int numeroMaximoCaracteres)
    {
        this.editor=editor;
        this.numeroMaximoCaracteres=numeroMaximoCaracteres;
    }
    
    public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException
    {
       if ((editor.getText().length()+arg1.length())>this.numeroMaximoCaracteres){
           return;      
       }
       
       for (int i=0;i<arg1.length();i++){
           if((!Character.isLetter(arg1.charAt(i)))&&(!Character.isDigit(arg1.charAt(i))) ){
             return;   
           }
            
       }
        super.insertString(arg0, arg1, arg2);    
            
            
        
    }
    
} 