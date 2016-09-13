import java.io.*;
import java.sql.*;


public class insert {
	
	final static String DRIVER = "com.mysql.jdbc.Driver";
	//資料庫 URL
	final static String URL = "jdbc:mysql://203.64.84.29/mangachatdb";
	//final static String URL = "jdbc:mysql://localhost:3306/mangatest";
	//資料庫使用者
	final static String USER = "sean";
	//final static String USER = "root";
	//資料庫密碼
	final static String PASS = "66dope99";
	//final static String PASS = "1234";
	
	//取得 connection
	public static Connection getConnection() throws SQLException{
		
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			throw new IllegalStateException("fail to class load : " + e.getMessage());
		}
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		return con;
	}
	
	//連接確認測試
		public static void ShowData() throws SQLException{
			Connection con = getConnection();
			Statement smt = con.createStatement();
			//localhost web1 DB
			ResultSet rs = smt.executeQuery("SELECT * FROM emotion_symbol");
			while(rs.next()){
				String data = "emotion_symbol_id:" + rs.getString("EMOTION_SYMBOL_ID") + ",emotion_symbol:" + rs.getString("EMOTION_SYMBOL");
				System.out.println(data);
			}
			
			smt.close();
			con.close();
			System.out.println("END");
		}
	
	public static void main(String[] argc) throws Exception {
		Connection conn = getConnection();
		Statement sta = conn.createStatement();
		
		FileReader fr = new FileReader("emotion");
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			//String[] temp =line.split("\\t");
			//System.out.println(temp[0]);
			//System.out.println(temp[1]);
			//int t3 = Integer.valueOf(temp[2]);
			
			try{
				//String ins = "INSERT INTO EMOTION_DICTIONARY(KEYWORD,EMOTION_CODE,EMOTION_SCALE)" + "VALUES ('"+temp[0]+"','"+temp[1]+"',"+t3+")";
				String ins = "INSERT INTO EMOTION_SYMBOL(EMOTION_SYMBOL)" + "VALUES ('"+line+"')";
				sta.executeUpdate(ins);
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
		ShowData();
		
	}
}
