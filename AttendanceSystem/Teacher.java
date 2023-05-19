public class Teacher {
    private String teacherID;
    private String teacherName;
    private String className;

    public Teacher(String teacherID, String teacherName, String className) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.className = className;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return teacherName;
    }
}