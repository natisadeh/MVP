package presenter;


/**
 * The Common Command is an abstract class that implements Command.
 * the class will hold the Command variables 
 * @author Nati Sadeh & Eli Salem
 */

public abstract class CommonCommand implements Command {

	/** Variable. */
	protected Presenter presenter;
	
	/**
	 * Instantiates a new common command.
	 *
	 * @param controller the controller
	 */
	public CommonCommand(Presenter presenter) {
		super();
		this.presenter = presenter;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	public abstract void doCommand(String string);

}
