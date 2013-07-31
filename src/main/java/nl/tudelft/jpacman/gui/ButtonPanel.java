package nl.tudelft.jpacman.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *A row of {@link PButton}s.
 * 
 * @author Jeroen Roosen
 */
public class ButtonPanel extends JPanel {

	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = -8518896337767712397L;

	private final PButton[] pButtons;

	/**
	 * Creates a new panel containing the provided buttons.
	 * 
	 * @param buttons
	 *            The buttons to put on this panel.
	 */
	public ButtonPanel(PButton... buttons) {
		super(new FlowLayout());
		this.pButtons = buttons;
		for (JButton button : buttons) {
			add(button);
		}
	}

	/**
	 * Sets the frame to which the focus will be returned after a button on this
	 * panel has been clicked.
	 * 
	 * @param parentWindow
	 *            The window to eventually return the focus to.
	 */
	void setParentWindow(JFrame parentWindow) {
		for (PButton b : pButtons) {
			b.setParentWindow(parentWindow);
		}
	}
}
