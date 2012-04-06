package com.lancethepants.minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Minesweeper {

	static JFrame gameFrame = new JFrame();
	static JFrame selectionFrame = new JFrame();

	public static void main(String[] args) {

		selectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectionFrame.setVisible(true);
		selectionFrame.setSize(300, 440);

		JPanel selectionPanel = new JPanel();
		JButton Beginner = new JButton("Beginner");
		JButton Intermediate = new JButton("Intermedieate");
		JButton Advanced = new JButton("Advanced");

		SpringLayout layout = new SpringLayout();

		selectionPanel.setLayout(layout);
		selectionPanel.add(Beginner);
		selectionPanel.add(Intermediate);
		selectionPanel.add(Advanced);
		selectionFrame.add(selectionPanel);

		layout.putConstraint(SpringLayout.NORTH, Beginner, 100,
				SpringLayout.NORTH, selectionPanel);
		layout.putConstraint(SpringLayout.EAST, Beginner, -50,
				SpringLayout.EAST, selectionPanel);
		layout.putConstraint(SpringLayout.NORTH, Intermediate, 75,
				SpringLayout.NORTH, Beginner);
		layout.putConstraint(SpringLayout.NORTH, Advanced, 75,
				SpringLayout.NORTH, Intermediate);
		layout.putConstraint(SpringLayout.WEST, Intermediate, 0,
				SpringLayout.WEST, Beginner);
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
				gameFrame.dispose();
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setVisible(true);
				gameFrame.setSize(243, 265);
				gameFrame.add(new Board(9, 9, 10));
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
				gameFrame.dispose();
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setVisible(true);
				gameFrame.setSize(418, 440);
				gameFrame.add(new Board(16, 16, 40));

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
				gameFrame.dispose();
				gameFrame = new JFrame();
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setVisible(true);
				gameFrame.setSize(768, 440);
				gameFrame.add(new Board(30, 16, 99));
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
}
