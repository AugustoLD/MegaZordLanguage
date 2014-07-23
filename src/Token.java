import java.io.*;

public class Token {

  public enum TokenType {
    INT_TYPE, CHAR_TYPE, BOOL_TYPE, 
    IF_DECL, ELSE_DECL, 
    WHILE_DECL,
    NUMERIC, STRING,
    ID, 
    SUM, DIF, MULT, DIV};

  TokenType tipo;
  String key;
  Double numValue;

  Token(TokenType t, String key, Double numValue){
    this.tipo = t;
    this.key = key;
    this.numValue = numValue;
  }

  public TokenType getType() {
    return(this.tipo);
  }

  public String toString() {
    return("<" + tipo + ", " + key + ", " + numValue + ">");
  }


}
