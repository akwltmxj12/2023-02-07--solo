import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WinQuestionChoice extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tftitle;
	private JTextField tfid;
	JTextArea tfcontent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinQuestionChoice dialog = new WinQuestionChoice("","","");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinQuestionChoice(String sTitle,String sID, String sContents) {
		setTitle("문의사항");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("제목 :");
			lblNewLabel.setBounds(12, 34, 83, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("내용 :");
			lblNewLabel_1.setBounds(12, 107, 57, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			tftitle = new JTextField();
			tftitle.setBounds(81, 34, 265, 25);
			contentPanel.add(tftitle);
			tftitle.setColumns(10);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 107, 332, 144);
		contentPanel.add(scrollPane);
		
		tfcontent = new JTextArea();
		scrollPane.setViewportView(tfcontent);
		
		JLabel lblname = new JLabel("작성자:");
		lblname.setBounds(12, 69, 57, 15);
		contentPanel.add(lblname);
		
		tfid = new JTextField();
		tfid.setBounds(81, 69, 134, 21);
		contentPanel.add(tfid);
		tfid.setColumns(10);
		
		tftitle.setText(sTitle);
		tfid.setText(sID);
		tfcontent.setText(sContents);
	}


	

	
}
