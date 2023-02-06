package MinHeapLib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MinHeap<T> {
   
    private List<T> MinHeap = null;
    private HeapComparator<T> comparator;
    private int size; 
    private Map<T, Integer> positions; 

    /* constructor: creates a Minimum Heap data structure using an ArrayList with a generic type. A minHeap is a semi-complete
     * binary tree where the value of each node is greater or equal to the value of its parent.
     * To implement this data structure an ArrayList is used.  
     * Since its a generic MinHeap, a HeapComparator (that extends Comparator) paremeter is needed, 
     * so that the user can override the compare() function
    */
    public MinHeap(HeapComparator<T> comparator) throws MinHeapException {
        if(comparator==null) throw new MinHeapException("MinHeap constructor: comparator parameter cannot be null");
        MinHeap = new ArrayList<>();
        size = 0; 
        this.comparator = comparator;
        this.positions = new HashMap<>();
    }

    public void printHashMap(){
        System.out.println(this.positions);
    }

    /*
     * returns the value of the node in position pos
     */
    public T get(int pos) throws MinHeapException{

        if(pos>this.heapSize()-1) throw new MinHeapException("pos parameter out of bounds for the Heap");

        return MinHeap.get(pos);
    }

    /*
     * inserts the T element in the array in the correct position 
     */
    public void insert(T element) throws MinHeapException {
        MinHeap.add(element); 
        this.size++; 
        positions.put(element, this.heapSize()-1);
        int i_el = this.heapSize() - 1;
        int i_parent = parent(i_el);

        while ((this.comparator).compare(MinHeap.get(i_el), MinHeap.get(i_parent))  <   0) {
            swap(i_el, i_parent);
            i_el = i_parent;
            i_parent = parent(i_el);
        }
    }
    /*
     * returns the size of heap
     */
    public int heapSize(){
        return this.size;
    }
    /*
     * returns the index of the parent of the node that calls this function
     */
    public int parent(int pos) throws MinHeapException{ 
        if(pos>this.heapSize()-1) throw new MinHeapException("pos parameter out of bounds for the Heap");
        //i am assuming that the parent of root returns 0 / is itself index for code simplicity
        return (pos - 1) / 2;
    }
    /*
     * returns the index of the left child of the node that calls this function
     */
    public int leftChild(int pos) throws MinHeapException {

        if(pos>this.heapSize()-1) throw new MinHeapException("pos parameter out of bounds for the Heap");
        if(!this.hasLeftChild(pos)) throw new MinHeapException("element has no child");


        return (2 * pos) + 1;
    
    }
    /*
     * returns the index of the right child of the node that calls this function
     */
    public int rightChild(int pos)throws MinHeapException {
        if(pos>this.heapSize()-1) throw new MinHeapException("pos parameter out of bounds for the Heap"); 
        if(!this.hasRightChild(pos)) throw new MinHeapException("element has no child");

        return (2 * pos) + 2; 
    }
    /*
     * checks if the node that calls this function has a left child
     */
    private boolean hasLeftChild(int pos){
        if(2*pos+1> this.heapSize()-1) return false;
        else return true;
    }
    /* 
     * checks if the node that calls this function has a right child 
    */
    private boolean hasRightChild(int pos){
        if(2*pos+2> this.heapSize()-1) return false;
        else return true;
    }
    /*
     * extracts the minimum element of the heap (the root) and rearranges all the elements so that 
     * the tree respects the condition of being a mimimum heap. It does this by using the heapify() 
     * function below.
     * 
     */
    public T extractMinElem() throws MinHeapException{
        if (this.heapSize() == 0) {
            throw new MinHeapException("MinHeap is empty");
        } 
        else if (this.heapSize() == 1) {
            T min = MinHeap.remove(0);
            positions.remove(min);
            this.size--;
            return min;
        }
        T min = MinHeap.get(0);


        T lastItem = MinHeap.remove(this.heapSize() - 1);
        MinHeap.set(0, lastItem);
        positions.replace(lastItem, 0);
        positions.remove(min);
        this.size--; 
        
        
        this.heapify(0);



        return min;

    }
    
    private void heapify(int i) throws MinHeapException{     
        int left, right, smallest = i;

        if(this.hasLeftChild(i)){
            left = leftChild(i);
            if((this.comparator).compare(MinHeap.get(left), MinHeap.get(i)) < 0) smallest = left;


        } 
        
        if(this.hasRightChild(i)){
            right = rightChild(i);
            if((this.comparator).compare(MinHeap.get(right), MinHeap.get(smallest)) < 0) smallest = right;
        } 
        
        if(smallest!=i){
            swap(i, smallest);
            this.heapify(smallest);
        }       
    }
    /*
     * replaces the value of the element in position of i with the value of newVal, and then rearranges 
     * all the elements so that the tree respects the condition of being a minimum heap.
     */
    public void decreaseKey(T element, T newVal) throws MinHeapException {
        if(element == null) throw new MinHeapException("invalid element parameter");
        if(!positions.containsKey(element)) throw new MinHeapException("Element not in Heap");
        if(comparator.compare(element, newVal) < 0) throw new MinHeapException("newVal is bigger than element");

        int posElement = positions.get(element);
        positions.remove(element);
        
        MinHeap.set(posElement, newVal);
        positions.put(newVal, posElement);


        int parent = parent(posElement);

        while (posElement > 0 && (this.comparator).compare(MinHeap.get(parent), newVal) > 0) {

            swap(posElement, parent);
            posElement = parent;
            parent = parent(parent);
        }
    }

    /*
     * swaps the values of element at index i with the value of element at index j in the heap. 
     */
    private void swap(int i, int j) {
        T el1 = MinHeap.get(i);
        T el2 = MinHeap.get(j);

        MinHeap.set(j, el1);
        positions.replace(el1, j);

        MinHeap.set(i, el2);
        positions.replace(el2, i);
    }

    public void printHeap(){
        System.out.println(Arrays.deepToString(MinHeap.toArray()));

    }
}