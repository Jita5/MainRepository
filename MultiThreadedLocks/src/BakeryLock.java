import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * The Class BakeryLock.
 */
public class BakeryLock implements Lock {

	/**
	 * The flag.
	 */
	volatile boolean[] flag;

	/**
	 * The label.
	 */
	volatile Label[] label;

	/**
	 * Instantiates a new bakery lock.
	 *
	 * @param threads
	 *            the threads
	 */
	public BakeryLock(int threads) {
		label = new Label[threads];
		for (int i = 0; i < label.length; i++) {
			label[i] = new Label();
		}
		flag = new boolean[threads];
	}

	/**
	 * Lock method
	 * 
	 * @see java.util.concurrent.locks.Lock#lock()
	 */
	public void lock() {
		int me = ThreadID.get();
		flag[me] = true;
		int max = Label.max(label);
		label[me] = new Label(max + 1);
		while (conflict(me)) {
		}
	}

	/**
	 * Unlock method
	 * 
	 * @see java.util.concurrent.locks.Lock#unlock()
	 */
	public void unlock() {
		flag[ThreadID.get()] = false;
	}

	/**
	 * Conflict.
	 *
	 * @param me
	 *            the me
	 * @return true, if successful
	 */
	private boolean conflict(int me) {
		for (int i = 0; i < label.length; i++) {
			if (i != me && flag[i] && label[me].compareTo(label[i]) < 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The Class Label.
	 */
	static class Label implements Comparable<Label> {

		/**
		 * The counter.
		 */
		int counter;

		/**
		 * The id.
		 */
		int id;

		/**
		 * Instantiates a new label.
		 */
		Label() {
			counter = 0;
			id = ThreadID.get();
		}

		/**
		 * Instantiates a new label.
		 *
		 * @param c
		 *            the c
		 */
		Label(int c) {
			counter = c;
			id = ThreadID.get();
		}

		/**
		 * Max.
		 *
		 * @param labels
		 *            the labels
		 * @return the int
		 */
		static int max(Label[] labels) {
			int c = 0;
			for (Label label : labels) {
				c = Math.max(c, label.counter);
			}
			return c;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(BakeryLock.Label other) {
			if (this.counter < other.counter || (this.counter == other.counter && this.id < other.id)) {
				return -1;
			} else if (this.counter > other.counter) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.locks.Lock#newCondition()
	 */
	public Condition newCondition() {
		throw new java.lang.UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.locks.Lock#tryLock(long,
	 * java.util.concurrent.TimeUnit)
	 */
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.locks.Lock#tryLock()
	 */
	public boolean tryLock() {
		throw new java.lang.UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.locks.Lock#lockInterruptibly()
	 */
	public void lockInterruptibly() throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}
}
