import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private int currentPos;

    public Lexer(String input) {
        this.input = input;
        this.currentPos = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentPos < input.length()) {
            char currentChar = input.charAt(currentPos);

            if (Character.isWhitespace(currentChar)) {
                currentPos++;
                continue;
            }

            Token token = nextToken();

            if (token != null)
                tokens.add(token);
            else
                throw new RuntimeException("Unkown character: " + token);
        }

        return tokens;
    }

    public Token nextToken() {
        if (currentPos >= input.length()) {
            return null;
        }

        String[] tokenPatterns = {
                "if|else|while|for",
                "[a-zA-Z_][a-zA-Z0-9_]*",
                "\\d+",
                "[+-/*=<>!]",
                "[.,;(){}]",
        };

        TokenType[] tokenTypes = TokenType.values();

        for (int i = 0; i < tokenPatterns.length; i++) {
            Pattern pattern = Pattern.compile("^" + tokenPatterns[i]);
            Matcher matcher = pattern.matcher(input.substring(currentPos));

            if (matcher.find()) {
                String value = matcher.group();
                currentPos += value.length();
                return new Token(tokenTypes[i], value);
            }
        }
        return null;
    }
}
