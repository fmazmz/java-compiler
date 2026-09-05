import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private final String input;
    private int currentPos;

    // Longest ops first so "==" is not lexed as two "=" tokens.
    private static final Pattern[] PATTERNS = {
            Pattern.compile("^==|!=|<=|>=|&&|\\|\\|"),
            Pattern.compile("^[+\\-*/%=<>!.,;(){}]"),
            Pattern.compile("^\\d+"),
            Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*"),
    };

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
            if (token == null) {
                throw new RuntimeException("Unknown character: '" + currentChar + "'");
            }
            tokens.add(token);
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    public Token nextToken() {
        if (currentPos >= input.length()) {
            return null;
        }

        String remaining = input.substring(currentPos);

        for (Pattern pattern : PATTERNS) {
            Matcher matcher = pattern.matcher(remaining);
            if (!matcher.find()) {
                continue;
            }

            String value = matcher.group();
            currentPos += value.length();
            return new Token(resolveType(value), value);
        }

        return null;
    }

    private TokenType resolveType(String value) {
        TokenType byLexeme = TokenType.fromLexeme(value);
        if (byLexeme != null) {
            return byLexeme;
        }
        if (Character.isDigit(value.charAt(0))) {
            return TokenType.INT_LIT;
        }
        return TokenType.IDENT;
    }
}
