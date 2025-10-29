import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class HangMan {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Visualizer visualizer = new Visualizer();

        Set<Character> guesses = new HashSet<Character>();

        System.out.println("Please input a word for others to guess");
//        String word = scan.nextLine();
        String word = "Revature";
        int triesLeft = 6;
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            build.append('_').append(' ');
        }
        build.deleteCharAt(build.length() - 1); // remove trailing space
        String currentDisplayWord = build.toString();
        //potensh add input for num of guesses

        // build char set for word to compare against for win
        HashSet<Character> wordChars = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            wordChars.add(word.charAt(i));
        }

        while (triesLeft > 0) {
            //display ascii hangman
            System.out.println(visualizer);
            //Display currently guessed word
            System.out.println(currentDisplayWord);
            System.out.print("Please input a single letter as your guess: ");

            String letterString = scan.nextLine();
            if( (letterString.length() > 1) || !isLetter(letterString.charAt(0)) ){ // not only a single letter
                System.out.println("Invalid entry.");
                continue;
            }
            char let = letterString.charAt(0);
            // check if letter has been guessed
            if (guesses.contains(let)) {
                System.out.println("Already guessed.");
                continue;
            }

            // if not, check if letter in the word
            else {
                // letter is in word
                if (wordChars.contains(let)) {
                    // message congrats you guessed a letter!
                    System.out.println(String.format("'%s' is in the word!"));
                    //add letter to guesses
                    guesses.add(let);
                    currentDisplayWord = updateDisplayWord(word, currentDisplayWord, let);

//                    do { //need to account for multiple instances of a letter in the guess word
//                         //but we're guaranteed at least one, so find the first occurrence,
//                         //and then look for others. Start from index 0
//                        index = -1;
//                        index = word.indexOf(letter, index + 1);
//                        //update our char array index from underscores
//                        //char array[index] = letter
//                    } while (index >= 0);
                        // update current display word

                }

                //letter is not in word
                else {
                    //subtract from tries
                    triesLeft --;
                    //add letter to guesses
                    guesses.add(let);
                    //move visualizer to next state
                    visualizer.nextState();
                }
            }

            // check for win
            HashSet<Character> checkSet = new HashSet<>(guesses);
            checkSet.retainAll(wordChars);
            if (checkSet.size() == wordChars.size()) {
                // You
                System.out.println("YOU WIN!!! :)");
                break;
            }
        }

        // Game over display
        if (triesLeft == 0) {
            System.out.println("YOU LOST :(");
        }

    }

    private static String updateDisplayWord(String word, String currDisplay, char guessedLetter) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char let = word.charAt(i);
            if (let == guessedLetter) {
                build.append(currDisplay.charAt(let)).append(' ');
            } else {
                build.append(currDisplay.charAt(i * 2)).append(' ');
            }
        }
        build.deleteCharAt(build.length()-1); // remove trailling space
        return build.toString();
    }

    private static boolean isLetter(char let) {
        return ((let <= 'z' && let >= 'a') || (let <= 'Z' && let >= 'A'));
    }

}
