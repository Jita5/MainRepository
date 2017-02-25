import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import javax.swing.JOptionPane;

/**
 * The Class SpinLockControl.
 */
public class SpinLockControl {

	/** The num threads. */
	private static int numThreads;

	/** The num attempts. */
	private static int numAttempts;

	/** The bakery lock data. */
	private static String bakeryLockData = "";

	/** The ttas lock data. */
	private static String ttasLockData = "";

	/** The a lock data. */
	private static String aLockData = "";

	/** The output. */
	private static String output = "";

	/** The start time. */
	private static long startTime = 0;

	/** The end time. */
	private static long endTime = 0;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// Thread label for data output
		output = ",2,4,8,16\n";
		numThreads = 2;
		// Informational message
		JOptionPane.showMessageDialog(null,
				"This program will test all three locks at once\n" + "for all number of threads 2^k (k = 1, 2, 3, 4)\n"
						+ "and will output the data to a csv file located in\n"
						+ "C:/temp/ with file name LockData(x) where x is\n" + "the number of attempts.");
		// Get user input for number of times each thread should increase
		// counter
		String userInput = JOptionPane
				.showInputDialog("Enter the number of times each thread should increment the counter: ");

		// Checking user input
		if (userInput.isEmpty() == true) {
			JOptionPane.showMessageDialog(null, "Please enter a value larger than 1");
			System.exit(0);
		} else {
			numAttempts = Integer.parseInt(userInput);
		}

		// Loop to run the Bakery Lock through 2, 4, 8, 16 threads
		System.out.println("Bakery Lock");
		bakeryLockData = "Bakery Lock";
		for (int i = 1; i <= 4; i++) {
			System.out.println("Num of Threads: " + numThreads);
			BakeryLock bakeLock = new BakeryLock(numThreads);
			startTest(bakeLock);
			bakeryLockData += "," + (endTime - startTime);
			// Increment number of threads
			numThreads = numThreads * 2;
			// resetting counter for next test
			LockMaster.counter = 0;
		}

		// Adding bakery lock data to output data and resetting number of
		// threads
		output += bakeryLockData + "\n";
		numThreads = 2;

		// Loop to run the TTAS Lock through 2, 4, 8, 16 threads
		System.out.println("TTAS Lock");
		ttasLockData = "TTAS Lock";
		for (int i = 1; i <= 4; i++) {
			System.out.println("Num of Threads: " + numThreads);
			startTest(new TTASLock());
			ttasLockData += "," + (endTime - startTime);
			// Increment number of threads
			numThreads = numThreads * 2;
			// resetting counter for next test
			LockMaster.counter = 0;
		}

		// Adding ttas lock data to output data and resetting number of threads
		output += ttasLockData + "\n";
		numThreads = 2;

		// Loop to run the ALock through 2, 4, 8, 16 threads
		System.out.println("ALock");
		aLockData = "ALock";
		for (int i = 1; i <= 4; i++) {
			System.out.println("Num of Threads: " + numThreads);
			ALock aLock2 = new ALock(numThreads);
			startTest(aLock2);
			aLockData += "," + (endTime - startTime);
			// Increment number of threads
			numThreads = numThreads * 2;
			// resetting counter for next test
			LockMaster.counter = 0;
		}

		// Adding ALock data to output data
		output += aLockData;
		output();

		System.out.println(bakeryLockData);
		System.out.println(ttasLockData);
		System.out.println(aLockData);
		System.out.println(output);
	}

	/**
	 * Start test.
	 *
	 * @param lock
	 *            the lock
	 */
	private static void startTest(Lock lock) {
		System.out.print("Time Taken = ");
		try {
			startThreads(lock, numThreads);
			// Added pause to avoid lock over lap
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start threads.
	 *
	 * @param lock
	 *            the lock
	 * @param numThreads
	 *            the num threads
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private static void startThreads(Lock lock, int numThreads) throws InterruptedException {

		// Creating new Thread set
		Thread[] ThreadSet = new Thread[numThreads];

		// Setting id's to 0
		ThreadID.reset();

		// Initializing thread set
		for (int i = 0; i < numThreads; i++) {
			ThreadSet[i] = new LockMaster(lock, numAttempts);
		}

		// Starting timer
		startTime = System.currentTimeMillis();

		// Starting the threads
		for (int i = 0; i < numThreads; i++) {
			ThreadSet[i].start();
		}

		// Stopping Threads
		for (int i = 0; i < numThreads; i++) {
			ThreadSet[i].join();
		}

		// Stopping timer
		endTime = System.currentTimeMillis();

		System.out.println(String.format("%-10d", (endTime - startTime)) + "Counter: " + LockMaster.counter);
		System.out.flush();
	}

	/**
	 * Output.
	 */
	public static void output() {
		try {
			File outFile = new File("C:/temp/LockData" + numAttempts + ".csv");
			PrintStream out = new PrintStream(new FileOutputStream(outFile));

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(output);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				out.println(line);
			}
			out.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
}