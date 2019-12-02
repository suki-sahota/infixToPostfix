
public class Stack 
{
	public Stack()
	{
		mStack = new List();
	}
	
	public void push(String item)
	{
		mStack.addToFront(item);
	}
	
	public void pop()
	{
		mStack.removeFront();
	}
	
	public String getTop()
	{
		return mStack.getFront();
	}
	
	public boolean isEmpty()
	{
		return mStack.askCount() == 0;
	}

	public void dump(String title)
	{
		mStack.print(title);
	}
	
	private List mStack;
}
