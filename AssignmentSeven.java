
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class AssignmentSeven {

	public static void main(String[] args) throws IOException 
	{
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		
		String line = reader.readLine();
		while(line != null)
		{
			doLine(line);
			line = reader.readLine();
		}	
		
		reader.close();	
	}
	
	private static void doLine(String line)
	{
		// Convert line from infix (in our input file) to postfix (in a queue)
		Queue queue = new Queue();
		Stack stack = new Stack();
		StringTokenizer tokenizer = new StringTokenizer(line);
		String newLine = "";
		System.out.print("Expression is: ");
		while(tokenizer.hasMoreTokens())
		{
			String token = tokenizer.nextToken();
			System.out.print(token + " ");
			if (token.equals("(") || isOperator(token)) {
				stack.push(token);
			} else if (token.equals(")")) {
				while (!stack.getTop().contentEquals("(")) {
					String item = stack.getTop();
					queue.add(item);
					stack.pop();
				}
				stack.pop();
			} else {
				queue.add(token);
			}
		}
		
		// Now turn our queue into a String
		System.out.println();
		System.out.print("Postfix is ");
		while (!queue.isEmpty()) {
			String newToken = queue.remove();
			newToken += " ";
			System.out.print(newToken);
			newLine += newToken;
		}
		System.out.println();
		
		// Now we run our old code with our newLine (postfix)
		Stack newStack = new Stack();
		StringTokenizer newTokenizer = new StringTokenizer(newLine);
		while(newTokenizer.hasMoreTokens())
		{
			String newToken = newTokenizer.nextToken();
			if(isOperator(newToken)) {
				evaluate(newStack, newToken);
			}
			else {
				newStack.push(newToken);
			}
		}
		System.out.println("Value: " + newStack.getTop());
		System.out.println();
	}
	
	private static boolean isOperator(String token)
	{
		return	token.equals("+") || token.equals("-") ||
				token.equals("*") || token.equals("/") ||
				token.equals("sin") || token.equals("cos") || token.equals("tan");
	}
	
	private static void evaluate(Stack s, String operator)
	{
		if(isBinary(operator)) {
			evaluateBinary(s, operator);
		}
		else {
			evaluateUnary(s, operator);
		}
	}
	
	private static boolean isBinary(String operator)
	{
		return 	operator.equals("+") || operator.equals("-") ||
				operator.equals("*") || operator.equals("/");
	}

	private static void evaluateBinary(Stack s, String operator)
	{
		Double y = Double.parseDouble(s.getTop());
		s.pop();
		Double x = Double.parseDouble(s.getTop());
		s.pop();
		
		Double value = 0.0;
		if(operator.equals("+")) value = x + y;
		else if(operator.equals("-")) value = x - y;
		else if(operator.equals("*")) value = x * y;
		else value = x / y;
		
		s.push(String.valueOf(value));
	}
	
	private static void evaluateUnary(Stack s, String operator)
	{
		Double x = Double.parseDouble(s.getTop());
		s.pop();
		
		double value = 0.0;
		double radians = PI * x;
		
		if(operator.equals("sin")) value = Math.sin(radians);
		else if(operator.equals("cos")) value = Math.cos(radians);
		else value = Math.tan(radians);
		
		s.push(String.valueOf(value));
	}
	
	private static final double PI = Math.PI / 180.0;
}
