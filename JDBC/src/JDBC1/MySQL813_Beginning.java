package JDBC1;

import java.sql.*;

class MySQL813_Beginning {
  
  //��: �N�Ӧp�GtheURL���U��XML�ɮפ����ɭԡA�H�U��[&]������g��[&amp;]
	public static final String theURL = 
			  "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei"
                                          // �����]�w�ɰ�:  [serverTimezone=Asia/Taipei]
	                                           /* �ƹ�W�]�i��[serverTimezone=GMT+8]  -->  [serverTimezone=GMT%2B8]
	                                            * GMT+8 �i�H��ܥx�W�έ���ɰ�: ���n�`�N���i�H��[+]���A�ݭn���[%2B]�N��[+]
	                                            * serverTimezone�Ȫ��C��: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
	                                           */
  //��: �H�U�i�ٲ����]�w
		  + "&sslMode=PREFERRED"              // SSL�`�N1.�qConnector / J 8.0.13�s���}�l�A"sslMode"���w�]�Ȭ�"PREFERRED"�A�����ĩ��ª���"useSSL=true&requireSSL=false&verifyServerCertificate=false"
                                          // SSL�`�N2.�s���PConnector / J 8.0.12�Χ󦭪������w�]�]�m���Ҥ��P�A�ª��`�ݨ쪺�O�ϥ�useSSL=false�H�ѨM�Ȥ�����ε{���LSSL�����D 
                                          // SSL�`�N3.�s���p�G���T�a�]�m�F"sslMode"�A�h�N�����¦��ݩʡC�p�G�����T�]�m"sslMode"��"useSSL"�A�h�N���ιw�]�]�m��"sslMode = PREFERRED" --> �i�H�d�ݩx����connector-j-8.0-en.pdf��32��

			+ "&rewriteBatchedStatements=true"  // �妸��s�ݭn����T

			+ "&allowPublicKeyRetrieval=true"   // �t�XMySQL 8�H�᪩����K�X�x�s����]�w

			+ "&useUnicode=true"                // �i�H���ΦA�]�w: �Ѩ��p�U
			+ "&characterEncoding=utf-8"        // �i�H���ΦA�]�w: �]��mySQL8 �w�]���s�X�w�g�q[latin1] �אּ [utf8mb4] --> �i�H���� show variables like '%character%'; �d��
      ; 
                                           
	
    public static void main(String argv[]) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");     //mySQL5 �X�ʵ{��-�ĥ|��
              Class.forName("com.mysql.cj.jdbc.Driver");  //mySQL8 �X�ʵ{��-�ĥ|��
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