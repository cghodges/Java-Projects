import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Calloway Hodges Project 3
//This class will generate 5 files of unsorted random integers and 5 files of almost sorted integers
//We will use merge sort to sort the random numbers, then invert 10% of the array to create our almost sorted file
//These files will be used to test our sorting algorithms
public class GenerateFiles {

    public static void main(String[] args) {

        Random rand = new Random();
        int ceiling = 800000;
        int[] numOfInput = {50000, 100000, 200000, 400000, 800000};

        //Generate 5 files of unsorted integers and 5 files of almost sorted integers
        //Loop through the numOfInput array 5 times to set the amount of random numbers to generate
        //We will set the text file name to be Random_Numbers1 for the first text file, Random_Numbers2 for the second,
        //and so on.
        for (int i = 0; i < 5; i++) {
            String nextFileName = "Random_Numbers" + (i+1) + ".txt";
            String nextAlmostSorted = "Almost_Sorted" + (i+1) + ".txt";
            int inputs = numOfInput[i];
            int[] randomNums = new int[inputs]; //declare array to store the random numbers, we will use this for our almost sorted integers
            try {
                File nextFile = new File(nextFileName); //Create the next file for the random integers

                if (nextFile.createNewFile()) {
                    System.out.println("File created: " + nextFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


                    try {
                        FileWriter myWriter = new FileWriter(nextFileName);
                        FileWriter myWriter2 = new FileWriter(nextAlmostSorted);
                        for (int j = 0; j < inputs; j++){
                            int random_int = rand.nextInt(ceiling);
                            randomNums[j] = random_int;
                            String randomNum = Integer.toString(random_int);
                            if(j != inputs - 1) {
                                myWriter.write(randomNum + "\n");
                            }
                            else{
                                myWriter.write(randomNum);
                            }

                        }

                        myWriter.close();
                        System.out.println("Successfully wrote to file: " + nextFileName);

                        //use mergeSort to sort the array of random numbers
                        mergeSort(randomNums);
                        //invert 10 percent of the sorted array:
                        //set two variables for the left and right sides of the array and use them to progressively
                        //swap 10 % of the values of the array
                        for(int k = 0; k < randomNums.length /10; k++){
                            int leftSide = randomNums[k];
                            int rightSide = randomNums[(randomNums.length - k) - 1];
                            randomNums[k] = rightSide;
                            randomNums[(randomNums.length - k) - 1] = leftSide;
                        }

                        //write the almost sorted array to a new text file
                        for(int h = 0; h < randomNums.length; h++){
                            int nearlyRandomNum = randomNums[h];
                            String almostRandomNum = Integer.toString(nearlyRandomNum);

                            myWriter2.write(almostRandomNum + "\n");
                        }
                        myWriter2.close();
                        System.out.println("Successfully wrote to file: "+ nextAlmostSorted);

                    } catch (IOException f) {
                        System.out.println("An error occurred.");
                        f.printStackTrace();
                    }
            }




    } //end of main

    /**
     * This is the main body of the mergeSort process
     * @param Arr array to be sorted
     * @param start leftmost index of the subarray
     * @param mid middle position of the subarray
     * @param end rightmost index of the subarray
     */
    static void merge(int Arr[], int start, int mid, int end) {

        // create a temp array
        int temp[] = new int[end - start + 1];

        // accumulators for both intervals and for temp
        int i = start, j = mid+1, k = 0;

        //loop through both arrays and in each iteration add smaller of both elements in temp
        while(i <= mid && j <= end) {
            if(Arr[i] <= Arr[j]) {
                temp[k] = Arr[i];
                k += 1; i += 1;
            }
            else {
                temp[k] = Arr[j];
                k += 1; j += 1;
            }
        }

        //restore elements of the first half
        while(i <= mid) {
            temp[k] = Arr[i];
            k += 1; i += 1;
        }

        //restore elements of the second half
        while(j <= end) {
            temp[k] = Arr[j];
            k += 1; j += 1;
        }

        //copy temp back to original
        for(i = start; i <= end; i += 1) {
            Arr[i] = temp[i - start];
        }
    }

    /**
     * this is the recursive portion of the MergeSort process
     * @param a our array to be sorted
     * @param start the leftmost index of the subarray
     * @param end the rightmost index of the subarray
     */
    static void mergeSort(int a[], int start, int end) {

        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid+1, end);
            merge(a, start, mid, end);
        }
    }

    /**
     * Initialize the mergeSort process with one array input
     * @param Arr our array to be sorted
     */
    static void mergeSort(int Arr[])
    {
        int[] a = Arr;
        mergeSort(a, 0, a.length - 1);
    }

} //end of class

