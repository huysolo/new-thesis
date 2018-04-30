package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Professor;

public class ProfInfo {
    private Professor professor;
    private String name;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
