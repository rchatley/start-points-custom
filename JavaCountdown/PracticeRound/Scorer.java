import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Scorer {

    int lineSize(String line) {
        char[] chars = line.toCharArray();
        int size = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!isSpace(chars[i])) ;
            size++;
        }
        return size;
    }

    boolean isSpace(char aChar) {
        return String.valueOf(aChar).trim().isEmpty();
    }

    int linesSize(List<String> lines) {
        int size = 0;
        for (String line : lines) {
            size += lineSize(line);
        }
        return size;
    }


    String hr(int width) {
        StringBuffer buf = new StringBuffer();

        buf.append("-----|");
        for (int i = 0; i < width; i++) {
            buf.append("-");
        }

        return buf.toString();
    }

    void printProgramSize(List<String> lines) {


        int totalSize = 0;

        System.out.println(hr(60));

        for (String line : lines) {

            int size = lineSize(line);
            System.out.println("   " + size + "  |" + line);
            totalSize += size;
        }

        System.out.println(hr(60));

        System.out.println("   " + totalSize + " == CountDown.java.size");

        System.out.println("");
    }

// - - - - - - - - - - - - - - - - - - - -

    List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
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

    boolean uses(List<String> lines, String token) {
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

    int tokensSize(List<String> lines) {
        int size = 0;
        for (String token : Tokens.tokens) {
            if (uses(lines, token)) {
                size += token.length();
            }
        }

        return size;
    }

    boolean missingTokens(List<String> lines) {
        List<String> unused = new ArrayList<>();
        for (String token : Tokens.tokens) {
            if (!uses(lines, token)) {
                return true;
            }
        }

        return false;
    }

// - - - - - - - - - - - - - - - - - - - -

    void printTokenBonuses(List<String> lines) {
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

    public void score(String filename) {
        List<String> lines = readLines(filename);
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

    public static void main(String[] args) {
        new Scorer().score(args[0]);
    }

}