package Analisis;

import RNA.Emociones;
import java.io.File;
import org.opencv.core.Point;
public class Analisis  {
    Point nariz = new Point();
    Point ojo1 = new Point();
    Point tamañoojo1 = new Point();
    Point ojo2 = new Point();
    Point tamañoojo2 = new Point();
    Point boca = new Point();
    Point tamañoboca = new Point();
    Point ceja1 = new Point();
    Point ceja2 = new Point();
    Point tamañoceja1 = new Point();
    Point tamañoceja2 = new Point();
    private double distancia11, distancia12,distancia13;
    private double distancia21, distancia22,distancia23;
    private double distancia31, distancia32,distancia33;
    private double distancia41, distancia42,distancia43;
    private double distancia51, distancia52,distancia53;
    private double distancia61, distancia62,distancia63;
    private String DirectorioPrincipal="imagen/Muestras/";
    private String Directorio;
    private String SubDirectorio="";
    private String DirectorioResultados="";
    private int TamanhoMuestras=0;
    private int TamanhoLista=0;
    private int[] Resultados=new int[6];
    private Rostro AnalisisRostro=new Rostro();
    private Cejas AnalisisCejas=new Cejas();
    private Ojos AnalisisOjos=new Ojos();
    private Nariz AnalisisNariz=new Nariz();
    private Boca AnalisisBoca=new Boca();
    public int[] Analizar(int codigo_perfil){
        Directorio=DirectorioPrincipal+codigo_perfil+"/";
        File listamuestras = new File(Directorio);
        if(!listamuestras.exists()){
            return null;
        }
        DirectorioResultados=Directorio+"Resultados/";
        File make=new File(DirectorioResultados);
        make.mkdir();
        TamanhoMuestras=listamuestras.list().length;
        if(TamanhoMuestras!=0){
            Resultados = new int[TamanhoMuestras];
            for(int x = 0;x<TamanhoMuestras;x++){
                SubDirectorio=Directorio+x+"/";
                if(x!=0){
                    Resultados[x]=AnalizarMuestras(SubDirectorio);
                    LimpiarDistancias(false);

                }else{
                    int neutros=AnalizarNeutros(SubDirectorio);
                    if(neutros==0){
                        x=TamanhoMuestras;
                        for(int y=1;y<TamanhoMuestras;y++){
                            Resultados[y]=0;
                        }
                        Resultados[0]=1;
                    }
                }
               
       }
            
        }else{
          return null;
        }
        return Resultados;
    }
    
    private int AnalizarMuestras(String DirectorioMuestras) {
        String Archivo;
        boolean primero=true;
        int primera=0;
        int emocion=0;
        File lista_imagenes=new File(DirectorioMuestras);
        int numero_imagenes=0;
        if(lista_imagenes.exists()){
            numero_imagenes=lista_imagenes.list().length;
            if(numero_imagenes!=0){
                for(int y=0;y<numero_imagenes;y++){
                    String[] muestras = lista_imagenes.list();
                    Archivo=DirectorioMuestras+muestras[y];
                    int s=AnalizarImagen(Archivo);
                    if(s==1){
                        if(primero){
                            CalcularDistancia(false,primero);
                            primero=false;
                        }else{
                            CalcularDistancia(false,primero);
                            emocion=Emociones();
                            LimpiarDistancias(false);
                            primero=true;
                            if(emocion!=0){
                                   return emocion;
                            }
                        }
                    }
                }
                LimpiarDistancias(true);
                return emocion;
            }else{
                LimpiarDistancias(true);
                return 0;
                
            }
        }else{
            return 0;
        }
        
    }
    
    private int AnalizarNeutros(String DirectorioMuestras) {
        String Archivo;
        File lista_imagenes=new File(DirectorioMuestras);
        int numero_imagenes=0;
        if(lista_imagenes.exists()){
            numero_imagenes=lista_imagenes.list().length;
            if(numero_imagenes!=0){
                for(int y=0;y<=numero_imagenes;y++){
                    String[] muestras = lista_imagenes.list();
                    Archivo=DirectorioMuestras+muestras[y];
                    int estadoanalisis=AnalizarImagen(Archivo);
                    if(estadoanalisis==1){
                        CalcularDistancia(true,false);
                        return 1;
                    }
                }
                return 0;
            }else{
                return 0;
                
            }
        }else{
            return 0;
        }
        
    }
   
