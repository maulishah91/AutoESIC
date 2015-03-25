package com.esic.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.esic.ObjectStore;
import com.esic.Dao.ESICExcelDAO;
import com.esic.Dao.XLSXFileReader;
import com.esic.domain.ESICRecord;

public class UI {

	final static Logger logger = Logger.getLogger(UI.class);

	private JFrame frame;
	private JFileChooser chooser;
	private JButton btnSelectFile;
	private JTextField textField;

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

		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Excel Files", "xlsx");
		chooser.setFileFilter(filter);

	}

	protected void handleProcessFile() {
		ObjectStore.getProcessor().processFile(textField.getText());
	}

	

	protected void handleSelectFileClick() {

		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			String filename = chooser.getSelectedFile().getAbsolutePath();
			logger.info("You chose to open this file: " + filename);
			textField.setText(filename);
			
		}

	}
}
