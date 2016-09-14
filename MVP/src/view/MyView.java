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

/**
 * The Class MyView that extends CommonView
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
	 * The method will call the start method in the CLI class
	 */
	@Override
	public void start() {
		new Thread(new Runnable() {

			// override run method from runnable
			@Override
			public void run() {
				try {
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

	/* (non-Javadoc)
	 * @see view.CommonView#printMsg(java.lang.String)
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

	/* (non-Javadoc)
	 * @see view.CommonView#displayStringArray(java.lang.String[])
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

	/* (non-Javadoc)
	 * @see view.CommonView#setCommand(java.util.HashMap)
	 */
	@Override
	public void setCommand(HashMap<String, Command> commandHash) {
		this.commandHash = commandHash;
	}



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

	@Override
	public void displaySolution(Solution<Position> solution) {
		solution.printSolution();
	}

	@Override
	public void displayMaze(Maze3d maze3d) {
		maze3d.printMaze();
	}

	@Override
	public void displayPosition(Position position) {
		out.println(position);
		out.flush();
	}

	@Override
	public void setProperties(Properties properties) {
		if(!(properties.getChooseView().equals("command line"))) {
			setChanged();
			notifyObservers("replaceUserInterface");
		}	
	}
	/* (non-Javadoc)
	 * @see view.CommonView#exit()
	 */
	@Override
	public void exit() {
		out.println("closing the program");
		out.flush();
	}
}
