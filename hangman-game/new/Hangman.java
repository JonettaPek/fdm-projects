import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;


class Generator {
    private static final List<String> WORDS = new ArrayList<>(List.of("Harry", "Hermione", "Ron"));
    
    static String generate() {
        int min = 0;
        int max = Generator.WORDS.size() - 1;
        int range = max - min;
        int randomIndex =  (int)(Math.random() * range) + min;
        return Generator.WORDS.get(randomIndex);
    }
}

class Game {
    private int tries;
    private String solution;
    private String[] word;

    Game() {
        this.tries = 0;
        this.solution = Generator.generate().toLowerCase();
        this.word = new String[this.solution.length()];
        for (int i = 0; i < this.solution.length(); i++) {
            this.word[i] = "_";
        }
    }

    void displayWord() {
        System.out.println("The word is " + String.join(" ", this.word) + ".");
    }

    void replaceWord(final String guess, final List<Integer> indices) {
        for (Integer index : indices) {
            this.word[index] = guess;
        }
    }

    void solve() {
        for (int i = 0; i < this.word.length; i++) {
            this.word[i] = this.solution.substring(i, i+1);
        }
    }

    void increaseTries() {
        if (this.tries < Player.getMaxTries()) {
            tries++;
        }
    }

    boolean isSolved() {
        for (int i = 0; i < this.solution.length(); i++) {
            if (! this.word[i].equals(this.solution.substring(i, i+1))) {
                return false;
            }
        }
        return true;
    }

    int getTries() {
        return this.tries;
    }

    String getSolution() {
        return this.solution;
    }
}

class Checker {

    static List<Integer> check(final String guess, final String solution) {
        if (guess.length() == 1) {
            return Checker.checkGuess(guess.charAt(0), solution);
        } else {
            return Checker.checkGuess(guess, solution);
        }

    }

    static List<Integer> checkGuess(final char guess, final String solution) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < solution.length(); i++) {
            if (solution.substring(i,i+1).equals(String.valueOf(guess))) {
                indices.add(i);
                continue;
            }
        }
        return indices;
    }

    static List<Integer> checkGuess(final String guess, final String solution) {
        List<Integer> bool = new ArrayList<>();
        if (guess.equals(solution)) {
            bool.add("true".hashCode());
        } else {
            bool.add("false".hashCode());
        }
        return bool;
    }
}

class Player {
    private Game game;
    private static final int MAX_TRIES = 8;

    Player() {
        this.game = new Game();
    }

    String guess() {
        System.out.print("Enter a letter:\n>>> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    boolean isAlive() {
        if (this.game.getTries() < Player.MAX_TRIES) {
            return true;
        }
        return false;
    }

    void win(final int lives) {
        System.out.println("You won with " + String.valueOf(lives) + " tries remaining!");
    }

    void lose() {
        System.out.println("You lose!");
    }

    void play() {
        while (this.isAlive()) {
            System.out.println(this.game.getSolution());
            this.game.displayWord();
            String guess = this.guess();
            List<Integer> result = Checker.check(guess, this.game.getSolution());
            if (result.size() == 0 || result.get(0) == 97196323) {
                // Try again
                this.game.increaseTries();
                continue;
            } else if (result.get(0) == 3569038) {
                // Instant win
                this.game.solve();
                this.win(this.MAX_TRIES - this.game.getTries());
                break;
            } else {
                this.game.replaceWord(guess, result);
                if (this.game.isSolved()) {
                    this.win(this.MAX_TRIES - this.game.getTries());
                    break;
                }
            }
        }
        if (! this.game.isSolved()) {
            this.lose();
        }
    }

    static int getMaxTries() {
        return Player.MAX_TRIES;
    }
}

class Hangman {
    private static boolean play = true;

    static boolean ask() {
        System.out.print("Do you want to play a game? [y/n]\n>>>");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().substring(0,1).toLowerCase();
        if (input.equals("y")) {
            return true;
        }
        return false;
    }

    static void loop() {
        if (Hangman.ask()) {
            Player player = new Player();
            player.play();
            return;
        }
        Hangman.play = false;
    }
    public static void main(String[] args) {
        while (Hangman.play) {
            Hangman.loop();
        }
    }
}
