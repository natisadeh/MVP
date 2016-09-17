package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class MyView that extends CommonView.
 *
 * @author Nati Sadeh & Eli Salem
 */
public class MyView extends CommonView {

	/** Variable. */
	private ExecutorService threadPool;
	
	/**
	 * Instantiates a new my view.
	 *
	 * @param buff the buff
	 * @param print the print
	 */
	public MyView(BufferedReader buff, PrintWriter print){
		super(buff,print);
		this.threadPool = Executors.newCachedThreadPool();
	}
	
	
	/**
	 * Start method that running a thread, the method will receive commands from the user
	 * the view will notify the presenter that command has been received 
	 */
	@Override
	public void start() {
		new Thread(new Runnable() {

			// override run method from runnable
			@Override
			public void run() {
				try {
					
					out.println("               ***********");
					out.println("               *MAIN MENU*");
					out.println("               ***********");
					out.println("(1). dir <path>");
					out.println("(2). generate <maze name> <floors number> <columns number> <rows number>");
					out.println("(3). display <maze name>");
					out.println("(4). display cross section <dim> <maze name> <index>");
					out.println("(5). save <file name>");
					out.println("(6). load <file name> <maze name>");
					out.println("(7). solve <maze name> <algorithm>");
					out.println("(8). display solution <maze name>");
					out.println("(9). exit");
					// ask user for command
					out.println("please enter command");
					out.flush();
					String userCommand = in.readLine();
					while (!(userCommand.equals("exit"))) {
						// update observer that something change.
						setChanged();
						notifyObservers(userCommand);
						try {
							threadPool.awaitTermination(2, TimeUnit.SECONDS);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// ask user for new command
						out.println("Please enter a new command");
						out.flush();
						userCommand = in.readLine();
					}
					setChanged();
					notifyObservers(userCommand);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}).start();

	}

	/**
	 * Prints the msg.
	 *
	 * @param msg the msg
	 */
	@Override
	public void printMsg(String msg) {
		if (msg != null){
			out.println(msg);
			out.flush();
		}
		else
		{
			out.print("There is no message");
			out.flush();
		}
	}

	/**
	 * Display string array.
	 *
	 * @param strings the strings
	 */
	@Override
	public void displayStringArray(String[] strings) {
		if(strings != null){
			for (String string : strings){
				out.println(string);
			}
			out.flush();
		}
		else
		{
			out.print("The array is empty");
			out.flush();
		}
	}

	/**
	 * Sets the command.
	 *
	 * @param commandHash the command hash
	 */
	@Override
	public void setCommand(HashMap<String, Command> commandHash) {
		this.commandHash = commandHash;
	}

	/**
	 * Method that will be responsible to display  a 2D mazeCrossSection 
	 */
	@Override
	public void displayCrossSectionBy(int[][] maze2d) {
		//display all maze2d 
		for(int i=0; i< maze2d.length; i++) {
			for(int j=0; j< maze2d[i].length; j++) {
				out.print(maze2d[i][j]);
			}
			out.println();
			out.flush();	
		}
	}

	/**
	 * Method that will be responsible to display a solution
	 */
	@Override
	public void displaySolution(Solution<Position> solution) {
		solution.printSolution();
	}

	/**
	 * Method that will be responsible to display a maze
	 */
	@Override
	public void displayMaze(Maze3d maze3d) {
		maze3d.printMaze();
	}

	/**
	 * Method that will be responsible to display position
	 */
	@Override
	public void displayPosition(Position position) {
		out.println(position);
		out.flush();
	}

	/**
	 * Method that will be responsible to identify if we working on CLI or GUI
	 */
	@Override
	public void setProperties(Properties properties) {
		if(!(properties.getChooseView().equals("command line"))) {
			setChanged();
			notifyObservers("replaceUserInterface");
		}	
	}
	
	/**
	 * Exit.
	 */
	@Override
	public void exit() {
		out.println("closing the program");
		out.flush();
	}
}
