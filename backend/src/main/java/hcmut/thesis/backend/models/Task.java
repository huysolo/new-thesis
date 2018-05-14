package hcmut.thesis.backend.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Task {
    private int idTask;
    private String title;
    private String description;
    private Timestamp deadline;
    private Integer idTopicSem;
    private int submit;
    private int pass;
    private Integer currentVersion;
    private Timestamp updateTime;

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id_task")
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "deadline")
    public Timestamp getDeadline() {
        return deadline;
    }
    
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "id_topic_sem")
    public Integer getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(Integer idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Basic
    @Column(name = "submit")
    public int getSubmit() {
        return this.submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }

    @Basic
    @Column(name = "pass")
    public int getPass() {
        return this.pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(deadline, task.deadline) &&
                Objects.equals(idTopicSem, task.idTopicSem) &&
                Objects.equals(submit, task.submit) &&
                Objects.equals(pass, task.pass);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, title, description, deadline, submit, pass, idTopicSem);
    }

    @Basic
    @Column(name = "current_version")
    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }


    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    private String topicName;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }


}
