import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.*;

public class EventPriorityModel {

    private static final int FEATURES_COUNT = 1000; // Number of features for the bag-of-words model
    private static final int PRIORITY_COUNT = 3;   // Number of priority levels (e.g., low, medium, high)

    private Map<String, Integer> wordIndex = new HashMap<>(); // Dictionary for word indexing
    private MultiLayerNetwork model; // Neural network model

    public static void main(String[] args) {
        // Initialize and train the priority model
        EventPriorityModel priorityModel = new EventPriorityModel();
        priorityModel.trainModel();

        // Example usage: create an event and predict its priority
        Event event = new Event("1", "Meeting", LocalDate.now(), "Office", "Important meeting with the CEO");
        priorityModel.predictPriority(event);
        System.out.println(event);
    }

    // Constructor initializes the neural network model
    public EventPriorityModel() {
        this.model = createModel();
    }

    // Method to train the neural network model
    public void trainModel() {
        List<DataSet> trainingData = createTrainingData();
        ListDataSetIterator<DataSet> iterator = new ListDataSetIterator<>(trainingData, 10);

        // Train the model with the training data
        for (int i = 0; i < 100; i++) {
            iterator.reset();
            model.fit(iterator);
        }
    }

    // Method to create sample training data
    private List<DataSet> createTrainingData() {
        List<DataSet> list = new ArrayList<>();

        // Sample training data with descriptions and corresponding priorities
        String[] descriptions = {
                "Important meeting with the CEO",
                "Lunch with a friend",
                "Project deadline approaching",
                "Casual team hangout",
                "Urgent client call"
        };

        int[] priorities = {2, 0, 2, 0, 2}; // 2 = high, 0 = low

        // Convert descriptions to features and create datasets
        for (int i = 0; i < descriptions.length; i++) {
            INDArray features = createFeatures(descriptions[i]);
            INDArray labels = createLabels(priorities[i]);
            list.add(new DataSet(features, labels));
        }

        return list;
    }

    // Method to create feature vectors from descriptions using bag-of-words
    private INDArray createFeatures(String description) {
        INDArray features = Nd4j.zeros(FEATURES_COUNT);
        String[] words = description.split(" ");

        // Convert words to feature indices
        for (String word : words) {
            int index = wordIndex.computeIfAbsent(word, k -> wordIndex.size());
            if (index < FEATURES_COUNT) {
                features.putScalar(index, features.getDouble(index) + 1);
            }
        }

        return features;
    }

    // Method to create label vectors for priorities
    private INDArray createLabels(int priority) {
        INDArray labels = Nd4j.zeros(PRIORITY_COUNT);
        labels.putScalar(priority, 1);
        return labels;
    }

    // Method to configure and create the neural network model
    private MultiLayerNetwork createModel() {
        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .seed(123)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(0.1))
                .list()
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                        .nIn(FEATURES_COUNT)
                        .nOut(PRIORITY_COUNT)
                        .activation(Activation.SOFTMAX)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(config);
        model.init();
        model.setListeners(new ScoreIterationListener(10)); // Print score every 10 iterations
        return model;
    }

    // Method to predict and set the priority of an event based on its description
    public void predictPriority(Event event) {
        INDArray features = createFeatures(event.getDescription());
        INDArray output = model.output(features);
        int priority = Nd4j.argMax(output, 1).getInt(0); // Get the predicted priority
        event.setPriority(priority); // Set the predicted priority to the event
    }
}
