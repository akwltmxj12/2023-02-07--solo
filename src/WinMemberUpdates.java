import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinMemberUpdates extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfEmail;
	private JTextField tfPw;

	private String picURL;
	private JTextField tfName;
	private JTextField tfTel1;
	private JTextField tfAddress;
	private JTextField tfTel3;
	private JTextField tfTel2;
	private JTextField tfId;
	
	/**
	 * Create the dialog.
	 */
	public WinMemberUpdates() {
		setTitle("정보 수정 창");
		setBounds(100, 100, 380, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//----------------------------2022/12/20-------------------------------------
		String imgText = "https://shopping-phinf.pstatic.net/main_3248718/32487182777.20221019152842.jpg";
		picURL = imgText;
		
		imgText = "<html><img src='" + picURL ;
		imgText = imgText + "' width=150 height=200></html>";
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setBounds(28, 100, 57, 15);
		contentPanel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(91, 97, 230, 21);
		contentPanel.add(tfEmail);
		
		JButton btnInsert = new JButton("변경");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertBook();
			}
		});
		btnInsert.setBounds(136, 268, 97, 23);
		contentPanel.add(btnInsert);
		
		JLabel lblpw = new JLabel("비밀번호:");
		lblpw.setBounds(28, 63, 57, 15);
		contentPanel.add(lblpw);
		
		tfPw = new JTextField();
		tfPw.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPw.setColumns(10);
		tfPw.setBounds(91, 60, 104, 21);
		contentPanel.add(tfPw);
		
		JLabel lblName = new JLabel("이름 :");
		lblName.setBounds(28, 139, 57, 15);
		contentPanel.add(lblName);
		
		JLabel lblTel = new JLabel("전화번호 :");
		lblTel.setBounds(28, 182, 57, 15);
		contentPanel.add(lblTel);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setBounds(28, 228, 57, 15);
		contentPanel.add(lblAddress);
		
		tfName = new JTextField();
		tfName.setHorizontalAlignment(SwingConstants.RIGHT);
		tfName.setColumns(10);
		tfName.setBounds(91, 136, 104, 21);
		contentPanel.add(tfName);
		
		tfTel1 = new JTextField();
		tfTel1.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTel1.setColumns(10);
		tfTel1.setBounds(91, 179, 57, 21);
		contentPanel.add(tfTel1);
		
		tfAddress = new JTextField();
		tfAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		tfAddress.setColumns(10);
		tfAddress.setBounds(91, 225, 230, 21);
		contentPanel.add(tfAddress);
		
		tfTel3 = new JTextField();
		tfTel3.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTel3.setColumns(10);
		tfTel3.setBounds(246, 179, 57, 21);
		contentPanel.add(tfTel3);
		
		tfTel2 = new JTextField();
		tfTel2.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTel2.setColumns(10);
		tfTel2.setBounds(176, 179, 57, 21);
		contentPanel.add(tfTel2);
		
		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setBounds(155, 182, 19, 15);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("-");
		lblNewLabel_3_1.setBounds(236, 182, 19, 15);
		contentPanel.add(lblNewLabel_3_1);
		
		JLabel lblId = new JLabel("아이디 :");
		lblId.setBounds(28, 26, 57, 15);
		contentPanel.add(lblId);
		
		tfId = new JTextField();
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					ShowDetail();
				}
			}
		});
		tfId.setHorizontalAlignment(SwingConstants.RIGHT);
		tfId.setColumns(10);
		tfId.setBounds(91, 23, 104, 21);
		contentPanel.add(tfId);
	}

	protected void ShowDetail() {
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/myprojectdb",
								"root",
								"12345");
				Statement stmt = con.createStatement();			
				String sql = "select * from logintbl where id='";
				sql = sql + tfId.getText().trim() + "'";
				ResultSet rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					tfId.setText(rs.getString("id"));
					tfPw.setText(rs.getString("pw"));
					tfEmail.setText(rs.getString("email"));
					tfName.setText(rs.getString("name"));
					tfTel1.setText(rs.getString("tel").substring(0, 3));
					tfTel2.setText(rs.getString("tel").substring(3, 7));
					tfTel3.setText(rs.getString("tel").substring(7, 11));
					tfAddress.setText(rs.getString("address"));
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		
	}

	protected void InsertBook() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/myprojectdb",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "update logintbl set pw='";
			sql = sql +  tfPw.getText() + "',";
			sql = sql + "name='" + tfName.getText() + "',";
			sql = sql + "email='" + tfEmail.getText() + "',";						
			sql = sql + "tel='" + tfTel1.getText() + tfTel2.getText()+ tfTel3.getText()+ "',";								
			sql = sql + "address='" + tfAddress.getText() + "'";
			sql = sql + "WHERE id='";
			sql = sql + tfId.getText() + "'";
			System.out.println(sql);
			if(stmt.executeUpdate(sql) > 0) {
				JOptionPane.showMessageDialog(null, "변경 완료!!");

			}
			else
				JOptionPane.showMessageDialog(null, "변경 실패!!");
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
