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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ChessBoard {
	public static final int BLACK = 0, WHITE = 1;
	
	public final JPanel chessPanel;
	public JLabel message;
	
	public JButton startButton;
    public JButton nextButton;
    public JButton resetButton;
    public Action autoAction;
    public Action nextAction;
    public Action startAction;
    
    public JButton[][] chessBoardSquares;
    
    public JPanel chessBoard;
    public JPanel execBoard;
    
    public int boardSize;
    
    public Image[] knightImage = new Image[1];
    
    public int lastX, lastY;
    public int moveNumber = 1;
    public int[][] results;
    public Timer timer;
    public KnightsTour kt = new KnightsTour();
    
    
    
    public static void main(String args[]){
		Runnable r = new Runnable() {
			public void run() {
				ChessBoard cb = new ChessBoard(6);
				
				JFrame fr = new JFrame("Knight's Tour");
				fr.getContentPane().add(cb.chessPanel);
				fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.setLocationByPlatform(true);

				fr.pack();
				
				fr.setMaximumSize(fr.getSize());
				fr.setVisible(true);
				
            }
        };
        SwingUtilities.invokeLater(r);
    	
    }
    
    public ChessBoard(int x){
    	this.boardSize = x;
    	this.chessBoardSquares = new JButton[x][x];
    	this.chessPanel = new JPanel(new BorderLayout(3, 3));
    	this.message = new JLabel("READY TO START");
    	initialize();
    }
    
    
public void initialize() {
		
		createImages();
		chessPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JTextField x1 = new JTextField("1");
		JTextField y1 = new JTextField("1");
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
				String s1 = x1.getText();
				String s2 = y1.getText();
				//System.out.println(s1+"   " + s2);
				int xx=0,yy=0;
				if(s1.length()>0 && s2.length()>0){
					xx = Integer.parseInt(s1);
					yy = Integer.parseInt(s2);
					results = kt.run(boardSize,xx,yy);
					}
				
				if((xx>=1 && xx<=boardSize) && (yy>=1 && yy<=boardSize)){
					message.setText("READY TO GO WITH GIVEN COORDINATES...");
					startKnightsTour(xx,yy);
					nextButton.setEnabled(true);
					startButton.setEnabled(false);
					x1.setEditable(false);
					y1.setEditable(false);
				}
				else{
					message.setText("GIVE PROPER CO-ORDINATES.....!!!!");
				}
				
				
				if (timer.isRunning()) {
					timer.stop();
				}
	        }  
		});
		
		
		nextAction = new NextAction("Next");
		nextButton = new JButton(nextAction);
		nextButton.setEnabled(false);
		
		timer = new Timer(150, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				nextButton.doClick();
			}
		});
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  
				JButton b;
				for (int i = 0; i < chessBoardSquares.length; i++) {
					for (int j = 0; j < chessBoardSquares[i].length; j++) {
						b = chessBoardSquares[i][j];
						b.setIcon(null);
						b.setText(null);
						if (((j % 2 == 1) && (i % 2 == 1)) || ((j % 2 == 0) && (i % 2 == 0))) {
							b.setBackground(Color.WHITE);
						} else {
							b.setBackground(Color.BLACK);
						}
					}
				}
				nextButton.setEnabled(false);
				startButton.setEnabled(true);
				x1.setEditable(true);
				y1.setEditable(true);
				moveNumber = 1;
				message.setText("Ready to go.");
	        }  
		});
		
		
		
		execBoard = new JPanel(new GridLayout(16,55,5,15));
		
		JLabel xLabel = new JLabel("Row Number : ");
		JLabel yLabel = new JLabel("Column Number : ");
		xLabel.setForeground(Color.WHITE);
		yLabel.setForeground(Color.WHITE);
		execBoard.setBackground(Color.GRAY);
		execBoard.add(xLabel);
		execBoard.add(x1);
		execBoard.add(yLabel);
		execBoard.add(y1);
		execBoard.add(startButton);
		execBoard.add(nextButton);
		execBoard.add(resetButton);
		
		
		execBoard.setBorder(new LineBorder(Color.BLACK));
		chessPanel.add(execBoard, BorderLayout.LINE_START);
		
		
		chessPanel.add(message, BorderLayout.AFTER_LAST_LINE);
		
		
		//------------------------------------------
		
		
		
		
		
		chessBoard = new JPanel(new GridLayout(0, this.boardSize+1));
		chessBoard.setBorder(new LineBorder(Color.BLACK));
		chessPanel.add(chessBoard);
		
		fillBoard();
		
		chessBoard.add(new JLabel(""));
		for (int i = 0; i < this.boardSize; i++) {
			chessBoard.add(new JLabel("" + (i+1),SwingConstants.CENTER));
		}

		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				switch (j) {
				case 0:
					chessBoard.add(new JLabel("" + (i+1),SwingConstants.CENTER));
				default:
					chessBoard.add(chessBoardSquares[j][i]);
				}
			}
		}
		
		
	}

	
	
	public final void createImages() {
		try {
			InputStream in = ChessBoardMain.class.getResourceAsStream("./chess-pieces.png");
			BufferedImage bi = ImageIO.read(in);
			knightImage[0] = bi.getSubimage(192, 64, 64, 64);


		} catch (Exception e) {
			System.out.println("IMAGE LOADING PROBLEM........................");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	
	public void fillBoard() {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < chessBoardSquares.length; i++) {
			for (int j = 0; j < chessBoardSquares[i].length; j++) {
				JButton b = new JButton();
				b.setMargin(buttonMargin);
				
				// The chess pieces are 64x64 px in size, so we'll
				// "fill this in" using a transparent icon.
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64,
						BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				if (((j % 2 == 1) && (i % 2 == 1))
						|| ((j % 2 == 0) && (i % 2 == 0))) {
					b.setBackground(Color.WHITE);
				} else {
					b.setBackground(Color.BLACK);
				}
				chessBoardSquares[j][i] = b;
			}
		}
	}	
	
	
	
	public final void startKnightsTour(int xx,int yy) {
		message.setText("The Knight is ready to tour!");
		chessBoardSquares[xx-1][yy-1].setIcon(new ImageIcon(knightImage[0]));
	}

	class NextAction extends AbstractAction {
		public static final long serialVersionUID = 1L;

		NextAction(String text) {
			super(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (moveNumber > 1) {
				chessBoardSquares[lastX][lastY].setIcon(null);
				chessBoardSquares[lastX][lastY].setBackground(Color.GRAY);
				chessBoardSquares[lastX][lastY].setText(String.valueOf(moveNumber-1));
			}
			
			if (moveNumber == boardSize*boardSize) {
				this.setEnabled(false); 		// TODO: Should disable next button...
				nextButton.setEnabled(false);	// But had to add this.
				timer.stop();
				message.setText("The Knight has completed the tour.");
			}
			
			for (int i = 0; i < KnightsTour.N; i++) {
				for (int j = 0; j < KnightsTour.N; j++) {
					if (results[i][j] == moveNumber) {
						message.setText(String.format(
								"The Knight moved to (%d, %d). Move #%d.",
								i, j, moveNumber));
						chessBoardSquares[i][j].setIcon(new ImageIcon(knightImage[0]));
						lastX = i;
						lastY = j;
						break;
					}
				}
			}
			moveNumber++;
		}
	}
	
	

}
