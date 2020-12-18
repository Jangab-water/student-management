package original;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreCal {

	public static void main(String[] args) {
		
		ScoreCal();
	}

	private static void ScoreCal() {
		// 남은 학점 계산기
		Scanner sc = new Scanner(System.in);
		System.out.print("학번 입력 : ");
		int stt_pc = sc.nextInt();
		
		
		String url = "jdbc:oracle:thin:@smhrdai.ca6ktgfa4m7b.us-east-2.rds.amazonaws.com:1521/ORCL";// IP, 포트정보, DB이름
		String id = "smhrd";
		String pw = "rha5164508";	

		String SQL ="select * from s_score a, student b, course c where a.student_s_id=b.s_id and "
				+ "c.c_name=a.course_c_name and c.c_id=a.course_c_id"
				+ "	and b.s_id= ?";	


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공!");

			PreparedStatement ps = conn.prepareStatement(SQL); 
			ps.setInt(1, stt_pc);
			ResultSet rs = ps.executeQuery();
			
			ArrayList plist = new ArrayList();


			String stt_nm = null; 											// 불러올 학점 변수화. 0으로 초기화
			int tot2 = 20;													// 졸업 학점 고정!
			int total = 0;													// 이수 학점 담을 변수 (초기화)
			int result= 0;													// 남은 학점 초기화
			
			while (rs.next()) { // 데이터가 몇개있는지 모르니 while문에 rs.next()라는 True조건을 사용

				result+= rs.getInt("c_point"); 								// 내가 이수한  학점만 뽑아서 다 더함

			}
		
			System.out.println("졸업 학점 : " + tot2);
			System.out.println("이수 학점 : " + result);
			System.out.println("남은 학점 : " + (tot2-result));
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}