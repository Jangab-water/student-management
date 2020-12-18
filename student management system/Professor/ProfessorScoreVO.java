package Professor;

public class ProfessorScoreVO {
	int sudent_s_id;
    String sudent_s_name;
    String professor_p_name;
    int course_c_id;
    String course_c_name;
    String p_score;
	public ProfessorScoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProfessorScoreVO(int sudent_s_id, String sudent_s_name, String professor_p_name, int course_c_id,
			String course_c_name, String p_score) {
		super();
		this.sudent_s_id = sudent_s_id;
		this.sudent_s_name = sudent_s_name;
		this.professor_p_name = professor_p_name;
		this.course_c_id = course_c_id;
		this.course_c_name = course_c_name;
		this.p_score = p_score;
	}
	public int getSudent_s_id() {
		return sudent_s_id;
	}
	public void setSudent_s_id(int sudent_s_id) {
		this.sudent_s_id = sudent_s_id;
	}
	public String getSudent_s_name() {
		return sudent_s_name;
	}
	public void setSudent_s_name(String sudent_s_name) {
		this.sudent_s_name = sudent_s_name;
	}
	public String getProfessor_p_name() {
		return professor_p_name;
	}
	public void setProfessor_p_name(String professor_p_name) {
		this.professor_p_name = professor_p_name;
	}
	public int getCourse_c_id() {
		return course_c_id;
	}
	public void setCourse_c_id(int course_c_id) {
		this.course_c_id = course_c_id;
	}
	public String getCourse_c_name() {
		return course_c_name;
	}
	public void setCourse_c_name(String course_c_name) {
		this.course_c_name = course_c_name;
	}
	public String getP_score() {
		return p_score;
	}
	public void setP_score(String p_score) {
		this.p_score = p_score;
	}
	@Override
	public String toString() {
		return "학번: " + sudent_s_id + "   이름: " + sudent_s_name
			+"   과목: "+ course_c_name + "     평가점수: " + p_score;
	}
    
    
}
