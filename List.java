
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author 003598177
 * 
 * A simple List class with Doubly Linked implementation
 *
 */
public class List 
{
	/**
	 * Create an empty List
	 */
	public List()
	{
		mFront = new Node(Node.DUMMY);
		mRear = new Node(Node.DUMMY);
		mFront.mNext = mRear;
		mRear.mPrev = mFront;
	}
	
	/**
	 * Create a new List that is a copy of the list input
	 * @param list the List to copy
	 */
	public List(List list)
	{
		this();
		for(Node current = list.mFront.mNext; current != list.mRear; current = current.mNext)
		{
			addToRear(current.mData);
		}
	}
		
	/**
	 * Item becomes the new front element
	 * @param item the item to add
	 */
	public void addToFront(String item) 
	{
		Node newNode = new Node(item);
		newNode.mPrev = mFront;
		newNode.mNext = mFront.mNext;
		newNode.mPrev.mNext = newNode;
		newNode.mNext.mPrev = newNode;
	}
	
	/**
	 * Item becomes the new rear element
	 * @param item the item to add
	 */
	public void addToRear(String item) 
	{
		Node newNode = new Node(item);
		newNode.mNext = mRear;
		newNode.mPrev = mRear.mPrev;
		newNode.mPrev.mNext = newNode;
		newNode.mNext.mPrev = newNode;
	}
	
	/**
	 * If beforeItem is not in List, prints "Item Not Found" else add item in List before beforeItem
	 * @param beforeItem the item in the list to add item before
	 * @param item the item to add
	 */
	public void addBeforeItem(String beforeItem, String item) 
	{
		Node beforeNode = find(beforeItem);
		if(beforeNode == null)
		{
			sop("Item Not Found");
			return;
		}
	
		Node newNode = new Node(item);
		newNode.mPrev = beforeNode.mPrev;
		newNode.mNext = beforeNode;
		newNode.mPrev.mNext = newNode;
		newNode.mNext.mPrev = newNode;
	}
	
	/**
	 * Item becomes the element after afterItem
	 * @param afterItem the item in the list to add item before
	 * @param item the item to add
	 */
	public void addAfterItem(String afterItem, String item) 
	{
		Node afterNode = find(afterItem);
		if(afterNode == null)
		{
			sop("Item Not Found");
			return;
		}
		
		Node newNode = new Node(item);
		newNode.mPrev = afterNode;
		newNode.mNext = afterNode.mNext;
		newNode.mNext.mPrev = newNode;
		newNode.mPrev.mNext = newNode;
	}
	
	/**
	 * Returns the item at the front of the List (List is not altered)
	 * @return the item at the front of the List
	 */
	public String getFront() 
	{
		if(askCount() == 0)
		{
			sop("List Empty");
			return "";
		}
		
		return(mFront.mNext.mData);
	}
	
	/**
	 * Returns the item at the rear of the List (List is not altered)
	 * @return the item at the rear of the List
	 */
	public String getRear() 
	{
		if(askCount() == 0)
		{
			sop("List Empty");
			return "";
		}
		
		return(mRear.mPrev.mData);
	}
	
	/**
	 * Return true if item is in List, false otherwise
	 * @param item to check presence in List
	 * @return true if item is in List, false otherwise
	 */
	public boolean isPresent(String item)
	{
		if(find(item) != null) return true;
		else return false;
	}
	
	/**
	 * Returns the number of items in the List
	 * @return the number of items in the List
	 */
	public int askCount()
	{
		int count = 0;
		Node current = mFront.mNext;
		while(current != mRear) 
		{
			current = current.mNext;
			++count;
		}
		
		return count;
	}
	
	/**
	 * If the List is empty, prints "List Empty"
	 * If the List is not empty, removes the item at the front of the List
	 */
	public void removeFront() 
	{
		if(askCount() == 0)
		{
			sop("List Empty");
			return;
		}
		removeItem(mFront.mNext.mData);
	}
	
	/**
	 * If the List is empty, prints "List Empty"
	 * If the List is not empty, removes the item at the rear of the List
	 */
	public void removeRear() 
	{
		if(askCount() == 0)
		{
			sop("List Empty");
			return;
		}
		removeItem(mRear.mPrev.mData);
	}
	
	/**
	 * If the List is empty, prints "List Empty"
	 * If item is not present in List, prints "Item not found"
	 * Otherwise, item is removed from the List
	 * @param item the item to remove
	 */
	public void removeItem(String item) 
	{
		if(askCount() == 0)
		{
			sop("List Empty");
			return;
		}
		
		Node removeNode = find(item);
		if(removeNode == null)
		{
			sop("Item not found");
			return;
		}
		
		removeNode.mPrev.mNext = removeNode.mNext;
		removeNode.mNext.mPrev = removeNode.mPrev;
	}
	
	/**
	 * Print title on a line by itself
	 * Prints the List from front to rear with 1 space between each item
	 * @param title the description of the List
	 */
	public void print(String title)
	{
		System.out.println("\n" + title);
		for(Node current = mFront.mNext; current != mRear; current = current.mNext)
		{
			System.out.print(current.mData + " ");
		}
		System.out.println("");
	}
	
	/**
	 * Print title on a line by itself
	 * Prints the Sorted List with 1 space between each item
	 * Does not alter the List
	 * @param title the description of the List
	 */
	public void printSorted(String title)
	{
		ArrayList<String> tempList = new ArrayList<String>();
		for(Node current = mFront.mNext; current != mRear; current = current.mNext)
		{
			tempList.add(current.mData);
		}
		
		Collections.sort(tempList);
		System.out.println("\n" + title);
		for(String s : tempList)
		{
			System.out.print(s + " ");
		}
		
		System.out.println("");
		
	}
	
	private class Node
	{
		public Node(String data)
		{
			mData = data;
			mPrev = null;
			mNext = null;
		}
		
		private static final String DUMMY= "dummy";
		private String mData;
		private Node mPrev;
		private Node mNext;
	}
	
	private Node find(String item)
	{
		for(Node current = mFront.mNext; current != mRear; current = current.mNext)
		{
			if(current.mData.equals(item)) return current;
		}
		
		return null;
	}
	
	private static void sop(String s)
	{
		System.out.println(s);
	}
		
	private Node mFront;
	private Node mRear;
}
