package numbersimilarity;

public interface NumberSimilarityMetric{

    double compute(double firstNumber, double secondNumber, double threshold);
}