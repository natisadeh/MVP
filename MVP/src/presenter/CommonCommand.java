package presenter;

import java.io.PrintWriter;

/**
 * The Common Command is an abstract class that implements Command.
 * the class will hold the Command variables 
 * @author Nati Sadeh & Eli Salem
 */

public abstract class CommonCommand implements Command {

	/** Variable. */
	protected Presenter presenter;
	protected PrintWriter out;
	
	/**
	 * Instantiates a new common command.
	 *
	 * @param controller the controller
	 */
	public CommonCommand(Presenter presenter) {
		super();
		this.presenter = presenter;
	}

	
	public abstract void doCommand(String string);

}
