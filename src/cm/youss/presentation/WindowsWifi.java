package cm.youss.presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.mint.MintLookAndFeel;

import cm.youss.domaine.WifiModel;

public class WindowsWifi extends JFrame {

	private JPanel contentPane;
	private JTextField profilfield;
	private WifiModel wifiModel;
	private JTable jTableWifi;
	final static int interval=200;
	Timer temps;
	Timer temps1;
	int i;
	int j;
	private JProgressBar progressBar;
	private Wifi wifi;
	//private List<String> listProfils;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new MintLookAndFeel());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowsWifi frame = new WindowsWifi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WindowsWifi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(435, 525);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("MyWifiKey");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new Wifi().genererUsersFile();
		contentPane.add(tableWifi());
		
		JButton btnScan = new JButton("Cl\u00E9");
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//instructions
				wifi = new Wifi();
				if(profilfield.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez saisir le champ Profil svp");
				}else {
				wifi.genererKeyFile(profilfield.getText());
				i=0;
				temps.start();
				//btn.setEnabled(false);
				}
				
			}
		});
		
		
		btnScan.setBounds(308, 358, 101, 34);
		contentPane.add(btnScan);
	
		profilfield = new JTextField();
		profilfield.setBounds(57, 358, 241, 34);
		contentPane.add(profilfield);
		profilfield.setColumns(10);
		
		JButton btnViewAllUsers = new JButton("Afficher tous");
		btnViewAllUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//instructions
				wifi = new Wifi();
				try {
				wifi.readUsersProfils();
				}finally {
					j=0;
					temps1.start();
				}
			}
		});
		btnViewAllUsers.setBounds(157, 245, 108, 36);
		contentPane.add(btnViewAllUsers);
		
		JLabel lblUser = new JLabel("Profil :");
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUser.setBounds(10, 357, 48, 34);
		contentPane.add(lblUser);
		
		progressBar = new JProgressBar(0,20);
		progressBar.setBounds(78, 304, 270, 23);
		progressBar.setValue(0);
		progressBar.setForeground(new Color(204,22,2));
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(352, 314, 73, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 314, 73, 2);
		contentPane.add(separator_1);
		
		JLabel lblAPropos = new JLabel("A Propos");
		lblAPropos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAPropos.setBounds(185, 456, 80, 29);
		contentPane.add(lblAPropos);
		
		temps= new Timer(interval, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				if(i==20) {
					temps.stop();
					Wifi wifi1 = new Wifi();
					String key = wifi1.readKey();
					 JOptionPane.showMessageDialog(null, "Mot de passe "+profilfield.getText()+" : "+key);
					 profilfield.setText("");
					 Path wifiFile = FileSystems.getDefault().getPath("wifi.cmd");
					 Path keyFile = FileSystems.getDefault().getPath("key.txt");
					 Path filtreFile = FileSystems.getDefault().getPath("filtre.txt");
					 try {
						Files.delete(wifiFile);
						Files.delete(keyFile);
						Files.delete(filtreFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//btn.setEnabled(true);
					
				}else {
					i++;
					progressBar.setValue(i);
				}
				
			}
		});
		
		
		temps1= new Timer(interval, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			 
				if(j==20) {
					
					temps1.stop();
					
					wifi.lireAllLines();
					wifiModel.setData(wifi.getLignes());
					
				}else {
					j++;
					progressBar.setValue(j);
				}
				
			}
			
		});
		
	}
	
	
	//methode permettant d'afficher la liste de tous les profils utulisateurs sur la fenetre principale
		private JPanel tableWifi() {
			//IEleveService iEleveService=new EleveService();
			JPanel tablePanel=new JPanel();
		    wifiModel=new WifiModel();
			jTableWifi=new JTable(wifiModel);
			jTableWifi.setBounds(0, 214, 421, -216);
			JScrollPane jScrollPane= new JScrollPane(jTableWifi);
			tablePanel.setBackground(Color.WHITE);
			tablePanel.setBounds(0, 0, 425, 217);
			tablePanel.setLayout(new GridLayout());
			tablePanel.add(jScrollPane);
			//List<String> listEleve=iEleveService.listElevesService();
			//wifiModel.setData(listEleve);
			return tablePanel;
		}
}
