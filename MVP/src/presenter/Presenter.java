package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

/**
 * The Class Presenter.
 * @author Nati Sadeh & Eli Salem
 */
public class Presenter implements Observer {
	
	/**
	 * Variables
	 */
	private Model model;
	private View view;
	private HashMap<String, Command> commandHash;
	private Properties properties;
	
	/**
	 * C'tor 
	 * set the view
	 * set the model
	 * creating new properties and set them
	 */
	public Presenter (View view, Model model){
		this.view = view;
		this.model = model;
		this.properties = new Properties();
		properties.defaultProperties();
		model.setProperties(properties);
		
		
		/**
		 * Command hash which hold the commands
		 */
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

		/**
		 * Implement the update method from observer
		 * the method will get two parameters observable (model or view) , arg (the command we like to  do)
		 * checks if the observable is model or view
		 * and do the command 
		 */
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

			else if (o == model) {
				// local variables
				String userCommand = (String) arg;
				String[] tempArray = userCommand.split(" ");

				// switch-case for first word
				switch (tempArray[0]) {
				case "mazeIsReady":
//					Maze3d maze3d = (Maze3d) model.getUserCommand(userCommand);
					view.printMsg("maze " + properties.getMazeName() + " is ready!" );
//					view.displayMaze(maze3d);
//					view.displayPosition(maze3d.getStartPosition());
					break;

				case "displayCrossSectionBy":
					view.displayCrossSectionBy((int[][]) model.getUserCommand(userCommand));
					break;

				case "saveMaze":
					view.printMsg("maze is saved successfully in file " + (String) model.getUserCommand(userCommand));
					break;

				case "loadMaze":
					Maze3d newMaze3d = (Maze3d) model.getUserCommand(userCommand);
					view.displayMaze(newMaze3d);
					view.printMsg("Start position: ");
					view.displayPosition(newMaze3d.getStartPosition());
					
					break;

				case "solutionIsReady":
					view.printMsg("The solution for your maze is ready!");
//					view.displaySolution(model.getMazeSolution((String) model.getUserCommand(userCommand)));
					break;

				case "loadZip":
					view.printMsg("The maze has loaded from " + (String) model.getUserCommand(userCommand));
					break;

				case "saveZip":
					view.printMsg("file is saved to " + (String) model.getUserCommand(userCommand));
					break;

				case "move":
					view.displayMaze(model.getMaze3d((String) model.getUserCommand(userCommand)));
					view.displayPosition(model.getPosition((String) model.getUserCommand(userCommand)));
					break;

				case "exit":
					view.printMsg("The system is shutting down");
					break;

				case "null":
					view.printMsg("Maze is null");
					break;

				case "Invalid":
					// another switch-case for all problems.
					switch (tempArray[1]) {

					case "parameters":
						view.printMsg("Invalid parameters");
						break;

					case "index":
						view.printMsg("Invalid index");
						break;

					case "file":
						view.printMsg("The file " + (String) model.getUserCommand(userCommand) + " isn't exist");
						break;

					case "compress":
						view.printMsg("Cannot compress the " + (String) model.getUserCommand(userCommand));
						break;

					case "maze":
						view.printMsg("Error with " + (String) model.getUserCommand(userCommand) + " maze");
						break;

					case "algorithm":
						view.printMsg("Invalid algorithm");
						break;

					case "input":
						view.printMsg("Invalid input");
						break;

					default:
						view.printMsg("Invalid input");
						break;
					}
					break;

				default:
					view.printMsg("Invalid command");
					break;

				}
			}
	}
}
