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

public class WinBookReturn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfPublisher;
	private JTextField tfpDate;
	private JTextField tfISBN;

	private String picURL;
	private JCheckBox ckOnce;
	private JTextField tfRename;
	

	/**
	 * Create the dialog.
	 */
	public WinBookReturn() {
		setTitle("도서 반납 창");
		setBounds(100, 100, 472, 333);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//----------------------------2022/12/20-------------------------------------
		String imgText = "https://shopping-phinf.pstatic.net/main_3248718/32487182777.20221019152842.jpg";
		picURL = imgText;
		
		imgText = "<html><img src='" + picURL ;
		imgText = imgText + "' width=150 height=200></html>";
		
		JLabel lblTitle = new JLabel("책 제목:");
		lblTitle.setBounds(48, 13, 57, 15);
		contentPanel.add(lblTitle);
		
		tfTitle = new JTextField();
		tfTitle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String word = tfTitle.getText().trim();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = 
								DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/myprojectDB",
										"root",
										"12345");
						Statement stmt = con.createStatement();			
						String sql = "select distinct btitle from bookrentalinserttbl";
						sql = sql + " where btitle like '%" + word + "%'";
						ResultSet rs = stmt.executeQuery(sql);
						int cnt=0;
						Vector<String> v = new Vector<>();
						while(rs.next()) {	
							v.add(rs.getString("btitle"));
							System.out.println(rs.getString("btitle"));
							cnt++;
						}
						if(cnt == 1)
							tfTitle.setText(v.get(0));
						else {
							WinChoice winChoice = new WinChoice(v);
							winChoice.setModal(true);
							winChoice.setVisible(true);
							
							tfTitle.setText(winChoice.getPublisher());							
						}
						// ISBN, Title, ����, ���ǻ� ���̺� ���̱�
						WinShowTable winShowTable = new WinShowTable(tfTitle.getText(),"");
						winShowTable.setModal(true);
						winShowTable.setVisible(true);
						tfISBN.setText(winShowTable.getISBN());
						
						ShowDetail();						
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		tfTitle.setBackground(new Color(255, 255, 0));
		tfTitle.setBounds(117, 10, 316, 21);
		contentPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lblPublisher = new JLabel("출판사:");
		lblPublisher.setBounds(48, 50, 57, 15);
		contentPanel.add(lblPublisher);
		
		tfPublisher = new JTextField();
		tfPublisher.setBackground(new Color(255, 255, 0));
		tfPublisher.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String word = tfPublisher.getText().trim();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = 
								DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/sqlDB",
										"root",
										"12345");
						Statement stmt = con.createStatement();			
						String sql = "select distinct bpublisher from booktbl";
						sql = sql + " where bpublisher like '" + word + "%'";
						ResultSet rs = stmt.executeQuery(sql);
						int cnt=0;
						Vector<String> v = new Vector<>();
						while(rs.next()) {	
							v.add(rs.getString("bpublisher"));
							System.out.println(rs.getString("bpublisher"));
							cnt++;
						}
						if(cnt == 1) {
							tfPublisher.setText(v.get(0));
						}else if(cnt > 1) {//-----2022/12/20--------
							WinChoice winChoice = new WinChoice(v);
							winChoice.setModal(true);
							winChoice.setVisible(true);
							
							tfPublisher.setText(winChoice.getPublisher());								

						}else {//-----2022/12/20--------
							tfPublisher.setSelectionStart(0);
							tfPublisher.setSelectionEnd(tfPublisher.getText().length());
						}							
						
						// ISBN, Title, ����, ���ǻ� ���̺� ���̱�
						WinShowTable winShowTable = new WinShowTable("", tfPublisher.getText());
						winShowTable.setModal(true);
						winShowTable.setVisible(true);
						tfISBN.setText(winShowTable.getISBN());
						
						ShowDetail();						
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		tfPublisher.setColumns(10);
		tfPublisher.setBounds(117, 47, 158, 21);
		contentPanel.add(tfPublisher);
		
		JLabel lblpDate = new JLabel("반납일:");
		lblpDate.setBounds(48, 94, 57, 15);
		contentPanel.add(lblpDate);
		
		tfpDate = new JTextField();
		tfpDate.setColumns(10);
		tfpDate.setBounds(117, 91, 158, 21);
		contentPanel.add(tfpDate);
		
		JButton btnCalendar = new JButton("날짜 선택");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar winCalendar = new WinCalendar();
				winCalendar.setModal(true);
				winCalendar.setVisible(true);
				tfpDate.setText(winCalendar.getDate());
			}
		});
		btnCalendar.setBounds(298, 90, 97, 23);
		contentPanel.add(btnCalendar);
		
		JButton btnInsert = new JButton("반납");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				BookinsertReturn();
				ReturnBook();
				
			}
		});
		btnInsert.setBounds(178, 239, 97, 23);
		contentPanel.add(btnInsert);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(48, 179, 57, 15);
		contentPanel.add(lblIsbn);
		
		tfISBN = new JTextField();
		tfISBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					ShowDetail();
				}
			}
		});
		tfISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		tfISBN.setColumns(10);
		tfISBN.setBounds(117, 176, 104, 21);
		contentPanel.add(tfISBN);
		
		ckOnce = new JCheckBox("한번 입력");
		ckOnce.setBounds(299, 46, 115, 23);
		contentPanel.add(ckOnce);
		
		tfRename = new JTextField();
		tfRename.setBounds(117, 134, 116, 21);
		contentPanel.add(tfRename);
		tfRename.setColumns(10);
		
		JLabel lbltfRename = new JLabel("반납인:");
		lbltfRename.setBounds(48, 137, 57, 15);
		contentPanel.add(lbltfRename);
	}

	protected void BookinsertReturn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/myprojectDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "insert into bookreturn values('";
			sql = sql + tfISBN.getText() + "','" + tfTitle.getText() + "','";
			sql = sql + tfPublisher.getText() + "','";
			sql = sql + tfpDate.getText() + "','";
			sql = sql + tfRename.getText() +"')" ; 
			
			if(stmt.executeUpdate(sql) > 0) {
				JOptionPane.showMessageDialog(null, "반납 완료!!");
				
			}
			else
				JOptionPane.showMessageDialog(null, "반납 오류!!");
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		
	}

	protected void ShowDetail() {
		if(tfISBN.getText().length()==13) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/myprojectDB",
								"root",
								"12345");
				Statement stmt = con.createStatement();			
				String sql = "select * from bookrentalinserttbl where ISBN='";
				sql = sql + tfISBN.getText().trim() + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					tfTitle.setText(rs.getString("btitle"));
					tfPublisher.setText(rs.getString("bpublisher"));
					tfpDate.setText(rs.getString("bday"));
					tfRename.setText(rs.getString("bname"));
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			tfISBN.setText("");
			tfISBN.requestFocus();						
		}
		
	}

	protected void ReturnBook() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/myprojectDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "delete from bookrentalinserttbl where isbn='" + tfISBN.getText() + "'"; 

			
			if(stmt.executeUpdate(sql) > 0) {
				JOptionPane.showMessageDialog(null, "반납 완료!!");
				if(ckOnce.isSelected())
					setVisible(false);
				else
					clearAll();
			}
			else
				JOptionPane.showMessageDialog(null, "반납 실패!!");
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	private void clearAll() {
		tfTitle.setText("");		
		tfPublisher.setText("");
		tfpDate.setText("");
		tfRename.setText("");
		tfISBN.setText("");
		tfTitle.requestFocus();
	}
}
