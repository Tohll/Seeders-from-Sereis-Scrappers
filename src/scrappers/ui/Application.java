/**
 *
 */
package scrappers.ui;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * @author Seldan
 *
 */
public class Application extends JFrame {

    private static final long serialVersionUID = 6424756108494662748L;

    public Application() {
        try {
			this.add(new GraphicEngine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.setResizable(false);
        this.pack();
        this.setTitle("Fourmiliere 1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
