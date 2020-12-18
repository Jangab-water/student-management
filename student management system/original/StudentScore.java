package original;
import java.util.Scanner;

public class StudentScore {
	
	public void Scoremanage(){
		Scanner sc=new Scanner(System.in);
		System.out.println("학생 성적 관리창입니다. ");
		System.out.println("원하시는 서비스를 선택해주세요.");
		System.out.println("[1]학생별 성적 리스트    [2]학기별 성적 리스트   [3]뒤로가기");
		int menu=sc.nextInt();
		
		switch(menu)
		{
		case 1:
		{
			scoreList.manageStudentScore();
			break;
		}
		case 2:
		{
//			???
			break;
		}
		case 3:
		{
			System.out.println("뒤로 가기");
			break;
		}
		default:
		{
			System.out.println("잘못된 입력입니다.");
		}
		}
	}

}
