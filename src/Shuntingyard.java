

public class Shuntingyard {
	
	private enum Precedence
    {
		L_BRACE(0), R_BRACE(1), L_PAR(2), R_PAR(3), PLUS(4), MINUS(5), DIV(6), MULT(7), eos(8), operand(8);
 
        private int index;
        Precedence(int index){
            this.index = index;
        }
        public int getIndex(){
            return index;
        }        
    } 
	/** in stack precedence **/
    private static final int[] isp = {0, 19, 12, 12, 13, 13, 13, 0};
    /** incoming character precedence **/
    private static final int[] icp = {20, 19, 12, 12, 13, 13, 13, 0};
    /** operators **/
    private static final char[] operators = { '[', ']', '(', ')', '+', '-', '/', ' '};
    /** precedence stack **/
    private Precedence[] stack; 
    /** stack top pointer **/
    private int top;
 
    /** pop element from stack **/
    private Precedence pop()
    {
        return stack[top--];
    }
    /** push element onto stack **/
    private void push(Precedence ele)
    {
        stack[++top] = ele;
    }
    /** get precedence token for symbol **/
    public Precedence getToken(char symbol)
    {
        switch (symbol)
        {
        case '['  : return Precedence.L_BRACE;
        case ']'  : return Precedence.R_BRACE;
        case '('  : return Precedence.L_PAR;
        case ')'  : return Precedence.R_PAR;
        case '+'  : return Precedence.PLUS;
        case '-'  : return Precedence.MINUS;
        case '/'  : return Precedence.DIV;
        case '*'  : return Precedence.MULT;
        case ' '  : return Precedence.eos;
        default   : return Precedence.operand;
        }
    }
	public String postfix(String infix)
    {
        String postfix = "";
        top = 0;
        stack = new Precedence[infix.length()];
        stack[0] = Precedence.eos;
        Precedence token;
        for (int i = 0; i < infix.length(); i++)
        {
            token = getToken(infix.charAt(i));
            /** if token is operand append to postfix **/
            if (token == Precedence.operand)
                postfix = postfix + infix.charAt(i);
            /** if token is right parenthesis pop till matching left parenthesis **/
            else if (token == Precedence.R_PAR)
            {
                while (stack[top] != Precedence.L_PAR)
                    postfix = postfix + operators[pop().getIndex()];
                /** discard left parenthesis **/
                pop();
            }
            /** else pop stack elements whose precedence is greater than that of token **/
            else
            {
                while (isp[stack[top].getIndex()] >= icp[token.getIndex()])
                    postfix = postfix + operators[pop().getIndex()];
                push(token);
            }
        }
        /** pop any remaining elements in stack **/
        while ((token = pop()) != Precedence.eos)
            postfix = postfix + operators[token.getIndex()];
 
        return postfix;
    }
}
