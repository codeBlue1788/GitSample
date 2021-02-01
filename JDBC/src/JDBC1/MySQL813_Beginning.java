package JDBC1;

import java.sql.*;

class MySQL813_Beginning {
  
  //註: 將來如果theURL註冊於XML檔案內的時候，以下的[&]必須改寫為[&amp;]
	public static final String theURL = 
			  "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei"
                                          // 必須設定時區:  [serverTimezone=Asia/Taipei]
	                                           /* 事實上也可用[serverTimezone=GMT+8]  -->  [serverTimezone=GMT%2B8]
	                                            * GMT+8 可以表示台灣或香港時區: 但要注意不可以用[+]號，需要轉用[%2B]代替[+]
	                                            * serverTimezone值的列表: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
	                                           */
  //註: 以下可省略不設定
		  + "&sslMode=PREFERRED"              // SSL注意1.從Connector / J 8.0.13新版開始，"sslMode"的預設值為"PREFERRED"，它等效於的舊版的"useSSL=true&requireSSL=false&verifyServerCertificate=false"
                                          // SSL注意2.新版與Connector / J 8.0.12及更早版本的預設設置有所不同，舊版常看到的是使用useSSL=false以解決客戶端應用程式無SSL的問題 
                                          // SSL注意3.新版如果明確地設置了"sslMode"，則將忽略舊式屬性。如果未明確設置"sslMode"或"useSSL"，則將應用預設設置為"sslMode = PREFERRED" --> 可以查看官方文件connector-j-8.0-en.pdf第32頁

			+ "&rewriteBatchedStatements=true"  // 批次更新需要此資訊

			+ "&allowPublicKeyRetrieval=true"   // 配合MySQL 8以後版本對密碼儲存機制的設定

			+ "&useUnicode=true"                // 可以不用再設定: 參見如下
			+ "&characterEncoding=utf-8"        // 可以不用再設定: 因為mySQL8 預設的編碼已經從[latin1] 改為 [utf8mb4] --> 可以執行 show variables like '%character%'; 查看
      ; 
                                           
	
    public static void main(String argv[]) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");     //mySQL5 驅動程式-第四類
              Class.forName("com.mysql.cj.jdbc.Driver");  //mySQL8 驅動程式-第四類
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        }

        try {
            Connection con =  DriverManager.getConnection(theURL, "root", "1qaz2wsx");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from emp2");

            while (rs.next()) {
                String str1 = rs.getString(1);
                String str2 = rs.getString(2);

                System.out.print(" EMPNO= " + str1);
                System.out.print(" ENAME= " + str2);
                System.out.print("\n");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}