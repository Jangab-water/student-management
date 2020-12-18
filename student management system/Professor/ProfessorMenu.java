package Professor;

import java.util.Scanner;

public class ProfessorMenu {

	public void ProfessorMenu() {

		Scanner scan = new Scanner(System.in);
		ProfessorDAO pf = new ProfessorDAO();


		loop1: while (true) {
			System.out.println("[1]수업평가점수 보기     [2]학적보기     [3]학생 성적관리     [4]뒤로가기");
			int menuSel = scan.nextInt();
			switch (menuSel) {
			case 1: {
				System.out.println("수업평가점수 확인");
				pf.ScoreView();
					break;
				}
			
			case 2: {
				System.out.println("학생 정보 보기");
				pf.studentInfo();
				break;
			}
			case 3: {
				System.out.println("학생 성적 관리");
//				pf.manageStudentScore();
//				System.out.print("학번 입력 >>");
//				int studentId=scan.nextInt();
//				System.out.print("교수 이름 입력 >>");
//				String professorName=scan.next();
//				System.out.print("점수 입력>>");
//				int score=scan.nextInt();
//				pf.UpdateProfessorScore(studentId, professorName, score);
				pf.manageStudentScore();
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
