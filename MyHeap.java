import java.util.Arrays;
public class MyHeap{
  public static void main(String[] args){
    int[] test = {0, 3, 9, 7, 5, 4, 1};
    System.out.println("Test: " + Arrays.toString(test));
    System.out.println("heapifying");
    heapify(test, test.length-2);
    System.out.println("Test: " + Arrays.toString(test));
  }
  /*We discussed these 2 methods already:
  - size  is the number of elements in the data array.
  - push the element at index i downward into the correct position.
  This will swap with the larger of the child nodes provided that child is larger.
  This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
  - precondition: index is between 0 and size-1 inclusive
  - precondition: size is between 0 and data.length-1 inclusive.*/
  private static void pushDown(int[]data, int size, int index){
    if (size == 0){
      throw new IllegalArgumentException("no array");
    }
    if (index >= size || index < 0){
      throw new IllegalArgumentException("index is out of bounds");
    }
    try{
      int kidInd1 = 2*index + 1;
      int kidInd2 = 2*index + 2;
      if (kidInd1 >= size || kidInd2 >= size){
        //stop the recurion -- this is for heapSort when sorted is at the back
      } else if (data[kidInd1] > data[kidInd2] && data[kidInd1] > data[index]){
        swap(data, kidInd1, index);
        pushDown(data, size, kidInd1);
      } else if (data[kidInd2] >= data[kidInd1] && data[kidInd2] > data[index]){
        swap(data, kidInd2, index);
        pushDown(data, size, kidInd2);
      } else{
        //stop recursion, neither the kids are larger
      }
    }catch(ArrayIndexOutOfBoundsException e){
      //stop recursion, at a leaf
    }
  }
  private static void swap(int[] data, int ind1, int ind2){
    int temp = data[ind1];
    data[ind1] = data[ind2];
    data[ind2] = temp;
  }

  /*- push the element at index i up into the correct position.
  This will swap it with the parent node until the parent node is larger or the root is reached.
  [ should be O(logn) ]
  - precondition: index is between 0 and data.length-1 inclusive.
  */
  private static void pushUp(int[]data,int index){
    if (index  >= data.length || index < 0){
      throw new IllegalArgumentException("index out of bounds");
    }
    if (data.length == 0){
      throw new IllegalArgumentException("data length 0");
    }
    try{
      int parentInd = (index - 1) / 2;
      int siblingInd = parentInd * 2 + 1;
      if (siblingInd == index){
        siblingInd++;
      }
      if (data[index] >= data[siblingInd] && data[index] > data[parentInd]){
        swap(data, parentInd, index);
        pushUp(data, parentInd);
      }else{
        //stop recursion
      }
    }catch(ArrayIndexOutOfBoundsException e){
      //stop recursion, at top
    }
  }


  /*- convert the array into a valid heap. [ should be O(n) ]
  */
  public static void heapify(int[] data, int size){
    int s = ((size - 1) - 1) / 2; //get the last valid parent node
    for (int start = s; start>-1; start--){
      pushDown(data, size, start); //working up to the top, push down
    }
  }

  /*  - sort the array by converting it into a heap then removing the
  largest value n-1 times. [ should be O(nlogn) ]
  */
  public static void heapsort(int[] data){}

}
