package MinHeapLib;
import java.util.Comparator;

public interface HeapComparator<T> extends Comparator<T>{

    int compare(T elem1, T elem2);



}

