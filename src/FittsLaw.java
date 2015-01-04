import java.awt.BorderLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import java.util.Date;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FittsLaw{

	///////////////comment code
	///////////////slow down countdown timer
	
	JFileChooser fileChooser = new JFileChooser();
	File file;// = new File("LoggedData.csv");
	FileWriter writer;// = new FileWriter(file);
	BufferedWriter bufferedWriter;
	
	Boolean isAtEnd = false;
	
	String totalString;//="1,10000000,1000000000,10000000000";
	String emptyString = "\n";
	int timesToRun=5;
	long date; ////////////HHUUGGEEEE
	long dateUpdate;
	int trialNumber=1;	//check
	int targetSize;		//check
	int distanceToTarget;	//check
	long timeToClick=0;
	int timesClicked=1;
	int picWidth;
//	int picHeight;
	int xAxis;
	int yAxis;
	int sliderPicWidth=200-50;

	JSlider slider = new JSlider(JSlider.VERTICAL,100,300,200);
	
	Random rand = new Random();	
	BorderLayout layout= new BorderLayout(800,300);
	TheListener listener= new TheListener();
	Mlistener mListener = new Mlistener();
	CListener cListener = new CListener();

	JButton saveButton;
	JButton playAgainButton;
	JButton exitButton;
	
	JRadioButton button50;
	JRadioButton button75;
	JRadioButton button100;

		
	Timer time = new Timer(80,listener); //calls action after 2 seconds
	int count=5;
	
	ImageIcon chefIcon = new ImageIcon("target.jpg");
	Image scaleImage;// = chefIcon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
	ImageIcon plok;// = new ImageIcon(scaleImage);
	
	JMenuBar realMenuBar = new JMenuBar();
	JMenu menu = new JMenu("File");

	
	JMenuItem saveMenu = new JMenuItem("SaveAs");
	//JMenuItem settingsMenu = new JMenuItem("settings");
	JMenuItem exitMenu = new JMenuItem("Exit");
	JMenuItem restartMenu = new JMenuItem("Restart");
	
	JPanel menuBar = new JPanel();
	JPanel gamePanel = new JPanel();
	
	JLabel picLabel=null;

	
	private JLabel imgLabel = new JLabel(new ImageIcon("background.gif"));
	private JLabel countdownLabel=new JLabel("jkhkjhgjkg");
	private JLabel betterLabel=new JLabel(chefIcon);
	
	private JPanel countdownPanel = new JPanel();
	
	private JFrame frame;
	private JFrame frame1;
	private JPanel panelL;
	private JPanel panelC;
	private JPanel panelR;
	private Panel allPanel;
	
	BufferedImage image; 
	
	private JButton buttonSettings;
	private JButton buttonStart;
	private JButton buttonExit;
	private JButton buttonRules;
	//private JLabel label;
	JPanel finalPanel;
	GridLayout gl = new GridLayout(6,3,10,10);	
	
	/**
	 * 
	 */
	public FittsLaw(){
		
		saveMenu.addActionListener(listener);
		exitMenu.addActionListener(listener);
		restartMenu.addActionListener(listener);
	
		slider.addChangeListener(cListener);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(25);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setFont(new Font("Arial",2,12));
		slider.setPreferredSize(new Dimension(100,100));
		slider.setAutoscrolls(true);
		
		try{
		file = new File("LoggedData.txt");
		writer = new FileWriter(file);
		bufferedWriter= new BufferedWriter(writer);
		}
		catch(IOException e){
			System.out.println("there was an error with the file. try again");
			System.exit(1);
		}
	//	initWriteFile();
		
		initialize();
	}
	
	public void initialize(){
		
		
	//	System.out.println(date);
		scaleImage = chefIcon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
		plok = new ImageIcon(scaleImage);
		
		menuBar.setPreferredSize(new Dimension(900,30));
		
		frame = new JFrame("name of frame");
		frame.setLayout(new BorderLayout());
		panelL = new JPanel();
		panelC = new JPanel();
		panelR = new JPanel();
		allPanel = new Panel(new BorderLayout());

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);  
//		frame.setResizable(false);
//		frame.setSize(700, 700);
		frame.setVisible(true);
		buttonStart = new JButton("Start Game");
		
		buttonSettings = new JButton("Settings");
		buttonExit = new JButton("Exit");
		buttonRules= new JButton("Help");
		buttonStart.setSize(150, 50);
		buttonExit.setSize(150, 50);
		buttonRules.setSize(150, 50);
		buttonSettings.setSize(150, 50);

		buttonSettings.addActionListener(listener);
		buttonStart.addActionListener(listener);
		buttonExit.addActionListener(listener);
		buttonRules.addActionListener(listener);
		
		///////////////////////////////////////////////////
		
		finalPanel = new JPanel(gl);
		finalPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
//		c.anchor = GridBagConstraints.CENTER;
		finalPanel.setPreferredSize(new Dimension(300,300));
		finalPanel.setBackground(Color.GREEN);
		//gamePanel.setBackground(Color.getHSBColor(200, 54, 200));
		//finalPanel.setBackground(Color.getHSBColor(370, 100, 100));
		finalPanel.setLocation(20, 20);
		c.insets=new Insets(5,5,5,5);
		c.ipadx=50;
		c.ipady=30;
		c.gridx=1;
		c.gridy=0;
		c.anchor = GridBagConstraints.CENTER;
		finalPanel.add(buttonStart,c);
		
		c.gridy=1;
		c.ipadx=67;
		finalPanel.add(buttonSettings,c);
		c.gridy=2;
		c.ipadx=89;
		finalPanel.add(buttonRules,c);
		c.ipadx=93;
		c.gridy=3;
		finalPanel.add(buttonExit,c);
		frame.add(finalPanel);
		realMenuBar.setSize(frame.getWidth(), 100);
		realMenuBar.add(menu);
		//menu.add(saveMenu);
		menu.add(restartMenu);
		menu.add(exitMenu);
		frame.add(realMenuBar,layout.NORTH);
	//////////////////////////////////////////////////	
//		
//		panelL.add(buttonStart);
//		panelC.add(buttonRules);
//		panelR.add(buttonExit);
//		allPanel.add(panelL,layout.EAST);
//		allPanel.add(panelC,layout.CENTER);
//		allPanel.add(panelR,layout.WEST);
//		frame.add(allPanel);


	}
	public void help(){
		JDialog helpBox = new JDialog(frame,"Help");
		helpBox.setSize(500,500);
		helpBox.setLayout(new BorderLayout());
		helpBox.setVisible(true);
		
		JPanel helpPanel = new JPanel();
		JLabel helpLabel = new JLabel("Help");
		
		
		
		JPanel helpPanel1 = new JPanel();
		JTextArea helpText = new JTextArea();
		helpText.setSize(400, 400);
		helpText.setLineWrap(true);
		helpText.setText("This game simulates the Fitt's Law Experiment, where images of varying length and position will pop up"
				+ "on the screen one at a time. The user tries to click on the images as fast as possible. This game proves "
				+ "that the amount of time it takes to move quickly to a target area is a function of the distance of the "
				+ "target and the size of it.");
		helpText.setEditable(false);
		helpPanel.add(helpLabel);
		helpPanel1.add(helpText);
		helpBox.add(helpPanel,layout.NORTH);		
		helpBox.add(helpPanel1,layout.CENTER);
		helpBox.pack();
		
	}
	public void showSettings(){
		
		JDialog dialog = new JDialog(frame,"Settings");
		dialog.setLayout(new BorderLayout());
		dialog.setVisible(true);
		dialog.setSize(500,500);
		
		
		JPanel settingPanel2 = new JPanel(new FlowLayout());	 //figure out how to use jradio buttons 
		JLabel labelRadio = new JLabel("Set number of trials per game");
		
		button50 = new JRadioButton("50");
		button50.addActionListener(listener);
		button75 = new JRadioButton("75");
		button75.addActionListener(listener);
		button100 = new JRadioButton("100");
		button100.addActionListener(listener);
		button50.setSelected(true);		
		
		settingPanel2.add(labelRadio);
		settingPanel2.add(button50);
		settingPanel2.add(button75);
		settingPanel2.add(button100);		
		
		JLabel label1 = new JLabel("Max Size of Images(px)");
		JPanel settingPanel1 = new JPanel(new FlowLayout());
		settingPanel1.add(label1);
		settingPanel1.add(slider);
		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		dialog.add(settingPanel1,layout.NORTH);
		dialog.add(settingPanel2,layout.SOUTH);
		dialog.pack();

		
	}
	public void exit(){
		System.exit(0);
	}
	public void start(){
		if(count>1)
		timeCountDown();
		else{
		//	System.out.println("actual game starting");
			startGame();
		}
	}
	public void startGame(){ 
		time.stop();
		countdownPanel.remove(countdownLabel);
		countdownPanel.setBackground(Color.getHSBColor(200, 54, 200));
		frame.add(countdownPanel,layout.CENTER);
	//	System.out.println("reaches here");
		frame.repaint();  
		draw();
	}
	public void draw(){
		
//		int gameWidth=gamePanel.getWidth();
//		int gameHeight = gamePanel.getHeight();
		//sliderPicWidth-=50;
		frame.getContentPane().removeAll();
		gamePanel.removeAll();
		if(picLabel!=null)
		picLabel.removeAll();
		
	//	frame.revalidate();//////////////here
		frame.invalidate();
		frame.validate();
		frame.repaint();
		
		

//		int storeX = rand.nextInt(gameWidth);
//		int storeY = rand.nextInt(gameHeight);
//		picWidth= rand.nextInt(sliderPicWidth);         /////set ith slider
//		
//		if(trialNumber!=0){
//			distanceToTarget=(int) Math.sqrt(Math.pow((xAxis-storeX),2)+Math.pow((yAxis-storeY),2));
//		}
//		xAxis=storeX;
//		yAxis=storeY;
//		System.out.println("xaxis "+xAxis);
//		System.out.println("yaxis "+yAxis);
//		System.out.println("picWidth/2 "+picWidth);
//		
//		
//		targetSize=picWidth*picWidth;
		System.out.println(sliderPicWidth);
		if(trialNumber==1){
			picWidth= rand.nextInt(sliderPicWidth);//-50);         /////set ith slider
		}
		if(trialNumber!=1){
			picWidth= rand.nextInt(sliderPicWidth);//-50);         /////set ith slider
		}
		
		//if((picWidth+50)>sliderPicWidth)
		
		targetSize=(picWidth+50);//*(picWidth+50);
		
		scaleImage = chefIcon.getImage().getScaledInstance(targetSize, targetSize,Image.SCALE_SMOOTH);
		plok = new ImageIcon(scaleImage);		
		picLabel = new JLabel(plok);
		picLabel.addMouseListener(mListener);
		gamePanel.remove(picLabel);
		frame.remove(countdownPanel);
		frame.remove(gamePanel);
		frame.setLayout(new BorderLayout());
		menuBar.setBackground(Color.blue);
		
		
		realMenuBar.setSize(frame.getWidth(), 100);
		realMenuBar.add(menu);
	//	menu.add(saveMenu);
		menu.add(restartMenu);
		menu.add(exitMenu);

//		saveMenu.addActionListener(listener);
//		exitMenu.addActionListener(listener);
//		restartMenu.addActionListener(listener);
		
		gamePanel.setBackground(Color.getHSBColor(200, 54, 200));
		gamePanel.setSize(frame.getWidth(), frame.getHeight()-realMenuBar.getHeight());
		gamePanel.setLayout(null);
		gamePanel.add(picLabel);
		
//		if(trialNumber==1){
//			xAxis=0;
//			yAxis=0;
//		}
		
		int gameWidth=gamePanel.getWidth();
		int gameHeight = gamePanel.getHeight();
		int storeX = rand.nextInt(gameWidth-picWidth-100-50);
		int storeY = rand.nextInt(gameHeight-picWidth-100-50);			//////////////////////////////////////////////didnt have -100 before, was blank
		
		if(trialNumber!=1){
			distanceToTarget=(int) Math.sqrt((Math.pow((xAxis-storeX),2)+Math.pow((yAxis-storeY),2)));
		}
		xAxis=storeX;
		yAxis=storeY;
		picLabel.setBounds(xAxis, yAxis, picWidth+50, picWidth+50);//////////YESSS--change first two param for x,y
		gamePanel.addMouseListener(mListener);
;
		frame.add(realMenuBar,layout.NORTH);
		frame.add(gamePanel,layout.CENTER);
		
		
//		if(trialNumber!=1){
//			picWidth= rand.nextInt(sliderPicWidth);         /////set ith slider
//		}
//		
//		
//		
//		targetSize=(picWidth+50)*(picWidth+50);
		
	//	frame.revalidate();///////////////////////////here
		frame.invalidate();
		frame.validate();
		frame.repaint();		
//		System.out.println("panel width "+menuBar.getHeight());
//		System.out.println();
//		if((targetSize-20)>sliderPicWidth){
//			System.out.println("------------------");
//			draw();
//		}
//		if(((xAxis+picWidth)>gamePanel.getWidth())/*||(xAxis-(picWidth)<0)*/||((yAxis+picWidth+30)>gamePanel.getHeight())){//||(yAxis-(picWidth)<0)){
//			System.out.println("redrawing");
//			draw();
//		}
		date = new Date().getTime();
//		System.out.println("width"+gamePanel.getWidth());
//		System.out.println("height"+gamePanel.getHeight());
//
//		System.out.println("time to click///////////// "+timeToClick);
		
	}
	public void clearFrame(){
		frame.removeAll();
		frame.repaint();
	}
	
	
	public void timeCountDown(){
		time.addActionListener(listener);
	}
	public void countdown(int count){
		
		countdownPanel.add(countdownLabel,layout.CENTER);
		countdownPanel.setBackground(Color.getHSBColor(200, 54, 200));
		frame.add(countdownPanel,layout.CENTER);
		frame.invalidate();
		frame.validate();
		frame.repaint();
		countdownLabel.setText("STARTING GAME IN "+count+"");
		countdownLabel.setFont(new Font("Arial",1,40));					
		System.out.println(count+"");			
	}
	public JFrame getFrame(){
		return frame;
	}
