import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

//Calloway Hodges Project 3 QuickSort
//This class will test Quicksort on our input files of unsorted and almost sorted random numbers
public class QuickSort {
    public static void main(String[] args) throws FileNotFoundException {

        //Declare variables to store the names of our text files to avoid confusion while testing
        String unsorted50000 = "Random_Numbers1.txt";
        String unsorted100000 = "Random_Numbers2.txt";
        String unsorted200000 = "Random_Numbers3.txt";
        String unsorted400000 = "Random_Numbers4.txt";
        String unsorted800000 = "Random_Numbers5.txt";
        String almost50000 = "Almost_Sorted1.txt";
        String almost100000 = "Almost_Sorted2.txt";
        String almost200000 = "Almost_Sorted3.txt";
        String almost400000 = "Almost_Sorted4.txt";
        String almost800000 = "Almost_Sorted5.txt";

        //declare array to store the integers from the input file
        int [] randomNums = new int[50000];
        //Set the input file to be sorted
        Scanner scanner = new Scanner(new File(unsorted800000));
        int i = 0;
        //scan through the file and input into array
        while(scanner.hasNextInt())
        {
            //Double the size of the array if the number of inputs is greater than current size.
            if(i == randomNums.length){
                int [] tempArray = new int[randomNums.length * 2];
                for (int j = 0; j < randomNums.length; j++){
                    tempArray[j] = randomNums[j];
                }
                randomNums = tempArray;
            }

            randomNums[i] = scanner.nextInt();
            i++;
        }

        //Sort the array using insertion sort and measure execution time for this method.
        long startTime = System.nanoTime();

        quickSort(randomNums);

        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);

        //convert to milliseconds
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
        System.out.println("Runtime: " + durationInMillis + " milliseconds");
    }

    public static void quickSort(int[] inputArray)
    {
        int[] a = inputArray;
        quickSort(a, 0, a.length - 1);
    }

    /**
     * The recursive portion of the quicksort process
     * @param a the array to be sorted
     * @param left the leftmost index of the subarray
     * @param right the rightmost index of the subarray
     */
    public static void quickSort(int [] a, int left, int right){

        if(left + 10 <= right)
        {
            int pivot = median3(a, left, right);

            //Begin partitioning
            int i = left, j = right - 1;
            for ( ; ; )
            {
                while (a[++i] < a[pivot]) { }
                while (a[--j] > a[pivot]) { }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1); //Restore pivot

            quickSort(a, left, i - 1); //Sort left side elements
            quickSort(a, i + 1, right); //Sort right side elements
        }
        else //Do insertion sort on the subarray if length is 10 or less
            insertionSort(a, left, right);
        }


    /**
     * find the position of pivot using median of three
     * @param a array to find the pivot of
     * @param left leftmost index of the subarray
     * @param right rightmost index of the subarray
     * @return the index of the pivot
     */
    public static int median3(int[] a, int left, int right){

        int center = (left + right) / 2;
        if(a[center] < a[left])
        {
            swapReferences(a, left, center);
        }
        if(a[right] < a[left])
        {
            swapReferences(a, left, right);
        }
        if(a[right] < a[center])
        {
            swapReferences(a, center, right);
        }

        //The pivot is now the center index
        //Place the pivot at position right - 1 and return that index
        swapReferences(a, center, right-1);
        return right - 1;

    }

    /**
     * Swap elements a and b
     * @param Array the array we are swapping elements in
     * @param a one element to be swapped
     * @param b the other element to be swapped
     */
    static void swapReferences(int[] Array, int a, int b){
        int x = Array[a];
        Array[a] = Array[b];
        Array[b] = x;
    }

    /**
     * Insertion sort for when the subarrays are 10 or less in length
     * @param a the array being sorted
     * @param left the leftmost index in the sub array
     * @param right the rightmost index in the sub array
     */
    public static void insertionSort(int [] a, int left, int right)
    {
        int j;

        //loop through the array and swap elements if the left is greater than the right
        //using a gap of 1
        for(int p = left; p < right + 1; p++)
        {
            int tmp = a[p];
            for(j = p; j > 0 && tmp < a[j-1]; j--)
            {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }


}
