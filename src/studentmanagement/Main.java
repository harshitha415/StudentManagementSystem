package studentmanagement;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        
        loadStudents(students);

        int choice = 0;

        while (choice != 6) {

            System.out.println("\n================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

            // ADD STUDENT
            case 1:

                Student student = new Student();

                System.out.print("Enter Student ID: ");
                student.id = sc.nextInt();

                boolean idExists = false;

                for (Student s : students) {

                    if (s.id == student.id) {

                        idExists = true;
                        break;
                    }
                }

                while (idExists) {

                    System.out.println("This Student ID already exists!");

                    System.out.print("Enter a different Student ID: ");
                    student.id = sc.nextInt();

                    idExists = false;

                    for (Student s : students) {

                        if (s.id == student.id) {

                            idExists = true;
                            break;
                        }
                    }
                }

                System.out.print("Enter Student Name: ");
                student.name = sc.next();

                System.out.print("Enter Age (1-100): ");
                student.age = sc.nextInt();

                while (student.age < 1 || student.age > 100) {

                    System.out.println("Invalid age! Please enter between 1 and 100.");

                    System.out.print("Enter Age (1-100): ");
                    student.age = sc.nextInt();
                }

                System.out.print("Enter Course: ");
                student.course = sc.next();

                System.out.print("Enter Marks (0-100): ");
                student.marks = sc.nextDouble();

                while (student.marks < 0 || student.marks > 100) {

                    System.out.println("Invalid marks! Please enter between 0 and 100.");

                    System.out.print("Enter Marks (0-100): ");
                    student.marks = sc.nextDouble();
                }
                students.add(student);

                System.out.println("\nStudent added successfully!");

                break;


            // VIEW STUDENTS
            case 2:

                if (students.isEmpty()) {

                    System.out.println("\nNo students added yet.");

                } else {

                    System.out.println("\n----- Student Details -----");

                    for (Student s : students) {

                        System.out.println("ID: " + s.id);
                        System.out.println("Name: " + s.name);
                        System.out.println("Age: " + s.age);
                        System.out.println("Course: " + s.course);
                        System.out.println("Marks: " + s.marks);

                        System.out.println("--------------------------");
                    }
                }

                break;


            // SEARCH STUDENT
            case 3:

                System.out.print("Enter Student ID to search: ");
                int searchId = sc.nextInt();

                boolean found = false;

                for (Student s : students) {

                    if (s.id == searchId) {

                        System.out.println("\nStudent Found!");
                        System.out.println("ID: " + s.id);
                        System.out.println("Name: " + s.name);
                        System.out.println("Age: " + s.age);
                        System.out.println("Course: " + s.course);
                        System.out.println("Marks: " + s.marks);

                        found = true;

                        break;
                    }
                }

                if (!found) {

                    System.out.println("\nStudent not found.");
                }

                break;


            // DELETE STUDENT
            case 4:

                System.out.print("Enter Student ID to delete: ");
                int deleteId = sc.nextInt();

                boolean deleted = false;

                for (int i = 0; i < students.size(); i++) {

                    if (students.get(i).id == deleteId) {

                        students.remove(i);

                        deleted = true;

                        System.out.println("\nStudent deleted successfully!");

                        break;
                    }
                }

                if (!deleted) {

                    System.out.println("\nStudent not found.");
                }

                break;


            // UPDATE STUDENT
            case 5:

                System.out.print("Enter Student ID to update: ");
                int updateId = sc.nextInt();

                boolean updated = false;

                for (Student s : students) {

                    if (s.id == updateId) {

                        System.out.print("Enter New Name: ");
                        s.name = sc.next();

                        System.out.print("Enter New Age: ");
                        s.age = sc.nextInt();

                        System.out.print("Enter New Course: ");
                        s.course = sc.next();

                        System.out.print("Enter New Marks: ");
                        s.marks = sc.nextDouble();

                        updated = true;

                        System.out.println("\nStudent updated successfully!");

                        break;
                    }
                }

                if (!updated) {

                    System.out.println("\nStudent not found.");
                }

                break;


            // EXIT
            case 6:
            	saveStudents(students);

                System.out.println("\nThank you for using Student Management System.");

                break;


            default:

                System.out.println("\nInvalid choice. Please try again.");
            }
        }

        sc.close();
    }
	public static void saveStudents(ArrayList<Student> students) {

        try {

            FileWriter writer = new FileWriter("students.txt");

            for (Student s : students) {

                writer.write(s.id + "," + s.name + "," + s.age + ","
                        + s.course + "," + s.marks + "\n");
            }

            writer.close();

            System.out.println("Students saved successfully!");

        } catch (IOException e) {

            System.out.println("Error while saving students.");
        }
    }
	public static void loadStudents(ArrayList<Student> students) {

        try {

           java.io.BufferedReader reader = new  java.io.BufferedReader(
                    new java.io.FileReader("students.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Student student = new Student();

                student.id = Integer.parseInt(data[0]);
                student.name = data[1];
                student.age = Integer.parseInt(data[2]);
                student.course = data[3];
                student.marks = Double.parseDouble(data[4]);

                students.add(student);
            }

            reader.close();

            System.out.println("Students loaded successfully!");

        } catch (IOException e) {

            System.out.println("No saved students found.");
        }
    }

}
