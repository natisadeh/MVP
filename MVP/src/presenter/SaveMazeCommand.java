package presenter;

public class SaveMazeCommand extends CommonCommand {

	public SaveMazeCommand(Presenter presenter) {
		super(presenter);
	}
	
	/**
	 * Override "doCommand" and implements this method.
	 * This method will tell the controller that the user want to save the maze
	 */
	@Override
	public void doCommand(String string) {
		// local variables
		String[] tempArray = string.split(" ");

		if (tempArray.length < 1)
			presenter.getView().printMsg("Invalid input");
		// if we want save to zip
		else if (tempArray[0].equals("zip"))
			presenter.getModel().saveToZip();
		else {
			String name = tempArray[0];
			presenter.getModel().saveMaze(name);
		}
	}
}
