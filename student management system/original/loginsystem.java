package original;

import java.util.Scanner;

public class loginsystem {

	public void login() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("────────────로그인 방식을 선택해주세요────────────");
			System.out.print("[1]학생용   [2]교수용 >> ");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("────학생용──── ");

				System.out.print(" ID를 입력해주세요 >>");
				int id_st = sc.nextInt();
				int cnt = select_stdunt_login.student_login(id_st);
				if (cnt > 0) {
				} else {
					System.out.println("로그인 실패. 다시입력해주세요.");
				}

			} else if (menu == 2) {
				System.out.println("────교수용────");
				System.out.print(" ID를 입력해주세요 >> ");
				int id_pf = sc.nextInt();
				int cnt = select_professor_login.professor_login(id_pf);
				if (cnt > 0) {
				} else {
					System.out.println("로그인 실패  다시입력해주세요 >>");
				}
			} else {
				System.out.println(" 잘못 입력 하셨습니다");
				System.out.println(" 다시 한번 입력해 주세요");

			}

		}
	}
}
