package nphard;

import java.util.Collections;
import java.util.List;

/**
 * Created by kmarathe on 8/11/13.
 */
public class BestFitBinPacking<T> {

    /*
    public abstract static class WeightedNode implements Comparable{
        private final double score;
        private final double capacity;

        protected WeightedNode(double score, double capacity) {
            this.score = score;
            this.capacity = capacity;
        }


        public double getCapacity() {
            return capacity;
        }

        public double getScore() {
            return score;
        }

        public abstract boolean remainingCapacity();

    } */


    public static interface WeightedNode<T> extends Comparable<T> {
        T getScore();
    }

    public static interface Bin<T> extends Comparable<T> {
        T getCapacity();
        T getRemainingCapacity();
    }

    public class WeightedTask implements WeightedNode<Double> {

        private final Double score;

        public WeightedTask(Double score) {
            this.score = score;
        }


        @Override
        public Double getScore() {
            return null;
        }

        @Override
        public int compareTo(Double o) {
            return 0;
        }
    }

    //public class ExampleBin<>


    public BestFitBinPacking(List<WeightedNode<T>> items,List<Bin<T>> bins) {
        Collections.shuffle(items);
    }

    public static void main(String[] args) {
    }

}
