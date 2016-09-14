package presenter;

import java.io.File;

public class DirCommand extends CommonCommand {

	/**
	 * C'tor
	 * @param controller
	 */
	public DirCommand(Presenter presenter) {
		super(presenter);
	}

	/**
	 * Override "doCommand" and implements this method.
	 * This method will display a files in specific dir
	 */
	@Override
	public void doCommand(String string) {
		if (string == null){
			presenter.getView().printMsg("Invalid input");
		}
		else {
			try {
				File file = new File(string);
				if (file.list().length == 0) {
					presenter.getView().printMsg("Not such a file");
				} else {
					presenter.getView().displayStringArray(file.list());
				}
			} catch (NullPointerException e) {
				presenter.getView().printMsg("Invalid path");
			}
		}
	}
}