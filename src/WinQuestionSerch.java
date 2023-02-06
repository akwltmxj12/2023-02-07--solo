import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinQuestionSerch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinQuestionSerch dialog = new WinQuestionSerch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinQuestionSerch() {
		setTitle("문의사항 조회");
		setBounds(100, 100, 450, 300);
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
				
				String columnNames[]= {"번호","책 제목","작성자","내용"};
				DefaultTableModel dtm = new DefaultTableModel(columnNames,0);		
				table = new JTable(dtm);		
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table.getSelectedRow();
						String sTitle = table.getValueAt(row, 1).toString() ;
						String sId = table.getValueAt(row, 2).toString() ;
						String sContents  = table.getValueAt(row, 3).toString() ;
						
						WinQuestionChoice winQuestionChoice = new WinQuestionChoice(sTitle,sId,sContents);
						winQuestionChoice.setModal(true);
						winQuestionChoice.setVisible(true);	
						
						
						
					}
				});
				scrollPane.setViewportView(table);
				// 글꼴 선택(WindowBuilder -> Design - Properties)
				table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				// 셀 너비 조절
				int widths[] = { 5, 5, 200, 10, 10, 5 };		 
				for (int i=0; i<4; i++) {
				  TableColumn column = table.getColumnModel().getColumn(i);
				  column.setPreferredWidth( widths[i] );
				}
				// 셀 높이 조절
				table.setRowHeight(25);
				// 셀 정렬
				DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
				cellAlignRight.setHorizontalAlignment(JLabel.RIGHT);
				table.getColumn("번호").setCellRenderer(cellAlignRight);		
				//table.getColumn("제목").setCellRenderer(cellAlignRight);
				table.getColumn("내용").setCellRenderer(cellAlignRight);

				
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
			String sql = "SELECT * FROM questiontbl";
			ResultSet rs = stmt.executeQuery(sql);			
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			int cnt=0;			
			while(rs.next()) {
				String record[] = new String[4];
				record[0] = Integer.toString(++cnt);
				record[1] = rs.getString("title");
				record[2] = rs.getString("bid");
				record[3] = rs.getString("content");
				dtm.addRow(record);					
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}


}
