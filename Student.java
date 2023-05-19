class Student {
    String studentID;
    String studentName;
    boolean attendanceStatus;

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.attendanceStatus = true; // default to present
    }
}