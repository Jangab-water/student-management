package Professor;

import java.sql.*;
import java.util.*;



public class ProfessorDAO {

	// declare loading driver static:���Ǽ�
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	// declare variable for connection static: ���Ǽ�
	public static final String URL = "jdbc:oracle:thin:@smhrdai.cwnxiom872oa.ap-northeast-2.rds.amazonaws.com:1521/ORCL";
	public static final String USER = "smhrd";
	public static final String PASSWORD = "hkread132";

	// connection ����: ���Ǽ�
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}
	
	// �л������Է�: ���Ǽ�
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
	// �����������Է�: ���Ǽ�
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

	// ������ �л��� �� ���� �ҷ�����2 : ���Ǽ�
	public List<ProfessorScoreVO> SelectProfessorScore() {
		String sql = "select * from p_score where professor_p_name = ?"; // ���� �̸��� �������� ���� ���
		Scanner sc = new Scanner(System.in);
		List<ProfessorScoreVO> professorScore = new ArrayList<ProfessorScoreVO>();
		Connection conn = null;
		conn = getConnection();
		System.out.println("Ȯ���� ���� �̸��� �Է��ϼ���.");
		String name = sc.next();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name); // SQL���� �ش� ���� �̸��� ��
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
	// ������ �л��� �� ���� ����ϱ�1 :���Ǽ�
	public void ScoreView() {
		List<ProfessorScoreVO> cnt1 = SelectProfessorScore();
		for (int i = 0; i < cnt1.size(); i++) {
			System.out.println(cnt1.get(i));
		}
	}

	// �л����� ���: ������
	public void studentInfo() {
		Connection conn = null;
		conn = getConnection();
		try {

			String sql = "select * from student";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			// �л� ����Ʈ ���
			System.out.println("==========�л� ����Ʈ==========");

			// ��¹� ����
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
	
	// !�й����� �л������Է�: ������
	public void manageStudentScore() {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		conn=getConnection();
		try {
			String sql = "select * from student order by s_id";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// �л� ����Ʈ ���
			System.out.println("==========�л� ����Ʈ==========");
			// ��¹� ����
			while (rs.next()) {
				int student_id = rs.getInt("s_id");
				String student_name = rs.getString("s_name");
				System.out.format("%d\t %s\n", student_id, student_name);
			}
			System.out.println("============================");

			System.out.print("ã���� �ϴ� �л��� �й��� �Է��ϼ��� >> ");
			int inputStudentId = scan.nextInt();

			String sql2 = "select s_id, s_name from student where s_id=?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, inputStudentId);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next() == false) {
				System.out.println("ã���ô� �л��� �����ϴ�");

			} else {
				int student_id = rs2.getInt("s_id");
				String student_name = rs2.getString("s_name");
				System.out.format("ã���ô� �л��� �й��� �̸��� %d, %s�Դϴ�.\n", student_id, student_name);

				String sql3 = "select s_score, course_c_name from s_score where student_s_id =?";
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setInt(1, inputStudentId);
				ResultSet rs3 = ps3.executeQuery();
				System.out.println("�˻���� >> ");
				if(rs3.next()==false) {
					System.out.println("�ش� �л��� �̹��б⿡ �������� �ʾҽ��ϴ�.");
				}
				while (rs3.next()) {
					int s_score = rs3.getInt("s_score");
					String course_c_name = rs3.getString("course_c_name");
					System.out.println(course_c_name + "�� ������" + s_score + "�� �Դϴ�.");
					System.out.print("�л��� ������ �����Ͻðڽ��ϱ�?  [Y/N] >>");
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
	
	// �л� ���� �ٲٱ�: ������
	public void ChangeScore(int student_s_id) {
		Scanner sc = new Scanner(System.in);
		Connection conn=null;
		String sql = "update s_score set s_score= ? where student_s_id =? and course_c_name = ?";
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int cnt = 1;
			while(cnt==1) {
			System.out.print("���� ���� �� �����ϰ��� �ϴ� ������ �����ϼ��� >> ");
			String course = sc.next();
			System.out.print("������ ������ �Է��ϼ��� >> ");
			int score = sc.nextInt();
			ps.setInt(1, score);
			ps.setInt(2, student_s_id);
			ps.setNString(3, course);
			ResultSet rs = ps.executeQuery();
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			
			System.out.print("��� �����Ͻðڽ��ϱ�?  (Y/N) >> ");
			String str = sc.next();
			if(str.toUpperCase().equals("Y")) {
				continue;
			}else if(str.toUpperCase().equals("N")) {
				System.out.println("���� ���α׷��� �����մϴ�.");
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
	
//	// �л� ���� �ٲٱ�: ������ ���纻
//		public void ChangeScore1() {
//			Scanner sc = new Scanner(System.in);
//			Connection conn=null;
//			String sql = "update s_score set s_score= ? where student_s_id =? and course_c_name = ?";
//			try {
//				conn = getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql);
//				int cnt = 1;
//				while(cnt==1) {
//				System.out.print("������ �����ϰ��� �ϴ� �л��� �й��� �Է��ϼ��� >> ");
//				int id = sc.nextInt();
//				System.out.print("���� ���� �� �����ϰ��� �ϴ� ������ �����ϼ��� >> ");
//				String course = sc.next();
//				System.out.print("������ ������ �Է��ϼ��� >> ");
//				int score = sc.nextInt();
//				ps.setInt(1, score);
//				ps.setInt(2, id);
//				ps.setNString(3, course);
//				ResultSet rs = ps.executeQuery();
//				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
//				
//				System.out.print("��� �����Ͻðڽ��ϱ�?  (Y/N) >> ");
//				String str = sc.next();
//				if(str.toUpperCase().equals("Y")) {
//					continue;
//				}else if(str.toUpperCase().equals("N")) {
//					System.out.println("���� ���α׷��� �����մϴ�.");
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
	
