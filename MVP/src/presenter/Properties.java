package presenter;

import java.io.Serializable;

/**
 * The Class Properties.
 * @author Nati Sadeh & Eli Salem
 */
public class Properties implements Serializable {


/** Variables. */
	private static final long serialVersionUID = 3374L;
	
	
	private int z;
	private int y;
	private int x;
	private int numberOfThreads;
	private String algorithm;
	private String typeOfMaze;
	private String mazeName;
	private String chooseView;
	private char axis;
	
	/**
	 * default C'tor.
	 */
	public Properties(){
		super();
	}
	
	/**
	 * C'tor that will assign the variables of the properties.
	 *
	 * @param z the z
	 * @param y the y
	 * @param x the x
	 * @param numberOfThreads the number of threads
	 * @param algorithm the algorithm
	 * @param typeOfMaze the type of maze
	 * @param mazeName the maze name
	 * @param chooseView the choose view
	 * @param axis the axis
	 */
	public Properties(int z, int y, int x, int numberOfThreads, String algorithm, String typeOfMaze, String mazeName,
			String chooseView, char axis) {
		super();
		this.z = z;
		this.y = y;
		this.x = x;
		this.numberOfThreads = numberOfThreads;
		this.algorithm = algorithm;
		this.typeOfMaze = typeOfMaze;
		this.mazeName = mazeName;
		this.chooseView = chooseView;
		this.axis = axis;
	}
	
	/**
	 * A method that will set a default parameters to the properties variables.
	 */
	public void defaultProperties(){
		this.z = 5;
		this.y = 5;
		this.x = 5;
		this.numberOfThreads = 5;
		this.algorithm = "BFS";
		this.typeOfMaze = "SimpleMaze3dGenerator";
		this.mazeName = "RoadHog";
		this.chooseView = "CLI";
		this.axis = 'x';
	}

	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the number of threads.
	 *
	 * @return the number of threads
	 */
	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	/**
	 * Sets the number of threads.
	 *
	 * @param numberOfThreads the new number of threads
	 */
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	/**
	 * Gets the algorithm.
	 *
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm the new algorithm
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * Gets the type of maze.
	 *
	 * @return the type of maze
	 */
	public String getTypeOfMaze() {
		return typeOfMaze;
	}

	/**
	 * Sets the type of maze.
	 *
	 * @param typeOfMaze the new type of maze
	 */
	public void setTypeOfMaze(String typeOfMaze) {
		this.typeOfMaze = typeOfMaze;
	}

	/**
	 * Gets the maze name.
	 *
	 * @return the maze name
	 */
	public String getMazeName() {
		return mazeName;
	}

	/**
	 * Sets the maze name.
	 *
	 * @param nameMaze the new maze name
	 */
	public void setMazeName(String nameMaze) {
		this.mazeName = nameMaze;
	}

	/**
	 * Gets the choose view.
	 *
	 * @return the choose view
	 */
	public String getChooseView() {
		return chooseView;
	}

	/**
	 * Sets the choose view.
	 *
	 * @param chooseView the new choose view (CLI or GUI)
	 */
	public void setChooseView(String chooseView) {
		this.chooseView = chooseView;
	}

	/**
	 * Gets the axis.
	 *
	 * @return the axis
	 */
	public char getAxis() {
		return axis;
	}

	/**
	 * Sets the axis.
	 *
	 * @param axis the new axis
	 */
	public void setAxis(char axis) {
		this.axis = axis;
	}

	/**
	 * Gets the serial version uid.
	 *
	 * @return the serial version uid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
