import java.util.*;

public class Visualizer{
    double currentState;
    double maxGuesses;
    String[] states;

    public Visualizer(){
        currentState = 0;
        maxGuesses = 6;
        states = new String[7];
        setStates();
    }

    public Visualizer(double maxGuesses){
        currentState = 0;
        this.maxGuesses = maxGuesses;
        states = new String[7];
        setStates();
    }

    public void nextState(){
        currentState++;
        if (currentState > maxGuesses) currentState = 0;
    }

    public void setMaxGuesses(double maxGuesses){ this.maxGuesses = maxGuesses; }

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
        //return states[(int)currentState];
        return states[(int)((currentState/maxGuesses)*6)];
    }
}