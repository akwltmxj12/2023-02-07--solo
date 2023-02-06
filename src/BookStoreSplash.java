import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.thehowtotutorial.splashscreen.*;


public class BookStoreSplash {

	public static void main(String[] args) throws Exception {
		int total = numOfRecords();
		JSplash splash = new JSplash(BookStoreSplash.class.getResource("/image/bookstore.jpg"),
				true, true, false, "V1", null, Color.RED,Color.BLACK);
		splash.splashOn();
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("번호");
		columnNames.add("ISBN");
		columnNames.add("책 제목");
		columnNames.add("저자");
		columnNames.add("출판사");
		columnNames.add("가격");
		
		Vector data = new Vector<>();		// 테이블(레코드 집합)  
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/bookDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			String sql = "select * from bookTBL";
			
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
	         int cnt=0;
	         while(rs.next()) {
	            String record[] = new String[6];
	            record[0] = Integer.toString(++cnt);
	            record[1] = rs.getString("ISBN");
	            record[2] = rs.getString("Title");
	            record[3] = rs.getString("Author");
	            record[4] = rs.getString("Publisher");
	            record[5] = rs.getString("Price");
	            
	            Vector row = new Vector<>();	// 하나의 레코드
	            for(int i=0; i<6; i++)
	            	row.add(record[i]);
				data.add(row);
	            
				splash.setProgress((cnt*100/total), (cnt*100/total) + "% Loading...");
	            Thread.sleep(10);
	         }
	                    
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	

		
		for(int i=1; i<=total; i++) {
			splash.setProgress(i, (i/total)*100 + "% Loading...");	
			Thread.sleep(10);
		}		
		splash.splashOff();
		
		DefaultTableModel dtm = new DefaultTableModel(data,columnNames);
		
		
		Winlogin login = new Winlogin();
		login.setVisible(true);
		
	}

	private static int numOfRecords() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/bookDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			
			String sql = "select count(*) as cnt from booktbl";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				return rs.getInt("cnt");
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return 0;
	}

}