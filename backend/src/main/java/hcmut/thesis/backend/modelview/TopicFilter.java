package hcmut.thesis.backend.modelview;

public class TopicFilter {
    private Integer semesterNo;
    private Integer profId;
    private Boolean available;
    private Integer specialize;

    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Integer getProfId() {
        return profId;
    }

    public void setProfId(Integer profId) {
        this.profId = profId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getSpecialize() {
        return specialize;
    }

    public void setSpecialize(Integer specialize) {
        this.specialize = specialize;
    }
}
