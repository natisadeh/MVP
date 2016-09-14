package presenter;

public class SolveMazeCommand extends CommonCommand {

	public SolveMazeCommand(Presenter presenter) {
		super(presenter);
	}

	/**
	 * Override "doCommand" and implements this method.
	 * This method will tell the controller that the user want to solve the maze
	 */
	@Override
	public void doCommand(String string) {
		String[] tempArray = string.split(" ");
		if (tempArray.length != 1) {
			presenter.getView().printMsg("Invalid Input");
		} else {
			String name = tempArray[0];
			presenter.getModel().solveMaze(name);
		}
	}

}
