import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scorer {

    static int lineSize(String line) {
        char[] chars = line.toCharArray();
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isSpace(chars[i]));
                size++;
        }
        return size;
    }

    private static boolean isSpace(char aChar) {
        return aChar == ' ';
    }

    static int linesSize(List<String> lines) {
        int size = 0;
        for (String line : lines) {
            size += lineSize(line);
        }
        return size;
    }


    static String hr(int width) {
        StringBuffer buf = new StringBuffer();

        buf.append("-----|");
        for (int i = 0; i < width; i++) {
            buf.append("-");
        }

        return buf.toString();
    }

    static void printProgramSize(List<String> lines) {


        int totalSize = 0;

        System.out.println(hr(60));

        for (String line : lines) {

            int size = lineSize(line);
            System.out.println("   " +  size + "  |" + line);
            totalSize += size;
        }

        System.out.println(hr(60));

        System.out.println("   " + totalSize + " == CountDown.java.size");

        System.out.println("");
    }

// - - - - - - - - - - - - - - - - - - - -

    static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String sCurrentLine;
            while ((sCurrentLine = reader.readLine()) != null) {
                lines.add(sCurrentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

// - - - - - - - - - - - - - - - - - - - -

    static boolean uses(List<String> lines, String token) {
        // ... also matches .
        // double also matches do
        // etc etc

        for (String line : lines) {
            if (line.contains(token)) {
                return true;
            }
        }

        return false;
    }

    static int tokensSize(List<String> lines) {
        int size = 0;
        for (String token : Tokens.tokens) {
            if(uses(lines, token)) {
                size += token.length();
            }
        }

        return size;
    }

    static boolean missingTokens(List<String> lines) {
        List<String> unused = new ArrayList<>();
        for (String token : Tokens.tokens) {
            if (!uses(lines, token)) {
                return true;
            }
        }

        return false;
    }

// - - - - - - - - - - - - - - - - - - - -

    static void printTokenBonuses(List<String> lines) {
        int tokensSize = 0;
        System.out.println(hr(20));

        for (String token : Tokens.tokens) {
            if (uses(lines, token)) {
                System.out.println("   " + token.length() + "  |" + token);
                tokensSize += token.length();
            }
        }

        for (String token : Tokens.tokens) {
            if (!uses(lines, token)) {
                System.out.println("   " + 0 + "  |" + token);
            }
        }

        System.out.println(hr(20));

        System.out.println("   " + tokensSize + " == used_tokens.size");

        int completion_bonus = missingTokens(lines) ? 0 : 50;

        System.out.println("   " + completion_bonus + "  == completion.bonus");
    }

// - - - - - - - - - - - - - - - - - - - -

    public static void main(String[] args) {

        List<String> lines = readLines(args[1]);
        int program_size = linesSize(lines);
        int used_token_bonus = tokensSize(lines);
        int completion_bonus = missingTokens(lines) ? 0 : 50;

        System.out.println(">>> Score = -CountDown.java.size + 3*usedTokens.size + completion.bonus");
        System.out.println("   " + (-program_size) + " + 3*" + used_token_bonus + " + " + completion_bonus);
        System.out.println(">>>       = " + (-program_size + (3 * used_token_bonus) + completion_bonus));

        System.out.println();
        printTokenBonuses(lines);
        System.out.println();
        printProgramSize(lines);

        // green-traffic light pattern...put it out of sight
        for (int i = 0; i < 100; i++)
            System.out.println();
        System.out.println("All tests passed");
    }

}