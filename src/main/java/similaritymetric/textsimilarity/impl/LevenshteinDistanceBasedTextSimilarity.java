package similaritymetric.textsimilarity.impl;

import org.apache.commons.text.similarity.LevenshteinDistance;
import similaritymetric.textsimilarity.TextSimilarityMetric;

public class LevenshteinDistanceBasedTextSimilarity implements TextSimilarityMetric {

    @Override
    public double compute(String firstText, String secondText) {
        boolean areTextsEqual = firstText.equals(secondText);

        if (areTextsEqual) {
            return 1;
        }

        int longerStringLength = Math.max(firstText.length(), secondText.length());
        int levenshteinDistance = LevenshteinDistance.getDefaultInstance().apply(firstText, secondText);
        return (longerStringLength - levenshteinDistance)/ (double) longerStringLength;
    }
}