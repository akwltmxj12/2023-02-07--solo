import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class WinMemberInsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfPW;
	private JTextField tfTel2;
	private JTextField tfAdderss;
	private JTextField tfID;
	private JTextField tfTel1;
	private JTextField tfTel3;
	private JTextField tfEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMemberInsert dialog = new WinMemberInsert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinMemberInsert() {
		setTitle("\uACE0\uAC1D \uCD94\uAC00");
		setBounds(100, 100, 395, 342);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("이름 :");
			lblName.setBounds(22, 120, 57, 15);
			contentPanel.add(lblName);
		}
		{
			tfName = new JTextField();
			tfName.setBounds(85, 117, 156, 21);
			contentPanel.add(tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lblPW = new JLabel("비밀번호 :");
			lblPW.setBounds(22, 80, 57, 15);
			contentPanel.add(lblPW);
		}
		{
			tfPW = new JTextField();
			tfPW.setBounds(85, 77, 156, 21);
			contentPanel.add(tfPW);
			tfPW.setColumns(10);
		}
		{
			JLabel lblClientFirst = new JLabel("");
			lblClientFirst.setBounds(12, 55, 71, 15);
			contentPanel.add(lblClientFirst);
		}
		{
			tfTel2 = new JTextField();
			tfTel2.setBounds(151, 182, 71, 21);
			contentPanel.add(tfTel2);
			tfTel2.setColumns(10);
		}
		
		JLabel lblTel = new JLabel("전화번호 :");
		lblTel.setBounds(22, 185, 84, 15);
		contentPanel.add(lblTel);
		
		tfAdderss = new JTextField();
		tfAdderss.setColumns(10);
		tfAdderss.setBounds(87, 210, 268, 21);
		contentPanel.add(tfAdderss);
		
		JLabel lblAdderss = new JLabel("주소 :");
		lblAdderss.setBounds(24, 213, 57, 15);
		contentPanel.add(lblAdderss);
		
		JLabel lblID = new JLabel("아이디 :");
		lblID.setBounds(22, 39, 57, 15);
		contentPanel.add(lblID);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(87, 36, 156, 21);
		contentPanel.add(tfID);
		
		tfTel1 = new JTextField();
		tfTel1.setColumns(10);
		tfTel1.setBounds(85, 182, 47, 21);
		contentPanel.add(tfTel1);
		
		tfTel3 = new JTextField();
		tfTel3.setColumns(10);
		tfTel3.setBounds(243, 182, 71, 21);
		contentPanel.add(tfTel3);
		
		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(134, 182, 19, 21);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(228, 182, 19, 21);
		contentPanel.add(lblNewLabel_1);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(85, 151, 156, 21);
		contentPanel.add(tfEmail);
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setBounds(22, 154, 57, 15);
		contentPanel.add(lblEmail);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnInsert = new JButton("회원가입");
				btnInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = 
									DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/myprojectDB",
											"root",
											"12345");
							Statement stmt = con.createStatement();			
							
							String sql = "insert into logintbl values('";
							sql = sql + tfID.getText() + "','";
							sql = sql + tfPW.getText() +  "','";
							sql = sql + tfName.getText() + "','";
							sql = sql + tfEmail.getText() +  "','";
							sql = sql + tfTel1.getText() + tfTel2.getText() + tfTel3.getText() + "','";
							sql = sql + tfAdderss.getText() +  "')";
							
							
							if(stmt.executeUpdate(sql) > 0)
								JOptionPane.showMessageDialog(null, "회원 가입 완료!!!");
							else
								JOptionPane.showMessageDialog(null, "회원 가입 오류!!!");
							
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}

					}
				});
				btnInsert.setActionCommand("OK");
				buttonPane.add(btnInsert);
				getRootPane().setDefaultButton(btnInsert);
			}
			{
				JButton cancelButton = new JButton("취소");
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
