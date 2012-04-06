package com.lancethepants.minesweeper;

import java.awt.Dimension;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int width;
	private static int height;
	private static int mines;
	private static int minesLeftToPlace;
	static int spacing;
	static SpringLayout layout = new SpringLayout();
	static Square[][] squares;
	static boolean gameOver = false;

	// Constructor
	public Board(int width, int height, int mines) {

		Board.setBoardWidth(width);
		Board.setBoardHeight(height);
		Board.setMines(mines);
		Board.setMinesLeftToPlace(mines);
		spacing = 0;
		this.setLayout(layout);
		squares = new Square[height][width];

		// Initialize a grid of squares
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				squares[i][j] = new Square(new Dimension(j, i));

				if (i == 0 && j == 0) {
					layout.putConstraint(SpringLayout.NORTH, squares[i][j],
							spacing, SpringLayout.NORTH, this);
					layout.putConstraint(SpringLayout.WEST, squares[i][j],
							spacing, SpringLayout.WEST, this);
					this.add(squares[i][j]);
				}
				if (i == 0 && j > 0) {
					layout.putConstraint(SpringLayout.NORTH, squares[i][j],
							spacing, SpringLayout.NORTH, this);
					layout.putConstraint(SpringLayout.WEST, squares[i][j],
							spacing, SpringLayout.EAST, squares[i][j - 1]);
					this.add(squares[i][j]);
				}
				if (i > 0 && j == 0) {
					layout.putConstraint(SpringLayout.NORTH, squares[i][j],
							spacing, SpringLayout.SOUTH, squares[i - 1][j]);
					layout.putConstraint(SpringLayout.WEST, squares[i][j],
							spacing, SpringLayout.WEST, this);
					this.add(squares[i][j]);
				}
				if (i > 0 && j > 0) {
					layout.putConstraint(SpringLayout.NORTH, squares[i][j],
							spacing, SpringLayout.SOUTH, squares[i - 1][j]);
					layout.putConstraint(SpringLayout.WEST, squares[i][j],
							spacing, SpringLayout.EAST, squares[i][j - 1]);
					this.add(squares[i][j]);
				}
			}
		}

		Random random = new Random();
		int rand1;
		int rand2;

		// Initialize
		while (getMinesLeftToPlace() > 0) {
			rand1 = random.nextInt(height);
			rand2 = random.nextInt(width);

			if (squares[rand1][rand2].isSet == false) {
				squares[rand1][rand2].setType("MINE");
				squares[rand1][rand2].setIsSet(true);
				setMinesLeftToPlace(getMinesLeftToPlace() - 1);
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int numberOfMines = 0;

				if (squares[i][j].isSet == false) {

					// Check Top Left Square
					if (i > 0 && j > 0) {
						if (squares[i - 1][j - 1].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Top Square
					if (i > 0) {
						if (squares[i - 1][j].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Top Right Square
					if (i > 0 && j < (width - 1)) {
						if (squares[i - 1][j + 1].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Left Square
					if (j > 0) {
						if (squares[i][j - 1].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Right Square
					if (j < (width - 1)) {
						if (squares[i][j + 1].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Bottom Left Square
					if (i < (height - 1) && j > 0) {
						if (squares[i + 1][j - 1].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Bottom Square
					if (i < (height - 1)) {
						if (squares[i + 1][j].isMine()) {
							numberOfMines += 1;
						}
					}

					// Check Bottom Right Square
					if (i < (height - 1) && j < (width - 1)) {
						if (squares[i + 1][j + 1].isMine()) {
							numberOfMines += 1;
						}
					}

					squares[i][j].setType(Integer.toString(numberOfMines));
					squares[i][j].setIsSet(true);
				}
			}
		}

		// Print Board to console
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if (squares[i][j].getType().equals("MINE")) {
					System.out.print("M ");
				} else {
					System.out.print(squares[i][j].getType() + " ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}// End Constructor

	public static void setBoardWidth(int width) {
		Board.width = width;
	}

	public static int getBoardWidth() {
		return width;
	}

	public static void setBoardHeight(int height) {
		Board.height = height;
	}

	public static int getBoardHeight() {
		return height;
	}

	public static void setMines(int mines) {
		Board.mines = mines;
	}

	public static int getMines() {
		return mines;
	}

	public static void setMinesLeftToPlace(int minesLeftToPlace) {
		Board.minesLeftToPlace = minesLeftToPlace;
	}

	public static int getMinesLeftToPlace() {
		return minesLeftToPlace;
	}

	public static void setGameOver(boolean over) {
		gameOver = over;
	}

	static public void reveal0Squares(Square square) {

		int squareWidth = square.getPosition().width;
		int squareHeight = square.getPosition().height;

		// Check Top Left Square
		if (squareHeight > 0 && squareWidth > 0) {

			Square tempSquare = squares[squareHeight - 1][squareWidth - 1];
			checkSquare(tempSquare);
		}

		// Check Top Square
		if (squareHeight > 0) {

			Square tempSquare = squares[squareHeight - 1][squareWidth];
			checkSquare(tempSquare);
		}

		// Check Top Right Square
		if (squareHeight > 0 && squareWidth < (getBoardWidth() - 1)) {

			Square tempSquare = squares[squareHeight - 1][squareWidth + 1];
			checkSquare(tempSquare);
		}

		// Check Left Square
		if (squareWidth > 0) {

			Square tempSquare = squares[squareHeight][squareWidth - 1];
			checkSquare(tempSquare);
		}

		// Check Right Square
		if (squareWidth < (getBoardWidth() - 1)) {

			Square tempSquare = squares[squareHeight][squareWidth + 1];
			checkSquare(tempSquare);
		}

		// Check Bottom Left Square
		if (squareHeight < (getBoardHeight() - 1) && squareWidth > 0) {

			Square tempSquare = squares[squareHeight + 1][squareWidth - 1];
			checkSquare(tempSquare);
		}

		// Check Bottom Square
		if (squareHeight < (getBoardHeight() - 1)) {

			Square tempSquare = squares[squareHeight + 1][squareWidth];
			checkSquare(tempSquare);
		}

		// Check Bottom Right Square
		if (squareHeight < (getBoardHeight() - 1)
				&& squareWidth < (getBoardWidth() - 1)) {

			Square tempSquare = squares[squareHeight + 1][squareWidth + 1];
			checkSquare(tempSquare);
		}
	}

	static private void checkSquare(Square tempSquare) {

		if (tempSquare.isRevealed == false && tempSquare.isFlagged == false) {
			tempSquare.revealSquare();
			tempSquare.setIsRevealed(true);
			if (tempSquare.getType().equals("0")) {
				reveal0Squares(tempSquare);
			}
		}
	}

	static boolean checkWin() {

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				if ((squares[i][j].isFlagged == false && squares[i][j].isRevealed == false)
						|| (squares[i][j].isFlagged && squares[i][j].isMine() == false)) {

					System.out.println("Square [" + i + "," + j
							+ "] did not pass");
					return false;
				}
			}
		}
		System.out.println("you win!");
		return true;
	}
}