//	public void initWriteFile()
//	{
//		file = new File(file.getPath());
//		try {
//			PrintWriter out = new PrintWriter(new FileWriter(file));
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(null,"Error with the file");
//		}
//
//	}
	public void makeString(){
		
		if(trialNumber==1){
			distanceToTarget=yAxis;
		}
		totalString="";
		totalString=(trialNumber+","+targetSize+","+distanceToTarget+","+timeToClick);
		System.out.println("picWidth"+picWidth);
	}
	public void askToSave(boolean isAtEnd){
		
//		try{
//		writer.close();
//		bufferedWriter.close();
//		}catch(Exception e){}
//		JFrame saveFrame = new JFrame();
//		saveFrame.setVisible(true);
//		saveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileChooser.setDialogTitle("Save As");
	
		
		int userSelection = fileChooser.showSaveDialog(frame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try{
				writer.close();
				bufferedWriter.close();
				}catch(Exception e){}
		   // file = fileChooser.getSelectedFile();
			boolean hasDot=false;
		    String filenameTyped = fileChooser.getSelectedFile().getName();
		   
		    for(int x=0; x<filenameTyped.length();x++){
		    	if(filenameTyped.charAt(x)=='.'){
		    		hasDot=true;
		    		if(filenameTyped.charAt(filenameTyped.length()-1)=='.'){//dot is last
		    			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		    			filenameTyped=filenameTyped.substring(0,filenameTyped.length()-1);
		    			hasDot=false;
		    		}
		    		if(filenameTyped.indexOf('.')+1!='t'&&filenameTyped.indexOf('.')+1!='T'&&filenameTyped.indexOf('.')+1!='c'&&filenameTyped.indexOf('.')+1!='C'){
		    			filenameTyped=filenameTyped.substring(0,filenameTyped.indexOf('.'));//if extemsion not txt or csv, make it csv
		    			hasDot=false;
		    		}
		    	}
		    }
		   
		    if(hasDot==false){
		    	filenameTyped=filenameTyped.concat(".csv");
		    }
		    
		    System.out.println("what they typed "+filenameTyped);
		    //filenameTyped.replaceAll("\\\\", "/");
		    String directoryName = fileChooser.getCurrentDirectory().getPath();
		    directoryName=directoryName.replace("\\", "/");
		    File fileToSave = new File(directoryName,filenameTyped);
		    //write to the file
		    InputStream inputStream = null;
		    OutputStream outputStream = null;
		    try{
		    	outputStream = new FileOutputStream(fileToSave);
		        inputStream = new FileInputStream(file);
		        byte[] byteArray = new byte[1024];
		        int bytesDone;
		        while ((bytesDone = inputStream.read(byteArray)) > 0) {
		            outputStream.write(byteArray, 0, bytesDone);
		        }   
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    try{
		    inputStream.close();
		    outputStream.close();
		    }catch(Exception e){e.printStackTrace();}
		    
			try {
				fileToSave.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(filenameTyped);
			System.out.println("directory: "+directoryName);
		    //System.out.println("Save as file: " + file.getAbsolutePath());
		}
		else if (userSelection == JFileChooser.CANCEL_OPTION) {
			try {
				writer=new FileWriter(file);
				bufferedWriter = new BufferedWriter(writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//saveFrame.dispose();
		} 
		
	}
	
	
	public void addDoneStatements(){
		
		JLabel message = new JLabel();
		message.setText("Good Trial!");
		message.setFont(new Font("Arial",1,25));
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
		saveButton=new JButton("Save Logged Play");
		saveButton.setSize(150, 50);
		exitButton= new JButton("Exit");
		exitButton.setSize(150, 50);
		playAgainButton= new JButton("Play Again");
		playAgainButton.setSize(150, 50);
		
		saveButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		playAgainButton.addActionListener(listener);
		
		gamePanel.removeAll();
		gamePanel.setLayout(new GridBagLayout());
//		gamePanel.add(message);
		GridBagConstraints c= new GridBagConstraints();
		gamePanel.setBackground(Color.CYAN);
		
		c.anchor = GridBagConstraints.PAGE_START;
		
		c.insets=new Insets(0,15,15,15);
		c.gridx=1;
		c.gridy=0;
		c.ipadx=21;
		c.ipady=30;
		gamePanel.add(message,c);
		
		c.anchor = GridBagConstraints.CENTER;

		c.insets=new Insets(5,5,5,5);
		c.ipadx=21;
		c.ipady=30;
		
		c.gridx=1;
		c.gridy=2;	
		gamePanel.add(saveButton,c);
		
		c.ipadx=62;
		c.gridy=1;
		gamePanel.add(playAgainButton,c);
		
//		c.ipadx=75;
//		c.gridy=3;
//		gamePanel.add(buttonSettings,c);
		
		c.ipadx=101;
		c.gridy=3;
		gamePanel.add(exitButton,c);
		
//		c.gridy=4;
//		gamePanel.add(message,c);
		
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		gamePanel.repaint();

		realMenuBar.setSize(frame.getWidth(), 100);
		realMenuBar.add(menu);
		menu.add(saveMenu);
		menu.add(restartMenu);
		menu.add(exitMenu);
		frame.add(realMenuBar,layout.NORTH);
//		frame.revalidate();//////////////////here
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	public void clearVariables(){
		timesClicked=1;
		trialNumber=1;
		
	}
	

	public class CListener implements ChangeListener{

		public void stateChanged(ChangeEvent e) {
			if(e.getSource()==slider){
				sliderPicWidth=slider.getValue()-50;
				System.out.println(slider.getValue());
			}
		}
		
	}
	public class Mlistener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
//			
//			if(e.getSource()==picLabel){
//				if(timesClicked<timesToRun){
//					dateUpdate = new Date().getTime();
//					System.out.println("/////////"+dateUpdate);
//					timeToClick=dateUpdate-date;
//					System.out.println(".............."+timeToClick);
//					timesClicked++;
//					trialNumber = timesClicked;
//					System.out.println("trial number " +timesClicked);
//					System.out.println("target size "+targetSize);
//					System.out.println("distance to target "+distanceToTarget);
//					System.out.println("asdfasdfasdf"+gamePanel.getWidth());
//					System.out.println("asdfasdfasdf"+gamePanel.getHeight());
//					makeString();
//					try {
//						bufferedWriter.write(totalString);
//						bufferedWriter.write(emptyString);
//					} catch (IOException e1) {
//						System.out.println("you have failed your city!");
//						System.exit(1);
//					}
//					draw();
//				}
//				else{
//					try {
//						bufferedWriter.close();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					//do something insted of closing the program
//					askToSave();
//					askToPlayAgain();
//					System.exit(0);
//				}
//				
//				
//				
//			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if(e.getSource()==picLabel){
				if(trialNumber<5+1){//timesToRun){
					dateUpdate = new Date().getTime();
				//	System.out.println("/////////"+dateUpdate);
					timeToClick=dateUpdate-date;
					//System.out.println(".............."+timeToClick);
					
//					System.out.println("trial number " +trialNumber);
					System.out.println("target size "+targetSize);
					System.out.println("distance to target "+distanceToTarget);
//					System.out.println("gameWidth"+gamePanel.getWidth());
//					System.out.println("gameHeight"+gamePanel.getHeight());
					
					System.out.println("/////////"+xAxis);
					System.out.println("/////////"+yAxis);

					
					makeString();
					try {
						writer.write(totalString);//used to be bufferedWriter
						writer.write(emptyString);
					} catch (IOException e1) {
						System.out.println("you have failed your city!");
						System.exit(1);
					}
					trialNumber++;
					if(trialNumber!=5+1)
					draw();
					else{
						isAtEnd=false;
						try {
							writer.close();
							bufferedWriter.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//do something insted of closing the program
						addDoneStatements();
					}
				}
				else{
//					dateUpdate = new Date().getTime();
//					//	System.out.println("/////////"+dateUpdate);
//						timeToClick=dateUpdate-date;
//						//System.out.println(".............."+timeToClick);
//						
//						System.out.println("trial number " +trialNumber);
//						System.out.println("target size "+targetSize);
//						System.out.println("distance to target "+distanceToTarget);
//						System.out.println("gameWidth"+gamePanel.getWidth());
//						System.out.println("gameHeight"+gamePanel.getHeight());
//						
//						makeString();
//						try {
//							writer.write(totalString);//used to be bufferedWriter
//							writer.write(emptyString);
//						} catch (IOException e1) {
//							System.out.println("you have failed your city!");
//							System.exit(1);
//						}
//					System.out.println("it reached its end");
					isAtEnd=false;
					try {
						writer.close();
						bufferedWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//do something insted of closing the program
					addDoneStatements();
					
				}
				
				
				
			}			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	public class TheListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){	
			
			if(event.getSource()==buttonStart){
				System.out.println("Start countdown");
				frame.getContentPane().removeAll();	
				frame.repaint();
			//clearVariables();
				time.start();
				
			}
			else if(event.getSource()==playAgainButton||event.getSource()==restartMenu){
//				frame.getContentPane().removeAll();
//				frame.repaint();
//				time.start();
				frame.dispose();
				new FittsLaw();
			}
			else if(event.getSource()==saveButton){
				try {
					writer.close();
					bufferedWriter.close();		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				askToSave(isAtEnd);
			}
			else if(event.getSource()==buttonExit||event.getSource()==exitButton){
				System.out.println("exiting");
				exit();
			}
			else if(event.getSource()==buttonRules){
				System.out.println("show info");
				help();
			}
			else if(event.getSource()==buttonSettings){
				System.out.println("going into settings");
				showSettings();
				
			}
			else if(event.getSource()==time){//after start is clicked-- happens every x seconds
				System.out.println("the timer is going!!");
				if(count>0){

						
						countdownPanel.add(countdownLabel,layout.CENTER);
						frame.add(countdownPanel);

						countdownLabel.setFont(new Font("Arial",1,36));					
						countdown(count);
				}
				else{
					start();
				}
				count--;
			}
			
//			else if(event.getSource()==restartMenu){
//				System.out.println("restarting");
//				new FittsLaw();
//			}
			else if(event.getSource()==exitMenu){
				System.out.println("also exiting");
				exit();
			}
//			else if(event.getSource()==settingsMenu){
//				showSettings();
//			}
			else if(event.getSource()==saveMenu){
				askToSave(isAtEnd);
			}
			else if(event.getSource()==button50){
				System.out.println("button 50 was pressed");
				button75.setSelected(false);
				button100.setSelected(false);
				timesToRun=50;
			}
			else if(event.getSource()==button75){
				System.out.println("button 75 was pressed");
				button50.setSelected(false);
				button100.setSelected(false);
				timesToRun=75;
			}
			else if(event.getSource()==button100){
				System.out.println("button 100 was pressed");
				button50.setSelected(false);
				button75.setSelected(false);
				timesToRun=100;
			}
			
		}

	}
	
}
