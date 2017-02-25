/**
 * FridgeNotRunningState Class.
 */
public class FridgeNotRunningState extends FridgeRunState {

	/** The fridge clock. */
	public static int fridgeClock = 0;

	/** The instance. */
	private static FridgeNotRunningState instance;
	static {
		instance = new FridgeNotRunningState();
	}

	/**
	 * For singleton.
	 *
	 * @return the object
	 */
	public static FridgeNotRunningState instance() {
		return instance;
	}

	/**
	 * Handle the event.
	 *
	 * @param event
	 *            the event to be processed
	 */
	@Override
	public void handle(Object event) {
		if (event.equals(Clock.Events.CLOCK_TICKED_EVENT)) {
			processClockTick();
		}
	}

	/**
	 * Process clock ticks Generates the timer runs out event Checks if fridge
	 * door is open or closed to adjust warming rate.
	 */
	public void processClockTick() {

		if (fridgeClock == Configuration.FRIDGE_OPEN_TIME
				&& FridgeContext
						.instance()
						.getCurrentFridgeDoorEvent()
						.equals(FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_OPENED_EVENT)) {
			context.setFridgeTemp(context.getFridgeTemp() + 1);
			display.displayFridgeTemp(context.getFridgeTemp());
			fridgeClock = 0;
		} else if (fridgeClock == Configuration.FRIDGE_CLOSED_TIME
				&& FridgeContext
						.instance()
						.getCurrentFridgeDoorEvent()
						.equals(FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_CLOSED_EVENT)) {
			context.setFridgeTemp(context.getFridgeTemp() + 1);
			display.displayFridgeTemp(context.getFridgeTemp());
			fridgeClock = 0;
		}

		if (context.getFridgeTemp() == FridgeContext.instance()
				.getDesiredFridgeTemp()
				+ Configuration.FRIDGE_TEMP_UNTIL_ACTIVE) {
			context.changeCurrentFridgeRunState(FridgeRunningState.instance());
		}

		fridgeClock++;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public void run() {
		context.setFridgeTemp(FridgeContext.instance().getDesiredFridgeTemp());
		display.fridgeNotRunning();
		display.displayFridgeTemp(context.getFridgeTemp());
	}
}