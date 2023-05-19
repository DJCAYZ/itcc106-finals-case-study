import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Arrays {
    public static void main(String[] args) throws IOException {
        final int COUNT = 10;
        int[] nums = new int[COUNT];
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter " + COUNT + " numbers");
        
        for (int i = 0; i < COUNT; i++) {
            System.out.print("Enter a number: ");
            int num;
            try {
                num = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number. Try again.");
                i--;
                continue;
            }

            nums[i] = num;
        }

        boolean running = true;

        while (running) {
            System.out.println("\nPRESS\tDESCRIPTION");
            System.out.println(" A/a \t   Display the numbers");
            System.out.println(" B/b \t   Display the numbers in the even indexes");
            System.out.println(" C/c \t   Display the numbers in the odd indexes");
            System.out.println(" D/d \t   Display the numbers in ascending order");
            System.out.println(" E/e \t   Display the numbers in descending order");
            System.out.println(" Q/q \t   Exit the program.");

            System.out.print("\nEnter a choice: ");
            char choice;

            try {
                choice = input.readLine().toLowerCase().charAt(0);
            } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch(choice) {
                case 'a':
                    displayElements(nums);
                    break;
                
                case 'b':
                    displayEvenIndexes(nums);
                    break;
                
                case 'c':
                    displayOddIndexes(nums);
                    break;

                case 'd':
                    displayInAscendingOrder(nums);
                    break;

                case 'e':
                    displayInDescendingOrder(nums);
                    break;

                case 'q':
                    System.out.println("\nThank you for using our program, Have a nice day!\n");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input. Try again.");
                    continue;
            }
        }
        
    }

    public static void displayElements(int[] nums) {
        System.out.print("\nArray: [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + (i == nums.length - 1 ? "]\n" : ", "));
        }
    }

    public static void displayEvenIndexes(int[] nums) {
        System.out.println("\nArray Elements in Even Indexes: ");
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                System.out.println("[" + i + "] = " + nums[i]);
            }
        }
    }

    public static void displayOddIndexes(int[] nums) {
        System.out.println("\nArray Elements in Odd Indexes: ");
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                System.out.println("[" + i + "] = " + nums[i]);
            }
        }
    }

    public static void displayInAscendingOrder(int[] nums) {
        int[] sortedNums = sortAscending(nums);
        displayElements(sortedNums);
        
    }

    public static void displayInDescendingOrder(int[] nums) {
        int[] sortedNums = sortAscending(nums);
        sortedNums = reverse(sortedNums);
        displayElements(sortedNums);
    }

    public static int[] sortAscending(int[] nums) {
        int[] numsCopy = nums.clone();
        
        int numsSize = numsCopy.length;

        for (int i = 0; i < numsSize; i++) {
            for (int j = 0; j < numsSize - i - 1; j++) {
                if (numsCopy[j] > numsCopy[j + 1]) {
                    int temp = numsCopy[j];
                    numsCopy[j] = numsCopy[j + 1];
                    numsCopy[j + 1] = temp;
                }
            }
        }

        return numsCopy;
    }

    public static int[] reverse(int[] nums) {
        int[] newArray = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            newArray[nums.length - i - 1] = nums[i];
        }

        return newArray;
    }
}
