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

public class WinBookRentelinsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfPublisher;
	private JTextField tfpDate;
	private JTextField tfISBN;

	private String picURL;
	private JCheckBox ckOnce;
	private JLabel lblPic;
	private JTextField tfmName;
	
	/**
	 * Create the dialog.
	 */
	public WinBookRentelinsert() {
		setTitle("도서 변경 창");
		setBounds(100, 100, 600, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//----------------------------2022/12/20-------------------------------------
		String imgText = "https://shopping-phinf.pstatic.net/main_3248718/32487182777.20221019152842.jpg";
		picURL = imgText;
		
		imgText = "<html><img src='" + picURL ;
		imgText = imgText + "' width=150 height=200></html>";
		//----------------------------2022/12/20-------------------------------------
		
		lblPic = new JLabel(imgText);
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					picURL = JOptionPane.showInputDialog("그림 주소를 입력하시오");
					if(picURL != null && !picURL.equals(""))				
						lblPic.setText("<html><img src='"+picURL+"' width=150 height=200></html>");
					else
						picURL = "https://shopping-phinf.pstatic.net/main_3248718/32487182777.20221019152842.jpg";
				
				}else if(e.getButton()==MouseEvent.BUTTON3){
					WinPicture winPicture = new WinPicture(picURL);
					winPicture.setModal(true);
					winPicture.setVisible(true);
				}
			}
		});
		
		lblPic.setOpaque(true);
		lblPic.setBackground(new Color(255, 255, 0));
		lblPic.setBounds(12, 10, 150, 200);
		contentPanel.add(lblPic);
		
		JLabel lblTitle = new JLabel("책 제목:");
		lblTitle.setBounds(191, 26, 57, 15);
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
										"jdbc:mysql://localhost:3306/sqlDB",
										"root",
										"12345");
						Statement stmt = con.createStatement();			
						String sql = "select distinct title from booktbl";
						sql = sql + " where title like '%" + word + "%'";
						ResultSet rs = stmt.executeQuery(sql);
						int cnt=0;
						Vector<String> v = new Vector<>();
						while(rs.next()) {	
							v.add(rs.getString("title"));
							System.out.println(rs.getString("title"));
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
		tfTitle.setBounds(256, 23, 316, 21);
		contentPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JLabel lblPublisher = new JLabel("출판사:");
		lblPublisher.setBounds(191, 65, 57, 15);
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
						String sql = "select distinct publisher from booktbl";
						sql = sql + " where publisher like '" + word + "%'";
						ResultSet rs = stmt.executeQuery(sql);
						int cnt=0;
						Vector<String> v = new Vector<>();
						while(rs.next()) {	
							v.add(rs.getString("publisher"));
							System.out.println(rs.getString("publisher"));
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
		tfPublisher.setBounds(256, 62, 158, 21);
		contentPanel.add(tfPublisher);
		
		JLabel lblpDate = new JLabel("신청:");
		lblpDate.setBounds(191, 144, 57, 15);
		contentPanel.add(lblpDate);
		
		tfpDate = new JTextField();
		tfpDate.setColumns(10);
		tfpDate.setBounds(256, 141, 158, 21);
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
		btnCalendar.setBounds(426, 140, 97, 23);
		contentPanel.add(btnCalendar);
		
		JButton btnInsert = new JButton("변경");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertBook();
			}
		});
		btnInsert.setBounds(243, 546, 97, 23);
		contentPanel.add(btnInsert);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(191, 104, 57, 15);
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
		tfISBN.setBounds(256, 101, 135, 21);
		contentPanel.add(tfISBN);
		
		ckOnce = new JCheckBox("한번 입력");
		ckOnce.setBounds(426, 61, 115, 23);
		contentPanel.add(ckOnce);
		
		JButton btnNewButton = new JButton("신청");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertBook();
			}
		});
		btnNewButton.setBounds(346, 254, 97, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(455, 254, 97, 23);
		contentPanel.add(btnNewButton_1);
		
		JLabel lblmName = new JLabel("신청인:");
		lblmName.setBounds(191, 184, 57, 15);
		contentPanel.add(lblmName);
		
		tfmName = new JTextField();
		tfmName.setBounds(256, 181, 116, 21);
		contentPanel.add(tfmName);
		tfmName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("*도서 대출은 14일이내에 반납바랍니다.");
		lblNewLabel.setBounds(12, 254, 229, 23);
		contentPanel.add(lblNewLabel);
	}

	protected void ShowDetail() {
		if(tfISBN.getText().length()==13) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = 
						DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/sqlDB",
								"root",
								"12345");
				Statement stmt = con.createStatement();			
				String sql = "select * from bookTBL where ISBN='";
				sql = sql + tfISBN.getText().trim() + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					tfTitle.setText(rs.getString("title"));
					tfPublisher.setText(rs.getString("publisher"));
					tfpDate.setText(rs.getString("pDate"));
					picURL = rs.getString("image");
					lblPic.setText("<html><img src='"+picURL+"' width=150 height=200></html>");
				}
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			tfISBN.setText("");
			tfISBN.requestFocus();						
		}
		
	}

	protected void InsertBook() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/myprojectDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "insert into bookrentalinserttbl values('";
			sql = sql + tfISBN.getText() + "','" + tfTitle.getText() + "','";
			sql = sql + tfPublisher.getText() + "','";
			sql = sql + tfpDate.getText() + "','";
			sql = sql + tfmName.getText() +"')" ; 
			System.out.println(sql);
			if(stmt.executeUpdate(sql) > 0) {
				JOptionPane.showMessageDialog(null, "신청 완료!!");
				if(ckOnce.isSelected())
					setVisible(false);
				else
					clearAll();
			}
			else
				JOptionPane.showMessageDialog(null, "신청 오류!!");
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	private void clearAll() {
		tfTitle.setText("");
		tfPublisher.setText("");
		tfpDate.setText("");
		tfISBN.setText("");
		tfmName.setText("");
		picURL = "";
		lblPic.setText(picURL);
		tfTitle.requestFocus();
	}
}
