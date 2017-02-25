/**
 * FreezerDoorClosedState Class Changes state of freezer door.
 */
public class FreezerDoorClosedState extends FreezerDoorState {

	/** The instance. */
	private static FreezerDoorClosedState instance;
	static {
		instance = new FreezerDoorClosedState();
	}

	/**
	 * returns the instance.
	 *
	 * @return this object
	 */
	public static FreezerDoorClosedState instance() {
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
				.equals(FreezerContext.FreezerDoorEvents.FREEZER_DOOR_OPENED_EVENT)) {
			processFreezerDoorOpen();
		}
	}

	/**
	 * processes door open event.
	 */
	public void processFreezerDoorOpen() {
		context.changeCurrentFreezerDoorState(FreezerDoorOpenedState.instance());
	}

	/**
	 * initialize the state.
	 */
	@Override
	public void run() {
		display.freezerDoorClosed();
		display.turnFreezerLightOff();
		display.displayFreezerTemp(context.getFreezerTemp());
	}
}