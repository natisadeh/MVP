package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {
	
	private Model model;
	private View view;
	private HashMap<String, Command> commandHash;
	private Properties properties;
	
	public Presenter (View view, Model model){
		this.view = view;
		this.model = model;
		this.properties = new Properties();
		properties.defaultProperties();
		model.setProperties(properties);
		
		this.commandHash = new HashMap<String, Command>();
		commandHash.put("dir", new DirCommand(this));
		commandHash.put("generate", new GenerateCommand(this));
		commandHash.put("display", new DisplayCommand(this));
		commandHash.put("save", new SaveMazeCommand(this));
		commandHash.put("load", new LoadMazeCommand(this));
		commandHash.put("solve", new SolveMazeCommand(this));
		commandHash.put("move", new MovesCommand(this));
		commandHash.put("exit", new ExitCommand(this));
		
		view.setCommand(commandHash);
	}
		
		/**
		 * setters and getters for view & model.
		 */
		public Model getModel() {
			return model;
		}

		public void setModel(Model model) {
			this.model = model;
		}

		public View getView() {
			return view;
		}

		public void setView(View view) {
			this.view = view;
		}

		public Properties getProperties() {
			return properties;
		}

		public void setProperties(Properties properties) {
			this.properties = properties;
			if (view != null)
				this.view.setProperties(properties);
			if (model != null)
				this.model.setProperties(properties);
		}

		@Override
		public void update(Observable o, Object arg) {
		// if the observable is the view.
		if (o == view) {
			// check if the class is properties
			if (((arg.getClass()).getName()).equals("presenter.Properties")) {
				// set the properties to specific model
				Properties properties = (Properties) arg;
				model.setProperties(properties);
			} else {
				// local variables
				Command command;
				String string = (String) arg;
				command = commandHash.get(string.split(" ")[0]);
				// check if the specific command is exist
				if (command != null) {
					if (string.split(" ").length > 0) {
						command.doCommand(string.substring(string.indexOf(' ') + 1));
					} else {
						command.doCommand("");
					}
				} else {
					view.printMsg("Invalid input");
				}
			}
		}
	}
}
