import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lotto {
    public static void main(String[] args) {
        final int NUMBER_COUNT = 6;
        final int NUMBER_MIN   = 1;
        final int NUMBER_MAX   = 42;
        final int DELAY_PRINT = 1000;

        System.out.println("Welcome to Lotto " + NUMBER_COUNT + "/" + NUMBER_MAX);

        int[] userNums = getUserNumbers(NUMBER_COUNT, NUMBER_MIN, NUMBER_MAX);
        int[] winningNums = generateWinningNumbers(NUMBER_COUNT, NUMBER_MAX, DELAY_PRINT);

        checkWinningConditions(userNums, winningNums);
        
    }

    public static int[] getUserNumbers(int count, int min, int max) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        if (count < 1) {
            throw new Error("count must be greater than 0");
        }

        if (min < 1) {
            throw new Error("min must be greater than 0");
        }
        
        int[] userNums = new int[count];

        System.out.printf("Please enter %d numbers from %d to %d.%n", count, min, max);
        
        for (int i = 0; i < count; i++) {
            int userNum = 0;
            
            System.out.print("Enter a number: ");
            
            try {
                userNum = Integer.parseInt(input.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Input not valid integer.");
                i--;
                continue; // starts a new iteration of the loop

            } catch (IOException ie) {
                System.err.println("Error: IO Exception.");
                ie.printStackTrace();
                System.exit(1);
            }          
            
            if (userNum < min || userNum > max) {
                System.out.printf("Enter a number from %d to %d.%n", min, max);
                i--;
                continue;
            }
            
            if (contains(userNums, userNum)) {
                System.out.printf("You already entered %d.%n", userNum);
                i--;
                continue;
            }
            
            userNums[i] = userNum;
            System.out.printf("You entered: %d%n", userNum);
            

        }

        System.out.print("Your numbers are: [");
        for (int i = 0; i < userNums.length; i++) {
            System.out.print(userNums[i] + (i+1 == userNums.length ? "]" : ","));
        }
        System.out.println();

        return userNums;
    }

    public static int[] generateWinningNumbers(int count, int max, int delayPrint) {
        int[] winningNums = new int[count];

        System.out.println("The winning numbers are:");

        for (int i = 0; i < count; i++) {
            int winningNum =  (int) ((Math.random() * max) + 1);
            
            if (contains(winningNums, winningNum)) {
                i--;
                continue;
            }

            System.out.print(winningNum + " ");

            try {
                Thread.sleep(delayPrint);
            } catch (InterruptedException ie) {
                System.err.println("Thread interrupted.");
            }
        }

        System.out.println();

        return winningNums;
    }

    public static void checkWinningConditions(int[] userNumbers, int[] winningNumbers) {
        int matchingNums = 0;
        for (int i = 0; i < userNumbers.length; i++) {
            if (contains(winningNumbers, userNumbers[i])) {
                matchingNums++;
            }
        }

        System.out.printf("Your chosen numbers matched %d of the winning numbers.%n", matchingNums);

        switch (matchingNums) {
            case 0:
            case 1:
            case 2:
                System.out.println("Unfortunately, you won nothing.");
                System.out.println("Better luck next time!");
                break;

            case 3:
                System.out.println("Congratulations! You won two hundred (200) pesos!");
                break;

            case 4:
                System.out.println("Congratulations! You won one thousand (1,000) pesos!");
                break;
            
            case 5:
                System.out.println("Congratulations! You won twenty five thousand (25,000) pesos!");
                break;

            case 6:
                System.out.println("Congratulations! You won the grand prize of nine million (9,000,000) pesos!");
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
