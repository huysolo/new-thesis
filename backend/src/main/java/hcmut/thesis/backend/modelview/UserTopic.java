package hcmut.thesis.backend.modelview;

public class UserTopic {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(Boolean teamlead) {
        this.teamlead = teamlead;
    }

    public UserTopic(String name, Integer studentId, Boolean teamlead) {
        this.name = name;
        this.studentId = studentId;
        this.teamlead = teamlead;
    }

    private String name;
    private Integer studentId;
    private Boolean teamlead;
}
