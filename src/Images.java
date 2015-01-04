import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Images {

	JFrame mainFrame;
	JPanel p = new JPanel();
	public Images(JFrame frame){
		mainFrame=new JFrame();//takes in previous frame and uses it
		frame = mainFrame;
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH); 
		mainFrame.setVisible(true);

	}
	public void makeChanges(){
		mainFrame.removeAll();
		p.setBackground(Color.green);
		mainFrame.add(p);
		mainFrame.repaint();
	}
	
	
	
	
}
