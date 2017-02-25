/**
 * RefrigeratorDisplay Class
 */
import java.util.Observable;

/**
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */
public abstract class RefrigeratorDisplay extends Observable {

	/** The fridge context. */
	protected static FridgeContext fridgeContext;

	/** The freezer context. */
	protected static FreezerContext freezerContext;

	/** The instance. */
	protected static RefrigeratorDisplay instance;

	/**
	 * Initializes the fridge and freezer context and instance.
	 */
	protected RefrigeratorDisplay() {
		instance = this;
		fridgeContext = FridgeContext.instance();
		freezerContext = FreezerContext.instance();
	}

	/**
	 * For singleton.
	 *
	 * @return the object
	 */
	public static RefrigeratorDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer.
	 */
	public void initialize() {
		instance().addObserver(fridgeContext);
		instance().addObserver(freezerContext);
		fridgeContext.initialize();
		freezerContext.initialize();
	}

	/**
	 * Indicates fridge temperature.
	 *
	 * @param temp
	 *            the temp
	 */
	public abstract void displayFridgeTemp(int temp);

	/**
	 * Indicates that the fridge light is on.
	 */
	public abstract void turnFridgeLightOn();

	/**
	 * Indicates that the fridge light is off.
	 */
	public abstract void turnFridgeLightOff();

	/**
	 * Indicates that the fridge door is closed.
	 */
	public abstract void fridgeDoorClosed();

	/**
	 * Indicates that the fridge door is open.
	 */
	public abstract void fridgeDoorOpened();

	/**
	 * Indicates that the fridge is running.
	 */
	public abstract void fridgeRunning();

	/**
	 * Indicates that fridge is idle.
	 */
	public abstract void fridgeNotRunning();

	/**
	 * Indicates that the freezer light is on.
	 */
	public abstract void turnFreezerLightOn();

	/**
	 * Indicates that the freezer light is off.
	 */
	public abstract void turnFreezerLightOff();

	/**
	 * Indicates that the freezer door is closed.
	 */
	public abstract void freezerDoorClosed();

	/**
	 * Indicates that the freezer door is open.
	 */
	public abstract void freezerDoorOpened();

	/**
	 * Indicates the freezer temperature.
	 *
	 * @param value
	 *            the value
	 */
	public abstract void displayFreezerTemp(int value);

	/**
	 * Indicates the freezer is running.
	 */
	public abstract void freezerRunning();

	/**
	 * Indicates the freezer is idle.
	 */
	public abstract void freezerNotRunning();

}