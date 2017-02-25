/**
 * FridgeDoorOpenedState Class Changes the state of the fridge door.
 */
public class FridgeDoorOpenedState extends FridgeDoorState {

	/** The instance. */
	private static FridgeDoorOpenedState instance;
	static {
		instance = new FridgeDoorOpenedState();
	}

	/**
	 * For the singleton pattern.
	 *
	 * @return the object
	 */
	public static FridgeDoorOpenedState instance() {
		return instance;
	}

	/**
	 * Handle door closed event.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void handle(Object event) {
		if (event
				.equals(FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_CLOSED_EVENT)) {
			processFridgeDoorClose();
		}
	}

	/**
	 * Process door closed event.
	 */
	public void processFridgeDoorClose() {
		context.changeCurrentFridgeDoorState(FridgeDoorClosedState.instance());
	}

	/**
	 * Initialize the state.
	 */
	@Override
	public void run() {
		display.turnFridgeLightOn();
		display.fridgeDoorOpened();
		display.displayFridgeTemp(context.getFridgeTemp());
	}
}