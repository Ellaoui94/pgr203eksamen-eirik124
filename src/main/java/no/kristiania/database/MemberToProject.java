package no.kristiania.database;

public class MemberToProject {

    private String status;
    private String projectMemberName;
    private String projectName;
    private Long id;
    private String taskName;
    private String description;
    private int projectId;
    private int taskId;

    //GETTERS

    public String getStatus() {
        return status;
    }

    public String getProjectMemberName() {
        return projectMemberName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    //SETTERS


    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectMemberName(String projectMemberName) {
        this.projectMemberName = projectMemberName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }
}
