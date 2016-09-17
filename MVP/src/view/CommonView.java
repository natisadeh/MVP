package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Properties;

/**
 * An abstract class that implements View interface
 * @author Nati Sadeh & Eli Salem
 */
public abstract class CommonView extends Observable implements View {
	/**
	 * Variables
	 */
	protected BufferedReader in;
	protected PrintWriter out;
	protected HashMap<String, Command> commandHash;
	protected HashMap<Command, Object> objectHash;
	
	/**
	 * Constructor
	 */
	public CommonView(BufferedReader in, PrintWriter out) {
		super();
		this.in = in;
		this.out = out;
		this.commandHash = new HashMap <String,Command>();
		this.objectHash = new HashMap<Command,Object>();
	}
	
	/**
	 * View's methods that will be implemented in MyVie class
	 */
	public abstract void start();
	public abstract void printMsg(String msg);
	public abstract void displayStringArray(String[] strings); 
	public abstract void setCommand(HashMap<String, Command> commandHash); 
	public abstract void displayCrossSectionBy(int[][] maze2d);
	public abstract void displaySolution(Solution<Position> solution); 
	public abstract void displayMaze(Maze3d maze3d);
	public abstract void displayPosition(Position position); 
	public abstract void setProperties(Properties properties); 
	public abstract void exit();
}
