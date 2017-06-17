package org.jointheleague.level4.minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MineSweeper {
	static final int WIDTH = 10;
	static final int HEIGHT = 15;
	static final int NUM_MINES = 149;
int counter=(WIDTH*HEIGHT)-NUM_MINES;
	final JFrame frame;
	Optional<boolean[][]> mines = Optional.empty();

	public MineSweeper() {
		frame = new JFrame();
		frame.setSize(WIDTH * 20, HEIGHT * 20);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(HEIGHT, WIDTH));

		for (int y = 0; y < HEIGHT; ++y) {
				for (int x = 0; x < WIDTH; ++x) {
				final JButton b = new JButton();
				final int x1 = x;
				final int y1 = y;
			
				// Uncomment the following line, adding a listener that:
				// 1. Initializes the mines if necessary.
				// 2. Display either the mine if there is one, or the number of
				// neighboring cells contain mines.
				// Use a lambda here.
				 b.addActionListener(this::showDialogIfAllMinesSwept);
				// Uncomment the following line, add a listener that displays a
				// dialog box if mines have been swept.
				// Use a method handle here.
				b.addActionListener(evt -> {
					
					if (!mines.isPresent()) {
						initializeMines(x1, y1);
					}
					System.out.println("X1 = " + x1 + "	Y1 = " + y1);
					if (mines.get()[x1][y1]) {
						b.setText("X");

						JOptionPane.showMessageDialog(null, "A Mine has been triggered you have lost!");
						System.exit(0);
					}
					else
					{
						String t = Integer.toString(getNeighboringMinesCount(x1,y1));
						b.setText(t);
	
						
					}
					
					b.setEnabled(false);
					
				});

				frame.add(b);
			}
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

	public void initializeMines(int firstCellX, int firstCellY) {
		boolean[][] b = new boolean[WIDTH][HEIGHT];
		int counter = 0;
		Random ran = new Random();
		while (counter < NUM_MINES) {
			int x = ran.nextInt(WIDTH);
			int y = ran.nextInt(HEIGHT);
			if (!b[x][y] && !(x == firstCellX && y == firstCellY)) {
				b[x][y] = true;
				counter++;
			}

		}
		mines = Optional.of(b);
		// TODO initialize the mines variable with NUM_MINES randomly
		// distributed mines.

	}

	public int getNeighboringMinesCount(int x, int y) {
		// TODO for a given cell, count the number of neighboring cells that are
		// mines.
		boolean[][] b = mines.get();
		int xStart = Math.max(0, x - 1);
		int xEnd = Math.min(WIDTH - 1, x + 1);
		int yStart = Math.max(0, y - 1);
		int yEnd = Math.min(HEIGHT - 1, y + 1);
		int counter = 0;
		for (int i = xStart; i <= xEnd; i++) {
			for (int j = yStart; j <= yEnd; j++) {
				System.out.println(i +" "+j);
				if (b[i][j]) {
					counter++;
				}
			}
		}
		return counter;
	}

	public void showDialogIfAllMinesSwept(ActionEvent evt) {
		counter--;
	if(counter==0)
	{
		JOptionPane.showMessageDialog(null, "You win!");
	}
	}
	public static void main(String[] args) {
		new MineSweeper();
	}
}
