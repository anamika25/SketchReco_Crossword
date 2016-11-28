package learner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utilities {
	
	public static void addConnections(Neuron neuron, ArrayList<Neuron> neurons) {
        for(Neuron prevLayerNeuron : neurons) {
            prevLayerNeuron.outputNeuron.add(neuron);
            neuron.inputNeuron.add(prevLayerNeuron);
        }
    }
	
	public static double getSumFromPrev(Neuron neuron) {
		double sum = 0.0;
	    for(int i = 0; i < neuron.weights.size(); ++i){
	    	if(i == 0) {
	    		sum += (1.0 * neuron.weights.get(i));
	        } else {
	            sum += (neuron.inputNeuron.get(i-1).activationValue * neuron.weights.get(i));
	        }
	     }
	     return sum;
	}
	   
	public static void activate(Neuron neuron, double sum) {
		double num = 1.0;
		double denom = 1.0 + Math.exp(-sum);
		neuron.activationValue = (num / denom);
	}
	
    public static void updateWeightsInner(Neuron neuron, double eta) {
        for(int i = 0; i < neuron.weights.size(); ++i) {
            double deltaVal = 0.0;
            if(i == 0) {
                deltaVal = eta * neuron.delta * 1.0;
            } else {
                deltaVal = eta * neuron.delta * neuron.inputNeuron.get(i-1).activationValue;
            }
            double newWeight = neuron.weights.get(i) + deltaVal;
            neuron.weights.set(i, newWeight);
        }
    }
    
    public static double getSumFromInputs(Neuron neuron, ArrayList<Double> inputs) {
        double sum = 0.0;
        for(int i = 0; i < neuron.weights.size(); ++i) {
            if(i == 0) {
                sum += (1.0 * neuron.weights.get(i));
            } else {
                sum += (inputs.get(i-1) * neuron.weights.get(i));
            }
        }
        return sum;
    }
   
    public static void updateWeights(Neuron neuron, ArrayList<Double> inputs, double eta) {
        for(int i = 0; i < neuron.weights.size(); ++i) {
            double deltaVal = 0.0;
            if(i == 0) {
                deltaVal = eta * neuron.delta * 1.0;
            } else {
                deltaVal = eta * neuron.delta * inputs.get(i-1);
            }
            double newWeight = neuron.weights.get(i) + deltaVal;
            neuron.weights.set(i, newWeight);
        }
    }    
}
