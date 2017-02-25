import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ThreadLocal;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;

/**
 * The Class ALock.
 */
public class ALock implements Lock {

	/** The my slot index. */
	ThreadLocal<Integer> mySlotIndex = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};

	/** The tail. */
	AtomicInteger tail;

	/** The flag. */
	boolean[] flag;

	/** The size. */
	int size;

	/**
	 * Instantiates a new a lock.
	 *
	 * @param threads
	 *            the threads
	 */
	public ALock(int threads) {
		size = threads;
		tail = new AtomicInteger(0);
		flag = new boolean[threads];
		flag[0] = true;
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#lock()
	 */
	public void lock() {
		int slot = tail.getAndIncrement() % size;
		mySlotIndex.set(slot);
		while (!flag[mySlotIndex.get()]) {
			// System.out.println("Thread: " + slot);
		}
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#unlock()
	 */
	public void unlock() {
		flag[mySlotIndex.get()] = false;
		flag[(mySlotIndex.get() + 1) % size] = true;
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#newCondition()
	 */
	public Condition newCondition() {
		throw new java.lang.UnsupportedOperationException();
	}

	/**
	 * @see java.util.concurrent.locks.Lock#tryLock(long,
	 *      java.util.concurrent.TimeUnit)
	 */
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#tryLock()
	 */
	public boolean tryLock() {
		throw new java.lang.UnsupportedOperationException();
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#lockInterruptibly()
	 */
	public void lockInterruptibly() throws InterruptedException {
		throw new java.lang.UnsupportedOperationException();
	}
}
