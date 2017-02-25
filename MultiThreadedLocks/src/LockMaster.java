import java.util.concurrent.locks.Lock;

/**
 * The Class LockMaster.
 */
public class LockMaster extends Thread {

	/** The num attempts. */
	private final int numAttempts;

	/** The lock. */
	private final Lock lock;

	/** The counter. */
	public static int counter;

	/**
	 * Instantiates a new lock master.
	 *
	 * @param lock
	 *            the lock
	 * @param numAttempts
	 *            the num attempts
	 */
	public LockMaster(Lock lock, int numAttempts) {
		this.lock = lock;
		this.numAttempts = numAttempts;
	}

	/**
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		for (int i = 0; i < numAttempts; i++) {
			// Acquiring lock on object
			lock.lock();
			try {
				// Performing critical section of code
				criticalSection();
			} finally {
				// Unlocking object
				lock.unlock();
			}
		}
	}

	/**
	 * Critical section.
	 */
	private void criticalSection() {
		// Incrementing counter
		counter++;
	}
}