    private int AnalizarImagen(String Archivo){
        LimpiarAnalisis();
        boolean positivo;
        positivo=AnalisisRostro.Buscar(Archivo,DirectorioResultados,true); 
        if(positivo){
            AnalisisOjos.Analizar(DirectorioResultados+"Rostro.png", DirectorioResultados);
            ojo1=AnalisisOjos.getOjo1();
            tamañoojo1=AnalisisOjos.getTamaño1();
            ojo2=AnalisisOjos.getOjo2();
            tamañoojo2=AnalisisOjos.getTamaño2();
            AnalisisCejas.Analizar(DirectorioResultados+"Rostro.png",DirectorioResultados, ojo1, ojo2, tamañoojo1);
            ceja1=AnalisisCejas.getCeja1();
            ceja2=AnalisisCejas.getCeja2();
            AnalisisNariz.Analizar(DirectorioResultados+"Rostro.png",DirectorioResultados,ojo1, ojo2);
            nariz=AnalisisNariz.getNariz();
            AnalisisBoca.Analizar(DirectorioResultados+"Rostro.png", DirectorioResultados, nariz, ojo1, ojo2, tamañoojo1);
            boca=AnalisisBoca.getBoca();
            tamañoboca=AnalisisBoca.getTamaño();
            if(ceja1.x==0||ojo1.x==0||nariz.x==0||boca.x==0){
                return 0;
            }else{
                return 1;
            }
                
        }
        return 0;
    }
    
    private int Emociones(){
       Emociones identificar=new Emociones();
       int emocion1=identificar.DeterminarEmocion(distancia11/distancia13,distancia21/distancia23,distancia31/distancia33,
       distancia41/distancia43,distancia51/distancia53,distancia61/distancia63);
       identificar=new Emociones();
       int emocion2= identificar.DeterminarEmocion(distancia12/distancia13,distancia22/distancia23,distancia32/distancia33,
       distancia42/distancia43,distancia52/distancia53,distancia62/distancia63);
       if(emocion1==emocion2){
           return emocion1;
       }
        return 0;
     }
    
    public void CalcularDistancia(boolean neutro,boolean primero) {
         if(neutro){
           double P=Distancia(ojo1,ojo2);
           distancia11=P/tamañoboca.x;
           distancia21=P/tamañoojo1.x;
           distancia31=P/((Distancia(ojo1,boca)+Distancia(ojo2,boca))/2);
           distancia41=P/((Distancia(ceja1,boca)+Distancia(ceja2,boca))/2);
           distancia51=P/((Distancia(ojo1,ceja1)+Distancia(ojo2,ceja2))/2);
           distancia61=P/((Distancia(ceja1,nariz)+Distancia(ceja2,nariz))/2); 
         }else{
           if(primero){
               double P=Distancia(ojo1,ojo2);
               distancia12=P/tamañoboca.x;
               distancia22=P/tamañoojo1.x;
               distancia32=P/((Distancia(ojo1,boca)+Distancia(ojo2,boca))/2);
               distancia42=P/((Distancia(ceja1,boca)+Distancia(ceja2,boca))/2);
               distancia52=P/((Distancia(ojo1,ceja1)+Distancia(ojo2,ceja2))/2);
               distancia62=P/((Distancia(ceja1,nariz)+Distancia(ceja2,nariz))/2);
           }else{
               double P=Distancia(ojo1,ojo2);
               distancia13=P/tamañoboca.x;
               distancia23=P/tamañoojo1.x;
               distancia33=P/((Distancia(ojo1,boca)+Distancia(ojo2,boca))/2);
               distancia43=P/((Distancia(ceja1,boca)+Distancia(ceja2,boca))/2);
               distancia53=P/((Distancia(ojo1,ceja1)+Distancia(ojo2,ceja2))/2);
               distancia63=P/((Distancia(ceja1,nariz)+Distancia(ceja2,nariz))/2);
           }  
         }
    }
     
    
    
    private double Distancia(Point x1, Point x2){
        double Distancia = Math.sqrt( Math.pow((x1.x-x2.x),2)+ Math.pow((x1.y-x2.y),2));
        return Distancia;
    }
    
     private void LimpiarAnalisis(){
        AnalisisRostro=new Rostro();
        AnalisisCejas=new Cejas();
        AnalisisOjos=new Ojos();
        AnalisisNariz=new Nariz();
        AnalisisBoca=new Boca();
    }
    
    private void LimpiarDistancias(boolean distancia){
        distancia12=0;
        distancia22=0;
        distancia32=0;
        distancia42=0;
        distancia52=0;
        distancia62=0;
        if(distancia){
            distancia13=0;
            distancia23=0;
            distancia33=0;
            distancia43=0;
            distancia53=0;
            distancia63=0;
        }
        
        
    }
}
