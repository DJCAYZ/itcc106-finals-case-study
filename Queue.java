import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ! OUTDATED, Please grab edited version

public class Queue {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static char[] queue;
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a word: ");
        String word = input.readLine();

        queue = word.toCharArray();

        boolean running = true;

        while (running) {
            if (queue.length > 0) printQueue();
            else System.out.println("Queue: Empty");

            System.out.println();
            System.out.println("PRESS\tDESCRIPTION");
            System.out.println(" D/d\t   Dequeue");
            System.out.println(" E/e\t   Enqueue");
            System.out.println(" Q/q\t   Quit");
            System.out.println();

            System.out.print("Enter a choice: ");
            char choice;

            try {
                choice = input.readLine().toLowerCase().charAt(0);
            } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch(choice) {
                case 'd':
                    if (queue.length == 0) {
                        System.out.println("Error: Queue Empty.");
                        continue;
                    }
                    
                    char dequeued = dequeue();
                    System.out.println("Dequeued letter: " + dequeued);
                    break;

                case 'e':
                    System.out.print("Enter a letter: ");
                    char letter;

                    try {
                        letter = input.readLine().charAt(0);
                    } catch(IndexOutOfBoundsException ioobe) {
                        System.out.println("Invalid input. Try again.");
                        break;
                    }
                    enqueue(letter);
                    break;

                case 'q':
                    running = false;
                    break;
                
                default:
                    System.out.println("Invalid input. Try again.");
            }
            
        }
    }

    public static void printQueue() {
        System.out.print("Queue: {");
        for (int i = 0; i < queue.length; i++) {
            System.out.print(queue[i] + (i == queue.length-1 ? "}\n" : ", "));
        }
    }

    public static void enqueue(char letter) {
        String word = new String(queue);
        word += letter;
        queue = word.toCharArray();
    }

    public static char dequeue() {
        char dequeued = queue[0];

        String newWord = new String(queue).substring(1);
        queue = newWord.toCharArray();

        return dequeued;
    }
}
