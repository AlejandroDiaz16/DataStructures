package Dictionary;

import java.util.Random;

public class SkipList implements Dictionary {

    // data members of SkipList
    protected float prob; // probability to decide level
    protected int maxLevel; // max permissible chain level
    protected int levels; // max current nonempty chain
    protected int size; // current number of elements
    protected Comparable tailKey; // a large key
    protected SkipNode headNode; // head node
    protected SkipNode tailNode; // tail node
    protected SkipNode[] last; // last node on each level
    protected Random r; // needed for random numbers

    protected static class SkipNode {
        // data members
        protected Comparable key;
        protected Object element;
        protected SkipNode[] next;
        
        // constructor
        protected SkipNode(Object theKey, Object theElement, int size) {
            key = (Comparable) theKey;
            element = theElement;
            next = new SkipNode[size];
        }
    }
    
    int level() {
        int lev = 0;
        while (r.nextFloat() <= prob) {
            lev++;
        }
        return (lev <= maxLevel) ? lev : maxLevel;
    }
    
    /**
     * create an empty skip list
     *
     * @param largeKey used as key in tail node all elements must have a smaller
     * key than this
     * @param maxElements largest number of elements to be stored in the
     * dictionary
     * @param theProb probability that element on one level is also on the next
     * level
     */
    public SkipList(Comparable largeKey, int maxElements, float theProb) {
        prob = theProb;
        maxLevel = (int) Math.round(Math.log(maxElements)/Math.log(1 / prob)) - 1;
        // size and levels have default initial value 0
        tailKey = largeKey;
        // create head & tail nodes and last array
        headNode = new SkipNode(null, null, maxLevel + 1);
        tailNode = new SkipNode(tailKey, null, 0);
        last = new SkipNode[maxLevel + 1];
        // headNode points to tailNode at all levels initially
        for (int i = 0; i <= maxLevel; i++) {
            headNode.next[i] = tailNode;
        }
        // initialize random number generator
        r = new Random();
    }

    /**
     * @return element with specified key
     * @return null if there is no matching element
     */
    public Object get(Object theKey) {
        if (tailKey.compareTo(theKey) <= 0) {
            return null;
        }
        SkipNode p = headNode;
        for (int i = levels; i >= 0; i--) {
            while (p.next[i].key.compareTo(theKey) < 0) {
                p = p.next[i];
            }
        }
        if (p.next[0].key.equals(theKey)) {
            return p.next[0].element;
        }
        return null; // no matching element
    }

    /**
     * search for theKey saving last nodes seen at each level in the array last
     *
     * @return node that might contain theKey
     */
    SkipNode search(Object theKey) {
        SkipNode p = headNode;
        for (int i = levels; i >= 0; i--) {
            while (p.next[i].key.compareTo(theKey) < 0) {
                p = p.next[i];
            }
            last[i] = p; // last level i node seen
        }
        return (p.next[0]);
    }
    
    /**
     * insert an element with the specified key overwrite old element if there
     * is already an element with the given key
     *
     * @return old element (if any) with key theKey
     * @throws IllegalArgumentException when theKey >= largeKey = tailKey
     */
    public Object put(Object theKey, Object theElement) {
        SkipNode p = search(theKey);
        if (p.key.equals(theKey)) { // update p.element
            Object elementToReturn = p.element;
            p.element = theElement;
            return elementToReturn;
        }
        int lev = level(); // level of new node
        if (lev > levels) {
            lev = ++levels;
            last[lev] = headNode;
        }
        SkipNode y = new SkipNode(theKey, theElement, lev + 1);
        for (int i = 0; i <= lev; i++) { // insert into level i chain
            y.next[i] = last[i].next[i];
            last[i].next[i] = y;
        }
        size++;
        return null;
    }
    
    /**
     * @return matching element and remove it
     * @return null if no matching element
     */
    public Object remove(Object theKey) {
        if (tailKey.compareTo(theKey) <= 0) // too large
        {
            return null;
        }
        // see if matching element present
        SkipNode p = search(theKey);
        if (!p.key.equals(theKey)) // not present
        {
            return null;
        }
        // delete node from skip list
        for (int i = 0; i <= levels && last[i].next[i] == p; i++) {
            last[i].next[i] = p.next[i];
        }
        // update Levels
        while (levels > 0 && headNode.next[levels] == tailNode) {
            levels--;
        }
        size--;
        return p.element;
    }
    
    public String toString() {
        StringBuffer s = new StringBuffer("[");
        // put elements into the buffer
        SkipNode currentNode = headNode;
        
        while (currentNode.next[0] != tailNode) {
            s.append("(" + currentNode.next[0].key.toString() + " * " + currentNode.next[0].element.toString() + "), ");
            currentNode = currentNode.next[0];
        }

        if (size > 0) {
            s.delete(s.length() - 2, s.length());
        }

        s.append("]");
        // create equivalent String
        return new String(s);
    }
    
    public static void main (String[] args) {
        
        SkipList myDic = new SkipList(100,20,0.5f);
        myDic.put(24,"a");
        myDic.put(74,"b");
        myDic.put(35,"c");
        myDic.put(52,"d");
        myDic.put(29,"e");
        
        System.out.println(myDic);
        System.out.println(myDic.get(52));
        
    }

    
}
