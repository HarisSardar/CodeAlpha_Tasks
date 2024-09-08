package studentgrade;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGrades {

	public static void main(String[] args) {
		
		 ArrayList<Double> grades = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the number of students: ");
	        int numberOfStudents = scanner.nextInt();

	        for (int i = 0; i < numberOfStudents; i++) {
	            System.out.print("Enter grade for student " + (i + 1) + ": ");
	            double grade = scanner.nextDouble();
	            grades.add(grade);
	        }
	        double total = 0;
	        double highest = grades.get(0);
	        double lowest = grades.get(0);

	        for (double grade : grades) {
	            total += grade;
	            if (grade > highest) {
	                highest = grade;
	            }
	            if (grade < lowest) {
	                lowest = grade;
	            }
	        }
	        double average = total / numberOfStudents;

	        System.out.println("\nAverage Grade: " + average);
	        System.out.println("Highest Grade: " + highest);
	        System.out.println("Lowest Grade: " + lowest);

	        scanner.close();
	}
}
