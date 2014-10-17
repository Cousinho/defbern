/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Analisis;

import BaseDeDatos.BDPerfiles;
import Entidades.Registro;
import java.sql.SQLException;

/**
 *
 * @author jose
 */

//G.F+.T.V* G.F+.T.V*,NADA,NADA

public class AnalisisRorschach {
    private Registro registro_actual;
    private String[] respuestas;
    private String[] nomenclaturas;
    
    /*
    NR ->Numero de respuestas totales
    
    
    B  ->Respuestas de movimiento
    B+ ->Respuestas de movimiento que guardan relacion con la lamina
    B- ->Respuestas de movimiento que no guardan relacion con la lamina
    C  ->Respuestas de color puro
    D  ->Respuestas de detalle
    Dd ->Respuestas de detalle pequeño
    F  ->Respuestas de forma
    F+ ->Respuestas de forma que guardan relacion con la lamina
    F- ->Respuestas de forma que no guardan relacion con la lamina
    G  ->Respuestas Globales
    M  ->Respuestas de figuras humanas completas
    Md ->Respuestas de partes del ser humano
    T  ->Respuestas globales de un animal
    Td ->Respuestas de parte de un animal
    V* ->Respuestas obvias en cada lamina, se usa para determinar el indice de realidad
    Orig ->Respuestas originales
    Orig+ ->Respuestas originales que guardan relacion con la lamina
    Orig- ->Respuestas originales que no guardan relacion con la lamina
   
    
    Abstr ->Respuestas abstractas
    Anat->Respuestas Respuestas de anatomia humana
    Sex->Respuestas en que se integra de alguna manera lo sexual
    Geo->Respuestas que involucren accidentes geograficos
    Obj->Respuestas de objetos en gral.
    Mascara->Respuestas de mascaras o disfraces
    Sangre->Respuestas que involucren sangre
    Fuego->Respuestas de fuego, llamas
    Refl->Respuestas en que una mitad de la lamina es considerada el reflejo de la otra
    Simetria->Su nombre lo indica
    Pinza, abismo->Sus nombres lo indican
    Arq->Respuestas que involucren construccion
    
    */
    private double C,M;
    private int G,D,Dd,Fmas,Fmenos,Md,Origmas,Origmenos,Bmas,Bmenos/*,H,Hd,*/,
                A,Ad,NR,V,T,Td,Anat,Geo,GAbs,Simetria,Abstr,Sex,Obj,Mascara,Sangre,Refl,Fuego,Pinza,Arq;
    private int Gp,Dp,Bmasp,Fmasp,Bmenosp,Fp,IR,Tp,Bp,Origp;
    private int Origd,Origg,Origmenosp;
    private int ChoqueNegro, ChoqueColor,ChoqueRojo,ChoqueKinestesico,ChoqueSexual,ChoqueLVI;
    private String perfil;
    private String vivencia;
    private Analisis analizar;
    private int[] choques;
    
    
    public String AnalizarRegistro(Registro registro, int duracion) throws SQLException{
        registro_actual = registro;
        for(int i=0;i<10;i++){
            respuestas = registro_actual.getRespuestas()[i].split(" ");
            for(int j=0;i<respuestas.length;j++){
                nomenclaturas = respuestas[j].split(",");
                AnalizarRespuesta(nomenclaturas,j);
                NR = NR++;
            }
        }
        choques = analizar.Analizar(registro_actual.getCodigo());
        Gp = (G*100)/NR;
        Dp = (D*100)/NR;
        Fmasp = (Fmas*100)/(Fmas+Fmenos);
        Fp = ((Fmas+Fmenos)*100)/NR;
        //Hp = ((H+Hd)*100)/NR;
        Bmasp = (Bmas*100)/NR;
        Bmenosp = (Bmenos*100)/NR;
        Tp = ((T+Td)*100)/NR;
        Origp = ((Origmas+Origmenos)*100)/NR;
        Origmenosp = (Origmenos*100)/(Origmenos+Origmas);
        
        
        //Analisis de nomenclaturas
        if(Math.abs(C-M)!=0.5){
            perfil = perfil + BDPerfiles.buscarId(3).getDescripcion();
            vivencia = "ambigual";
        }else{
            if(C>M){
                perfil = perfil + BDPerfiles.buscarId(1).getDescripcion();
                vivencia = "extratensivo";
            }else{
                perfil = perfil + BDPerfiles.buscarId(2).getDescripcion();
                vivencia = "introtensivo";
            }
        }
        
        if(G<33){
            perfil = perfil + BDPerfiles.buscarId(4).getDescripcion();
        }else if(G>80){
            perfil = perfil + BDPerfiles.buscarId(5).getDescripcion();
        }
        
        if(Dd>0){
            perfil = perfil + BDPerfiles.buscarId(6).getDescripcion();
        }
        
        if(Dp<=75){
            perfil = perfil + BDPerfiles.buscarId(7).getDescripcion();
            if(Dp==0){
                perfil = perfil + BDPerfiles.buscarId(7).getDescripcion();
            }
        }else if(Dp>75){
            perfil = perfil + BDPerfiles.buscarId(9).getDescripcion();
        }
        
        if(M<=3){
            perfil = perfil + BDPerfiles.buscarId(10).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(11).getDescripcion();
        }
        
        if(Tp>=30 && Tp<=50){
            perfil = perfil + BDPerfiles.buscarId(12).getDescripcion();
        }else if(Tp<30){
            perfil = perfil + BDPerfiles.buscarId(13).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(14).getDescripcion();
        }
        
        if(Td>0){
            perfil = perfil + BDPerfiles.buscarId(15).getDescripcion();
        }
        
        if(Fmasp>=80 && Fmasp<=90){
            perfil = perfil + BDPerfiles.buscarId(16).getDescripcion();
        }else{
            if(Fmasp>70 && Fmasp<80){
                perfil = perfil + BDPerfiles.buscarId(17).getDescripcion();
            }else{
                if(Fmasp>=60 && Fmasp<=70){
                    perfil = perfil + BDPerfiles.buscarId(18).getDescripcion();
                }else{
                    if(Fmasp<60){
                        perfil = perfil + BDPerfiles.buscarId(19).getDescripcion();
                    }else{
                        perfil = perfil + BDPerfiles.buscarId(20).getDescripcion();
                    }
                }
            }
        }
        
        if(IR<=1){
            perfil = perfil + BDPerfiles.buscarId(23).getDescripcion();
        }else if(IR==2){
            perfil = perfil + BDPerfiles.buscarId(24).getDescripcion();
        }else if(IR==3){
            perfil = perfil + BDPerfiles.buscarId(25).getDescripcion();
        }else if(IR==4){
            perfil = perfil + BDPerfiles.buscarId(26).getDescripcion();
        }else if(IR>4 && IR<=7){
            perfil = perfil + BDPerfiles.buscarId(27).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(28).getDescripcion();
        }
    
        if(Bmas<=1){
            perfil = perfil + BDPerfiles.buscarId(29).getDescripcion();
        }else if(Bmas>1 && Bmas<5){
            perfil = perfil + BDPerfiles.buscarId(30).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(31).getDescripcion();
        }
        
        if(Origp>=40 && Origp<=60){
            perfil = perfil + BDPerfiles.buscarId(32).getDescripcion();
        }else if(Origp<40){
            perfil = perfil + BDPerfiles.buscarId(33).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(34).getDescripcion();
        }
        
        //Análisis de determinantes
        if(Anat<12){
            perfil = perfil + BDPerfiles.buscarId(35).getDescripcion();
        }else{
            perfil = perfil + BDPerfiles.buscarId(35).getDescripcion();
        }
        if(Abstr>0){
            perfil = perfil + BDPerfiles.buscarId(37).getDescripcion();
        }
        if(Sex>0){
            perfil = perfil + BDPerfiles.buscarId(38).getDescripcion();
        }
        if(Geo>0){
            perfil = perfil + BDPerfiles.buscarId(39).getDescripcion();
        }
        if(Obj>0){
            perfil = perfil + BDPerfiles.buscarId(40).getDescripcion();
        }
        if(Mascara>0){
            perfil = perfil + BDPerfiles.buscarId(41).getDescripcion();
        }
        if(Sangre>0){
            perfil = perfil + BDPerfiles.buscarId(42).getDescripcion();
        }
        if(Fuego>0){
            perfil = perfil + BDPerfiles.buscarId(43).getDescripcion();
        }
        if(Refl>0){
            perfil = perfil + BDPerfiles.buscarId(44).getDescripcion();
        }
        if(Simetria>0){
            perfil = perfil + BDPerfiles.buscarId(45).getDescripcion();
        }
        if(Pinza>0){
            perfil = perfil + BDPerfiles.buscarId(46).getDescripcion();
        }
        if(Arq>0){
            perfil = perfil + BDPerfiles.buscarId(47).getDescripcion();
        }
        
        
        //Determinación de los tres tipos verdaderos de inteligencia, según Rorschach
        //La aptitud abstracta-teórica
        if(GAbs>5 && 
                (Fmasp>=85 && Fmasp<=95) && 
                (Bmas>=2 && Bmas<=5) &&
                (Origp>=20 && Origp<=30) &&
                (V>=3 && V<=5) &&
                (Tp>=35 && Tp<=45) &&
                (vivencia.equals("ambigual"))){
            
                perfil = perfil + BDPerfiles.buscarId(48).getDescripcion();
        
        }
        //La aptitud práctica es
        if(vivencia.equals("extratensivo") &&
                (A>Ad) &&
                (Origd>Origg)){
            
                perfil = perfil + BDPerfiles.buscarId(49).getDescripcion();
            
        }
        if(vivencia.equals("ambigual")&&
                (GAbs>5)&&
                (Fmasp>=85 && Fmasp<=95)&&
                (Bmas>=2 && Bmas<=5) &&
                (Simetria>0)&&
                (Origmas>=5)&&
                (V>=3 && V<=5)&&
                (Tp>=30 && Tp<=40)&&
                (T>Td)){
            
                perfil = perfil + BDPerfiles.buscarId(50).getDescripcion();
        
        }
        //La aptitud artística es
        if(NR>30 &&
                Bmas>5 &&
                (Fmasp>=85 && Fmasp<=95)&&
                Origp>=30 &&
                (V>=2 && V<=5) &&
                (Tp<=30) &&
                (vivencia.equals("ambigual"))){
                
            perfil = perfil + BDPerfiles.buscarId(51).getDescripcion();
        }
        
        //Análisis de oligofrenia

        if((duracion>60 && NR<=25) || 
                (Fmasp>=45 && Fmasp<=60) &&
                (Tp>=60) &&
                (Bmas<=1) &&
                (IR<=4) &&
                (Origmenosp>=30 && Origmenosp<=40)&&
                (C<=4)){
            //Debiles de inteligencia            
            perfil = perfil + BDPerfiles.buscarId(52).getDescripcion();
        
        }else if((duracion>60 && NR<=25) || 
                (Fmasp>=0 && Fmasp<=45) &&
                (Tp>=60) &&
                (Bmas<=1) &&
                (IR<=4) &&
                (Origmenosp>=40 && Origmenosp<=70)&&
                (C<=4)){
            //Bajos de inteligencia
            perfil = perfil + BDPerfiles.buscarId(53).getDescripcion();
        }
        
        //Análisis de los choques
        if(ChoqueNegro>0 || (choques[0]!=0 || choques[1]!=0 || choques[2]!=0 || choques[3]!=0 || choques[4]!=0 || choques[5]!=0 || choques[6]!=0)){
            perfil = perfil + BDPerfiles.buscarId(54).getDescripcion();
        }
        if(ChoqueRojo>0 || (choques[1]!=0 || choques[2]!=0)){
            perfil = perfil + BDPerfiles.buscarId(55).getDescripcion();
        }
        if(ChoqueLVI>0 || choques[5]!=0){
            perfil = perfil + BDPerfiles.buscarId(56).getDescripcion();
        }
        if(ChoqueColor>0 || (choques[7]!=0 || choques[8]!=0 || choques[9]!=0)){
            perfil = perfil + BDPerfiles.buscarId(57).getDescripcion();
        }
        if(ChoqueSexual>0 || (choques[0]!=0 || choques[1]!=0 || choques[2]!=0 || choques[3]!=0 || choques[5]!=0 || choques[6]!=0 || choques[8]!=0)){
            perfil = perfil + BDPerfiles.buscarId(58).getDescripcion();
        }
        if(ChoqueKinestesico>0 || (choques[0]!=0 || choques[1]!=0 || choques[2]!=0 || choques[8]!=0)){
            perfil = perfil + BDPerfiles.buscarId(59).getDescripcion();
        }
        
        //Resultado del analisis
        return perfil;
    }

