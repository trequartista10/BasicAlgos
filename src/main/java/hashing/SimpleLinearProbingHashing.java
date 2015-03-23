package hashing;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/20/14
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleLinearProbingHashing<K,V> {

    public static class Pair<K,V> {
        private final K key;
        private final V val;
        private Pair next;

        Pair(K key, V val) {
            this.key = key;
            this.val = val;
        }


        public V getVal() {
            return val;
        }

        public K getKey() {
            return key;
        }
    }

    private int capacity = 12; //Say some default value
    private int hashingBase = 31;
    private Pair[] pairs;// = new Pair[capacity];
    private double loadFactor = 0.75;

    SimpleLinearProbingHashing(int capacity, double loadFactor) {
        this(capacity);
        this.loadFactor = loadFactor;
    }

    SimpleLinearProbingHashing(int allocSize) {
        this.capacity = allocSize;
    }

    SimpleLinearProbingHashing() {
        pairs = new Pair[capacity];
    }


    private int getBucketIndex(String smeString) {
        char[] arr = smeString.toCharArray();
        int totalHash = 0; //Make sure this does not overflow
        for (int i=0; i < arr.length; i++) {
            totalHash = totalHash + (int)(Math.pow(31,i) * arr[i]);
        }

        return totalHash% capacity;
    }

    void put(K key, V val) {
         Pair<K,V> p = new Pair(key, val);
        int idx = getBucketIndex((String)p.key);
        if(pairs[idx] == null)
            pairs[idx] = p;
        else {
            //Use the strategy here


            HashMap map = new HashMap();
            //map.put()
        }

    }

}
