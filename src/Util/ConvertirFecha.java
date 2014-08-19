package Util;

//esta clase se utiliza para convertir una fecha al formato de fecha mysql
public abstract class  ConvertirFecha {
    public static String Convertir( java.util.Date fecha){
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("YYYY-MM-dd");
        String fecha_texto = sdf.format(fecha);
        return fecha_texto;
    }
    
    
}
