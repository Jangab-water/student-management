package Student;

import java.util.Scanner;

public class StudentMenu {

	public void StudentMenu() {
		Scanner scan = new Scanner(System.in);
		StudentDAO st = new StudentDAO();

		
		loop1:
		while (true) {
			System.out.println("─────────────────────────");
			System.out.println("[1]수업만족도 평가   [2]본인 성적 확인   [3]졸업학점   [4]뒤로가기");
			int menuSel = scan.nextInt();
			
			switch (menuSel) {
			case 1: {
				st.ProfessorScoreMain();
				// 수정
				break;
			}
			case 2: {
				System.out.println("본인 성적 확인");
				st.StudentScoreList();
				break;
			}
			case 3: {
				System.out.println("졸업학점확인");
				st.ScoreCal();
				// 미완
				break;
			}
			case 4: {
				System.out.println("뒤로가기");
				break loop1;
			}
			default: {
				System.out.println("잘못된 입력입니다.");
			}
			
			}

		}
	}

}
