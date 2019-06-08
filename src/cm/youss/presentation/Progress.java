package cm.youss.presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Progress extends JFrame {
	
	final static int interval=200;
	int i;
	Timer temps;
	JButton btn;
	JProgressBar prg;

	public Progress() {
		getContentPane().setLayout(new FlowLayout());
		btn = new JButton("Virement");
		btn.setPreferredSize(new Dimension(90, 30));
		btn.setBorder(null);
		//btn.setBackground(new Color(37,7,180));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				i=0;
				temps.start();
				btn.setEnabled(false);
			}
		});
		
		prg = new JProgressBar(0,20);
		prg.setValue(0);
		prg.setPreferredSize(new Dimension(400, 30));
		//prg.setBorder(null);
		prg.setForeground(new Color(204,22,2));
		prg.setStringPainted(true);
		
		getContentPane().add(prg);
		getContentPane().add(btn);
		
		temps= new Timer(interval, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				if(i==20) {
					temps.stop();
					btn.setEnabled(true);
					
				}else {
					i++;
					prg.setValue(i);
				}
				
			}
		});
		
		setSize(590,90);
		setResizable(false);
		setLocationRelativeTo(null);
		//setBackground(new Color(255,255,255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		
		//UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Progress vpb=new Progress();
	}

}
