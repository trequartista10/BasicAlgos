package trees;

/**
 * Created by kuldeep on 3/13/15.
 */
public class RedBlackTree<K extends Comparable, V> {
    
    RedBlackNode<K,V> root = null;
    
    public static final class RedBlackNode <K extends Comparable, V> implements Comparable <RedBlackNode<K,V>>{
        
        @Override
        public int compareTo(RedBlackNode<K, V> o) {
            return this.key.compareTo(o.key);
        }

        static enum COLOR {RED, BLACK};
        static final int LEFT = 0;
        static final int RIGHT = 0;
        
        final K key;
        final V value;
        final RedBlackNode[] children = new RedBlackNode[2];        
        COLOR color;
        
        
        int heightSoFar; //Just for debugginh
        
        public RedBlackNode(K key,V value) {
            this.key = key;
            this.value = value;
            color = COLOR.BLACK;
            heightSoFar = 0;
        }

        public RedBlackNode(K key, V value, RedBlackNode left, RedBlackNode right) {
            this(key, value);
            children[LEFT] = left;
            children[RIGHT]  = right;           
        }       
    }
    
    void copyNodeMeta(RedBlackNode<K,V> from, RedBlackNode<K,V> to) {
        to.children[RedBlackNode.LEFT] = from.children[RedBlackNode.LEFT];
        to.children[RedBlackNode.RIGHT] = from.children[RedBlackNode.RIGHT];
        to.color = from.color;        
    }
    
    void add(K key, V value) {
        RedBlackNode<K,V> node = new RedBlackNode<>(key,value);
        if(root == null) {
            root = node;
            return;
        } else if(node.compareTo(root) == 0) {
            copyNodeMeta(root,node);            
            return;
        }
        
        root = add(root, node);
        root.color = RedBlackNode.COLOR.BLACK;
        //Do Color checks
    }
    
    RedBlackNode add(RedBlackNode<K,V> current, RedBlackNode<K,V> node)  {
        if(current == null) return node;
        
        int side = node.compareTo(current);
        if(side == 0)
            copyNodeMeta(current, node);
        else if(side < 0)
            current.children[RedBlackNode.LEFT] = add(current.children[RedBlackNode.LEFT],node);
        else
            current.children[RedBlackNode.LEFT] = add(current.children[RedBlackNode.LEFT],node);
        
        //if(isRed(current.children[RedBlackNode.RIGHT) &&)
        
        if(isRed(current.children[RedBlackNode.LEFT]) && isRed(current.children[RedBlackNode.RIGHT]))
          current = flipColors(current);
        
        
        
        
        return current;
        
    }
    
    boolean isRed(RedBlackNode<K,V> node) {
        return  (node != null && node.color.compareTo(RedBlackNode.COLOR.RED) == 0);
        
    }
    
    RedBlackNode<K,V> flipColors(RedBlackNode<K,V> node) {
        
        return node;
        
    }
    
    V get(K key) {
        if(key == null)
            return null;
        
        RedBlackNode<K,V> node = new RedBlackNode<>(key,null);
        RedBlackNode<K,V> current = root;
        while (current != null) {
            int c = node.key.compareTo(current.key);
            if (c == 0)
                return current.value;
            else if(c < 0)
                current = current.children[RedBlackNode.LEFT];
            else
                current = current.children[RedBlackNode.RIGHT];
        }                  
        return null;    
    }
    
    

    public static void main(String[] args) {
        
    }
}
