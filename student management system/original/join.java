package original;
import java.util.Scanner;

public class join {

	public static void join_ps() {
		Scanner sc = new Scanner(System.in);
		
		// 회원가입
		while (true) {
			System.out.println("────────────회원 가입 방식을 선택해주세요────────────");
			System.out.println("[1]학생용   [2]교수용 >> ");
			int menu = sc.nextInt();

			if (y == c) {

				int cnt = student_0.student();// 학생정보입력화면
				if (cnt == -1) {
					System.out.println("아이디가 중복 되었습니다.");
				}
			} else if (y == d) {
				int dnt = professor_0.professor();

				if (dnt == -1) {
					System.out.println("아이디가 중복 되었습니다.");
				}

			}

			else {
				System.out.println(" 잘못 입력 하셨습니다");
				System.out.println(" 다시 한번 입력해 주세요 ");
				System.out.println(" ");

			}

		}
	}

}
