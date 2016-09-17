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
	

	void generate(String name);
	void crossSectionBy(String ZYX, String name, int index);
	
	void saveMaze(String fileName);
	void loadMaze(String fileName, String name);
	
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
	

	void exit();
	
	
}
