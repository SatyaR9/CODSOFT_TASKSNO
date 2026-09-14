import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int regCount;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.regCount = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSlots() {
        return capacity - regCount;
    }

    public boolean registerStudent() {
        if (regCount < capacity) {
            regCount++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (regCount > 0) {
            regCount--;
            return true;
        }
        return false;
    }

    public void displayDetails() {
        System.out.printf("[%s] %s%n", code, title);
        System.out.printf("   Description : %s%n", description);
        System.out.printf("   Schedule    : %s%n", schedule);
        System.out.printf("   Available   : %d / %d slots%n", getAvailableSlots(), capacity);
    }
}


class Student {
    private String stdID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String stdID, String name) {
        this.stdID = stdID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return stdID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("Error: Already registered for " + course.getCode());
            return false;
        }
        if (course.registerStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
            return true;
        } else {
            System.out.println("Registration failed: " + course.getCode() + " is full.");
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            System.out.println("Error: You are not enrolled in " + course.getCode());
            return false;
        }
        if (course.dropStudent()) {
            registeredCourses.remove(course);
            System.out.println("Successfully dropped " + course.getTitle());
            return true;
        }
        return false;
    }

    public void displayRegisteredCourses() {
        System.out.println("\n--- Registered Courses for " + name + " (" + stdID + ") ---");
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("- [" + course.getCode() + "] " + course.getTitle());
            }
        }
    }
}


public class CourseRegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        courseDatabase = new ArrayList<>();
        studentDatabase = new ArrayList<>();
        scanner = new Scanner(System.in);
        seedInitialData();
    }

    private void seedInitialData() {
        courseDatabase.add(new Course("CS101", "Data Structures", "Learn arrays, lists, and trees", 3, "Mon/Wed 10:00 AM"));
        courseDatabase.add(new Course("CS102", "Algorithms", "Sorting, searching, and dynamic programming", 2, "Tue/Thu 02:00 PM"));
        courseDatabase.add(new Course("CS103", "Database Systems", "Relational databases and SQL", 3, "Fri 09:00 AM"));

        studentDatabase.add(new Student("S101", "Alex Smith"));
        studentDatabase.add(new Student("S102", "Jordan Lee"));
    }

    public void start() {
        boolean exit = false;
        System.out.println("=== STUDENT COURSE REGISTRATION SYSTEM ===");

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a valid number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    handleRegistration();
                    break;
                case 3:
                    handleDrop();
                    break;
                case 4:
                    handleViewRegistered();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Select between 1 and 5.");
            }
        }
        scanner.close();
    }

    private void displayCourses() {
        System.out.println("\n=== AVAILABLE COURSES ===");
        for (Course course : courseDatabase) {
            course.displayDetails();
            System.out.println("----------------------------");
        }
    }

    private Student findStudent(String id) {
        for (Student s : studentDatabase) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private Course findCourse(String code) {
        for (Course c : courseDatabase) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    private void handleRegistration() {
        System.out.print("Enter Student ID: ");
        String stdID = scanner.nextLine().trim();
        Student student = findStudent(stdID);

        if (student == null) {
            System.out.println("Student ID not found!");
            return;
        }

        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.nextLine().trim();
        Course course = findCourse(courseCode);

        if (course == null) {
            System.out.println("Course code not found!");
            return;
        }

        student.registerCourse(course);
    }

    private void handleDrop() {
        System.out.print("Enter Student ID: ");
        String stdID = scanner.nextLine().trim();
        Student student = findStudent(stdID);

        if (student == null) {
            System.out.println("Student ID not found!");
            return;
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine().trim();
        Course course = findCourse(courseCode);

        if (course == null) {
            System.out.println("Course code not found!");
            return;
        }

        student.dropCourse(course);
    }

    private void handleViewRegistered() {
        System.out.print("Enter Student ID: ");
        String stdID = scanner.nextLine().trim();
        Student student = findStudent(stdID);

        if (student == null) {
            System.out.println("Student ID not found!");
            return;
        }

        student.displayRegisteredCourses();
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.start();
    }
}