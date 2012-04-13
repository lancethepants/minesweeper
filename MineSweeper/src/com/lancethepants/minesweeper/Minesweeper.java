package com.lancethepants.minesweeper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Minesweeper {

	static JFrame gameFrame = new JFrame();
	static JFrame selectionFrame = new JFrame("Minesweeper - By Lance");
	private static int Beginner_North;
	private static int Intermediate_North;
	private static int Advanced_North;

	public static void main(String[] args) {
		
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionFrame.setSize(300, 300);
		
		// Determine the new location of the window
		int w = selectionFrame.getSize().width;
		int h = selectionFrame.getSize().height;
		int x = (dim.width/2)-(w/2);
		int y = (dim.height/2)-(h/2);
		
		// Move the window
		selectionFrame.setLocation(x, y);
		selectionFrame.setVisible(true);

		JPanel selectionPanel = new JPanel();
		JButton Beginner = new JButton("Beginner");
		JButton Intermediate = new JButton("Intermediate");
		JButton Advanced = new JButton("Advanced");

		SpringLayout layout = new SpringLayout();

		selectionPanel.setLayout(layout);
		selectionPanel.add(Beginner);
		selectionPanel.add(Intermediate);
		selectionPanel.add(Advanced);
		selectionFrame.add(selectionPanel);
		
		Beginner_North = h/6;
		
		Intermediate_North = h/3;
		
		Advanced_North = h/2;

		layout.putConstraint(SpringLayout.NORTH, Beginner, Beginner_North,
				SpringLayout.NORTH, selectionPanel);
		layout.putConstraint(SpringLayout.WEST, Beginner, 50,
				SpringLayout.WEST, selectionPanel);
		
		layout.putConstraint(SpringLayout.NORTH, Intermediate, Intermediate_North,
				SpringLayout.NORTH, selectionPanel);
		layout.putConstraint(SpringLayout.WEST, Intermediate, 0,
				SpringLayout.WEST, Beginner);
		
		layout.putConstraint(SpringLayout.NORTH, Advanced, Advanced_North,
				SpringLayout.NORTH, selectionPanel);
		layout.putConstraint(SpringLayout.WEST, Advanced, 0, SpringLayout.WEST,
				Intermediate);

		Beginner.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				selectionFrame.setVisible(false);
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setTitle("Beginner");
				gameFrame.setSize(243, 265);
				gameFrame.add(new Board(9, 9, 10));
				
				setBounds();
			}
		});

		Intermediate.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				selectionFrame.setVisible(false);
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setTitle("Intermediate");
				gameFrame.setSize(418, 440);
				gameFrame.add(new Board(16, 16, 40));
				
				setBounds();

			}
		});

		Advanced.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {

				selectionFrame.setVisible(false);
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setTitle("Advanced");
				gameFrame.setSize(768, 440);
				gameFrame.add(new Board(30, 16, 99));
				
				setBounds();
			}
		});

	}

	static public void gameLost() {

		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You stepped on a mine.");
		Board.setGameOver(true);
		frame.dispose();
		gameFrame.dispose();
		selectionFrame.setVisible(true);
		Board.gameOver = false;

	}

	static public void gameWon() {

		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You Win!");
		Board.setGameOver(true);
		frame.dispose();
		gameFrame.dispose();
		selectionFrame.setVisible(true);
		Board.gameOver = false;
	}
	
	public static void setBounds() {
		
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Determine the new location of the window
		int w = gameFrame.getSize().width;
		int h = gameFrame.getSize().height;
		int x = (dim.width/2)-(w/2);
		int y = (dim.height/2)-(h/2);
		// Move the window
		gameFrame.setLocation(x, y);
		gameFrame.setVisible(true);
	}
}
