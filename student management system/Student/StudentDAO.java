package Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {

	// declare loading driver static:한의석
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	// declare variable for connection static: 한의석
	public static final String URL = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	public static final String USER = "smhrd";
	public static final String PASSWORD = "hkread132";

	// declare getConnection Method return connection: 한의석
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}

	// Professor score student give: 한의석
	public void ProfessorScoreMain() {
		Scanner sc = new Scanner(System.in);
		System.out.println("본인의 학번 입력 >> ");
		int studentId = sc.nextInt();
		System.out.println("교수 이름 입력 >> ");
		String professorName = sc.next();
		System.out.println("교수한테 주실 점수 입력 >> ");
		int score = sc.nextInt();

		int cnt = UpdateProfessorScore(studentId, professorName, score);
		if (cnt > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}

	public int UpdateProfessorScore(int studentId, String professorName, int score) {
		String SQL = "update P_Score set p_score = ? where Professor_p_name = ? and Student_s_id = ?";
		Connection conn = null;
		int cnt = -1;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, score);
			ps.setString(2, professorName);
			ps.setInt(3, studentId);
			cnt = ps.executeUpdate();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// remain score for graduate: 문지순
	public void Graduate_score() {
		Connection conn = null;

	}

	// 남은 학점 계산기: 문지순
	public void ScoreCal() {
		Scanner sc = new Scanner(System.in);
		System.out.print("학번 입력 : ");
		int stt_pc = sc.nextInt();
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and "
				+ "c.c_name=a.course_c_name and c.c_id=a.course_c_id" + "	and b.s_id= ?";

		Connection conn = null;

		try {
			conn = getConnection();

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, stt_pc);
			ResultSet rs = ps.executeQuery();
			int tot2 = 20; // 졸업 학점 고정!
			int result = 0; // 남은 학점 초기화

			while (rs.next()) { // 데이터가 몇개있는지 모르니 while문에 rs.next()라는 True조건을 사용
				result += rs.getInt("c_point"); // 내가 이수한 학점만 뽑아서 다 더함
			}

			System.out.println("졸업 학점 : " + tot2);
			System.out.println("이수 학점 : " + result);
			System.out.println("남은 학점 : " + (tot2 - result));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 학생 성적 리스트 확인: 문지순
	public void StudentScoreList() {
		// 학생 성적 리스트
		
		Scanner sc = new Scanner(System.in);
		System.out.print("학번 입력 : ");
		int st_id = sc.nextInt();
		
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and c.c_name=a.course_c_name and b.s_id= ?";
		Connection conn = null;
			
		
		try {
			conn=getConnection();
//			System.out.println("접속 성공!");
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

			
			// String 과목명으로 몇글자까지 탭 몇번 맞추기
			
			for (int i=0; i<alist.size(); i++) {
				
				System.out.print(clist.get(i)+"   ");					//과목
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
