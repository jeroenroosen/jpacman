package nl.tudelft.jpacman.gui;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A row of {@link PButton}s. Buttons can either be added at construction or by
 * using {@link #addButton(PButton)}.
 * 
 * @author Jeroen Roosen
 */
public class ButtonPanel extends JPanel {

	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = -8518896337767712397L;

	/**
	 * The list of buttons on this panel.
	 */
	private final List<PButton> pButtons;

	/**
	 * The parent frame, which may be <code>null</code>.
	 */
	private JFrame parentFrame;

	/**
	 * Creates a new panel containing the provided buttons.
	 * 
	 * @param buttons
	 *            The buttons to put on this panel.
	 */
	public ButtonPanel(PButton... buttons) {
		super(new FlowLayout());
		this.pButtons = new ArrayList<>(Arrays.asList(buttons));
		for (JButton button : pButtons) {
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
		assert parentWindow != null;
		for (PButton b : pButtons) {
			b.setParentWindow(parentWindow);
		}
		this.parentFrame = parentWindow;
	}

	/**
	 * Adds a {@link PButton} to this button panel.
	 * 
	 * @param button
	 *            The button to add.
	 */
	public void addButton(PButton button) {
		add(button);
		if (parentFrame != null) {
			button.setParentWindow(parentFrame);
		}
		pButtons.add(button);
	}
}
