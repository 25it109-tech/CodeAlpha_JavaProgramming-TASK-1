import java.util.*;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===\n");

        // Input number of students
        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Input student data
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter number of subjects: ");
            int numSubjects = scanner.nextInt();
            scanner.nextLine();

            double[] grades = new double[numSubjects];

            System.out.println("Enter grades for " + numSubjects + " subjects:");
            for (int j = 0; j < numSubjects; j++) {
                System.out.print("Subject " + (j + 1) + ": ");
                grades[j] = scanner.nextDouble();
            }
            scanner.nextLine(); // consume newline

            students.add(new Student(name, grades));
        }

        // Display Summary Report
        displayReport(students);

        scanner.close();
    }

    private static void displayReport(ArrayList<Student> students) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              STUDENT GRADE REPORT");
        System.out.println("=".repeat(50));

        double classTotal = 0;
        double highestOverall = Double.MIN_VALUE;
        double lowestOverall = Double.MAX_VALUE;
        String topStudent = "", bottomStudent = "";

        for (Student s : students) {
            s.displayInfo();
            
            classTotal += s.getAverage();
            
            if (s.getAverage() > highestOverall) {
                highestOverall = s.getAverage();
                topStudent = s.name;
            }
            if (s.getAverage() < lowestOverall) {
                lowestOverall = s.getAverage();
                bottomStudent = s.name;
            }
        }

        double classAverage = classTotal / students.size();

        System.out.println("\n" + "-".repeat(50));
        System.out.printf("Class Average       : %.2f%n", classAverage);
        System.out.printf("Highest Average     : %.2f (%s)%n", highestOverall, topStudent);
        System.out.printf("Lowest Average      : %.2f (%s)%n", lowestOverall, bottomStudent);
        System.out.println("=".repeat(50));
    }
}

class Student {
    String name;
    double[] grades;

    public Student(String name, double[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public double getAverage() {
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.length;
    }

    public double getHighest() {
        double max = grades[0];
        for (double g : grades) if (g > max) max = g;
        return max;
    }

    public double getLowest() {
        double min = grades[0];
        for (double g : grades) if (g < min) min = g;
        return min;
    }

    public void displayInfo() {
        System.out.printf("%-15s | Avg: %.2f | Highest: %.2f | Lowest: %.2f%n", 
            name, getAverage(), getHighest(), getLowest());
    }
}