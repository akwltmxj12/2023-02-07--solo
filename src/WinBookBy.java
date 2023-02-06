import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinBookBy extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinBookBy dialog = new WinBookBy();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinBookBy() {
		setTitle("도서 대여 프로그램");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("책 제목");
		lblTitle.setBounds(63, 167, 48, 25);
		contentPanel.add(lblTitle);
		
		JLabel lblNewLabel_1 = new JLabel("책 제목을 정확히 입력해주세요.");
		lblNewLabel_1.setBounds(30, 10, 181, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("대출은 회원만 가능합니다.");
		lblNewLabel_2.setBounds(30, 35, 181, 15);
		contentPanel.add(lblNewLabel_2);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(134, 169, 182, 21);
		contentPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
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
					String sql = "select * from bookrental where='";
					sql = sql +  tfISBN.getText() + "','";
					sql = sql + tfTitle.getText() + "'";

					System.out.println(sql);
					if(stmt.executeUpdate(sql) > 0) {
						JOptionPane.showMessageDialog(null, "조회 완료!!");

							
					}
					else
						JOptionPane.showMessageDialog(null, "조회오류!!");
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(153, 217, 97, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setBounds(65, 126, 57, 15);
		contentPanel.add(lblISBN);
		
		tfISBN = new JTextField();
		tfISBN.setBounds(134, 123, 182, 21);
		contentPanel.add(tfISBN);
		tfISBN.setColumns(10);
			
		
	}
	

	
	
	
}
