
package original;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	public static void main(String[] args) {

		
		StudentScoreList();
	}

	private static void StudentScoreList() {
		// 학생 성적 리스트
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("학번 입력 : ");
		int st_id = sc.nextInt();

		
		String url = "jdbc:oracle:thin:@smhrdai.ca6ktgfa4m7b.us-east-2.rds.amazonaws.com:1521/ORCL";// IP, 포트정보, DB이름
		String id = "smhrd";
		String pw = "rha5164508";	
		
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and c.c_name=a.course_c_name and b.s_id= ?";
			
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공!");
			System.out.println();

			PreparedStatement ps = conn.prepareStatement(SQL); 
			ps.setInt(1, st_id);
			ResultSet rs = ps.executeQuery();
			
			// 학점으로 과목 출력. 점수 나오게 하기
			System.out.println("         과목                " + "점수");
			System.out.println("─────────────────────────");
			
			String stt_sc = null; 
			String stt_cn = null;
			int tot = 0;
			
			ArrayList<Integer> alist = new ArrayList<Integer>();
			ArrayList clist = new ArrayList<>(); 
			while (rs.next()) { // 데이터가 몇개있는지 모르니 while문에 rs.next()라는 True조건을 사용

				stt_cn = rs.getString("c_name"); 						// 과목 
				stt_sc = rs.getString("s_score");						// 점수

				clist.add(stt_cn);  									// ArrayList 과목
				alist.add(Integer.parseInt(stt_sc));  					// ArrayList 점수
			}

			
			//String 과목명으로 몇글자까지 탭 몇번 맞추기
			
			for (int i=0; i<alist.size(); i++) {
				
				System.out.print(clist.get(i));					//과목
				System.out.print(alist.get(i));										//점수
				System.out.println();	
				tot += alist.get(i);												//총점 계산식
			}	
			System.out.println("─────────────────────────");
			System.out.println("\t    총점 : " + tot);								//총점
			System.out.println("\t    평균 : " + tot/clist.size());					//평균

 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}