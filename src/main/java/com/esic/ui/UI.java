package com.esic.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.esic.ObjectStore;
import com.esic.processor.ESICProcessor.ProcessName;
import javax.swing.JLabel;

public class UI {

	final static Logger logger = Logger.getLogger(UI.class);

	public JFrame frame;
	private JFileChooser chooser;
	private JButton btnSelectFile;
	private JTextField textField;
	private JTextField fileDownloadLocation;
	private JLabel lblDownloadLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					UI window = ObjectStore.getUI();

					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error("Error in UI", e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public UI() {
		initialize();
		registerEvents();
	}

	
	/**
	 * register window managment events.
	 */
	private void registerEvents() {

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit?",
						"Online Examination System",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(500, 500);
		btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSelectFileClick();
			}
		});
		btnSelectFile.setBounds(365, 11, 89, 23);
		frame.getContentPane().add(btnSelectFile);

		 textField = new JTextField();
		textField.setBounds(23, 11, 332, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnProcessFile = new JButton("Process File");
		btnProcessFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleProcessFile();
			}
		});
		btnProcessFile.setBounds(170, 63, 89, 23);
		frame.getContentPane().add(btnProcessFile);
		
		JButton btnNewButton = new JButton("Download Insert IP Files.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				handleDownloadFilesBatch();
			}
		});
		btnNewButton.setBounds(269, 181, 185, 23);
		frame.getContentPane().add(btnNewButton);
		
		fileDownloadLocation = new JTextField();
		fileDownloadLocation.setColumns(10);
		fileDownloadLocation.setBounds(122, 150, 332, 23);
		frame.getContentPane().add(fileDownloadLocation);
		
		lblDownloadLocation = new JLabel("Download Location");
		lblDownloadLocation.setBounds(23, 154, 95, 14);
		frame.getContentPane().add(lblDownloadLocation);

		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Excel Files", "xlsx");
		chooser.setFileFilter(filter);
 
	}

	protected void handleProcessFile() {
		ObjectStore.getProcessor().processFile(textField.getText(), ProcessName.ESIC_FILE_PROCESS);
	}

	protected void handleDownloadFilesBatch() {
		
		
		if (fileDownloadLocation.getText() == null
				|| fileDownloadLocation.getText().isEmpty()) {
			JOptionPane.showMessageDialog(frame,
					"Please enter valid file download location");

		}

		ObjectStore.getProcessor().processFile(textField.getText(),ProcessName.ESIC_IP_DETAILS_DOWNLOAD);
	}

	
	
	

	
	
	protected void handleSelectFileClick() {

		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			String filename = chooser.getSelectedFile().getAbsolutePath();
			logger.info("You chose to open this file: " + filename);
			textField.setText(filename);
			
		}

	}
	
	
	public String getFileDownloadLocation() {
		return fileDownloadLocation.getText();
	}
}
