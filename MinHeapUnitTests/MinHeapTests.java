package MinHeapUnitTests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import MinHeapLib.MinHeap;
import MinHeapLib.MinHeapException;




public class MinHeapTests {

    private Integer i1, i2, i3, i4, i5, i6, i7;
    private MinHeap<Integer> heap;

    @Before
    public void createMinHeap() throws MinHeapException{
        i1 = 3;
        i2 = 14; 
        i3 = 32; 
        i4 = 12;
        i5 = 11; 
        i6 = 1; 
        i7 = 4;
        heap = new MinHeap<>(new IntegerComparator());
    }

    @Test
    public void testInsert() throws MinHeapException{

        boolean checkElements = true;

        ArrayList<Integer> expected_array = new ArrayList<>();
        expected_array.add(i6);
        expected_array.add(i5);
        expected_array.add(i1);
        expected_array.add(i2);
        expected_array.add(i4);
        expected_array.add(i3);
        expected_array.add(i7);


        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        for(int i = 0; i<7;i++){
            if(heap.get(i)!=expected_array.get(i)) checkElements = false;
        }

        assertTrue(checkElements);
    }

    @Test
    public void testHeapSize() throws MinHeapException{
        Integer expectedSize = 7; 

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        assertTrue(expectedSize==heap.heapSize());
        
    }


    @Test
    public void testParent() throws MinHeapException{
        int expecteParentValue = 11;

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        assertTrue(heap.get(heap.parent(4))==expecteParentValue);

    }
    
    @Test
    public void testRootParent() throws MinHeapException{

        int expectedParentValue = 1;

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        assertTrue(heap.get(heap.parent(0))==expectedParentValue);
    }

    @Test
    public void testLeftChild() throws MinHeapException{
        
        int expectedLeftChildValue = 32;

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        assertTrue(heap.get(heap.leftChild(2))==expectedLeftChildValue);

    }

    @Test
    public void testRightChild() throws MinHeapException{
    
        int expectedLeftChildValue = 12;

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        assertTrue(heap.get(heap.rightChild(1))==expectedLeftChildValue);
    }

    @Test
    public void testExtractMinElem() throws MinHeapException{

        Integer[] expected = {3, 11, 4, 14, 12, 32};

        boolean checkElements = true;
        int expected_value = 1;

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);
        Integer min = heap.extractMinElem();

        for(int i=0;i<6;i++){
            if(heap.get(i)!=expected[i]) checkElements = false;
        }

        assertTrue(min==expected_value && checkElements);
        

    }

    @Test
    public void testExtractMinElemOneEl() throws MinHeapException{
        
        ArrayList<Integer> expected = new ArrayList<>();

        heap.insert(i1);
        heap.extractMinElem();

        assertTrue(heap.heapSize()==expected.size());
    }

    @Test
    public void testDecreaseKey() throws MinHeapException{

        boolean checkElements = true;
        
        ArrayList<Integer> expected_array = new ArrayList<>();
        expected_array.add(i6);
        expected_array.add(i5);
        expected_array.add(i1);
        expected_array.add(i2);
        expected_array.add(i4);
        expected_array.add(17);//da 32 a 17
        expected_array.add(i7);

        heap.insert(i1);
        heap.insert(i2);
        heap.insert(i3);
        heap.insert(i4);
        heap.insert(i5);
        heap.insert(i6);
        heap.insert(i7);

        heap.decreaseKey(32, 17);

        for(int i = 0; i<7;i++){
            if(heap.get(i)!=expected_array.get(i)) checkElements = false;
        }

        assertTrue(checkElements);
    }
}
