package game;

import game.*;
import engine.*;
import entity.*;
import input.*;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.*;

public class GameGrid extends Component {

	int width, height;
	int tileSize;
	Tile[][] entityGrid;
	Tile[][] oldGrid;
	int[] randomChoice = Tile.BASIC;
	int shieldCount;
	Random rgen;

	public GameGrid(Entity e, int width, int height) {
		super(e);
		this.width = width;
		this.height = height;
		entityGrid = new Tile[width][height];
		oldGrid = new Tile[width][height];
		emptyGrid();
		//tileSize = this.entity.game.windowWidth / width;
		tileSize = 128;
		rgen = new Random();
		// for now, add a king in the middle
		addEntity(new King(this.entity.game), 2, 2);
		//addEntity(new Arrow(this.entity.game), 0, 1);
		//addEntity(new Bow(this.entity.game), 2, 1);
	}

	private void initCounts() {
		shieldCount = 0;
	}

	private void updateCounts() {
		initCounts();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (entityGrid[x][y] == null) continue;
				if (entityGrid[x][y] instanceof Shield) shieldCount++;
			}
		}
		if (shieldCount > 1) {
			randomChoice = Tile.DEFENDED;
		} else {
			randomChoice = Tile.BASIC;
		}

	}

	public class Coord {
		int x, y;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private boolean addRandomTile() {
		// get all the legitimate positions
		ArrayList<Coord> positions = new ArrayList<Coord>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (entityGrid[x][y] == null) {
					positions.add(new Coord(x, y));
				}
			}
		}

		if (positions.size() == 0) return false;
		Coord position = positions.get(rgen.nextInt(positions.size()));
		addEntity(getRandomTile(), position.x, position.y);
		return true;
	}

	private Tile getRandomTile() {
		int type = randomChoice[rgen.nextInt(randomChoice.length)];
		Tile result;
		switch(type) {
			case Tile.KING:
				result = new King(this.entity.game);
				break;
			case Tile.ARROW:
				result = new Arrow(this.entity.game);
				break;
			case Tile.BOW:
				result = new Bow(this.entity.game);
				break;
			case Tile.SHIELD:
				result = new Shield(this.entity.game);
				break;
			default:
				result = null;
				break;
		}
		return result;
	}

	private void copyGrid(Tile[][] src, Tile[][] dest) {
		for (int x = 0; x < src.length; x++) {
			for (int y = 0; y < src[0].length; y++) {
				dest[x][y] = src[x][y];
			}
		}
	}

	private boolean gridsEqual(Tile[][] a, Tile[][] b) {
		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[0].length; y++) {
				if (a[x][y] != b[x][y]) return false;
			}
		}
		return true;
	}

	private void emptyGrid() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				entityGrid[x][y] = null;
			}
		}
	}

	private void addEntity(Tile t, int x, int y) {
		entityGrid[x][y] = t;
	}

	@Override
	public void update() {
		InputHandler input = this.entity.game.input;
		copyGrid(entityGrid, oldGrid);
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
			slideLeft();
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
		} else if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			slideLeft();
		} else if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
			slideLeft();
			entityGrid = rotateBoard(entityGrid);
		} else if (input.isKeyDown(KeyEvent.VK_UP)) {
			entityGrid = rotateBoard(entityGrid);
			slideLeft();
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
			entityGrid = rotateBoard(entityGrid);
		}

		// update the piece positions
		updatePositions();

		// if the grid is different, then we count
		// it as a turn
		if (!gridsEqual(oldGrid, entityGrid)) {
			//add in a random tile
			boolean possible = addRandomTile();
			if (!possible) {
				System.out.println("Game over");
			}
			updateCounts();
		}
	}

	private void slideLeft() {
		int w = entityGrid.length;
		int h = entityGrid[0].length;
		Tile[][] newGrid = new Tile[w][h];
		for (int y = 0; y < h; y++) {
			if (entityGrid[0][y] != null) newGrid[0][y] = entityGrid[0][y];
			for (int x = 1; x < w; x++) {
				if (entityGrid[x][y] == null) continue;
				int loc = x - 1;
				while (loc >= 0 && newGrid[loc][y] == null) {
					loc--;
				}
				// can compare with loc to do merging if necessary
				if (loc >= 0) {
					Tile result = entityGrid[x][y].combine(newGrid[loc][y]);
					if (result != null) {
						newGrid[loc][y] = result;
					} else {
						newGrid[loc+1][y] = entityGrid[x][y];
					}
				} else {
					newGrid[loc+1][y] = entityGrid[x][y];
				}
			}
		}
		entityGrid = newGrid;
	}

	private Tile[][] rotateBoard(Tile[][] board) {
		int m = board.length;
		int n = board[0].length;
		Tile[][] rotated = new Tile[n][m];
		for (int x = 0; x < m; x++) {
			for (int y = 0; y < n; y++) {
				rotated[y][n - 1 - x] = board[x][y];
			}
		}
		return rotated;
	}

	private void updatePositions() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (entityGrid[x][y] == null) continue;
				entityGrid[x][y].slider.xDest = x * tileSize + entity.transform.x;
				entityGrid[x][y].slider.yDest = y * tileSize + entity.transform.y;
				entityGrid[x][y].destroyChildTiles();
				if (entityGrid[x][y].removeOnUpdate) {
					entityGrid[x][y].destroySelf();
					entityGrid[x][y] = null;
				}
			}
		}
	}
}
