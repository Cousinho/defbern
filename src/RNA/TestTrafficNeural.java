package RNA;

import org.neuroph.core.NeuralNetwork;

public class TestTrafficNeural {

NeuralNetwork network = NeuralNetwork.load("RNA/Sorpresa.nnet");

    public static void main(String[] args)
    {
        new TestTrafficNeural().go();
    }
 
    private void go()
    {
        calculate(1.091983993341587 ,1.124192581071625 ,1.3564322047211514 ,1.061734714409149);
    }
 
    private void calculate(double... input)
    {
        network.setInput(input);
        network.calculate();
        double[] output=null;
        output = network.getOutput();
        Double answer1 = output[0];
        Double answer2 = output[1];
        System.out.println("Negativo: "+answer1);
        System.out.println("Positivo: "+answer2);
    }    
}
