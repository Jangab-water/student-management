package Professor;

import java.sql.*;
import java.util.*;



public class ProfessorDAO {

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

	// connection 변수: 한의석
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}
	
	// 학생점수입력: 한의석
	public int updateStudentScore(int studentId,  String professorName, int score) {
		Connection conn = null;
		int cnt = -1;
		try {
			conn = getConnection();
			String SQL = "update S_Score set s_score = ? where Professor_p_name = ? and Student_s_id = ?";
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
	// 교수평가점수입력: 한의석
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

	// 교수가 학생이 준 점수 불러오기2 : 한의석
	public List<ProfessorScoreVO> SelectProfessorScore() {
		String sql = "select * from p_score where professor_p_name = ?"; // 교수 이름을 기준으로 평점 출력
		Scanner sc = new Scanner(System.in);
		List<ProfessorScoreVO> professorScore = new ArrayList<ProfessorScoreVO>();
		Connection conn = null;
		conn = getConnection();
		System.out.println("확인할 교수 이름을 입력하세요.");
		String name = sc.next();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name); // SQL문에 해당 교수 이름이 들어감
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int student_s_id = rs.getInt("student_s_id");
				String student_s_name = rs.getString("student_s_name");
				String professor_p_name = rs.getString("professor_p_name");
				int course_c_id = rs.getInt("course_c_id");
				String course_c_name = rs.getString("course_c_name");
				String p_score = rs.getString("p_score");

				ProfessorScoreVO vo = new ProfessorScoreVO(student_s_id, student_s_name, professor_p_name, course_c_id,
						course_c_name, p_score);
				

				professorScore.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Method result array: professorScore
		return professorScore;
	}
	// 교수가 학생이 준 점수 출력하기1 :한의석
	public void ScoreView() {
		List<ProfessorScoreVO> cnt1 = SelectProfessorScore();
		for (int i = 0; i < cnt1.size(); i++) {
			System.out.println(cnt1.get(i));
		}
	}

	// 학생정보 출력: 류지형
	public void studentInfo() {
		Connection conn = null;
		conn = getConnection();
		try {

			String sql = "select * from student";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// 학생 리스트 출력
			System.out.println("==========학생 리스트==========");

			// 출력문 실행
			while (rs.next()) {
				int student_id = rs.getInt("s_id");
				String student_name = rs.getString("s_name");
				String student_addr = rs.getString("s_addr");
				String student_tel = rs.getString("s_tel");
				System.out.format("%d\t %s\t %s\t %s\n", student_id, student_name, student_addr, student_tel);
			}
			System.out.println("============================");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// !학번으로 학생점수입력: 남현웅
	public void manageStudentScore() {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		conn=getConnection();
		try {
			String sql = "select * from student order by s_id";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// 학생 리스트 출력
			System.out.println("==========학생 리스트==========");
			// 출력문 실행
			while (rs.next()) {
				int student_id = rs.getInt("s_id");
				String student_name = rs.getString("s_name");
				System.out.format("%d\t %s\n", student_id, student_name);
			}
			System.out.println("============================");

			System.out.print("찾고자 하는 학생의 학번을 입력하세요 >> ");
			int inputStudentId = scan.nextInt();

			String sql2 = "select s_id, s_name from student where s_id=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, inputStudentId);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next() == false) {
				System.out.println("찾으시는 학생이 없습니다");

			} else {
				int student_id = rs2.getInt("s_id");
				String student_name = rs2.getString("s_name");
				System.out.format("찾으시는 학생의 학번과 이름은 %d, %s입니다.\n", student_id, student_name);

				String sql3 = "select s_score, course_c_name from s_score where student_s_id =?";
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setInt(1, inputStudentId);
				ResultSet rs3 = ps3.executeQuery();
				System.out.println("검색결과 >> ");
				if(rs3.next()==false) {
					System.out.println("해당 학생은 이번학기에 수강하지 않았습니다.");
				}
				while (rs3.next()) {
					int s_score = rs3.getInt("s_score");
					String course_c_name = rs3.getString("course_c_name");
					System.out.println(course_c_name + "의 점수는" + s_score + "점 입니다.");
					System.out.print("학생의 성적을 변경하시겠습니까?  [Y/N] >>");
					String YorN=scan.next();
					if(YorN.toUpperCase().equals("Y"))
					{
						ChangeScore(student_id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 학생 성적 바꾸기: 남현웅
	public void ChangeScore(int student_s_id) {
		Scanner sc = new Scanner(System.in);
		Connection conn=null;
		String sql = "update s_score set s_score= ? where student_s_id =? and course_c_name = ?";
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int cnt = 1;
			while(cnt==1) {
			System.out.print("다음 성적 중 변경하고자 하는 과목을 선택하세요 >> ");
			String course = sc.next();
			System.out.print("수정할 점수를 입력하세요 >> ");
			int score = sc.nextInt();
			ps.setInt(1, score);
			ps.setInt(2, student_s_id);
			ps.setNString(3, course);
			ResultSet rs = ps.executeQuery();
			System.out.println("수정이 완료되었습니다.");
			
			System.out.print("계속 진행하시겠습니까?  (Y/N) >> ");
			String str = sc.next();
			if(str.toUpperCase().equals("Y")) {
				continue;
			}else if(str.toUpperCase().equals("N")) {
				System.out.println("수정 프로그램을 종료합니다.");
				break;
				
			}
			if(cnt==0) {
				break;
			}
				
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
//	// 학생 성적 바꾸기: 남현웅 복사본
//		public void ChangeScore1() {
//			Scanner sc = new Scanner(System.in);
//			Connection conn=null;
//			String sql = "update s_score set s_score= ? where student_s_id =? and course_c_name = ?";
//			try {
//				conn = getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql);
//				int cnt = 1;
//				while(cnt==1) {
//				System.out.print("성적을 변경하고자 하는 학생의 학번을 입력하세요 >> ");
//				int id = sc.nextInt();
//				System.out.print("다음 성적 중 변경하고자 하는 과목을 선택하세요 >> ");
//				String course = sc.next();
//				System.out.print("수정할 점수를 입력하세요 >> ");
//				int score = sc.nextInt();
//				ps.setInt(1, score);
//				ps.setInt(2, id);
//				ps.setNString(3, course);
//				ResultSet rs = ps.executeQuery();
//				System.out.println("수정이 완료되었습니다.");
//				
//				System.out.print("계속 진행하시겠습니까?  (Y/N) >> ");
//				String str = sc.next();
//				if(str.toUpperCase().equals("Y")) {
//					continue;
//				}else if(str.toUpperCase().equals("N")) {
//					System.out.println("수정 프로그램을 종료합니다.");
//					break;
//					
//				}
//				if(cnt==0) {
//					break;
//				}
//					
//				
//				}
//			} catch (Exception e) {
//				
//				e.printStackTrace();
//			}
//			
//		}
}
	
