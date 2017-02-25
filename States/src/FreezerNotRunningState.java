/**
 * FreezerNotRunningState Class.
 */
public class FreezerNotRunningState extends FreezerRunState {

	/** The freezer clock. */
	public static int freezerClock = 0;

	/** The instance. */
	private static FreezerNotRunningState instance;
	static {
		instance = new FreezerNotRunningState();
	}

	/**
	 * For singleton.
	 *
	 * @return the object
	 */
	public static FreezerNotRunningState instance() {
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
	 * Process clock ticks Generates the timer runs out event Checks if freezer
	 * door is open or closed to adjust warming rate.
	 */
	public void processClockTick() {

		if (freezerClock == Configuration.FREEZER_OPEN_TIME
				&& FreezerContext
						.instance()
						.getCurrentFreezerDoorEvent()
						.equals(FreezerContext.FreezerDoorEvents.FREEZER_DOOR_OPENED_EVENT)) {
			context.setFreezerTemp(context.getFreezerTemp() + 1);
			display.displayFreezerTemp(context.getFreezerTemp());
			freezerClock = 0;
		} else if (freezerClock == Configuration.FREEZER_CLOSED_TIME
				&& FreezerContext
						.instance()
						.getCurrentFreezerDoorEvent()
						.equals(FreezerContext.FreezerDoorEvents.FREEZER_DOOR_CLOSED_EVENT)) {
			context.setFreezerTemp(context.getFreezerTemp() + 1);
			display.displayFreezerTemp(context.getFreezerTemp());
			freezerClock = 0;
		}

		if (context.getFreezerTemp() == FreezerContext.instance()
				.getDesiredFreezerTemp()
				+ Configuration.FREEZER_TEMP_UNTIL_ACTIVE) {
			context.changeCurrentFreezerRunState(FreezerRunningState.instance());
		}

		freezerClock++;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public void run() {
		context.setFreezerTemp(FreezerContext.instance()
				.getDesiredFreezerTemp());
		display.freezerNotRunning();
		display.displayFreezerTemp(context.getFreezerTemp());
	}
}