package presenter;

public class LoadMazeCommand extends CommonCommand {

	public LoadMazeCommand(Presenter presenter) {
		super(presenter);
	}

	/**
	 * Override "doCommand" and implements this method.
	 * This method will tell the presenter that the user want to load maze
	 */
	@Override
	public void doCommand(String string) {
		// local variables
		String[] tempArray = string.split(" ");

		if (tempArray.length < 1)
			presenter.getView().printMsg("Invalid input");
		// if we want to load from zip
		else if (tempArray[0].equals("zip"))
			presenter.getModel().loadFromZip();
		else {
			// local variables
			String fileName = tempArray[0];
			String mazeName = tempArray[1];
			presenter.getModel().loadMaze(fileName, mazeName);
		}
	}
}