    private void AnalizarRespuesta(String[] nomenclaturas, int lamina) {
        for(int i=0;i<nomenclaturas.length;i++){
            if(nomenclaturas[i].contains("G")){
                G++;
                if(nomenclaturas[i].contains("Orig")){
                    Origg++;
                }
            }
            if(nomenclaturas[i].contains("G")&&nomenclaturas[i].contains("Abstr")){
                GAbs++;
            }
            if(nomenclaturas[i].contains("D")||nomenclaturas[i].contains("Dd")){
                D++;
                if(nomenclaturas[i].contains("Dd")){
                    Dd++;
                }
                if(nomenclaturas[i].contains("Orig")){
                    Origd++;
                }
            }
            if(nomenclaturas[i].contains("M")||nomenclaturas[i].contains("Md")){
                M++;
                if(nomenclaturas[i].contains("Md")){
                    Md++;
                }
            }
            if(nomenclaturas[i].contains("F+")){
                Fmas++;
            }
            if(nomenclaturas[i].contains("F-")){
                Fmenos++;
            }
            if(nomenclaturas[i].contains("Orig+")){
                Origmas++;
            }
            if(nomenclaturas[i].contains("Orig-")){
                Origmenos++;
            }
            if(nomenclaturas[i].contains("T")){
                T++;
            }
            if(nomenclaturas[i].contains("Td")){
                Td++;
            }
            if(nomenclaturas[i].contains("CF")){
                C++;
            }
            if(nomenclaturas[i].contains("FC")){
                C= C+0.5;
            }
            if(nomenclaturas[i].contains("C")){
                C= C+1.5;
            }
            if(nomenclaturas[i].contains("B+")){
                Bmas++;
            }
            if(nomenclaturas[i].contains("B-")){
                Bmenos++;
            }
            if(nomenclaturas[i].contains("Anat")){
                Anat++;
            }
            if(nomenclaturas[i].contains("V*")){
                if(i==0){
                    IR = IR+2;
                }else{
                    IR++;
                }
                V++;
            }
            if(nomenclaturas[i].contains("Geo")){
                Geo++;
            }
            if(nomenclaturas[i].contains("Simetria")){
                Simetria++;
            }
            if(nomenclaturas[i].contains("Abstr")){
                Abstr++;
            }
            if(nomenclaturas[i].contains("Sex")){
                Sex++;
            }
            if(nomenclaturas[i].contains("Obj")){
                Obj++;
            }
            if(nomenclaturas[i].contains("Mascara")){
                Mascara++;
            }
            if(nomenclaturas[i].contains("Sangre")){
                Sangre++;
            }
            if(nomenclaturas[i].contains("Refl")){
                Refl++;
            }
            if(nomenclaturas[i].contains("Fuego")){
                Fuego++;
            }
            if(nomenclaturas[i].contains("Pinza")){
                Pinza++;
            }
            if(nomenclaturas[i].contains("Arq")){
                Arq++;
            }
            if(nomenclaturas[i].contains("Nada") && (lamina+1>0 && lamina+1<8)){
                ChoqueNegro++;
            }
            if(nomenclaturas[i].contains("Nada") && (lamina+1==2 || lamina+1==3)){
                ChoqueRojo++;
            }
            if(nomenclaturas[i].contains("Nada") && (lamina+1==6)){
                ChoqueLVI++;
            }
            if(nomenclaturas[i].contains("Nada") && (lamina+1==8 || lamina+1==9 || lamina+1==10)){
                ChoqueColor++;
            }
            if((nomenclaturas[i].contains("Nada") || nomenclaturas[i].contains("Sex")) && (lamina+1==1 || lamina+1==2 || lamina+1==3 || lamina+1==4 || lamina+1==6 || lamina+1==7 || lamina+1==9)){
                ChoqueSexual++;
            }
            if((nomenclaturas[i].contains("Nada") || nomenclaturas[i].contains("B")==false) && (lamina+1==1 || lamina+1==2 || lamina+1==3 || lamina+1==9)){
                ChoqueKinestesico++;
            }
        }
        
        
        
        
    }
    
}
