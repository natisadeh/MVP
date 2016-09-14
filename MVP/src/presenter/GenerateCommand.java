package presenter;

public class GenerateCommand extends CommonCommand {

	public GenerateCommand(Presenter presenter) {
		super(presenter);
	}
	/**
	 * Override "doCommand" and implements this method.
	 * This method will tell the controller that the user want to generate a maze
	 */
	@Override
	public void doCommand(String string) {
		presenter.getModel().generate(string);
	}
}