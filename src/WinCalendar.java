import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinCalendar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JPanel panelCalendar;
	JComboBox cbYear;
	JComboBox cbMonth;
	String retDate;
	
	public String getDate() {
		return retDate;
	}
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinCalendar dialog = new WinCalendar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * Create the dialog.
	 */
	public WinCalendar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {				
				Component compList[] = panelCalendar.getComponents();
				for(Component c : compList)
					if(c instanceof JButton)
						panelCalendar.remove(c);
				panelCalendar.revalidate();
				panelCalendar.repaint();
				
				// �빐�떦�븯�뒗 �뀈/�썡 �떖�젰�쓣 蹂댁씤�떎
				String yoil[]= {"일","월","화","수","목","금","토"};
				int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
				int year = (int)cbYear.getSelectedItem();
				int month = (int)cbMonth.getSelectedItem();
				for(int i=0;i<7;i++) {
					JButton btn = new JButton(yoil[i] + "요일");
					btn.setBackground(Color.YELLOW);
					panelCalendar.add(btn);
				}
				
				
				
					
				
				
			
				int index = 0;
				int sum = 0;
				
				for(int i=1922;i<year;i++) //1922�뀈遺��꽣 �씠�쟾 �빐源뚯��쓽 �빀
					if(i%4==0 && i%100!=0 || i%400==0)
						sum = sum + 366;
					else
						sum = sum + 365;
				
				int lastDay = 0; 
				for(int i=0; i<month-1;i++)  // 1�썡遺��꽣 �씠�쟾 �떖源뚯��쓽 �빀
					if(i==1 && (year%4==0 && year%100!=0 || year%400==0)) 
						sum = sum + ++Months[i];
					else 
						sum = sum + Months[i];						
				if(month==2 && (year%4==0 && year%100!=0 || year%400==0)) 
					lastDay = ++Months[month-1];
				else
					lastDay = Months[month-1];
				
				index = (index + sum) % 7;						
				for(int i=1;i<=index;i++) {
					JButton btn = new JButton("");
					panelCalendar.add(btn);		
					btn.setVisible(false);
				}						
				
				// �빐�떦 �썡�쓽 1�씪遺��꽣 留덉�留� �궇吏쒓퉴吏� 踰꾪듉 �깮�꽦
				for(int i=1;i<=lastDay;i++) {
					JButton btn = new JButton(Integer.toString(i));
					panelCalendar.add(btn);				
					btn.addActionListener(new ActionListener() {								
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton btn1 = (JButton)e.getSource();
							retDate = cbYear.getSelectedItem()+"-";
							retDate = retDate + cbMonth.getSelectedItem()+"-";
							retDate = retDate + btn1.getText();
							setVisible(false);
						}
					});			
				}
				panelCalendar.revalidate();
			}
		});
		setTitle("Calendar");
		setBounds(100, 100, 617, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				cbYear = new JComboBox();	
				cbYear.setEditable(true);
				
				cbYear.setEditable(true);
				for(int i=1922;i<=2100;i++)
					cbYear.addItem(i);				
				Calendar now = Calendar.getInstance();
				cbYear.setSelectedItem(now.get(Calendar.YEAR));				
				panel.add(cbYear);
				cbYear.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						// 湲곗〈 �떖�젰 吏��슦怨�
						Component compList[] = panelCalendar.getComponents();
						for(Component c : compList)
							if(c instanceof JButton)
								panelCalendar.remove(c);
						panelCalendar.revalidate();
						panelCalendar.repaint();
						
						// �빐�떦�븯�뒗 �뀈/�썡 �떖�젰�쓣 蹂댁씤�떎
						String yoil[]= {"일","월","화","수","목","금","토"};
						int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
						int year = (int)cbYear.getSelectedItem();
						int month = (int)cbMonth.getSelectedItem();
						for(int i=0;i<7;i++) {
							JButton btn = new JButton(yoil[i] + "요일");
							btn.setBackground(Color.YELLOW);
							panelCalendar.add(btn);
						}
						// 鍮� 踰꾪듉�쓣 異붽� 1922�뀈 1�썡 1�씪 �씪�슂�씪(怨듬갚)
						int index = 0;
						int sum = 0;
						
						for(int i=1922;i<year;i++) //1922�뀈遺��꽣 �씠�쟾 �빐源뚯��쓽 �빀
							if(i%4==0 && i%100!=0 || i%400==0)
								sum = sum + 366;
							else
								sum = sum + 365;
						
						int lastDay = 0; 
						for(int i=0; i<month-1;i++)  // 1�썡遺��꽣 �씠�쟾 �떖源뚯��쓽 �빀
							if(i==1 && (year%4==0 && year%100!=0 || year%400==0)) 
								sum = sum + ++Months[i];
							else 
								sum = sum + Months[i];						
						if(month==2 && (year%4==0 && year%100!=0 || year%400==0)) 
							lastDay = ++Months[month-1];
						else
							lastDay = Months[month-1];
						
						index = (index + sum) % 7;						
						for(int i=1;i<=index;i++) {
							JButton btn = new JButton("");
							panelCalendar.add(btn);		
							btn.setVisible(false);
						}						
						
						// �빐�떦 �썡�쓽 1�씪遺��꽣 留덉�留� �궇吏쒓퉴吏� 踰꾪듉 �깮�꽦
						for(int i=1;i<=lastDay;i++) {
							JButton btn = new JButton(Integer.toString(i));
							panelCalendar.add(btn);						
							btn.addActionListener(new ActionListener() {								
								@Override
								public void actionPerformed(ActionEvent e) {
									JButton btn1 = (JButton)e.getSource();
									retDate = cbYear.getSelectedItem()+"-";
									retDate = retDate + cbMonth.getSelectedItem()+"-";
									retDate = retDate + btn1.getText();
									setVisible(false);
								}
							});			
						}
						panelCalendar.revalidate();
					}
				});
			}
			{
				cbMonth = new JComboBox();	
				cbMonth.setEditable(true);
				for(int i=1;i<=12;i++)
					cbMonth.addItem(i);				
				Calendar now = Calendar.getInstance();
				cbMonth.setSelectedItem(now.get(Calendar.MONTH)+1);				
				panel.add(cbMonth);
				
				cbMonth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 湲곗〈 �떖�젰 吏��슦怨�
						Component compList[] = panelCalendar.getComponents();
						for(Component c : compList)
							if(c instanceof JButton)
								panelCalendar.remove(c);
						panelCalendar.revalidate();
						panelCalendar.repaint();
						
						// �빐�떦�븯�뒗 �뀈/�썡 �떖�젰�쓣 蹂댁씤�떎
						String yoil[]= {"일","월","화","수","목","금","토"};
						int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
						int year = (int)cbYear.getSelectedItem();
						int month = (int)cbMonth.getSelectedItem();
						for(int i=0;i<7;i++) {
							JButton btn = new JButton(yoil[i] + "요일");
							btn.setBackground(Color.YELLOW);
							panelCalendar.add(btn);
						}
						// 鍮� 踰꾪듉�쓣 異붽� 1922�뀈 1�썡 1�씪 �씪�슂�씪(怨듬갚)
						int index = 0;
						int sum = 0;
						
						for(int i=1922;i<year;i++) //1922�뀈遺��꽣 �씠�쟾 �빐源뚯��쓽 �빀
							if(i%4==0 && i%100!=0 || i%400==0)
								sum = sum + 366;
							else
								sum = sum + 365;
						
						int lastDay = 0; 
						for(int i=0; i<month-1;i++)  // 1�썡遺��꽣 �씠�쟾 �떖源뚯��쓽 �빀
							if(i==1 && (year%4==0 && year%100!=0 || year%400==0)) 
								sum = sum + ++Months[i];
							else 
								sum = sum + Months[i];						
						if(month==2 && (year%4==0 && year%100!=0 || year%400==0)) 
							lastDay = ++Months[month-1];
						else
							lastDay = Months[month-1];
						
						index = (index + sum) % 7;						
						// 해당 월의 1일부터 마지막 날짜까지 버튼 생성
						for(int i=1;i<=lastDay;i++) {
							JButton btn = new JButton(Integer.toString(i));
							panelCalendar.add(btn);		
							btn.addActionListener(new ActionListener() {							
								@Override	// Diary클래스와 연동. 날짜불러오기
								public void actionPerformed(ActionEvent e) {
									JButton btn1 = (JButton)e.getSource();
									retDate = cbYear.getSelectedItem()+"-";
									retDate = retDate + cbMonth.getSelectedItem()+"-";
									retDate = retDate + btn1.getText();
									setVisible(false);
									
								}
							});
						}
						panelCalendar.revalidate();
					}
				});
			}
		}
		{
			panelCalendar = new JPanel();
			contentPanel.add(panelCalendar, BorderLayout.CENTER);
			panelCalendar.setLayout(new GridLayout(0, 7, 0, 0));
		}
	}

}
