package presenter;

public class ExitCommand extends CommonCommand {

	public ExitCommand(Presenter presenter) {
		super(presenter);
	}

	/**
	 * Override "doCommand" and implements this method.
	 * This method will tell the controller to exit
	 */
	@Override
	public void doCommand(String string) {
		presenter.getView().exit();
		presenter.getModel().exit();
	}

}
