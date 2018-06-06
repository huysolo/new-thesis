package hcmut.thesis.backend.modelview;

public class UserUpload {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserUpload(String username, Integer userId) {
        this.username = username;
        this.userId = userId;
    }

    private String username;

    public UserUpload(String username, Integer userId, Integer studentId, Integer currentVersion) {
        this.username = username;
        this.userId = userId;
        this.studentId = studentId;
        this.currentVersion = currentVersion;
    }

    private Integer userId;
    private Integer studentId;

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    private Integer currentVersion;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
