package original;


import java.util.List;
import java.util.Scanner;

public class ProfessorScoreMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �й� �Է� >> ");
		int studentId = sc.nextInt();
		System.out.println("���� �̸� �Է� >> ");
		String professorName = sc.next();
		System.out.println("�������� �ֽ� ���� �Է� >> ");
		int score = sc.nextInt();
		
		ProfessorScore ps = new ProfessorScore();
		int cnt = ps.UpdateProfessorScore(studentId, professorName, score);
		if(cnt > 0) {
			System.out.println("����");
		}else {
			System.out.println("����");
		}
		
		ProfessorScore ps1 = new ProfessorScore();
		List<ProfessorScoreVO> cnt1 = ps1.SelectProfessorScore();
		for (int i = 0; i < cnt1.size(); i++) {
			System.out.println(cnt1.get(i));
//
//			
//			
//			
//		} 
		
	}
}


