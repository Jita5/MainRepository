/**
 * FridgeContext Class
 * Coordinates all fridge related activity
 */
import java.util.Observable;
import java.util.Observer;

/**
 * The context is an observer for the clock and stores the context info for
 * states.
 */
public class FridgeContext implements Observer {

	/**
	 * The Enum FridgeDoorEvents.
	 */
	public static enum FridgeDoorEvents {

		/** The fridge door closed event. */
		FRIDGE_DOOR_CLOSED_EVENT,
		/** The fridge door opened event. */
		FRIDGE_DOOR_OPENED_EVENT
	}
	/**
	 * The Enum FridgeRunEvents.
	 */
	public static enum FridgeRunEvents {

		/** The fridge run event. */
		FRIDGE_RUN_EVENT,
		/** The fridge idle event. */
		FRIDGE_IDLE_EVENT
	}

	/** The refrigerator display. */
	private static RefrigeratorDisplay refrigeratorDisplay;

	/** The temp. */
	private int temp;

	/** The desired fridge temp. */
	private int desiredFridgeTemp;

	/** The current fridge run state. */
	private FridgeRunState currentFridgeRunState;

	/** The current fridge door state. */
	private FridgeDoorState currentFridgeDoorState;

	/** The current fridge door event. */
	private FridgeDoorEvents currentFridgeDoorEvent;

	/** The instance. */
	private static FridgeContext instance;
	static {
		instance = new FridgeContext();
		refrigeratorDisplay = RefrigeratorDisplay.instance();
	}

	/**
	 * Make it a singleton.
	 */
	private FridgeContext() {
	}

	/**
	 * Return the instance.
	 *
	 * @return the object
	 */
	public static FridgeContext instance() {
		if (instance == null) {
			instance = new FridgeContext();
		}
		return instance;
	}

	/**
	 * sets starting state - fridge door closed - fridge temp at room temp min
	 * from configuration file - turns fridge on sets up observable for clock.
	 */
	public void initialize() {
		instance.changeCurrentFridgeDoorState(FridgeDoorClosedState.instance());
		instance.setFridgeTemp(Configuration.ROOM_MIN);
		instance.changeCurrentFridgeRunState(FridgeRunningState.instance());
		currentFridgeDoorEvent = FridgeDoorEvents.FRIDGE_DOOR_CLOSED_EVENT;
		Clock.instance().addObserver(instance);
	}

	/**
	 * For observer.
	 *
	 * @param observable
	 *            will be the clock
	 * @param arg
	 *            the event that clock has ticked
	 */
	@Override
	public void update(Observable observable, Object arg) {
		currentFridgeRunState.handle(arg);
		currentFridgeDoorState.handle(arg);
	}

	/**
	 * handles fridge door events (open/close).
	 *
	 * @param arg
	 *            the event from the GUI
	 */
	public void processFridgeDoorEvent(Object arg) {
		currentFridgeDoorState.handle(arg);
		currentFridgeDoorEvent = (FridgeDoorEvents) arg;
		FridgeNotRunningState.fridgeClock = 0;
	}

	/**
	 * handles fridge run events (run/idle).
	 *
	 * @param arg
	 *            the arg
	 */
	public void processFridgeRunEvent(Object arg) {
		currentFridgeRunState.handle(arg);
	}

	/**
	 * Called from the states to change the current run state.
	 *
	 * @param nextFridgeRunState
	 *            the next fridge run state
	 */
	public void changeCurrentFridgeRunState(FridgeRunState nextFridgeRunState) {
		currentFridgeRunState = nextFridgeRunState;
		nextFridgeRunState.run();
	}

	/**
	 * Called from the states to change the current door state.
	 *
	 * @param nextFridgeDoorState
	 *            the next fridge door state
	 */
	public void changeCurrentFridgeDoorState(FridgeDoorState nextFridgeDoorState) {
		currentFridgeDoorState = nextFridgeDoorState;
		nextFridgeDoorState.run();
	}

	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}

	/**
	 * get the fridge temperature.
	 *
	 * @return current fridge temp
	 */
	public int getFridgeTemp() {
		return temp;
	}

	/**
	 * set the desired fridge temperature.
	 *
	 * @param temp
	 *            the new desired fridge temp
	 * @return current fridge temp
	 */
	public void setDesiredFridgeTemp(int temp) {
		this.desiredFridgeTemp = temp;
	}

	/**
	 * get the desired fridge temperature.
	 *
	 * @return current fridge temp
	 */
	public int getDesiredFridgeTemp() {
		return desiredFridgeTemp;
	}

	/**
	 * Sets the fridge temperature.
	 *
	 * @param temp
	 *            temperature of fridge
	 */
	public void setFridgeTemp(int temp) {
		this.temp = temp;
	}

	/**
	 * get the current event of the fridge door.
	 *
	 * @return currentFridgeDoorEvent
	 */
	public FridgeDoorEvents getCurrentFridgeDoorEvent() {
		return currentFridgeDoorEvent;
	}

}