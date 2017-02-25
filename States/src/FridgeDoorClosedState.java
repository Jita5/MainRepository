/**
 * FridgeDoorClosedState Class Changes state of fridge door.
 */
public class FridgeDoorClosedState extends FridgeDoorState {

	/** The instance. */
	private static FridgeDoorClosedState instance;
	static {
		instance = new FridgeDoorClosedState();
	}

	/**
	 * returns the instance.
	 *
	 * @return this object
	 */
	public static FridgeDoorClosedState instance() {
		return instance;
	}

	/**
	 * Handles door open events.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void handle(Object event) {
		if (event
				.equals(FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_OPENED_EVENT)) {
			processFridgeDoorOpen();
		}
	}

	/**
	 * processes door open event.
	 */
	public void processFridgeDoorOpen() {
		context.changeCurrentFridgeDoorState(FridgeDoorOpenedState.instance());
	}

	/**
	 * initialize the state.
	 */
	@Override
	public void run() {
		display.fridgeDoorClosed();
		display.turnFridgeLightOff();
		display.displayFridgeTemp(context.getFridgeTemp());
	}
}