/**
 * FreezerContext Class
 * Coordinates all freezer related activity
 */
import java.util.Observable;
import java.util.Observer;

/**
 * The context is an observer for the clock and stores the context info for
 * states.
 */
public class FreezerContext implements Observer {

	/**
	 * The Enum FreezerDoorEvents.
	 */
	public static enum FreezerDoorEvents {

		/** The freezer door closed event. */
		FREEZER_DOOR_CLOSED_EVENT,
		/** The freezer door opened event. */
		FREEZER_DOOR_OPENED_EVENT
	}
	/**
	 * The Enum FreezerRunEvents.
	 */
	public static enum FreezerRunEvents {

		/** The freezer run event. */
		FREEZER_RUN_EVENT,
		/** The freezer idle event. */
		FREEZER_IDLE_EVENT
	}

	/** The refrigerator display. */
	private static RefrigeratorDisplay refrigeratorDisplay;

	/** The temp. */
	private int temp;

	/** The desired freezer temp. */
	private int desiredFreezerTemp;

	/** The current freezer run state. */
	private FreezerRunState currentFreezerRunState;

	/** The current freezer door state. */
	private FreezerDoorState currentFreezerDoorState;

	/** The current freezer door event. */
	private FreezerDoorEvents currentFreezerDoorEvent;

	/** The instance. */
	private static FreezerContext instance;
	static {
		instance = new FreezerContext();
		refrigeratorDisplay = RefrigeratorDisplay.instance();
	}

	/**
	 * Make it a singleton.
	 */
	private FreezerContext() {
	}

	/**
	 * Return the instance.
	 *
	 * @return the object
	 */
	public static FreezerContext instance() {
		if (instance == null) {
			instance = new FreezerContext();
		}
		return instance;
	}

	/**
	 * sets starting state - freezer door closed - freezer temp at room temp min
	 * from configuration file - turns freezer on sets up observable for clock.
	 */
	public void initialize() {
		instance.changeCurrentFreezerDoorState(FreezerDoorClosedState
				.instance());
		instance.setFreezerTemp(Configuration.ROOM_MIN);
		instance.changeCurrentFreezerRunState(FreezerRunningState.instance());
		currentFreezerDoorEvent = FreezerDoorEvents.FREEZER_DOOR_CLOSED_EVENT;
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
		currentFreezerRunState.handle(arg);
		currentFreezerDoorState.handle(arg);
	}

	/**
	 * handles freezer door events (open/close).
	 *
	 * @param arg
	 *            the event from the GUI
	 */
	public void processFreezerDoorEvent(Object arg) {
		currentFreezerDoorState.handle(arg);
		currentFreezerDoorEvent = (FreezerDoorEvents) arg;
		FreezerNotRunningState.freezerClock = 0;
	}

	/**
	 * handles freezer run events (run/idle).
	 *
	 * @param arg
	 *            the arg
	 */
	public void processFreezerRunEvent(Object arg) {
		currentFreezerRunState.handle(arg);
	}

	/**
	 * Called from the states to change the current run state.
	 *
	 * @param nextFreezerRunState
	 *            the next freezer run state
	 */
	public void changeCurrentFreezerRunState(FreezerRunState nextFreezerRunState) {
		currentFreezerRunState = nextFreezerRunState;
		nextFreezerRunState.run();
	}

	/**
	 * Called from the states to change the current door state.
	 *
	 * @param nextFreezerDoorState
	 *            the next freezer door state
	 */
	public void changeCurrentFreezerDoorState(
			FreezerDoorState nextFreezerDoorState) {
		currentFreezerDoorState = nextFreezerDoorState;
		nextFreezerDoorState.run();
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
	 * get the freezer temperature.
	 *
	 * @return current freezer temp
	 */
	public int getFreezerTemp() {
		return temp;
	}

	/**
	 * set the desired freezer temperature.
	 *
	 * @param temp
	 *            the new desired freezer temp
	 * @return current freezer temp
	 */
	public void setDesiredFreezerTemp(int temp) {
		this.desiredFreezerTemp = temp;
	}

	/**
	 * get the desired freezer temperature.
	 *
	 * @return current freezer temp
	 */
	public int getDesiredFreezerTemp() {
		return desiredFreezerTemp;
	}

	/**
	 * Sets the freezer temperature.
	 *
	 * @param temp
	 *            temperature of freezer
	 */
	public void setFreezerTemp(int temp) {
		this.temp = temp;
	}

	/**
	 * get the current event of the freezer door.
	 *
	 * @return currentFreezerDoorEvent
	 */
	public FreezerDoorEvents getCurrentFreezerDoorEvent() {
		return currentFreezerDoorEvent;
	}

}