package presenter;

public class MovesCommand extends CommonCommand {

	public MovesCommand(Presenter presenter) {
		super(presenter);
	}

	@Override
	public void doCommand(String string) {
		// switch-case for command (move)
		switch (string) {
		case "Up":
			presenter.getModel().moveUp();
			break;
		case "Down":
			presenter.getModel().moveDown();
			break;
		case "Forward":
			presenter.getModel().moveForward();
			break;
		case "Back":
			presenter.getModel().moveBack();
			break;
		case "Left":
			presenter.getModel().moveLeft();
			break;
		case "Right":
			presenter.getModel().moveRight();
			break;
		default:
			presenter.getView().printMsg("Invalid move");
			break;
		}
	}

}
