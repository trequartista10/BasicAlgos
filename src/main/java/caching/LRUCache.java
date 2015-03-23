package caching;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kuldeep on 3/4/15.
 */
public class LRUCache<K,V> {
    private final Map<K,Entry<K,V>> map = new HashMap<>();
    private final DoublelyLinkedList<K,V> queue = new DoublelyLinkedList<>();
    private final int maxSize;
    private int size;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    public static class Entry<K,V> {
        private K key;
        private V value;
        private Entry prev;
        private Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoublelyLinkedList<K,V> {
        Entry<K,V> head = new Entry<>(null,null);
        Entry<K,V> last = new Entry<>(null,null);

        void add(Entry<K,V> node) {
            if(head.next == null)
                head.next = node;

            if(last.next != null) {
                node.prev = last.next;
                last.next.next = node;
            }

            last.next = node;

        }

        void unlink(Entry<K,V> node) {
            if(node.next == null && node.prev == null) {
                head.next = null;
                last.next = null;
                node.next = null;
                node.prev = null;
                return;
            }


            if(node.prev != null) {
                node.prev.next = node.next;
            }
            else {
                //Must me the first node
                head.next = node.next;
            }

            if(node.next != null) {
                node.next.prev = node.prev;
            }else {
                //Must be the last node
                last.next = node.prev;
            }

            node.next = null;
            node.prev = null;
        }


        void touch(Entry<K,V> node) {
            unlink(node);
            add(node);
        }

        Iterator<Entry<K,V>> iterator() {

            return new Iterator<Entry<K,V>>() {
                Entry<K,V> entry = head;
                @Override
                public boolean hasNext() {
                    return entry.next != null;
                }

                @Override
                public Entry<K,V> next() {
                    entry = entry.next;
                    return entry;
                }

                @Override
                public void remove() {
                    Entry<K,V> temp = entry.prev;
                    unlink(entry);

                }
            };
        }

    }

    void put(K key, V val) {
        Entry<K,V> node =  map.get(key);
        if(node != null) {
            node.value = val;
            queue.touch(node);
        } else {
            if(size > maxSize) {
                Entry<K,V> entry =  queue.head.next;
                queue.unlink(queue.head.next);
                size--;
                if(entry != null)
                    map.remove(entry.key);
            }

            node = new Entry<>(key, val);
            queue.add(node);
            size++;
        }
        map.put(key,node);

    }

    V get(K key) {
        Entry<K,V> node = map.get(key);
        if(node == null) {
            return null;
        }

        queue.touch(node);
        return node.value;
    }

    V remove(K key) {
        Entry<K,V> node = map.remove(key);
        V val = null;
        if(node != null) {
            queue.unlink(node);
            val = node.value;
            size--;
        }
        return val;
    }

    void printPriority() {
        Entry<K,V> node = queue.head.next;
        if (node == null) {
            System.out.print("Queue is empty");
        }
        while(node != null) {
            System.out.print(node.value + (node.next != null ? "->":""));
            node= node.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LRUCache<String,Integer> cache = new LRUCache<>(2);
        cache.put("1",1);
        cache.printPriority();
        cache.put("2",2);
        cache.printPriority();
        cache.put("3",3);
        cache.printPriority();
        cache.put("4",4);
        cache.printPriority();
        cache.put("5",5);
        cache.printPriority();

        cache.get("1");
        cache.printPriority();
        cache.get("3");
        cache.printPriority();

        cache.get("2");
        cache.printPriority();
        cache.get("5");
        cache.printPriority();
        cache.get("5");
        cache.printPriority();
        cache.get("6");
        cache.printPriority();

        cache.remove("5");
        cache.printPriority();
        cache.remove("9");
        cache.printPriority();
        cache.remove("2");
        cache.printPriority();
        cache.remove("1");
        cache.printPriority();
        cache.remove("4");
        cache.printPriority();
        cache.remove("3");
        cache.printPriority();
        cache.get("7");
        cache.printPriority();


    }

}
