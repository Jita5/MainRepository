/**
 * FreezerRunState Class.
 */
public abstract class FreezerRunState {

	/** The context. */
	protected static FreezerContext context;

	/** The display. */
	protected static RefrigeratorDisplay display;

	/**
	 * Initializes the context and display.
	 */
	protected FreezerRunState() {
		context = FreezerContext.instance();
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
