package original; //�л� ȸ������

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//�л���

public class student_0 {
	


	public static int student() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("�й��Է��� �Է��ϼ��� >>");
		int S_ID =sc.nextInt();
		System.out.println("�̸��Է��� �Է��ϼ��� >>");
		String S_NAME =sc.next();
		System.out.println("�г��� �Է��ϼ��� >>");
		String S_GRADE =sc.next();
		System.out.println("��ȭ��ȣ�� �Է��ϼ��� >>");
		String S_TEL =sc.next();
		System.out.println("�ּҸ� �Է��ϼ��� >>");
		String S_ADDR=sc.next();
		System.out.println("�а��̸� >>");
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
			
			// �Ķ��Ÿ�� ���Ե� SQL ����
			String SQL ="insert into STUDENT values(?,?,?,?,?,?)";
				
			// SQL ������ �����ϴ� ��ü�� ���� (Statement - ���� )
			// Statement -> �ϼ��� SQL ���常 ���� �����ϴ�.
			// �Ķ���Ͱ� ���c�� SQL ������ �����Ҽ� �ִ� ��ü (PreparedStatement)
			
			PreparedStatement ps=conn.prepareStatement(SQL);
			// PreparedStatement        // 
	        //        d -> �������� �ٵ�   ps = 
			ps.setInt(1, S_ID);
			ps.setString(2, S_NAME);
			ps.setString(3, S_GRADE);
			ps.setString(4, S_TEL);
			ps.setString(5,S_ADDR);
			ps.setString(6, DEPARTMENT_D_NAME );
	   	    cnt=ps.executeUpdate(); //�ϼ��� SQL�� ���� -> ������ �ȵǰ� (PreparedStatement���� �������߱⋚����) �ٷ� ����
			
			if(cnt == 1) {System.out.println("����Ǿ����ϴ�");}
			else {System.out.println("������� �ʾҽ��ϴ�");}
						
		} catch (Exception e) {
	
			e.printStackTrace();
		}
	   return cnt;	
	}

}

	
	
	
	
	


