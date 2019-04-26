import java.util.Arrays;
public class MyHeap{
  public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {10, 500, 1000000000};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 500001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=30; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        heapsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
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
      //have to account for size 2!
      if (size == 2){
        int kidInd = index+1; //if you're pushing down the kid, will go to catch statement
        if (data[kidInd] > data[index]){
          swap(data, index, kidInd);
        }
      }
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
  public static void heapify(int[] data){
    int s = ((data.length-1) - 1) / 2; //get the last valid parent node
    for (int start = s; start>-1; start--){
      pushDown(data, data.length, start); //working up to the top, push down
    }
  }

  /*  - sort the array by converting it into a heap then removing the
  largest value n-1 times. [ should be O(nlogn) ]
  */
  public static void heapsort(int[] data){
    //System.out.println("current data: " + Arrays.toString(data));
    heapify(data); //minus i shows where sorted array begins
    //System.out.println("Heapified Data: " + Arrays.toString(data));
    for (int i = 0; i<data.length-1; i++){
      swap(data, 0, data.length-(1+i)); //swap first and last (that is being processed)
      //System.out.println("Moved largest to back: " + Arrays.toString(data));
      //System.out.println("size for pushDown: " + (data.length - i));
      pushDown(data, (data.length-(i+1)), 0); //reorganize heap
      //System.out.println("Pushed down small thing moved to the front: " + Arrays.toString(data) + "\n");
    }
  }

}
