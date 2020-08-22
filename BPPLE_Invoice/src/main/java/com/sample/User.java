package com.sample;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class User extends Thread{

	public JFrame frame;
	JButton btnNewButton;
	private Thread t;
	private String threadName;
	Object lock;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public User(String name) {
		threadName = name;
	      System.out.println("Creating " +  threadName );
		//initialize();
		//run();
		//window.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		btnNewButton = new JButton("Resume Execution");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized(lock){					
						lock.notify();
				}
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
		
	}
	
	@Override
	public void run(){
		initialize();
		frame.setVisible(true);
		/*synchronized(this){					
			if(btnNewButton.getModel().isPressed()){
				this.notify();
			}
		}*/
	}
	
	@Override
	public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}
