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
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
                continue;
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
}
