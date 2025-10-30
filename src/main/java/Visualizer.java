
public class Visualizer {
    double currentState;
    double maxGuesses;
    String[] states;

    public Visualizer() {
        this.currentState = 0;
        this.maxGuesses = 6;
        this.states = new String[7];
        setStates();
    }

    public Visualizer(double maxGuesses) {
        this.currentState = 0;
        this.maxGuesses = maxGuesses;
        this.states = new String[7];
        setStates();
    }

    public void nextState() {
        currentState++;
        if (currentState > maxGuesses) currentState = 0;
    }

    public void setMaxGuesses(double maxGuesses) {
        this.maxGuesses = maxGuesses;
    }

//    public void setStates(){
//        states[0] = "  +---+\n" +
//                "  |   |\n" +
//                "      |\n" +
//                "      |\n" +
//                "      |\n" +
//                "      |\n" +
//                "=========";
//        states[1] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                "      |\n" +
//                "      |\n" +
//                "      |\n" +
//                "=========";
//        states[2] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                "  |   |\n" +
//                "      |\n" +
//                "      |\n" +
//                "=========";
//        states[3] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                " /|   |\n" +
//                "      |\n" +
//                "      |\n" +
//                "=========";
//        states[4] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                " /|\\  |\n" +
//                "      |\n" +
//                "      |\n" +
//                "=========";
//        states[5] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                " /|\\  |\n" +
//                " /    |\n" +
//                "      |\n" +
//                "=========";
//        states[6] = "  +---+\n" +
//                "  |   |\n" +
//                "  O   |\n" +
//                " /|\\  |\n" +
//                " / \\  |\n" +
//                "      |\n" +
//                "=========";
//    }

    public void setStates() {
        states[0] =
                """
                              ┌───────────┐\s
                              │           │\s
                                          │\s
                                          │\s
                                          │\s
                                          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[1] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                                          │\s
                                          │\s
                                          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[2] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                              │           │\s
                              │           │\s
                                          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[3] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                             /│           │\s
                              │           │\s
                                          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[4] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                             /│\\          │\s
                              │           │\s
                                          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[5] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                             /│\\          │\s
                              │           │\s
                             /            │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;

        states[6] =
                """
                              ┌───────────┐\s
                              │           │\s
                             (_)          │\s
                             /│\\          │\s
                              │           │\s
                             / \\          │\s
                                          │\s
                                          │\s
                          ████████████████████\
                        """;
    }


    @Override
    public String toString() {
        //return states[(int)currentState];
        return states[(int) ((currentState / maxGuesses) * 6)];
    }
}