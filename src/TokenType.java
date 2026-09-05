public enum TokenType {
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    SEMI(";"),
    COMMA(","),
    DOT("."),

    PLUS("+"),
    MINUS("-"),
    STAR("*"),
    SLASH("/"),
    PERCENT("%"),
    BANG("!"),
    ASSIGN("="),
    LT("<"),
    GT(">"),

    EQ("=="),
    NEQ("!="),
    LTE("<="),
    GTE(">="),
    AND("&&"),
    OR("||"),

    IDENT(""),
    INT_LIT(""),

    CLASS("class"),
    PUBLIC("public"),
    STATIC("static"),
    VOID("void"),
    INT("int"),
    BOOLEAN("boolean"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    RETURN("return"),
    TRUE("true"),
    FALSE("false"),

    EOF("");

    private final String lexeme;

    TokenType(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public static TokenType fromLexeme(String text) {
        for (TokenType type : values()) {
            if (!type.lexeme.isEmpty() && type.lexeme.equals(text)) {
                return type;
            }
        }
        return null;
    }
}
