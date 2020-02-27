package GUIPackage;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class bar extends JPanel {

	/**
	 * Create the panel.
	 */
	public bar() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);

	}

}
