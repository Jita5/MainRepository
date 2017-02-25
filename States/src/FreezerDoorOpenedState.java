/**
 * FreezerDoorOpenedState Class Changes the state of the freezer door.
 */
public class FreezerDoorOpenedState extends FreezerDoorState {

	/** The instance. */
	private static FreezerDoorOpenedState instance;
	static {
		instance = new FreezerDoorOpenedState();
	}

	/**
	 * For the singleton pattern.
	 *
	 * @return the object
	 */
	public static FreezerDoorOpenedState instance() {
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
				.equals(FreezerContext.FreezerDoorEvents.FREEZER_DOOR_CLOSED_EVENT)) {
			processFreezerDoorClose();
		}
	}

	/**
	 * Process door closed event.
	 */
	public void processFreezerDoorClose() {
		context.changeCurrentFreezerDoorState(FreezerDoorClosedState.instance());
	}

	/**
	 * Initialize the state.
	 */
	@Override
	public void run() {
		display.turnFreezerLightOn();
		display.freezerDoorOpened();
		display.displayFreezerTemp(context.getFreezerTemp());
	}
}