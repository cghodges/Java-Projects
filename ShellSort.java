import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//Calloway Hodges Project 3 Shell Sort
//This class will test Shell sort on our input files of unsorted and almost sorted random numbers
public class ShellSort {

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
        Scanner scanner = new Scanner(new File(almost800000));
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

        ShellSort(randomNums);

        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);

        //convert to seconds
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
        System.out.println("Runtime: " + durationInMillis + " milliseconds");
    }

    /**
     * Body of the Shellsort process using Shell's increments
     * @param a the array to be sorted
     */
    public static void ShellSort(int [] a)
    {
        int j;

        //loop through the array and swap elements if left > right, using a gap
        //of half the length of the array, decreasing by half of that gap after each iteration
        for(int gap = a.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < a.length; i++)
            {
                int tmp = a[i];
                for(j = i; j >= gap && tmp < a[j-gap]; j-= gap)
                {
                    a[j] = a[j-1];
                }
                a[j] = tmp;
            }
        }

    }
}
