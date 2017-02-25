/**
 * The Class ThreadID.
 */
public class ThreadID {

	/** The next id. */
	private static volatile int nextID = 0;

	/** The thread id. */
	private static ThreadLocalID threadID = new ThreadLocalID();

	/**
	 * Gets the.
	 *
	 * @return the int
	 */
	public static int get() {
		return threadID.get();
	}

	/**
	 * Reset.
	 */
	public static void reset() {
		nextID = 0;
	}

	/**
	 * Sets the.
	 *
	 * @param value
	 *            the value
	 */
	public static void set(int value) {
		threadID.set(value);
	}

	/**
	 * The Class ThreadLocalID.
	 */
	private static class ThreadLocalID extends ThreadLocal<Integer> {

		/**
		 * 
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		protected synchronized Integer initialValue() {
			return nextID++;
		}
	}
}