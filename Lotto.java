import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lotto {
    public static void main(String[] args) {
        final int NUMBER_COUNT = 6;
        final int NUMBER_MIN   = 1;
        final int NUMBER_MAX   = 42;
        final int DELAY_PRINT = 1000;

        System.out.println("=============================");
        System.out.println("Welcome to Lotto " + NUMBER_COUNT + "/" + NUMBER_MAX);
        System.out.println("=============================\n");

        int[] userNums = getUserNumbers(NUMBER_COUNT, NUMBER_MIN, NUMBER_MAX);
        int[] winningNums = generateWinningNumbers(NUMBER_COUNT, NUMBER_MAX);
        // int[] winningNums = {21, 33, 6, 10, 12, 41};

        System.out.println("The winning numbers are: ");
        for (int i = 0; i < winningNums.length; i++) {
            try {
                Thread.sleep(DELAY_PRINT);
            } catch(InterruptedException ie) {
                System.err.println("Error: Interrupted.");
            }
            System.out.print(winningNums[i] + (i == winningNums.length-1 ? "\n" : " "));
        }

        checkWinningConditions(userNums, winningNums);
        
    }

    public static int[] getUserNumbers(int count, int min, int max) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int[] userNums = new int[count];

        System.out.println("Please enter " + count +  " numbers from " + min + " to " + max);
        
        for (int i = 0; i < count; i++) {
            int userNum = 0;
            
            System.out.print("Enter a number: ");
            
            try {
                userNum = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Input not valid integer.");
                i--;
                continue;

            } catch (IOException ie) {
                System.err.println("Error: IO Exception.");
                ie.printStackTrace();
                System.exit(1);
            }          
            
            if (userNum < min || userNum > max) {
                System.out.println("Enter a number from " + min + " to " + max);
                i--;
                continue;
            }
            
            if (contains(userNums, userNum)) {
                System.out.println("You already entered " + userNum);
                i--;
                continue;
            }
            
            userNums[i] = userNum;
            

        }

        System.out.print("\nYour numbers are: [");
        for (int i = 0; i < userNums.length; i++) {
            System.out.print(userNums[i] + (i+1 == userNums.length ? "]\n\n" : ","));
        }

        return userNums;
    }

    public static int[] generateWinningNumbers(int count, int max) {
        int[] winningNums = new int[count];

        for (int i = 0; i < count; i++) {
            int winningNum =  (int) ((Math.random() * max) + 1);
            
            if (contains(winningNums, winningNum)) {
                i--;
                continue;
            }
            winningNums[i] = winningNum;
        }

        return winningNums;
    }

    public static void checkWinningConditions(int[] userNumbers, int[] winningNumbers) {
        int matchingNums = 0;
        for (int i = 0; i < userNumbers.length; i++) {
            if (contains(winningNumbers, userNumbers[i])) {
                matchingNums++;
            }
        }

        System.out.println("\nYour chosen numbers matched " + matchingNums + " of the winning numbers.\n");

        switch (matchingNums) {
            case 3:
            System.out.println("Congratulations! You won 200 pesos!");
            break;
            
            case 4:
            System.out.println("Congratulations! You won 1,000 pesos!");
            break;
            
            case 5:
            System.out.println("Congratulations! You won 25,000 pesos!");
            break;
            
            case 6:
            System.out.println("Congratulations! You won the grand prize of 9,000,000 pesos!");
            break;

            default:
                System.out.println("Unfortunately, you won nothing.");
                System.out.println("Better luck next time!\n");
                break;
        }
    }

    public static boolean contains(int[] numArray, int num) {
        boolean containsNum = false;

        for (int i = 0; i < numArray.length; i++) {
            if (num == numArray[i]) containsNum = true;
        }

        return containsNum;
    }

}