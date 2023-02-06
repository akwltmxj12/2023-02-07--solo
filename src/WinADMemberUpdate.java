import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinADMemberUpdate extends JDialog {

	private final JPanel none = new JPanel();
	private JTextField tfid;
	private JTextField tfpw;
	private JTextField tfname;
	private JTextField tfemail;
	private JTextField tftel1;
	private JTextField tfaddress;
	private JTextField tftel2;
	private JTextField tftel3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinADMemberUpdate dialog = new WinADMemberUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinADMemberUpdate() {
		setTitle("회원정보수정");
		setBounds(100, 100, 450, 365);
		getContentPane().setLayout(new BorderLayout());
		none.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(none, BorderLayout.CENTER);
		none.setLayout(null);
		{
			JLabel lblId = new JLabel("아이디 :");
			lblId.setBounds(60, 39, 78, 24);
			none.add(lblId);
		}
		{
			tfid = new JTextField();
			tfid.setColumns(10);
			tfid.setBounds(132, 38, 198, 27);
			none.add(tfid);
		}
		{
			JLabel lblPw = new JLabel("비밀번호 :");
			lblPw.setBounds(60, 76, 78, 24);
			none.add(lblPw);
		}
		{
			tfpw = new JTextField();
			tfpw.setColumns(10);
			tfpw.setBounds(132, 75, 198, 27);
			none.add(tfpw);
		}
		{
			JLabel lblName = new JLabel("이름 :");
			lblName.setBounds(60, 112, 78, 24);
			none.add(lblName);
		}
		{
			tfname = new JTextField();
			tfname.setColumns(10);
			tfname.setBounds(132, 110, 198, 27);
			none.add(tfname);
		}
		{
			JLabel lblEmail = new JLabel("이메일 :");
			lblEmail.setBounds(60, 146, 78, 24);
			none.add(lblEmail);
		}
		{
			tfemail = new JTextField();
			tfemail.setColumns(10);
			tfemail.setBounds(132, 145, 198, 27);
			none.add(tfemail);
		}
		{
			JLabel lblTel = new JLabel("전화번호 :");
			lblTel.setBounds(60, 180, 78, 24);
			none.add(lblTel);
		}
		{
			tftel1 = new JTextField();
			tftel1.setColumns(10);
			tftel1.setBounds(132, 180, 57, 27);
			none.add(tftel1);
		}
		{
			JLabel lblAddress = new JLabel("주소 :");
			lblAddress.setBounds(60, 218, 78, 24);
			none.add(lblAddress);
		}
		{
			tfaddress = new JTextField();
			tfaddress.setColumns(10);
			tfaddress.setBounds(132, 217, 198, 27);
			none.add(tfaddress);
		}
		{
			tftel2 = new JTextField();
			tftel2.setColumns(10);
			tftel2.setBounds(214, 182, 57, 27);
			none.add(tftel2);
		}
		{
			tftel3 = new JTextField();
			tftel3.setColumns(10);
			tftel3.setBounds(298, 182, 57, 27);
			none.add(tftel3);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setBounds(195, 185, 17, 15);
			none.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setBounds(283, 185, 17, 15);
			none.add(lblNewLabel_1);
		}
		
		JButton btnNewButton = new JButton("조회");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = 
							DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/myprojectDB",
									"root",
									"12345");
					Statement stmt = con.createStatement();			
					String sql = "select * from logintbl where id='";
					sql = sql + tfid.getText() + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						tfid.setText(rs.getString("id"));
						tfpw.setText(rs.getString("pw"));
						tfname.setText(rs.getString("name"));
						tfemail.setText(rs.getString("email"));
						tfaddress.setText(rs.getString("address"));
					}
				}	
				 catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
					}
				
			}
		});
		btnNewButton.setBounds(342, 40, 66, 23);
		none.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("변경");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = 
									DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/myprojectDB",
											"root",
											"12345");
							Statement stmt = con.createStatement();			
							String sql = "update logintbl set id=' ";							
							sql = sql +  tfid.getText() + "',";
							sql = sql + "pw='" + tfpw.getText() + "',";
							sql = sql + "name='" + tfname.getText() + "',";
							sql = sql + "email='" + tfemail.getText() + "',";						
							sql = sql + "tel='" + tftel1.getText() + tftel2.getText()+ tftel3.getText()+ "',";								
							sql = sql + "address='" + tfaddress.getText() + "'";
							sql = sql + "WHERE id='" + tfid.getText() + "'";
							
							if(stmt.executeUpdate(sql) > 0) {
								JOptionPane.showMessageDialog(null, "회원 변경 완료!!");
							}
							else
								JOptionPane.showMessageDialog(null, "회원 변경 오류!!");
							
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
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
