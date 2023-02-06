import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Winlogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfid;
	private JTextField tfpw;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Winlogin dialog = new Winlogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Winlogin() {
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblID = new JLabel("아이디 :");
		lblID.setBounds(53, 45, 66, 24);
		contentPanel.add(lblID);
		
		tfid = new JTextField();
		tfid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {			
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					tfpw.requestFocus();
			}
		});
		tfid.setBounds(126, 43, 154, 29);
		contentPanel.add(tfid);
		tfid.setColumns(10);
		
		tfpw = new JTextField();
		tfpw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) 
					btnLogin.requestFocus();
			}
		});
		tfpw.setColumns(10);
		tfpw.setBounds(126, 103, 154, 29);
		contentPanel.add(tfpw);
		
		JLabel lblPW = new JLabel("비밀번호 :");
		lblPW.setBounds(53, 105, 79, 24);
		contentPanel.add(lblPW);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnLogin = new JButton("LOGIN");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = 
									DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/myprojectDB",
											"root",
											"12345");
							Statement stmt = con.createStatement();
							String sql = "SELECT * FROM logintbl ";
							sql = sql + "where id='" + tfid.getText();
							sql = sql + "' and pw='" + tfpw.getText() + "'";	
							ResultSet rs = stmt.executeQuery(sql);

							
							if(rs.next()) {	
									if(tfid.getText().trim().equals("admin")) {
									setVisible(false);
									String pw,id;
									id = rs.getString("id");
									pw = rs.getString("pw");
									WinMainAdmin winMainAdmin  = new WinMainAdmin (id,pw);
									winMainAdmin .setModal(true);
									winMainAdmin .setVisible(true);	
									System.exit(DISPOSE_ON_CLOSE);																
								}
																								
								
									else if(!tfid.getText().trim().equals("") && !tfpw.getText().trim().equals("")) {
										setVisible(false);
										String pw, id;
										id = rs.getString("id");
										pw = rs.getString("pw");
										
										WinMainMember winMainMember = new WinMainMember(id,pw);
										winMainMember.setModal(true);
										winMainMember.setVisible(true);	
										System.exit(DISPOSE_ON_CLOSE);

								}					

					
							}

								else {
									JOptionPane.showMessageDialog(null, "ID/PW 확인 요망");
									tfid.requestFocus();
							}	
							
							
							
					
							
							
							
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				});
				
				
				
				
				JButton btnJoin = new JButton("회원가입");
				btnJoin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinMemberInsert winMemberInsert = new WinMemberInsert();
						winMemberInsert.setModal(true);
						winMemberInsert.setVisible(true);
					}
				});
				buttonPane.add(btnJoin);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);
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
