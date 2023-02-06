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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinADMemberDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinADMemberDelete dialog = new WinADMemberDelete();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinADMemberDelete() {
		setTitle("회원 삭제");
		setBounds(100, 100, 419, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(55, 145, 91, 31);
		contentPanel.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(110, 145, 202, 31);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("회원 삭제는 돌이킬수없습니다.");
		lblNewLabel_1.setBounds(55, 47, 281, 25);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(55, 101, 91, 31);
		contentPanel.add(lblId);
		
		tfid = new JTextField();
		tfid.setColumns(10);
		tfid.setBounds(111, 101, 201, 31);
		contentPanel.add(tfid);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOKs = new JButton("OK");
				btnOKs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = 
									DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/myprojectDB",
											"root",
											"12345");
							Statement stmt = con.createStatement();			
							String sql = "delete from logintbl where id='" + tfid.getText() + "'";							
							System.out.println(sql);
							if(stmt.executeUpdate(sql) > 0) {
								JOptionPane.showMessageDialog(null, "회원 삭제 완료!!");						
							}
							else
								JOptionPane.showMessageDialog(null, "회원 삭제 오류!!");
							
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						
						
						
						
						
						
						
						
					}
				});
				btnOKs.setActionCommand("OK");
				buttonPane.add(btnOKs);
				getRootPane().setDefaultButton(btnOKs);
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
