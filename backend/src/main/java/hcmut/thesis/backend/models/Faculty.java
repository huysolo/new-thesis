package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Faculty {
    private int idFaculty;
    private String name;

    @Id
    @Column(name = "id_faculty")
    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return idFaculty == faculty.idFaculty &&
                Objects.equals(name, faculty.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFaculty, name);
    }
}
