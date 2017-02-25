/**
 * FridgeDoorState Class Abstract class inherited by FridgeDoorOpenedState and
 * FridgeDoorClosedState.
 */
public abstract class FridgeDoorState {

	/** The context. */
	protected static FridgeContext context;

	/** The display. */
	protected static RefrigeratorDisplay display;

	/**
	 * Initializes the context and display.
	 */
	protected FridgeDoorState() {
		context = FridgeContext.instance();
		display = context.getDisplay();
	}

	/**
	 * Initializes the state.
	 */
	public abstract void run();

	/**
	 * Handles an event.
	 *
	 * @param event
	 *            event to be processed
	 */
	public abstract void handle(Object event);

}
