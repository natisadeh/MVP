package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Properties;

/**
 * The Interface View.
 * @auth Nati Sadeh & Eli Salem
 */
public interface View {

	/**
	 * Start method that running a thread, the method will be implement in the CLI
	 */
	void start();
	
	/**
	 * Prints the msg.
	 *
	 * @param msg the msg
	 */
	void printMsg(String msg);
	
	/**
	 * Display string array.
	 *
	 * @param strings the strings
	 */
	void displayStringArray(String[] strings);
	
	/**
	 * Sets the command.
	 *
	 * @param commandHash the command hash
	 */
	void setCommand(HashMap<String, Command> commandHash);
	
	/**
	 * Exit.
	 */

	void displayCrossSectionBy(int[][] maze2d);
	void displaySolution(Solution<Position> solution);
	void displayMaze(Maze3d maze3d);
	void displayPosition(Position position);
	void setProperties(Properties properties);
	
	void exit();
	
}
