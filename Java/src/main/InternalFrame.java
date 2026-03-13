package main;
	import javax.swing.JInternalFrame;

/**
 * Base que se usa para crear los diferentes internal frames
 */
public class InternalFrame extends JInternalFrame{
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	 
	/**
	 * Contructor del internal frame
	 */
	public InternalFrame() {
		 super("Document #" + (++openFrameCount), 
				 true, //resizable
				 true, //cerrable
				 true, //maximizable
				 false);
		 setSize(300,300);
	     setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	 }
	 
}
