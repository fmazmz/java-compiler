public enum TokenType {
    // Expression symbols
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
    PERCENT("%"),
    BANG("!"),
    ASSIGN("="),
    LT("<"),
    GT(">"),

    // Keywords
    CLASS("class"),
    PUBLIC("public"),
    PRIVATE("private"),
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

    private String token;

    public TokenType tokenType(String token) {
        this.token(token);
    }
}
