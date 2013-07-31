package nl.tudelft.jpacman.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * A button on the JPacMan interface that returns the focus to the parent window
 * after it is clicked.
 * 
 * @author Jeroen Roosen
 */
public class PButton extends JButton {

	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = 1986224795131878617L;

	/**
	 * A reference to the parent frame this button is on, the focus will be
	 * returned to it after this button is clicked.
	 */
	private JFrame parentWindow;

	/**
	 * Creates a new JButton that will return focus to its parent after being clicked.
	 * @param caption The caption of the button.
	 */
	public PButton(String caption) {
		super(caption);
	}

	/**
	 * Sets the frame to which the focus will be returned after this button has
	 * been clicked.
	 * 
	 * @param parentWindow
	 *            The window to eventually return the focus to.
	 */
	void setParentWindow(JFrame parentWindow) {
		this.parentWindow = parentWindow;
	}

	/**
	 * Returns the focus to the parent window if present.
	 */
	private void returnFocus() {
		if (parentWindow != null) {
			parentWindow.requestFocusInWindow();
		}
	}

	@Override
	protected void fireActionPerformed(ActionEvent event) {
		super.fireActionPerformed(event);
		returnFocus();
	}
}
