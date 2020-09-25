package cs2321;

public class PostfixExpression {
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) throws IllegalArgumentException {
	    // Create a stack.
	    DLLStack<Integer> stack = new DLLStack<>();

	    // Create a string array of tokens from the expression, removing all whitespace.
	    String[] tokens = exp.split("\\s+");

	    // Loops through all tokens.
        for (String token : tokens) {
            // If the token is a number, push it to the stack, then go to the next token.
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            // If the token is not a number defined above and the token is not an operator,
            // as they have a length of 1, then it is not a valid operation.
            else if (token.length() > 1) {
                throw new IllegalArgumentException("Invalid input");
            }

            // Gets the first and second term from the stack.
            int second = stack.pop();
            int first = stack.pop();

            // Tests which operand the token is, then performs the operation and pushes the
            // result back onto the stack.
            switch (token) {
                case "+":
                    stack.push(first + second);
                    break;
                case "-":
                    stack.push(first - second);
                    break;
                case "*":
                    stack.push(first * second);
                    break;
                case "/":
                    stack.push(first / second);
                    break;
            }
        }
        // Gets the assumed last value from the stack, which should be the total.
        int result = stack.pop();

        // Tests if the stack is empty.
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

		return result;
	}

    /**
     * Gets the precedence of a given operator, ranging from 1 for the lowest, to 3 for the highest.
     * @param str the operator to test
     * @return the precedence of an operator
     */
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
