package model;

import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

/**
 * The Common Model is an abstract class that implements Model.
 * the class will hold the model variables 
 * @author Nati Sadeh & Eli Salem
 */

public abstract class CommonModel extends Observable implements Model {
	/**
	 * CommonModel variables
	 */
	protected HashMap<String, Maze3d> hashMaze;
	protected HashMap<String, Object> hashCommand;
	protected ExecutorService threadPool;
	protected Properties properties;
	
	/**
	 * Constructor
	 */
	public CommonModel() {
		this.hashMaze = new HashMap<String, Maze3d>();
		this.hashCommand = new HashMap<String, Object>();
		threadPool = Executors.newCachedThreadPool();
		this.properties= new Properties();
		properties.defaultProperties();
	}
	
	/**
	 * this method is notify observers : command and object.
	 * @param command
	 * @param object
	 */
	protected void setNotify(String command, Object object){
		if(object != null)
			hashCommand.put(command, object);
		
		//use setChanged from observable.
		setChanged();
		notifyObservers(command);
	}
	
	/**
	 * this method return user command from hashCommand.
	 * @return object - command
	 */
	public Object getUserCommand(String command){
		return hashCommand.get(command);
	}
	
	/**
	 * set&get properties.
	 */
	public Properties getProperties() {
		return properties;
	}
	
	public void setProperties(Properties prop){
		this.properties = prop;
		ExecutorService threads = threadPool;
		threadPool = Executors.newFixedThreadPool(properties.getNumberOfThreads());
		threads.shutdown();
	}
/**
 * The methods what will be implemented in MyModel class
 */
	public abstract void generate(String name);
	public abstract void crossSectionBy(String ZYX, String name, int index);
	public abstract void saveMaze(String fileName);
	public abstract void loadMaze(String fileName, String name);
	public abstract void solveMaze(String name);
	public abstract Maze3d getMaze3d(String string);
	public abstract Solution<Position> getMazeSolution(String name);
	public abstract void saveToZip();
	public abstract void loadFromZip();
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveLeft();
	public abstract void moveRight();
	public abstract void moveBack();
	public abstract void moveForward();
	public abstract Position getPosition(String name);
	public abstract void exit();
}
