package presenter;

import java.io.Serializable;

public class Properties implements Serializable {

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
	
	public Properties(){
		super();
	}
	
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

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getTypeOfMaze() {
		return typeOfMaze;
	}

	public void setTypeOfMaze(String typeOfMaze) {
		this.typeOfMaze = typeOfMaze;
	}

	public String getMazeName() {
		return mazeName;
	}

	public void setMazeName(String nameMaze) {
		this.mazeName = nameMaze;
	}

	public String getChooseView() {
		return chooseView;
	}

	public void setChooseView(String chooseView) {
		this.chooseView = chooseView;
	}

	public char getAxis() {
		return axis;
	}

	public void setAxis(char axis) {
		this.axis = axis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
