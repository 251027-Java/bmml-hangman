import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HangMan {

    public static final String ANSI_CONSOLE_CLEAR = "\033[H\033[2J";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        boolean quit = false;
        Scanner scan = new Scanner(System.in);
        while (!quit) {
            Visualizer visualizer = new Visualizer();

            Set<Character> guesses = new HashSet<Character>();

            String guessed_letter_string = "";

            boolean invalidEntry = true;
            String entry;
            String dictPath = "";
            do {
                System.out.print("Please enter 1 for normal difficulty and 2 for hard: ");
                entry = scan.nextLine();
                if (entry.length() == 1 && (entry.equals("1") || entry.equals("2"))) {
                    invalidEntry = false;
                    if (entry.equals("1")) {
                        dictPath = "./src/main/dict.txt";
                        System.out.println(ANSI_GREEN + "Playing Normal Mode" + ANSI_RESET);
                    } else {
                        dictPath = "./src/main/hardDict.txt";
                        System.out.println(ANSI_RED + "Playing Hard Mode" + ANSI_RESET);
                    }

                } else {
                    System.out.println("Please enter either 1 or 2...");
                }
            } while (invalidEntry);

            // Get word from dict

            String word;
            try {
                word = randomWord(dictPath);
            } catch (IOException e) {
                System.out.println("Trouble reading dict file....");
                return;
            }

            int triesLeft = 6;
        boolean usedHint = false;
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
                System.out.println(" ");
                System.out.println(visualizer);
                //Display currently guessed word
                System.out.println(currentDisplayWord);
                System.out.println(String.format("Tries Left: %d", triesLeft));
                System.out.println(String.format("Guessed Letters: %s", guessed_letter_string));
                System.out.print("\nPlease input a single letter as your guess: ");

                String letterString = scan.nextLine();
                if ((letterString.length() != 1) || !isLetter(letterString.charAt(0))) { // not only a single letter
                    System.out.println("Invalid entry.");
                    continue;
                }
                char let = Character.toLowerCase(letterString.charAt(0));
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
                        System.out.println(String.format("'%c' is in the word!", let));
                        //add letter to guesses
                        guesses.add(let);
                        guessed_letter_string = guessed_letter_string.concat(ANSI_GREEN + String.valueOf(let) + ANSI_RESET).concat(" ");
                        currentDisplayWord = updateDisplayWord(word, currentDisplayWord, let);
                    }

                    //letter is not in word
                    else {
                        //subtract from tries
                        triesLeft--;
                        //add letter to guesses
                        guesses.add(let);
                        guessed_letter_string = guessed_letter_string.concat(ANSI_RED + String.valueOf(let) + ANSI_RESET).concat(" ");
                        //move visualizer to next state
                        visualizer.nextState();
                        System.out.println(String.format("%c is not in the word. :(", let));
                        if ((triesLeft <= 2) && (usedHint == false)) {
                        String hint = "";
                        boolean valid_input = false;
                        do {
                            System.out.print("\nUse Hint? [Y/n] : ");
                            hint = scan.nextLine();
                            if ((hint.equalsIgnoreCase("y")) || (hint.equalsIgnoreCase("n"))) {
                                valid_input = true;
                            } else {
                                System.out.println("Please only enter 'y' or 'n'.");
                            }
                        } while (valid_input == false);
                        if (hint.equalsIgnoreCase("y")) {
                            // choose random letter from those that are left
                            // update display word
                            // update visualizer
                            usedHint = true;
                            HashSet<String> emptySpaces = new HashSet<>();
                            for (int i = 0; i < currentDisplayWord.length(); i++) {
                                if (currentDisplayWord.charAt(i) == '_') {
                                    emptySpaces.add(String.valueOf(i/2));
                                }
                            }
                            System.out.println("Please choose one of the available spaces to fill with your hint:");
                            System.out.print("\t");
                            for (String index : emptySpaces) {
                                System.out.print(String.format("%s  ", index));
                            }
                            String selection;
                            boolean invalidSelection = true;
                            do {
                                System.out.print("\nSelection: ");
                                selection = scan.nextLine();
                                if (!emptySpaces.contains(selection)) {
                                    System.out.println("Invalid selection, try again");
                                }
                                else {
                                    invalidSelection = false;
                                }
                            } while (invalidSelection);
                            Character chosenLetter = word.charAt(Integer.parseInt(selection));
                            guesses.add(chosenLetter);
                            guessed_letter_string = guessed_letter_string.concat(ANSI_GREEN + String.valueOf(chosenLetter) + ANSI_RESET).concat(" ");
                            currentDisplayWord = updateDisplayWord(word, currentDisplayWord, chosenLetter);
                        }

                    }
                }
                }

                // check for win
                HashSet<Character> checkSet = new HashSet<>(guesses);
                checkSet.retainAll(wordChars);
                if (checkSet.size() == wordChars.size()) {
                    // You
                    System.out.println("\nYOU WIN!!! :)");
                    System.out.println(String.format("The word was : %s", word));
                    break;
                }
            }

            // Game over display
            if (triesLeft == 0) {
                System.out.println(visualizer);
                System.out.println("\nYOU LOST :(");
                System.out.println(String.format("The word was : %s", word));
            }

            boolean badInput = true;
            while (badInput) {
                System.out.print("Play Again? (Y/N) \t");
                String input = scan.nextLine();
                if (input.equals("Y") | input.equals("y")) {
                    badInput = false;
                    clearScreen();
                } else if (input.equals("N") | input.equals("n")) {
                    quit = true;
                    badInput = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid Input");
                }
            }
        }
        scan.close();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String updateDisplayWord(String word, String currDisplay, char guessedLetter) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char let = word.charAt(i);
            if (let == guessedLetter) {
                build.append(word.charAt(i)).append(' ');
            } else {
                build.append(currDisplay.charAt(i * 2)).append(' ');
            }
        }
        build.deleteCharAt(build.length()-1); // remove trailing space
        return build.toString();
    }

    private static String randomWord(String filePath) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(filePath));
        int numWords = words.size();
        Random rand = new Random();

        boolean invalidWord = true;
        String word;
        do {
            int lineNum = rand.nextInt(numWords);
            word = words.get(lineNum).strip();
            if ((word.length() >= 5) && (word.length() <= 12)) {
                invalidWord = false;
            }
        } while (invalidWord);
        return word;
    }
    private static boolean isLetter(char let) {
        return ((let <= 'z' && let >= 'a') || (let <= 'Z' && let >= 'A'));
    }

}
