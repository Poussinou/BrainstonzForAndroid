/**
 * 
 */
package eu.veldsoft.brainstonz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
class GameBoard extends JPanel {

	public GameSpace[] spaces;

	public GameBoard() {
		super(new SquareLayout());
		JPanel innerBoard = new JPanel();
		GridLayout layout = new GridLayout(4, 4);
		layout.setVgap(10);
		layout.setHgap(10);
		innerBoard.setLayout(layout);
		innerBoard
				.setBorder(new InsetLineBorder(new EmptyBorder(10, 10, 10, 10),
						new LineBorder(Color.BLACK, 3)));
		innerBoard.setOpaque(false);
		spaces = new GameSpace[16];
		for (int i = 0; i < spaces.length; i++) {
			innerBoard.add(spaces[i] = new GameSpace(i));
		}
		this.add(innerBoard);
		this.setOpaque(false);
		shuffleStonz();
		EventHandler.getInstance().registerBoard(this);
	}

	public void reset() {
		for (GameSpace space : spaces) {
			space.setHighlighted(false);
		}
		shuffleStonz();
		this.repaint();
	}

	public void shuffleStonz() {
		for (int i = 0; i < spaces.length; i++) {
			spaces[i].setStonz(
					ImageLoader.blackStonz[(int) (ImageLoader.numStonz * Math
							.random())],
					ImageLoader.whiteStonz[(int) (ImageLoader.numStonz * Math
							.random())]);
		}
	}

}