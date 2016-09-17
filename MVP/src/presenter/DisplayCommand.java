package presenter;

public class DisplayCommand extends CommonCommand {

	public DisplayCommand(Presenter presenter) {
		super(presenter);
	}

	/**
	 * Override "doCommand" and implements this method.
	 * This method will display the command 
	 * or crossSection
	 * or solution
	 * or maze 
	 */
	@Override
	public void doCommand(String string) {
		String[] tempArray = string.split(" ");
		String mazeName;
		
		// if we want to display the crossSection
		if(tempArray.length > 4){
			if(tempArray[0].equals("cross")){
				int i = 0;
				try {
					i = Integer.parseInt(tempArray[4]);
				} catch (NumberFormatException e) {
					presenter.getView().printMsg("Invalid input");
				}

				String name = tempArray[2];
				mazeName = tempArray[3];
				presenter.getModel().crossSectionBy(name, mazeName, i);
			}
		}
		
		// if we want to display the solution
		else if (tempArray.length > 1){
			if(tempArray[0].equals("solution")){
				mazeName = tempArray[1];
				presenter.getView().displaySolution(presenter.getModel().getMazeSolution(mazeName));
			}
		}
		
		// if we want to display the maze
		else {
			mazeName = tempArray[0];
			presenter.getView().displayMaze(presenter.getModel().getMaze3d(mazeName));
			presenter.getView().printMsg("Start position is: ");
			presenter.getView().displayPosition(presenter.getModel().getMaze3d(mazeName).getStartPosition());
		}
	}
}
