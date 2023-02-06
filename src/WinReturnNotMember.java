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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class WinReturnNotMember extends JDialog {

	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	DefaultTableModel dtm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinReturnNotMember dialog = new WinReturnNotMember();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinReturnNotMember() {
		setTitle("연체 회원 조회");
		setBounds(100, 100, 462, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				
				
				JScrollPane scrollPane = new JScrollPane();
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				
				String columnNames[]= {"번호","ISBN","책 제목","출판사","신청일","신청인"};
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
				table.getColumn("신청인").setCellRenderer(cellAlignRight);

				
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
						"jdbc:mysql://localhost:3306/myprojectDB",
						"root",
						"12345");
		Statement stmt = con.createStatement();			
		String sql = "SELECT * FROM bookrentalinserttbl";
		ResultSet rs = stmt.executeQuery(sql);			
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		int cnt=0;
		while(rs.next()) {
			Calendar getToday = Calendar.getInstance();
			getToday.setTime(new Date()); //금일 날짜
			
			String bday = rs.getString("bday");
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bday);
			Calendar cmpDate = Calendar.getInstance();
			cmpDate.setTime(date); //특정 일자
			
			long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
			long diffDays = diffSec / (24*60*60); //일자수 차이				
		
			if(diffDays > 7) {
				String record[] = new String[6];
				record[0] = Integer.toString(++cnt);
				record[1] = rs.getString("ISBN");
				record[2] = rs.getString("btitle");
				record[3] = rs.getString("bpublisher");
				record[4] = rs.getString("bday");
				record[5] = rs.getString("bid");
				dtm.addRow(record);									
			}
					
			else if(diffDays < 7){
				setVisible(false);
			}

		}
							
	} catch (ClassNotFoundException | SQLException | ParseException e1) {
		e1.printStackTrace();
	}
	
  }
}
