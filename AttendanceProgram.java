import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AttendanceProgram {
    static Map<String, Teacher> teachers = new HashMap<>();
    static Map<String, List<Student>> classes = new HashMap<>();
    static Map<String, Integer> consecutiveAbsences = new HashMap<>();

    public static void main(String[] args) {
        // Initialize teachers
        teachers.put("ENG001", new Teacher("ENG001", "Mr. John Smith", "7", "A"));
        teachers.put("MATH002", new Teacher("MATH002", "Ms. Emily Johnson", "7", "B"));
        teachers.put("SCI003", new Teacher("SCI003", "Mr. David Anderson", "7", "C"));
        teachers.put("SS004", new Teacher("SS004", "Mrs. Sarah Davis", "7", "D"));
        teachers.put("CS005", new Teacher("CS005", "Mr. Michael Brown", "7", "E"));
        teachers.put("PE006", new Teacher("PE006", "Ms. Jessica Wilson", "7", "F"));
        teachers.put("ART007", new Teacher("ART007", "Mrs. Samantha Taylor", "7", "G"));
        teachers.put("MUS008", new Teacher("MUS008", "Mr. Christopher Martin", "7", "H"));
        teachers.put("BEN009", new Teacher("BEN009", "Ms. Priya Rahman", "7", "I"));
        teachers.put("ISL010", new Teacher("ISL010" , "Mr. Mohammed Ali", "7", "J"));

// Initialize students
        List<Student> students = new ArrayList<>();
        students.add(new Student("701", "John Anderson"));
        students.add(new Student("702", "Emily Wilson"));
        students.add(new Student("703", "David Johnson"));
        students.add(new Student("704", "Sarah Davis"));
        students.add(new Student("705", "Michael Smith"));
        students.add(new Student("706", "Jessica Brown"));
        students.add(new Student("707", "Christopher Taylor"));
        students.add(new Student("708", "Samantha Martin"));
        students.add(new Student("709", "Priya Rahman"));
        students.add(new Student("710", "Mohammed Ali"));
        students.add(new Student("711", "Emma Anderson"));
        students.add(new Student("712", "Daniel Wilson"));
        students.add(new Student("713", "Olivia Johnson"));
        students.add(new Student("714", "Ethan Davis"));
        students.add(new Student("715", "Ava Smith"));
        students.add(new Student("716", "Noah Brown"));
        students.add(new Student("717", "Mia Taylor"));
        students.add(new Student("718", "James Martin"));
        students.add(new Student("719", "Sophia Rahman"));
        students.add(new Student("720", "Benjamin Ali"));
        students.add(new Student("721", "Isabella Anderson"));
        students.add(new Student("722", "Alexander Wilson"));

// Assign all students to class 7A
        classes.put("7A", students);

        Scanner scanner = new Scanner(System.in);

// Sign in
        System.out.println("Enter teacher ID:");
        String teacherId = scanner.nextLine();
        System.out.println("Enter teacher name:");
        String teacherName = scanner.nextLine();

        Teacher teacher = teachers.get(teacherId);
        if (teacher == null || !teacher.teacherName.equals(teacherName)) {
            System.out.println("Invalid teacher ID or name.");
            return;
        }

// Input class and section
        System.out.println("Enter class:");
        String className = scanner.nextLine();
        System.out.println("Enter section:");
        String sectionName = scanner.nextLine();

        if (!teacher.className.equals(className) || !teacher.sectionName.equals(sectionName)) {
            System.out.println("You are not assigned to this class and section.");
            return;
        }

        List<Student> classStudents = classes.get(className + sectionName);
        if (classStudents == null) {
            System.out.println("Invalid class and section.");
            return;
        }

// Take attendance
        Map<String, Boolean> attendance = new HashMap<>();
        for (Student student : classStudents) {
            System.out.println(student.studentName + " (" + student.studentID + "): Present (Y/N)?");
            String present = scanner.nextLine();
            attendance.put(student.studentID, present.equalsIgnoreCase("Y"));

            if (present.equalsIgnoreCase("N")) {
                consecutiveAbsences.put(student.studentID,
                        consecutiveAbsences.getOrDefault(student.studentID, 0) + 1);
            } else {
                consecutiveAbsences.put(student.studentID, 0);
            }
        }

// Show absent students
        List<Student> absentStudents = new ArrayList<>();
        for (Student student : classStudents) {
            if (!attendance.get(student.studentID)) {
                absentStudents.add(student);

                if (consecutiveAbsences.get(student.studentID) >= 3) {
                    System.out.println(student.studentName + " (" + student.studentID + "): ABSENT FOR 3 CONSECUTIVE DAYS!");
                }
            }
        }
        System.out.println(absentStudents.size() + " student(s) absent:");
        for (Student student : absentStudents) {
            System.out.println(student.studentName + " (" + student.studentID + ")");
        }
    }
}
