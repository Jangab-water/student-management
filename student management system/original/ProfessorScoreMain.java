package original;


import java.util.List;
import java.util.Scanner;

public class ProfessorScoreMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("본인의 학번 입력 >> ");
		int studentId = sc.nextInt();
		System.out.println("교수 이름 입력 >> ");
		String professorName = sc.next();
		System.out.println("교수한테 주실 점수 입력 >> ");
		int score = sc.nextInt();
		
		ProfessorScore ps = new ProfessorScore();
		int cnt = ps.UpdateProfessorScore(studentId, professorName, score);
		if(cnt > 0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
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


