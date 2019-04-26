public class MyHeap{
  /*We discussed these 2 methods already:
  - size  is the number of elements in the data array.
  - push the element at index i downward into the correct position.
  This will swap with the larger of the child nodes provided that child is larger.
  This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
  - precondition: index is between 0 and size-1 inclusive
  - precondition: size is between 0 and data.length-1 inclusive.*/
  private static void pushDown(int[]data,int size,int index){
    if (size == 0){
      throw new IllegalArgumentException("no array");
    }
    if (index > size || index < 0){
      throw new IllegalArgumentException("index is out of bounds");
    }
    try{
      int kidInd1 = 2*index + 1;
      int kidInd2 = 2*index + 2;
      if (data[kidInd1] > data[kidInd2] && data[kidInd1] > data[index]){
        swap(data, kidInd1, index);
        pushDown(data, size, kidInd1);
      } else if (data[kidInd2] >= data[kidInd1] && data[kidInd2] > data[index]){
        swap(data, kidInd2, index);
        pushDown(data, size kidInd2);
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
  private static void pushUp(int[]data,int index)


  /*- convert the array into a valid heap. [ should be O(n) ]
  */
  public static void heapify(int[])

  /*  - sort the array by converting it into a heap then removing the
  largest value n-1 times. [ should be O(nlogn) ]
  */
  public static void heapsort(int[])

}
