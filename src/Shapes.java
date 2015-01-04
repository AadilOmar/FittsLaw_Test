import java.awt.BorderLayout;

import javax.swing.BoxLayout;

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

public class Shapes{

	TheListener listener = new TheListener();
	
	ImageIcon chefIcon = new ImageIcon("chef.gif");
	
	JFrame frame =new JFrame("Example frame");
	JPanel panel = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new BorderLayout());
	JLabel label = new JLabel("This is a simple label");
	JLabel label1 = new JLabel(chefIcon);
	
	
		

	
	public Shapes(){
		panel.setSize(100,400);
		panel.setBackground(Color.BLUE);
		panel2.setSize(100,400);
		panel2.setBackground(Color.red);
		label1.addMouseListener(listener);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
	//	panel.add(label);
		label1.setLocation(100, 200);
		panel.add(label1);
		
		frame.add(panel);
		frame.remove(panel);
		panel.remove(label1);
		panel.add(label);
		panel.add(label1);
		frame.add(panel);
		frame.add(panel2);
		

		
		
	}
	public class TheListener implements MouseListener{
		
		

		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==label1){
				System.out.println("The image was clicked!!");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==label1){
				System.out.println("The image was hovered over!!");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}	
	
}
