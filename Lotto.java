import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lotto {
    public static void main(String[] args) {
        // Define constants (values that will never change, makes it easy to configure the app)
        final int NUMBER_COUNT = 6; // amuont of numbers chosen and drawn for the lotto
        final int NUMBER_MIN   = 1; // minimum number that can be inputted
        final int NUMBER_MAX   = 42; // max number that can be inputted and drawn
        final int DELAY_PRINT = 1000; // how much milliseconds to wait in between announcing each drawn winning numbers

        System.out.println("Welcome to Lotto " + NUMBER_COUNT + "/" + NUMBER_MAX);

        int[] userNums = getUserNumbers(NUMBER_COUNT, NUMBER_MIN, NUMBER_MAX);
        int[] winningNums = generateWinningNumbers(NUMBER_COUNT, NUMBER_MAX, DELAY_PRINT);

        checkWinningConditions(userNums, winningNums);
        
    }

    public static int[] getUserNumbers(int count, int min, int max) {
        // initialize input reader (using BufferedReader)
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // if the specified count parameter is less than 1, throw an Error
        if (count < 1) {
            throw new Error("count must be greater than 0");
        }

        // if the minimum specified is less than 1, throw an Error
        if (min < 1) {
            throw new Error("min must be greater than 0");
        }
        
        // initialize an array with size of the parameter count
        int[] userNums = new int[count];

        System.out.printf("Please enter %d numbers from %d to %d.%n", count, min, max);
        
        for (int i = 0; i < count; i++) {
            int userNum = 0; // variable that will hold the number inputted by the user
            
            System.out.print("Enter a number: ");
            
            try {
                // ask for a number from the user
                userNum = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                // if the user inputted an invalid number, redo the current iteration of the loop
                // i-- makes it so the i++ in the for loop definition does nothing
                System.err.println("Input not valid integer.");
                i--;
                continue; // starts a new iteration of the loop

            } catch (IOException ie) {
                // if it catches an IO error, print the stacktrace and exit out of the program
                // emulates behavior as if 'throws IOException' was declared in the method header
                System.err.println("Error: IO Exception.");
                ie.printStackTrace();
                System.exit(1);
            }          
            
            // if the user's inputted number is less than the minimum OR greater than the maximum,
            // re-do the current iteration of the loop
            if (userNum < min || userNum > max) {
                System.out.printf("Enter a number from %d to %d.%n", min, max);
                i--;
                continue;
            }
            
            // if the number inputted by the user has already been selected by the user,
            // re-do the current iteration of the loop
            if (contains(userNums, userNum)) {
                System.out.printf("You already entered %d.%n", userNum);
                i--;
                continue;
            }
            
            // inserts the inputted number into the array that holds all the user's
            // inputted numbers
            userNums[i] = userNum;
            System.out.printf("You entered: %d%n", userNum);
            

        }

        System.out.print("Your numbers are: [");
        for (int i = 0; i < userNums.length; i++) {
            System.out.print(userNums[i] + (i+1 == userNums.length ? "]" : ","));
        }
        System.out.println();

        return userNums; // returns all the numbers inputted by the user in an array
    }

    public static int[] generateWinningNumbers(int count, int max, int delayPrint) {
        // initialize an array that will hold all randomly generated numbers
        // with size as defined by the count parameter
        int[] winningNums = new int[count];

        System.out.println("The winning numbers are:");

        for (int i = 0; i < count; i++) {
            // generates a random number between 1 and the defined value in the max parameter
            // Math.random() outputs a double between 0 and 1, not including 1 itself
            // it then gets multiplied by the max parameter and added 1
            // then it gets typecasted into an int
            int winningNum =  (int) ((Math.random() * max) + 1);
            
            // if the generated number has already been selected before
            // re-do the iteration of the loop
            if (contains(winningNums, winningNum)) {
                i--;
                continue;
            }

            System.out.print(winningNum + " ");

            // puts the program to sleep for the defined milliseconds from
            // the delayPrint parameter
            // it needs to be put into a try-catch to handle a possible
            // InterruptedException
            try {
                Thread.sleep(delayPrint);
            } catch (InterruptedException ie) {
                System.err.println("Thread interrupted.");
            }
        }

        System.out.println();

        return winningNums; // returns all the numbers randomly generated
    }

    public static void checkWinningConditions(int[] userNumbers, int[] winningNumbers) {
        int matchingNums = 0; // holds the number of times a user's number matches a winning number
        for (int i = 0; i < userNumbers.length; i++) {
            // if the number in userNumbers[i] is in winningNumbers
            // increment the matchingNums variable
            if (contains(winningNumbers, userNumbers[i])) {
                matchingNums++;
            }
        }

        //then check the winning numbers on the user's chosen numbers
        // if 3 numbers match, 20 pesos (LMFAO)
        // if 4, 1k petot (wow, so rich)
        // if 5, 25k pesus (omg)
        // if all 6 winning numbers match, 9 million (????????) (SHEEEEEEESH)

        System.out.printf("Your chosen numbers matched %d of the winning numbers.%n", matchingNums);

        switch (matchingNums) {
            // if the user has matched 0, 1, or 2 numbers in the winning numbers list
            // they win nothing
            case 0:
            case 1:
            case 2:
                System.out.println("Unfortunately, you won nothing.");
                System.out.println("Better luck next time!");
                break;

            // if user matched 3 numbers, they win 200 pesos
            case 3:
                System.out.println("Congratulations! You won two hundred (200) pesos!");
                break;

            // if user matched 4 numbers, they win 1k pesos
            case 4:
                System.out.println("Congratulations! You won one thousand (1,000) pesos!");
                break;
            
            // if user matched 5 numbers, they win 25k pesos
            case 5:
                System.out.println("Congratulations! You won twenty five thousand (25,000) pesos!");
                break;

            // if user matched 6 numbers, they win 25k pesos
            case 6:
                System.out.println("Congratulations! You won the grand prize of nine million (9,000,000) pesos!");
                break;
        }
    }

    public static boolean contains(int[] numArray, int num) {
        boolean containsNum = false; // if num is in numArray, this will turn to true

        for (int i = 0; i < numArray.length; i++) {
            // loops through the array, and if an element in array
            // matches the num parameter, put containsNum to true
            if (num == numArray[i]) containsNum = true;
        }

        return containsNum;
    }

}
