package com.example.softwaredesignproject4;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.stream.Collectors;

public class HistogramAlphaBet {

    Map<Character, Integer> frequency = new HashMap<Character, Integer>();
    Map<Character, Double> probability = new HashMap<Character, Double>();

    HistogramAlphaBet(){};
    HistogramAlphaBet(Map<Character, Integer> n) {
        frequency.putAll(n);
    }
    HistogramAlphaBet(String text) {
        String w = text.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for(int i = 0; i < w.length(); i++) {
            Character key = w.charAt(i);
            incrementFrequency(frequency, key);
        }
    }

    public Map<Character, Integer> getFrequency() {
        return frequency;
    }
    public Integer getCumulativeFrequency() {
        return frequency.values().stream().reduce(0, Integer::sum);
    }


    public Map<Character, Integer> sortUpFrequency() {

        return frequency.
                entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Map<Character, Double> sortUpProbability() {

        return getProbability().
                entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }


    public Map<Character, Integer> sortDownFrequency() {

        return frequency.entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Map<Character, Double> sortDownProbability() {

        return getProbability().entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }


    public Map<Character, Double> getProbability() {
        double inverseCumulativeFrequency = 1.0 / getCumulativeFrequency();
        for (Character Key : frequency.keySet()) {
            probability.put(Key, (double) frequency.get(Key) * inverseCumulativeFrequency);
        }

        return probability.entrySet().stream().
                sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public double getSumOfProbability() { return probability.values().stream().reduce(0.0, Double::sum); }

    public String toString() {
        String output = "Frequency of Characters\n\n";
        for(Character K : frequency.keySet()) {
            output += K + ": " + frequency.get(K) + "\n";
        }

        return output;
    }

    private static<K> void incrementFrequency(Map<K, Integer> n, K Key) {
        n.putIfAbsent(Key, 0);
        n.put(Key, n.get(Key) + 1);
    }

    //Inner Class
    class MyPieChart {

        Map<Character, Slice> slices = new HashMap<Character, Slice>();

        int N;
        MyPoint center;
        int radius;
        double startAngle;

        MyPieChart(int n, MyPoint p, int r, double startAngle) {

            this.N = n;
            this.center = p;
            this.radius = r;
            this.startAngle = startAngle;

            probability = getProbability();
            slices = getMyPieChart();
        }

        public Map<Character, Slice> getMyPieChart() {
            myColor [] colors = myColor.getMyColors();

            Map<Character, Double> sortedProbability = sortDownProbability();

            Random rand = new Random();
            int colorsSize = colors.length;
            double StartAngle = startAngle;
            for(Character Key : sortedProbability.keySet()) {
                double angle = 360.0 * probability.get(Key);
                slices.put(Key, new Slice(center, radius, StartAngle, angle, colors[rand.nextInt(colorsSize)]));
                StartAngle += angle;
            }

            return slices;
        }

        public void draw(GraphicsContext GC) {
            Map<Character, Integer> sortedFrequency = sortDownFrequency();
            int i = 0;
            for(Character Key : sortedFrequency.keySet()) {
                if( i < this.N ) {
                    slices.get(Key).draw(GC);
                    System.out.println(Key + ": " + slices.get(Key));
                    slices.get(Key).stroke(GC);
                    slices.get(Key).drawRect(GC, i);
                    slices.get(Key).strokeRect(GC, i);
                    slices.get(Key).strokeText(GC, i, String.valueOf(Key));
                }
                else{
                    Slice s = new Slice(center, radius, slices.get(Key).getStartAngle(), slices.get(Key).getAngle(), null);
                    s.uniqueDraw(GC);
                    s.uniqueStroke(GC);
                    slices.get(Key).uniqueDrawRect(GC, N);
                    slices.get(Key).uniqueStrokeRect(GC, N);
                    slices.get(Key).uniqueStrokeText(GC, N, "other");
                }
                i++;
                //System.out.println(slices.get(Key).toString() + " " + Key);
            }
            i = 0;
        }
         /*
        public void drawSortedFrequency(GraphicsContext GC, Map<Character, Integer> SortedFrequency) {
            int i = 0;
            for(Character Key : SortedFrequency.keySet()) {
                if( i < this.N ) {
                    //System.out.println(slices.get(Key).toString() + " " + Key);
                    slices.get(Key).draw(GC);
                    slices.get(Key).stroke(GC);
                    slices.get(Key).drawRect(GC, i);
                    slices.get(Key).strokeRect(GC, i);
                    slices.get(Key).strokeText(GC, i, String.valueOf(Key));
                }
                else{
                    Slice s = new Slice(center, radius, slices.get(Key).getStartAngle(), slices.get(Key).getAngle(), null);
                    s.uniqueDraw(GC);
                    s.uniqueStroke(GC);
                }
                i++;
                System.out.println(slices.get(Key).toString() + " " + Key);
            }
            i = 0;
        }
        */

    }

}
