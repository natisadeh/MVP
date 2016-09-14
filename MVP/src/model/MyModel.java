package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import algorithms.demo.SearchableMaze;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

// TODO: Auto-generated Javadoc
/**
 * The MyModel is a class that extends CommonModel.
 * the class will implement the methods from Model Interface 
 * @author Nati Sadeh & Eli Salem
 */
public class MyModel extends CommonModel {
     
	private HashMap<Maze3d, Solution<Position>> hashSolution;
	private HashMap<String, Position> hashPosition;

	public MyModel() {
		super();
		this.hashSolution = new HashMap<Maze3d,Solution<Position>>();
		this.hashPosition = new HashMap<String, Position>();
		threadPool = Executors.newFixedThreadPool(properties.getNumberOfThreads());
	}
	
	public HashMap<Maze3d, Solution<Position>> getHashSolution() {
		return hashSolution;
	}

	public void setHashSolution(HashMap<Maze3d, Solution<Position>> hashSolution) {
		this.hashSolution = hashSolution;
	}

	public HashMap<String, Position> getHashPosition() {
		return hashPosition;
	}

	public void setHashPosition(HashMap<String, Position> hashPosition) {
		this.hashPosition = hashPosition;
	}


	/**
	 * This method generate a new maze 
	 * The method will run a new thread from the threadpool when creating a new maze
	 */
	@Override
	public void generate(String name) {
		// local variables
				String mazeName = properties.getMazeName();
				int z = properties.getZ();
				int y = properties.getY();
				int x = properties.getX();

				// using Future to arrange allocate maze3d in different thread.
				Future<Maze3d> futureMaze = threadPool.submit(new Callable<Maze3d>() {

					@Override
					public Maze3d call() throws Exception {
						// create maze3d
						Maze3d maze3d = new SimpleMaze3dGenerator().generate(z, y, x);
						return maze3d;
					}
				});

				try {
					hashMaze.put(mazeName, futureMaze.get());
					hashPosition.put(mazeName, futureMaze.get().getStartPosition());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

				// set notify observers name
				setNotify("mazeIsReady", getMaze3d(mazeName));
	}

	/**
	 * This method will display a 2D maze
	 * The method will check in the hashmap if the  maze exist and will display as 2D maze 
	 * 
	 */
	@Override
	public void crossSectionBy(String ZYX, String name, int index) {
		String mazeName = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(mazeName);

		if (maze3d == null) {
			setChanged();
			notifyObservers("Maze is not available");
			return;
		}
		try {
			switch (ZYX) {
			case "z":
			case "Z":
				setNotify("displayCrossSectionBy", maze3d.getCrossSectionByZ(index));
				break;
			case "y":
			case "Y":
				setNotify("displayCrossSectionBy", maze3d.getCrossSectionByY(index));
				break;
			case "x":
			case "X":
				setNotify("displayCrossSectionBy", maze3d.getCrossSectionByX(index));
				break;
			default:
				setChanged();
				notifyObservers("Invalid parameters");
				return;
			}
		} catch (IndexOutOfBoundsException e) {
			setChanged();
			notifyObservers("Invalid index");
			return;
		}
	}

	/**
	 * This method will save a maze
	 * The method will check in the hashmap if the maze exist and will save him as bytes in OUT file
	 */
	@Override
	public void saveMaze(String fileName) {
		// get the maze from hashMap
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		
		if (maze3d == null){
			setChanged();
		    notifyObservers("Maze is not available");
		}
		else {
			try {
				// writing the maze by byteArray into file.
				OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fileName));
				out.write(maze3d.toByteArray());
				out.close();
				setNotify("saveMaze", fileName);
				// handle exceptions.
			} catch (FileNotFoundException e) {
				setNotify("Invalid file", fileName);
			} catch (IOException e) {
				setNotify("Invalid compress", name);
			}
		}

	}

	/**
	 * This method will display a maze
	 * The method will check in the hashmap if the maze exist and will load him from OUT file 
	 */
	@Override
	public void loadMaze(String fileName, String name) {
		properties.setMazeName(name);
		
		try {	
			
			// creating inputStream to get the file name.
			InputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
			byte[] byteArray = new byte[35000]; // 34586

			// read from file
			int numerOfByte = in.read(byteArray);
			in.read(byteArray);
			in.close();
			byte[] newByteArray = new byte[numerOfByte];

			// copy arrays
			for (int i = 0; i < newByteArray.length; i++)
				newByteArray[i] = byteArray[i];

			// creating maze3d and get him from hashMap.
			Maze3d maze3d = new Maze3d(byteArray);
			
			// put the new maze in hashMap
			hashMaze.put(name, maze3d);
			hashPosition.put(name, maze3d.getStartPosition());
			setNotify("loadMaze", getMaze3d(name));

			// handles exceptions.
		} catch (FileNotFoundException e) {
			setNotify("Invalid file", fileName);
		} catch (IOException e) {
			setNotify("Invalid maze", name);
		}
	}

	/**
	 * This method will solve a maze
	 * The method will check in the hashmap if the maze exist and solve him by a given algorithm
	 * The method run a thread when solving the maze
	 */
	@Override
	public void solveMaze(String name) {
		String mazeName = properties.getMazeName();
		String myAlgorithm = properties.getAlgorithm();
		Maze3d maze3d = hashMaze.get(mazeName);
		
		// check if the maze isn't empty
		if (maze3d != null) {
			// check if the solution isn't already exist
			if (!(hashSolution.containsKey(maze3d))) {
				// create future to calculate the solution in different thread.
				Future<Solution<Position>> futureSolution = threadPool.submit(new Callable<Solution<Position>>() {

					@Override
					public Solution<Position> call() throws Exception {
						// local variables
						Searcher<Position> algorithm;
						Searchable<Position> mazeSearch = new SearchableMaze(maze3d);
						Solution<Position> solution = new Solution<Position>();

						// switch-case to solve the maze.
						switch (myAlgorithm) {
						case "bfs":
						case "BFS":
							algorithm = new BestFirstSearch<Position>();
							solution = algorithm.search(mazeSearch);
							break;
						case "dfs":
						case "DFS":
							algorithm = new DepthFirstSearch<Position>();
							solution = algorithm.search(mazeSearch);
							break;
						default:
							setChanged();
							notifyObservers("Invalid algorithm");
							return null;
						}

						return solution;
					}

				});

				try {
					hashSolution.put(maze3d, futureSolution.get());
				}

				// handle exceptions.
				catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}

		setNotify("solutionIsReady", mazeName);
	}


	@Override
	public Maze3d getMaze3d(String string) {
		Maze3d maze3d = hashMaze.get(string);
		return maze3d;
	}


	@Override
	public Solution<Position> getMazeSolution(String name) {
		// local variables
		String mazeName = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(mazeName);

		if (maze3d == null) {
			setNotify("Invalid maze", name);
			return null;
		}

		// get solution from hashMap by key (maze3d).
		Solution<Position> solution = hashSolution.get(maze3d);
		return solution;
	}


	@Override
	public void saveToZip() {
		try {
			// use zipOutputStream to save the all mazes to zip file.
			ObjectOutputStream zippedMaze = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("ZippedMaze.zip")));
			zippedMaze.writeObject(hashMaze);
			zippedMaze.writeObject(hashSolution);
			zippedMaze.flush();
			zippedMaze.close();
			setNotify("saveZip", "ZippedMaze.zip");
		}
		// handle exceptions.
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void loadFromZip() {
		try {
			// use zipInputStream to load the all mazes from zip file.
			FileInputStream fileMaze = new FileInputStream("ZippedMaze.zip");
			ObjectInputStream mazeZipIn = new ObjectInputStream(new GZIPInputStream(fileMaze));
			hashMaze = (HashMap<String, Maze3d>) mazeZipIn.readObject();
			hashSolution = (HashMap<Maze3d, Solution<Position>>) mazeZipIn.readObject();
			mazeZipIn.close();
			setNotify("loadZip", "ZippedMaze.zip");
		}

		// handle exceptions.
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void moveUp() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Up") {
				position.setZ(position.getZ() + 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}

	}


	@Override
	public void moveDown() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Down") {
				position.setZ(position.getZ() - 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}

	}


	@Override
	public void moveLeft() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Left") {
				position.setX(position.getX() - 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}

	}


	@Override
	public void moveRight() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Right") {
				position.setX(position.getX() + 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}
		
	}


	@Override
	public void moveBack() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Back") {
				position.setY(position.getY() - 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}
		
	}

	@Override
	public void moveForward() {
		// local variables
		String name = properties.getMazeName();
		Maze3d maze3d = hashMaze.get(name);
		Position position = hashPosition.get(name);
		ArrayList<String> possibleMoves = maze3d.getPossibleMoves(position);

		// for each possible move
		for (String move : possibleMoves) {
			if (move == "Forward") {
				position.setY(position.getY() + 1);
				hashPosition.put(name, position);
				setNotify("move", name);
			}
		}
		
	}

	@Override
	public Position getPosition(String name) {
		return hashPosition.get(name);
	}
	
	/**
	 * This method will close the model work
	 * This method will inform the controller that the model has stop
	 * This method will close any thread that worked in the model
	 */
	@Override
	public void exit() {
//		saveToZip();
		threadPool.shutdownNow();
		try {
			while (!(threadPool.awaitTermination(10, TimeUnit.SECONDS)));
			setChanged();
			notifyObservers("exit");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
