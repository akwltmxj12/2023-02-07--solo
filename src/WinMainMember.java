import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.Session;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WinMainMember extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	String sId="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMainMember dialog = new WinMainMember("","");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param strName 
	 * @param strID 
	 */
	public WinMainMember(String id, String pw) {
		sId = id;
		setTitle("도서 대여 프로그램(일반사용자용)");
		setBounds(100, 100, 611, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuestion = new JMenu("문의하기");
		mnQuestion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WinQuestion winQuestion = new WinQuestion();
				winQuestion.setModal(true);
				winQuestion.setVisible(true);
			}
		});
		menuBar.add(mnQuestion);
		
		JMenu mnMyPage = new JMenu("마이페이지");
		menuBar.add(mnMyPage);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("정보 수정");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberUpdates winMemberUpdates = new WinMemberUpdates();
				winMemberUpdates.setModal(true);
				winMemberUpdates.setVisible(true);
			}
		});
		mnMyPage.add(mntmNewMenuItem_2);
		
		JMenuItem ntmMemberDelete = new JMenuItem("탈퇴하기");
		ntmMemberDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberDelete winMemberDelete = new WinMemberDelete();
				winMemberDelete.setModal(true);
				winMemberDelete.setVisible(true);
			}
		});
		mnMyPage.add(ntmMemberDelete);
		
		JMenu ntmBySySearch = new JMenu("대출/연체 조회");
		menuBar.add(ntmBySySearch);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("대여목록");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//내일와서 해야할것

				WinMemberRentalSerch WinMemberRentalSerch = new WinMemberRentalSerch(sId);
				WinMemberRentalSerch.setModal(true);
				WinMemberRentalSerch.setVisible(true);
			}
		});
		ntmBySySearch.add(mntmNewMenuItem_1);
	

		
		
		
	}


	
	
}
