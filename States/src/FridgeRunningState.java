/**
 * FridgeRunningState Class.
 */
public class FridgeRunningState extends FridgeRunState {

	/** The fridge count. */
	private int fridgeCount = 0;

	/** The instance. */
	private static FridgeRunningState instance;
	static {
		instance = new FridgeRunningState();
	}

	/**
	 * For singleton.
	 *
	 * @return the object
	 */
	public static FridgeRunningState instance() {
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
	 * at which the fridge cools based on configuration file.
	 */
	public void processClockTick() {

		if (fridgeCount == Configuration.FRIDGE_COOLING_MINUTES) {
			context.setFridgeTemp(context.getFridgeTemp() - 1);
			display.displayFridgeTemp(context.getFridgeTemp());
			fridgeCount = 0;
		}

		if (context.getFridgeTemp() == FridgeContext.instance()
				.getDesiredFridgeTemp()) {
			context.changeCurrentFridgeRunState(FridgeNotRunningState
					.instance());
		}

		fridgeCount++;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public void run() {
		display.fridgeRunning();
		display.displayFridgeTemp(context.getFridgeTemp());
	}
}