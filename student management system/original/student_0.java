package original; //학생 회원가입

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//학생용

public class student_0 {
	


	public static int student() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("학번입력을 입력하세요 >>");
		int S_ID =sc.nextInt();
		System.out.println("이름입력을 입력하세요 >>");
		String S_NAME =sc.next();
		System.out.println("학년을 입력하세요 >>");
		String S_GRADE =sc.next();
		System.out.println("전화번호를 입력하세요 >>");
		String S_TEL =sc.next();
		System.out.println("주소를 입력하세요 >>");
		String S_ADDR=sc.next();
		System.out.println("학과이름 >>");
		String DEPARTMENT_D_NAME =sc.next();
	
		
		String url = "jdbc:oracle:thin:@smhrdai.cgboj9u5u5ci.us-east-2.rds.amazonaws.com:1521/ORCL";
		String user = "smhrd";
		String password = "tjsdn112!";
		int cnt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, password);
			
			String SQL1 ="select * from STUDENT where S_ID=?";
			PreparedStatement ps1=conn.prepareStatement(SQL1);
			ps1.setInt(1, S_ID);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next()) {
				//cnt=1;
			   return -1;	
			}
			
			// 파라메타가 포함된 SQL 문장
			String SQL ="insert into STUDENT values(?,?,?,?,?,?)";
				
			// SQL 문장을 전송하는 객체를 생성 (Statement - 못씀 )
			// Statement -> 완성된 SQL 문장만 전송 가능하다.
			// 파라메터가 포홤된 SQL 문장을 전송할수 있는 객체 (PreparedStatement)
			
			PreparedStatement ps=conn.prepareStatement(SQL);
			// PreparedStatement        // 
	        //        d -> 컴파일이 다된   ps = 
			ps.setInt(1, S_ID);
			ps.setString(2, S_NAME);
			ps.setString(3, S_GRADE);
			ps.setString(4, S_TEL);
			ps.setString(5,S_ADDR);
			ps.setString(6, DEPARTMENT_D_NAME );
	   	    cnt=ps.executeUpdate(); //완성된 SQL을 실행 -> 컴파일 안되고 (PreparedStatement에서 컴파일했기떄문에) 바로 실행
			
			if(cnt == 1) {System.out.println("저장되었습니다");}
			else {System.out.println("저장되지 않았습니다");}
						
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	   return cnt;	
	}

}

	
	
	
	
	


