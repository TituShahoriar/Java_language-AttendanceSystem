import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class AttendanceProgram {
    static Map<String, Teacher> teachers = new HashMap<>();
    static Map<String, List<Student>> classes = new HashMap<>();

    public static void main(String[] args) {
        // Initialize teachers
        teachers.put("ENG001", new Teacher("Mr. John Smith", "ENG001", "7A"));
        teachers.put("MATH002", new Teacher("Ms. Emily Johnson", "MATH002", "7B"));
        teachers.put("SCI003", new Teacher("Mr. David Anderson", "SCI003", "7C"));
        teachers.put("SS004", new Teacher("Mrs. Sarah Davis", "SS004", "7D"));
        teachers.put("CS005", new Teacher("Mr. Michael Brown", "CS005", "7E"));
        teachers.put("PE006", new Teacher("Ms. Jessica Wilson", "PE006", "7F"));
        teachers.put("ART007", new Teacher("Mrs. Samantha Taylor", "ART007", "7G"));
        teachers.put("MUS008", new Teacher("Mr. Christopher Martin", "MUS008", "7H"));
        teachers.put("BEN009", new Teacher("Ms. Priya Rahman", "BEN009", "7I"));
        teachers.put("ISL010", new Teacher("Mr. Mohammed Ali", "ISL010", "7J"));

        // Initialize students
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Anderson", "701"));
        students.add(new Student("Emily Wilson", "702"));
        students.add(new Student("David Johnson", "703"));
        students.add(new Student("Sarah Davis", "704"));
        students.add(new Student("Michael Smith", "705"));
        students.add(new Student("Jessica Brown", "706"));
        students.add(new Student("Christopher Taylor", "707"));
        students.add(new Student("Samantha Martin", "708"));
        students.add(new Student("Priya Rahman", "709"));
        students.add(new Student("Mohammed Ali", "710"));
        students.add(new Student("Emma Anderson", "711"));
        students.add(new Student("Daniel Wilson", "712"));
        students.add(new Student("Olivia Johnson", "713"));
        students.add(new Student("Ethan Davis", "714"));
        students.add(new Student("Ava Smith" , "715"));
        students.add(new Student("Noah Brown", "716"));
        students.add(new Student("Mia Taylor", "717"));
        students.add(new Student("James Martin", "718"));
        students.add(new Student("Sophia Rahma", "719"));
        students.add(new Student("Benjamin Ali", "720"));
        students.add(new Student("Isabella Anderson", "721"));
        students.add(new Student("Alexander Wilson", "722"));

// Assign all students to class 7A
        classes.put("7A", students);

        Scanner scanner = new Scanner(System.in);

// Sign in
        System.out.println("Enter teacher ID:");
        String teacherId = scanner.nextLine();
        System.out.println("Enter teacher name:");
        String teacherName = scanner.nextLine();

        Teacher teacher = teachers.get(teacherId);
        if (teacher == null || !teacher.name.equals(teacherName)) {
            System.out.println("Invalid teacher ID or name.");
            return;
        }

// Input class and section
        System.out.println("Enter class and section (e.g. 7A):");
        String className = scanner.nextLine();

        if (!teacher.className.equals(className)) {
            System.out.println("You are not assigned to this class.");
            return;
        }

        List<Student> classStudents = classes.get(className);
        if (classStudents == null) {
            System.out.println("Invalid class.");
            return;
        }

// Take attendance
        Map<String, Boolean> attendance = new HashMap<>();
        for (Student student : classStudents) {
            System.out.println(student.name + " (" + student.id + "): Present (Y/N)?");
            String present = scanner.nextLine();
            attendance.put(student.id, present.equalsIgnoreCase("Y"));
        }

// Show absent students
        List<Student> absentStudents = new ArrayList<>();
        for (Student student : classStudents) {
            if (!attendance.get(student.id)) {
                absentStudents.add(student);
            }
        }
        System.out.println(absentStudents.size() + " student(s) absent:");
        for (Student student : absentStudents) {
            System.out.println(student.name + " (" + student.id + ")");
        }
    }
}