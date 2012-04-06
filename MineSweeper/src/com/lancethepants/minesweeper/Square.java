package com.lancethepants.minesweeper;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Square extends JButton {

	private static final long serialVersionUID = 1L;

	Dimension postion;
	String type;
	boolean isRevealed;
	boolean isFlagged;
	boolean isSet;
	int buttonSize = 25;

	public Square(Dimension position) {
		this.postion = position;
		this.type = "";
		this.isRevealed = false;
		this.isFlagged = false;
		this.isSet = false;

		BufferedImage bi = new BufferedImage(buttonSize, buttonSize,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(new ImageIcon("images/unrevealed.png").getImage(), 0, 0,
				buttonSize, buttonSize, null);
		g.dispose();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		this.setIcon(new ImageIcon(bi));

		this.setVisible(true);
		this.setPreferredSize(new Dimension(buttonSize, buttonSize));

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				if (Board.gameOver == false) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						leftMouseClick();
						checkWin();
					}
					if (e.getButton() == MouseEvent.BUTTON3) {
						rightMouseClick();
						checkWin();
					}
				}
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

			}
		});
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setIsRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}

	public boolean getIsRevealed() {
		return isRevealed;
	}

	public void setIsFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public void setIsSet(boolean isSet) {
		this.isSet = isSet;
	}

	public boolean getIsSet() {
		return isSet;
	}

	public Dimension getPosition() {
		return postion;
	}

	public boolean isMine() {
		if (type.equals("MINE")) {
			return true;
		} else {
			return false;
		}
	}

	public void revealSquare() {

		BufferedImage bi = new BufferedImage(buttonSize, buttonSize,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();

		if (type.equals("MINE")) {

			g.drawImage(new ImageIcon("images/Mine.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

			Minesweeper.gameLost();

		} else if (type.equals("0")) {

			g.drawImage(new ImageIcon("images/0.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("1")) {

			g.drawImage(new ImageIcon("images/1.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("2")) {

			g.drawImage(new ImageIcon("images/2.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("3")) {

			g.drawImage(new ImageIcon("images/3.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("4")) {

			g.drawImage(new ImageIcon("images/4.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("5")) {

			g.drawImage(new ImageIcon("images/5.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("6")) {

			g.drawImage(new ImageIcon("images/6.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("7")) {

			g.drawImage(new ImageIcon("images/7.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);

		} else if (type.equals("8")) {

			g.drawImage(new ImageIcon("images/8.png").getImage(), 0, 0,
					buttonSize, buttonSize, null);
		}
		g.dispose();
		this.setIcon(new ImageIcon(bi));

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		this.isRevealed = true;
	}

	public void leftMouseClick() {

		if (isFlagged == false && isRevealed == false) {
			revealSquare();
			this.isRevealed = true;
			if (this.getType().equals("0")) {
				Board.reveal0Squares(this);
			}
		}
	}

	public void rightMouseClick() {

		BufferedImage bi = new BufferedImage(buttonSize, buttonSize,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();

		if (isRevealed == false) {

			if (isFlagged == false) {

				this.isFlagged = true;
				g.drawImage(new ImageIcon("images/flag.png").getImage(), 0, 0,
						buttonSize, buttonSize, null);
				g.dispose();
				this.setIcon(new ImageIcon(bi));

			} else if (isFlagged) {

				this.isFlagged = false;
				g.drawImage(new ImageIcon("images/unrevealed.png").getImage(),
						0, 0, buttonSize, buttonSize, null);
				g.dispose();
				this.setIcon(new ImageIcon(bi));
			}

			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
	}

	private void checkWin() {

		if (Board.checkWin()) {
			Minesweeper.gameWon();
		}

	}
}
