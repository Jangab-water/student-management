package Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {

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

	// declare getConnection Method return connection: ���Ǽ�
	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return conn;
	}

	// Professor score student give: ���Ǽ�
	public void ProfessorScoreMain() {
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �й� �Է� >> ");
		int studentId = sc.nextInt();
		System.out.println("���� �̸� �Է� >> ");
		String professorName = sc.next();
		System.out.println("�������� �ֽ� ���� �Է� >> ");
		int score = sc.nextInt();

		int cnt = UpdateProfessorScore(studentId, professorName, score);
		if (cnt > 0) {
			System.out.println("����");
		} else {
			System.out.println("����");
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

	// remain score for graduate: ������
	public void Graduate_score() {
		Connection conn = null;

	}

	// ���� ���� ����: ������
	public void ScoreCal() {
		Scanner sc = new Scanner(System.in);
		System.out.print("�й� �Է� : ");
		int stt_pc = sc.nextInt();
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and "
				+ "c.c_name=a.course_c_name and c.c_id=a.course_c_id" + "	and b.s_id= ?";

		Connection conn = null;

		try {
			conn = getConnection();

			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setInt(1, stt_pc);
			ResultSet rs = ps.executeQuery();
			int tot2 = 20; // ���� ���� ����!
			int result = 0; // ���� ���� �ʱ�ȭ

			while (rs.next()) { // �����Ͱ� ��ִ��� �𸣴� while���� rs.next()��� True������ ���
				result += rs.getInt("c_point"); // ���� �̼��� ������ �̾Ƽ� �� ����
			}

			System.out.println("���� ���� : " + tot2);
			System.out.println("�̼� ���� : " + result);
			System.out.println("���� ���� : " + (tot2 - result));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �л� ���� ����Ʈ Ȯ��: ������
	public void StudentScoreList() {
		// �л� ���� ����Ʈ
		
		Scanner sc = new Scanner(System.in);
		System.out.print("�й� �Է� : ");
		int st_id = sc.nextInt();
		
		String SQL = "select * from s_score a, student b, course c where a.student_s_id=b.s_id and c.c_name=a.course_c_name and b.s_id= ?";
		Connection conn = null;
			
		
		try {
			conn=getConnection();
//			System.out.println("���� ����!");
			System.out.println();

			PreparedStatement ps = conn.prepareStatement(SQL); 
			ps.setInt(1, st_id);
			ResultSet rs = ps.executeQuery();
			
			// �������� ���� ���. ���� ������ �ϱ�
			System.out.println("         ����                " + "����");
			System.out.println("��������������������������������������������������");
			
			String stt_sc = null; 
			String stt_cn = null;
			int tot = 0;
			
			ArrayList<Integer> alist = new ArrayList<Integer>();
			ArrayList clist = new ArrayList<>(); 
			while (rs.next()) { // �����Ͱ� ��ִ��� �𸣴� while���� rs.next()��� True������ ���

				stt_cn = rs.getString("c_name"); 						// ���� 
				stt_sc = rs.getString("s_score");						// ����

				clist.add(stt_cn);  									// ArrayList ����
				alist.add(Integer.parseInt(stt_sc));  					// ArrayList ����
			}

			
			// String ��������� ����ڱ��� �� ��� ���߱�
			
			for (int i=0; i<alist.size(); i++) {
				
				System.out.print(clist.get(i)+"   ");					//����
				System.out.print(alist.get(i));										//����
				System.out.println();	
				tot += alist.get(i);												//���� ����
			}	
			System.out.println("��������������������������������������������������");
			System.out.println("\t    ���� : " + tot);								//����
			System.out.println("\t    ��� : " + tot/clist.size());					//���

 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
