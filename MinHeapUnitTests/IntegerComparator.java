package MinHeapUnitTests;

import MinHeapLib.*;

public class IntegerComparator implements HeapComparator<Integer> {

    @Override
    public int compare(Integer x, Integer y){
        int result = Integer.valueOf(x).compareTo(y);
        return result; 
    }

}   
