/**
 * FreezerRunningState Class.
 */
public class FreezerRunningState extends FreezerRunState {

	/** The freezer count. */
	private int freezerCount = 0;

	/** The instance. */
	private static FreezerRunningState instance;
	static {
		instance = new FreezerRunningState();
	}

	/**
	 * For singleton.
	 *
	 * @return the object
	 */
	public static FreezerRunningState instance() {
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
	 * Process clock ticks Generates the timer runs out event Adjusts the rate
	 * at which the freezer cools based on configuration file.
	 */
	public void processClockTick() {

		if (freezerCount == Configuration.FREEZER_COOLING_MINUTES) {
			context.setFreezerTemp(context.getFreezerTemp() - 1);
			display.displayFreezerTemp(context.getFreezerTemp());
			freezerCount = 0;
		}
		if (context.getFreezerTemp() == FreezerContext.instance()
				.getDesiredFreezerTemp()) {
			context.changeCurrentFreezerRunState(FreezerNotRunningState
					.instance());
		}
		freezerCount++;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public void run() {
		display.freezerRunning();
		display.displayFreezerTemp(context.getFreezerTemp());
	}
}