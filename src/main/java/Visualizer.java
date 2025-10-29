import java.util.*;

public class Visualizer{
    int currentState;
    int maxGuesses;
    String[] states;

    public Visualizer(){
        currentState = 0;
        maxGuesses = -1;
        states = new String[7];
        setStates();
    }

    public Visualizer(int maxGuesses){
        currentState = 0;
        this.maxGuesses = maxGuesses;
        states = new String[7];
        setStates();
    }

    public void nextState(){
        currentState++;
        if (maxGuesses)
        if (currentState > 6) currentState = 0;
    }

    public void setMaxGuesses(int maxGuesses){ this.maxGuesses = maxGuesses; }

    public void setStates(){
        states[0] = "  +---+\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "=========";
        states[1] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "      |\n" +
                "      |\n" +
                "      |\n" +
                "=========";
        states[2] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                "  |   |\n" +
                "      |\n" +
                "      |\n" +
                "=========";
        states[3] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|   |\n" +
                "      |\n" +
                "      |\n" +
                "=========";
        states[4] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                "      |\n" +
                "      |\n" +
                "=========";
        states[5] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " /    |\n" +
                "      |\n" +
                "=========";
        states[6] = "  +---+\n" +
                "  |   |\n" +
                "  O   |\n" +
                " /|\\  |\n" +
                " / \\  |\n" +
                "      |\n" +
                "=========";
    }

    @Override
    public String toString(){
        if (maxGuesses == -1){
            return states[currentState];
        }
        return states[currentState/maxGuesses];
    }
}