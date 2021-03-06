package RNA;

import org.neuroph.core.NeuralNetwork;

public class Emociones {
    private int emocion = 0;
    NeuralNetwork sorpresa = NeuralNetwork.load("RNA/NeuronaSorpresa.nnet");
    NeuralNetwork enojo = NeuralNetwork.load("RNA/NeuronaEnojo.nnet");
    NeuralNetwork felicidad = NeuralNetwork.load("RNA/NeuronaFelicidad.nnet");
    private double diferencia=0.45;
    public static void main(String[] args) {
    }
    public int DeterminarEmocion(double valor1,double valor2,double valor3,double valor4,double valor5,double valor6){
            System.out.println(valor1+" "+valor2+" "+valor3+" "+valor4+" "+valor5+" "+valor6);
            go(valor1,valor2,valor3,valor4,valor5,valor6);
            return emocion;
    }
    private void go(double valor1,double valor2,double valor3,double valor4,double valor5,double valor6)
    {
        int sor=CalcularSorpresa(valor3, valor4, valor5, valor6);
        int eno=CalcularEnojo(valor3, valor4, valor5, valor6);
        int fel=CalcularFelicidad(valor1,valor2,valor3, valor4, valor5, valor6);
        if(eno==1){
            emocion=2;
            System.out.println("enojo: "+valor1+" "+valor2+" "+valor3+" "+valor4+" "+valor5+" "+valor6);
        }
        if(sor==1){
            emocion=1;
            System.out.println("sorpresa: "+valor1+" "+valor2+" "+valor3+" "+valor4+" "+valor5+" "+valor6);
        }
        if(fel==1){
           System.out.println("felicidad: "+valor1+" "+valor2+" "+valor3+" "+valor4+" "+valor5+" "+valor6);
            emocion=3;
        }
        
    }
 
    private int CalcularSorpresa(double... input)
    {
        sorpresa.setInput(input);
        sorpresa.calculate();
        double[] output=null;
        output = sorpresa.getOutput();
        Double answer1 = output[0];
        Double answer2 = output[1];
        if(answer1<answer2&&Math.abs(answer1-answer2)>diferencia){
            return 1;
        }else {
            return 0;
        }
    }
    private int CalcularEnojo(double... input)
    {
        enojo.setInput(input);
        enojo.calculate();
        double[] output=null;
        output = enojo.getOutput();
        Double answer1 = output[0];
        Double answer2 = output[1];
        if(answer1<answer2&&Math.abs(answer1-answer2)>diferencia){
            return 1;
        }else {
            return 0;
        }
    }
    
    private int CalcularFelicidad(double... input)
    {
        felicidad.setInput(input);
        felicidad.calculate();
        double[] output=null;
        output = felicidad.getOutput();
        Double answer1 = output[0];
        Double answer2 = output[1];
        if(answer1<answer2&&Math.abs(answer1-answer2)>diferencia){
            return 1;
        }else {
            return 0;
        }
    }    
}
