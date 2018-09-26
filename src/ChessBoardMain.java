
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;



import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class ChessBoardMain {
	
    public static final int BLACK = 0, WHITE = 1;    
    public static JFrame f;
    
    JFrame newFrame;
    
	public JPanel mainPanel = new JPanel(new BorderLayout(3, 3));
	public final JLabel message = new JLabel(
            "Developers : Meet | Pooja | Maharshi");
 
    
    
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				ChessBoardMain cb = new ChessBoardMain();
				
				f = new JFrame("LOI Project - Knight's Tour");
				   f.getContentPane().add(cb.mainPanel);
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setLocationByPlatform(true);

				f.pack();
				
				f.setMaximumSize(f.getSize());
				f.setVisible(true);
				
            }
        };
        SwingUtilities.invokeLater(r);
	}
    
	
	
    public ChessBoardMain() {
    	initialize();
    }
	
	
	public void initialize() {
		
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		//tools.add(message);
		//JLabel l = new JLabel("<html><br><br></html>", SwingConstants.CENTER);
		
		mainPanel.add(tools, BorderLayout.PAGE_START);
		
		String array[] = { "6" , "8" };
		JComboBox<String> cb = new JComboBox<String>(array);
		
		cb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String data = (String) cb.getItemAt(cb.getSelectedIndex());
				
				int temp = Integer.parseInt(data);
				
				if(temp==6){
					ChessBoard cb6 = new ChessBoard(6);
					//mainPanel.add(cb6.chessPanel);
					Runnable rr = new Runnable() {
						public void run() {
							newFrame = new JFrame("Knight's Tour With " + 6 +"x"+ 6 + " Board.");

					    	newFrame.getContentPane().add(cb6.chessPanel);
							newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							newFrame.setLocationByPlatform(true);

							newFrame.pack();
							
							newFrame.setMaximumSize(newFrame.getSize());
							newFrame.setVisible(true);
			            }
			        };
			        SwingUtilities.invokeLater(rr);
			        
				}
				else{
					ChessBoard cb8 = new ChessBoard(8);
					//mainPanel.add(cb8.chessPanel);
					Runnable rr = new Runnable() {
						public void run() {
							newFrame = new JFrame("Knight's Tour With " + 6 +"x"+ 6 + " Board.");

					    	newFrame.getContentPane().add(cb8.chessPanel);
							newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							newFrame.setLocationByPlatform(true);

							newFrame.pack();
							
							newFrame.setMaximumSize(newFrame.getSize());
							newFrame.setVisible(true);
			            }
			        };
			        SwingUtilities.invokeLater(rr);
			        					
				}
				
			}
		});
		
		tools.add(cb);
		
		
	}



}
