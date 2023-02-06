import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinMainAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String category[] = {"책이름", "저자", "대출자"};
	private JTextField textField;
	private JTable table;
	DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMainAdmin dialog = new WinMainAdmin("","");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinMainAdmin(String id, String pw) {
		setTitle("도서 대여 프로그램(관리자용)");
		setBounds(100, 100, 611, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));


		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMemberOby = new JMenu("회원관리");
		menuBar.add(mnMemberOby);
		
		JMenuItem ntmMemberDelete = new JMenuItem("회원 삭제");
		ntmMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinADMemberDelete winADMemberDelete = new WinADMemberDelete();
				winADMemberDelete.setModal(true);
				winADMemberDelete.setVisible(true);
				
			}
		});
		mnMemberOby.add(ntmMemberDelete);
		
		JMenuItem ntmMemberUpdate = new JMenuItem("회원 정보변경");
		ntmMemberUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinADMemberUpdate winADMemberUpdate = new WinADMemberUpdate();
				winADMemberUpdate.setModal(true);
				winADMemberUpdate.setVisible(true);
				
				
			}
		});
		mnMemberOby.add(ntmMemberUpdate);
		
		JMenuItem ntmMemberInsert = new JMenuItem("회원추가");
		ntmMemberInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinAdminMemberInsert winAdminMemberInsert = new WinAdminMemberInsert();
				winAdminMemberInsert.setModal(true);
				winAdminMemberInsert.setVisible(true);
			}
		});
		mnMemberOby.add(ntmMemberInsert);
		
		JMenu mnBook = new JMenu("도서관리");
		menuBar.add(mnBook);
		
		JMenuItem mnBookUpdate = new JMenuItem("도서 수정");
		mnBookUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookUpdate winBookUpdate = new WinBookUpdate();
				winBookUpdate.setModal(true);
				winBookUpdate.setVisible(true);
			}
		});
		mnBook.add(mnBookUpdate);
		
		JMenuItem mnBookDelete = new JMenuItem("도서 삭제");
		mnBookDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookDelete winBookDelete = new WinBookDelete();
				winBookDelete.setModal(true);
				winBookDelete.setVisible(true);
			}
		});
		mnBook.add(mnBookDelete);
		
		JMenuItem mnBookSelect = new JMenuItem("도서 추가");
		mnBookSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookInsert winBookInsert = new WinBookInsert();
				winBookInsert.setModal(true);
				winBookInsert.setVisible(true);
			}
		});
		mnBook.add(mnBookSelect);
		
		JMenu ntmBookbysy = new JMenu("대출및반납");
		menuBar.add(ntmBookbysy);
		
		JMenuItem ntmBookby = new JMenuItem("대여목록조회");
		ntmBookby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRentalAllMember winRentalAllMember = new WinRentalAllMember();
				winRentalAllMember.setModal(true);
				winRentalAllMember.setVisible(true);
			}
		});
		ntmBookbysy.add(ntmBookby);
		
		JMenuItem ntmBooksy = new JMenuItem("연체도서조회");
		ntmBooksy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinReturnNotMember winReturnNotMember = new WinReturnNotMember();
				winReturnNotMember.setModal(true);
				winReturnNotMember.setVisible(true);
			}
		});
		ntmBookbysy.add(ntmBooksy);
		
		JMenuItem mtnRentel = new JMenuItem("도서 대출");
		mtnRentel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookRentelinsert winBookRentelinsert = new WinBookRentelinsert();
				winBookRentelinsert.setModal(true);
				winBookRentelinsert.setVisible(true);
			}
		});
		ntmBookbysy.add(mtnRentel);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("도서 반납");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookReturn winBookReturn = new WinBookReturn();
				winBookReturn.setModal(true);
				winBookReturn.setVisible(true);
				
			}
		});
		ntmBookbysy.add(mntmNewMenuItem);
		
		JMenu mnNewMenu = new JMenu("문의사항조회");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WinQuestionSerch winQuestionSerch = new WinQuestionSerch();
				winQuestionSerch.setModal(true);
				winQuestionSerch.setVisible(true);
				
				
			}
		});
		menuBar.add(mnNewMenu);
		

		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columnNames[]= {"번호","ISBN","책 제목","저자","출판사","가격"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);		
		table = new JTable(dtm);		
		scrollPane.setViewportView(table);
		// 글꼴 선택(WindowBuilder -> Design - Properties)
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		// 셀 너비 조절
		int widths[] = { 5, 5, 200, 10, 10, 5 };		 
		for (int i=0; i<6; i++) {
		  TableColumn column = table.getColumnModel().getColumn(i);
		  column.setPreferredWidth( widths[i] );
		}
		// 셀 높이 조절
		table.setRowHeight(25);
		// 셀 정렬
		DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
		cellAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumn("번호").setCellRenderer(cellAlignRight);		
		table.getColumn("ISBN").setCellRenderer(cellAlignRight);
		table.getColumn("가격").setCellRenderer(cellAlignRight);

		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		ShowAllBooks();
		
		
	}
	
	
	
	private void ShowAllBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "SELECT * FROM booktbl";
			ResultSet rs = stmt.executeQuery(sql);			
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			int cnt=0;
			while(rs.next()) {
				String record[] = new String[6];
				record[0] = Integer.toString(++cnt);
				record[1] = rs.getString("ISBN");
				record[2] = rs.getString("Title");
				record[3] = rs.getString("Author");
				record[4] = rs.getString("Publisher");
				record[5] = rs.getString("Price");
				dtm.addRow(record);
			}
											
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}
