import javax.swing.UIManager;

/**
 * Controla la ejecución del programa
 * @author Unai
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception ignored) {}
		//new InicioFrame();	
		MainFrame.createAndShowGUI();
	}
}
