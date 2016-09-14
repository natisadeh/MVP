package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Properties;

/**
 * The Interface Model.
 * @author Nati Sadeh & Eli Salem
 */
public interface Model {
	
	/**
	 * Generate 3 d maze.
	 *
	 * @param name the name of the maze
	 * @param z the floors
	 * @param y the columns
	 * @param x the rows
	 */
	void generate(String name);
	
	/**
	 * Cross section by.
	 *
	 * @param ZYX the zyx
	 * @param name the name
	 * @param index the index
	 */
	void crossSectionBy(String ZYX, String name, int index);
	
	/**
	 * Save maze.
	 *
	 * @param name the name
	 * @param fileName the file name
	 */
	void saveMaze(String fileName);
	
	/**
	 * Load maze.
	 *
	 * @param name the name
	 * @param fileName the file name
	 */
	void loadMaze(String fileName, String name);
	
	/**
	 * Solve maze.
	 *
	 * @param name the name
	 * @param algorithm the algorithm
	 */
	void solveMaze(String name);
	
	Maze3d getMaze3d(String string);
	Solution<Position> getMazeSolution(String name);
	Object getUserCommand(String command);
	void saveToZip();
	void loadFromZip();
	void moveUp();
	void moveDown();
	void moveLeft();
	void moveRight();
	void moveBack();
	void moveForward();
	Position getPosition(String name);
	void setProperties(Properties properties);
	Properties getProperties();
	
	/**
	 * Exit.
	 */
	void exit();
	
	
}
