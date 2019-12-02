
public class Queue {

	/**
	 * Creates a new empty queue
	 */
	public Queue() {
		mQueue = new List();
	}
	
	/**
	 * Adds item to the rear of the Queue
	 * @param item to be set to the top
	 */
	public void add(String item) {
		mQueue.addToRear(item);
	}
	
	/**
	 * Remove AND return the front of the Queue
	 * @param none
	 * @return front of the Queue
	 */
	public String remove() {
		String front = mQueue.getFront();
		mQueue.removeFront();
		return front;
	}
	
	/**
	 * Return true if Queue is empty, false otherwise
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return mQueue.askCount() == 0;
	}
	
	/**
	 * print the content of the Queue using the same format as
	 * the List print
	 * @param [fix javadoc later]
	 */
	public void dump(String title) {
		mQueue.print(title);
	}
	
	// Instance variable
	private List mQueue;
}
