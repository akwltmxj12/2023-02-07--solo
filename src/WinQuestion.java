import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinQuestion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinQuestion dialog = new WinQuestion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinQuestion() {
		setTitle("문의하기");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbltItle = new JLabel("제목 :");
		lbltItle.setBounds(12, 35, 72, 18);
		contentPanel.add(lbltItle);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(69, 34, 353, 21);
		contentPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lblContent = new JLabel("내용 :");
		lblContent.setBounds(30, 96, 57, 15);
		contentPanel.add(lblContent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 96, 354, 111);
		contentPanel.add(scrollPane);
		
		JTextArea taContent = new JTextArea();
		scrollPane.setViewportView(taContent);
		
		JLabel lblNewLabel = new JLabel("작성자 :");
		lblNewLabel.setBounds(12, 63, 57, 15);
		contentPanel.add(lblNewLabel);
		
		tfid = new JTextField();
		tfid.setBounds(69, 63, 189, 21);
		contentPanel.add(tfid);
		tfid.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = 
									DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/myprojectDB",
											"root",
											"12345");
							Statement stmt = con.createStatement();			
							String sql = "INSERT INTO questiontbl values('";
							sql = sql + tfTitle.getText() + "','";
							sql = sql + tfid.getText() + "','";
							sql = sql + taContent.getText() + "')";
						
							if(stmt.executeUpdate(sql) > 0) {
								JOptionPane.showMessageDialog(null, "전송되었습니다.");			
							}
							else
								JOptionPane.showMessageDialog(null, "전송실패");
						
						
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
