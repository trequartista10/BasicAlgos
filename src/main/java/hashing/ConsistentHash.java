package hashing;


import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ConsistentHash{

    public static Comparator<String> comparator = new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {

            if(o1.length() < o2.length())
                return -1;
            if(o2.length() < o1.length())
                return 1;

            return o1.compareTo(o2);
        }
    };
    private final LocalHashFunction hashFunctionX;
    private final int numberOfReplicas;
    private final SortedMap<String, String> circle = new TreeMap<String, String>(comparator);
    private static MessageDigest sha1 = null;
    private static MessageDigest sha2 = null;
    private static MessageDigest sha3 = null;

    static {
        try {
            sha1 = MessageDigest.getInstance("SHA-512");
            sha2 = MessageDigest.getInstance("MD5");
            sha3 = MessageDigest.getInstance("SHA-512");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class LocalHashFunction {

        HashFunction hf = Hashing.md5();

        String hash(Object o) {
            sha1= null;
            try {
                sha1 = MessageDigest.getInstance("SHA-512");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return byteArrayToHexString((""+Hashing.consistentHash(hf.hashString((String)o,Charset.forName("UTF8")),128)).getBytes());
            //return byteArrayToHexString((((String) o).getBytes(Charset.forName("UTF8"))));
        }

        /*
        String hash(Object o) {
            return byteArrayToHexString((""+Math.abs(o.hashCode())%256).getBytes());
        } */

        public static String byteArrayToHexString(byte[] b) {
            String result = "";
            for (int i=0; i < b.length; i++) {
                result +=
                        Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );

            }
            //System.out.println(result.length());
            return result;
        }


    }


    public ConsistentHash(LocalHashFunction hashFunctionX, int numberOfReplicas,
                          Collection<String> nodes) {


        this.hashFunctionX = hashFunctionX;
        this.numberOfReplicas = numberOfReplicas;

        for (String node : nodes) {
            add(node);
        }
    }

    static SecureRandom sr = new SecureRandom();

    public void add(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunctionX.hash(node.toString() +":"+ i), node);
        }
    }

    public void remove(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunctionX.hash(node.toString() +":"+ i));
        }
    }

    public String get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        String hash = hashFunctionX.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<String, String> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public static void printResults(Map<String,AtomicInteger> cntMap) {
        for(Map.Entry<String,AtomicInteger> entry : cntMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void updateCounts(Map<String,AtomicInteger> cntMap, String server) {
        AtomicInteger cnt = cntMap.get(server);
        if(cnt == null) {
            cnt = new AtomicInteger();
            cntMap.put(server, cnt);
        }
        cnt.incrementAndGet();
    }

    public static List<String> getServerList(int serverCount) {
        List<String> serverLst = new ArrayList<>(serverCount);
        for (int i=0;i < serverCount; i++) {
            serverLst.add("hostname"+":"+(char)(i+65)+"@foo.bar.com");
        }
        return serverLst;
    }

    public static void main(String[] args) {

        List<String> servers = getServerList(10);
        SecureRandom sr1 = new SecureRandom();
        SecureRandom wordLenRand = new SecureRandom();
        int noOfiterations =6000;
        LocalHashFunction hf = new LocalHashFunction();

        ConsistentHash chash = new ConsistentHash(hf,4,servers);

        StringBuilder builder = new StringBuilder();
        Map<String,AtomicInteger> consistentHashingCounter = new HashMap<String,AtomicInteger>();
        Map<String,AtomicInteger> simpleHashingCounter = new HashMap<String,AtomicInteger>();
        for(int i=0; i < noOfiterations; i++) {
            int len = wordLenRand.nextInt(4) + 1;
            for(int j=0; j < len;j++)
                builder.append((char) (65 + (sr1.nextInt(26))));


            String word = "TOPIC.NAME."+builder.toString();
            builder.setLength(0);
            //Update Consistent Hashing counts
            String server = chash.get(word);
            updateCounts(consistentHashingCounter,server);

            //Update simple hashing counts
            server = servers.get((int)Math.abs((double)word.hashCode())%servers.size());
            updateCounts(simpleHashingCounter,server);

        }

        //Print consistent hashing results
        System.out.println("Consistent hashing results");
        printResults(consistentHashingCounter);

        //Print Simple hashing results
        System.out.println("Simple hashing results");
        printResults(simpleHashingCounter);
    }
}
 