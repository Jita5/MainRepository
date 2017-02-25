import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * The Class TTASLock.
 */
public class TTASLock implements Lock {

	/**
	 * The lock.
	 */
	AtomicBoolean lock = new AtomicBoolean(false);

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#lock()
	 */
	public void lock() {
		while (true) {
			while (lock.get()) {
			}

			if (!lock.getAndSet(true)) {
				return;
			}
		}
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#unlock()
	 */
	public void unlock() {
		lock.set(false);
	}

	/**
	 * 
	 * @see java.util.concurrent.locks.Lock#newCondition()
	 */
	public Condition newCondition() {
		throw new java.lang.UnsupportedOperationException();
	}

	/**
	 * 
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