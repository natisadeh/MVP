package view;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Properties;

/**
 * The Interface View.
 * 
 * @author Nati Sadeh & Eli Salem
 */
public interface View {

	/**
	 * View's methods 
	 */
	void start();
	void printMsg(String msg);
	void displayStringArray(String[] strings);	
	void setCommand(HashMap<String, Command> commandHash);
	void displayCrossSectionBy(int[][] maze2d);
	void displaySolution(Solution<Position> solution);
	void displayMaze(Maze3d maze3d);
	void displayPosition(Position position);
	void setProperties(Properties properties);
	void exit();
	
}
