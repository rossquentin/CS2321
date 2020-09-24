package cs2321;

public class InfixToPostfix {
	/* Convert an infix expression and to a postfix expression
	 * infix expression : operator is between operands. Ex. 3 + 5
	 * postfix Expression: operator is after the operands. Ex. 3 5 +
	 * 
	 * The infixExp expression includes the following
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      and parenthesis: ( , )
	 *      
	 *      For easy parsing the expression, there is a space between operands and operators, parenthesis. 
	 *  	Ex: "1 * ( 3 + 5 )"
	 *      Notice there is no space before the first operand and after the last operand/parentheses. 
	 *  
	 * The postExp includes the following 
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      
	 *      For easy parsing the expression, there should have a space between operands and operators.
	 *      Ex: "1 3 5 + *"
	 *      Notice there is space before the first operand and last operator. 
	 *      Notice that postExp does not have parenthesis. 
	 */

    /**
     * Converts an infix expression to a postfix expression.
     * @param infixExp the infix expression
     * @return the converted postfix expression
     */
	public static String convert(String infixExp) {
        // Creates an array of tokens without any whitespace from the given infix expression
		String[] tokens = infixExp.split("\\s+");

		// Creates a stack and a StringBuilder to use for the output.
		DLLStack<String> stack = new DLLStack<>();
		StringBuilder output = new StringBuilder();

		// Loop through all tokens
		for (String token : tokens) {
		    // If it's a number, push to output
            if (isNumeric(token)) {
                output.append(token).append(" ");
            }

		    // If it's an "(", push to stack
            else if (token.equals("(")) {
		        stack.push(token);
            }

		    // If it's an ")", pop all elements from stack until "(", push those elements to output
            // then remove "(" from the stack
		    else if (token.equals(")")) {
		        while (!stack.top().equals("(")) {
		            output.append(stack.pop()).append(" ");
                }
		        stack.pop();
            }

		    // If it's an operator, check if the stack is empty and if the token given is a lower or equal precedence
            // than the top element in the stack. Finally, push the current token to the stack.
		    else {
                while (!stack.isEmpty() && getPrecedence(token) <= getPrecedence(stack.top())) {
                    output.append(stack.pop()).append(" ");
                }
		        stack.push(token);
            }
        }

		// Reached end of tokens. Pop all elements in stack and push to output.
		while (!stack.isEmpty()) {
		    output.append(stack.pop()).append(" ");
        }

		// This implementation adds a whitespace to the end of the string. Return the
        // substring without the ending whitespace.
		return output.substring(0, output.length()-1);
	}

    /**
     * Gets the precedence of a given operator, ranging from 1 for the lowest, to 3 for the highest.
     * @param op the operator to test
     * @return the precedence of an operator
     */
    private static int getPrecedence(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Tests if a given string is a number.
     * @param str the string to test
     * @return true if the string is numeric, false otherwise
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}