import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stack {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static char[] stack;

    public static void main(String[] args) throws IOException {
        
        System.out.print("Enter a word: ");
        String word = input.readLine();

        stack = word.toCharArray();

        boolean running = true;
        
        while(running) {
            if(stack.length > 0) printStack();
            else System.out.println("Stack: Empty");
            
            System.out.println();
            System.out.println("PRESS\tDESCRIPTION");
            System.out.println(" O/o\t   POP");
            System.out.println(" U/u\t   PUSH");
            System.out.println(" Q/q\t   QUIT");
            System.out.println();
			System.out.println("\n________________________________________________\n");
            System.out.print("Enter a choice: ");
  
            
            char choice;

            try {
                choice = input.readLine().toLowerCase().charAt(0);
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("Invalid input. Are you sleepy?");
                continue;
            }

            switch(choice) {
                case 'o':
                    if(stack.length == 0) {
                        System.out.println("Error: Stack Empty.");
                        continue;
                    }
                    
                    char popped = pop();
                    System.out.println("Popped character: " + popped);
                    break;

                case 'u': 
                    System.out.print("Enter a character: ");
                    
                    char letter;
                    try {
                        letter = input.readLine().charAt(0);
                    } 
                    
                    catch (IndexOutOfBoundsException ioobe) {
                        System.out.println("Invalid input. Are you sleepy?");
                        break;
                    }
                    push(letter);
                    break;

                case 'q':
                    running = false;
         		                    
                    System.out.println("\n________________________________________________\n");
                    System.out.println("\nThank you for using our program\n");
                    	
                    break;

                    	
                default:
                    System.out.println("\nInvalid input. Are you sleepy?");
            }
        }
    }

    public static void printStack() {
        System.out.print("\nStack: {");
        
        for (int i = 0; i < stack.length; i++) {
            System.out.print(stack[i] + (i == stack.length - 1 ? "}\n" : ", ") );
        }
    }
    
    public static void push(char letter) {
        String word = new String(stack);
        word += letter;

        stack = word.toCharArray();
        
    }

    public static char pop() {

        char popped = stack[stack.length-1];
        String word = new String(stack);
        word = word.substring(0, word.length()-1);
        stack = word.toCharArray();


        return popped;
    }

}
