import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Wisielec {
    private static int amountofwins = 0;
    private static int amountofloses = 0;
    private static int amountoftries = 0;
    private static final int MAX_TRIES = 6;

    private static final String[] easy = { "kot", "las", "dom", "oko", "pies" };
    private static final String[] medium = { "bajka", "teren", "lampa", "trawa", "ptaki" };
    private static final String[] hard = { "zawodnik", "ogród", "programista", "cywilizacja" };
    private static ArrayList<String> custom = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playagain = true;

        while (playagain) {
            playGame();

            System.out.println("Czy chcesz zagrać jeszcze raz? (Tak/Nie): ");
            String response1 = scanner.next();
            playagain = response1.equalsIgnoreCase("tak");
        }

        System.out.println("Dziękujemy za gre!");

        scanner.close();
    }

    public static void playGame() {
        String word = difficulty();
        int numberoftries = 6;
        StringBuilder rightguess = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            rightguess.append("_");
        }

        while (numberoftries > 0) {
            System.out.println("Odgadnięte słowo: " + rightguess.toString());
            System.out.println("Podaj litere: ");

            String input = scanner.next();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Nieprawidłowa litera. Podaj tylko jedną literę.");
                continue;
            }
            amountoftries++;

            boolean correctguess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == input.charAt(0)) {
                    rightguess.setCharAt(i, input.charAt(0));
                    correctguess = true;
                }
            }
            if (!correctguess) {
                numberoftries--;
                System.out.println("Niepoprawna litera. Pozostało prób: " + numberoftries);
                drawHangman(MAX_TRIES - numberoftries);
            }
            if (word.equals(rightguess.toString())) {
                System.out.println("Gratulacje! Odgadłeś słowo: " + word);
                amountofwins++;
                break;
            }
            if (numberoftries == 0) {
                amountofloses++;
                System.out.println("Niestety, nie udało ci się odgadnąć słowa. Prawidłowe słowo to: " + word);
            }
        }
        System.out.println("Czy chcesz zobaczyć swoje statystyki? (Tak/Nie):");
        String response2 = scanner.next();
        if (response2.equalsIgnoreCase("tak")) {
            stats();
        }

    }

    public static String difficulty() {
        Random random = new Random();
        int index;
        String difficultychoice;

        do {
            System.out.println("Wybierz poziom trudności (Easy, Medium, Hard, Custom): ");
            difficultychoice = scanner.next().toLowerCase();

            switch (difficultychoice) {
                case "easy":
                    index = random.nextInt(easy.length);
                    return easy[index];
                case "medium":
                    index = random.nextInt(medium.length);
                    return medium[index];
                case "hard":
                    index = random.nextInt(hard.length);
                    return hard[index];
                case "custom":
                    if (custom.isEmpty()) {
                        System.out.println("Brak słów w poziomie Custom. Dodaj nowe słowa.");
                        addCustomWords();
                    }
                    index = random.nextInt(custom.size());
                    return custom.get(index);
                default:
                    System.out.println("Nieprawidłowy poziom trudności. Wybierz spośród: Easy, Medium, Hard.");
            }
        } while (true);
    }

    public static void addCustomWords() {
        String response;
        do {
            System.out.println("Podaj nowe słowo do poziomu Custom: ");
            String newWord = scanner.next();
            custom.add(newWord);
            System.out.println("Czy chcesz dodać kolejne słowo? (Tak/Nie): ");
            response = scanner.next();
        } while (response.equalsIgnoreCase("tak"));
    }

    public static void stats() {
        System.out.println("Ilość wygranych: " + amountofwins);
        System.out.println("Ilość przegranych: " + amountofloses);
        System.out.println("Ilość prób: " + amountoftries);
    }

    public static void drawHangman(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 1:
                System.out.println("             ");
                System.out.println("               ");
                System.out.println("               ");
                System.out.println("     ");
                System.out.println("     ");
                System.out.println("     ");
                System.out.println("  _____");
                break;
            case 2:
                System.out.println("             ");
                System.out.println("    |          ");
                System.out.println("    |          ");
                System.out.println("    |          ");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("  __|__");
                break;
            case 3:
                System.out.println("    ___________");
                System.out.println("    |          ");
                System.out.println("    |          ");
                System.out.println("    |          ");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("  __|__");
                break;
            case 4:
                System.out.println("    ___________");
                System.out.println("    |         |");
                System.out.println("    |         O");
                System.out.println("    |         |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("  __|__");
                break;
            case 5:
                System.out.println("    ___________");
                System.out.println("    |         |");
                System.out.println("    |         O");
                System.out.println("    |        /|\\");
                System.out.println("    |         ");
                System.out.println("    |");
                System.out.println("  __|__");
                break;
            case 6:
                System.out.println("    ___________");
                System.out.println("    |         |");
                System.out.println("    |         O");
                System.out.println("    |        /|\\");
                System.out.println("    |        / \\");
                System.out.println("    |");
                System.out.println("  __|__");
                break;
            default:
                break;
        }
    }
}