import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
	private List<Double> grades;
	private double midTermGrade;
	private double week5EcGrade;
	private double week8EcGrade;
	private Double[] regPoints;
	private double forumPosts;
	private double urlLinks;

	public Calculator(List<Double> grades, double midTermGrade, double week5EcGrade, double week8EcGrade,
			double forumPosts, double urlLinks) {
		this.grades = grades;
		this.midTermGrade = midTermGrade;
		this.week5EcGrade = week5EcGrade;
		this.week8EcGrade = week8EcGrade;
		this.forumPosts = forumPosts;
		this.urlLinks = urlLinks;
		Scanner sc = null;
		try {
			sc = new Scanner(new File("maxRegularPoints.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Double> maxRegularPoints = new ArrayList<Double>();
		while (sc.hasNext()) {
			maxRegularPoints.add(sc.nextDouble());
		}
		sc.close();

		Double[] regularPoints = maxRegularPoints.toArray(new Double[0]);
		regPoints = regularPoints;
	}

	public void calculate() {
		System.out.println("Calculating Homework Grade Average...");
		double homeWork = 0;
		for (int i = 0; i < 4; i++) {
			homeWork += grades.get(i) / regPoints[i];
		}
		homeWork += (grades.get(4) + week5EcGrade) / regPoints[4];
		for (int i = 5; i < 7; i++) {
			homeWork += grades.get(i) / regPoints[i];
		}
		homeWork += (grades.get(7) + week8EcGrade) / regPoints[7];
		for (int i = 8; i < 11; i++) {
			homeWork += grades.get(i) / regPoints[i];
		}
		homeWork += midTermGrade / 162.0;
		homeWork = homeWork * (.75) * .091;
		System.out.println("Homework %: " + homeWork * 100 + "%");
		double forumGrade = (.20) * (.04545) * (forumPosts);
		System.out.println("Forum %: " + forumGrade * 100 + "%");
		double urlGrade = (.05) * (.04545) * urlLinks;
		System.out.println("URL Links Grade %: " + urlGrade * 100 + "%");
		System.out.println("Final Grade Estimation = " + ((homeWork + forumGrade + urlGrade) * 100) + "%");
	}

	public static void main(String[] cheese) {
		List<Double> grades = new ArrayList<Double>();
		System.out.println("----THIS ONLY GOES UP TO WEEK 11 SINCE THATS WHATS BEEN GRADED---------");
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i <= 11; i++) {
			System.out.println("Week " + i + " Points Earned: ");
			double n = sc.nextDouble();
			grades.add(n);
		}
		System.out.println("Enter Points Earned for Mid Term: ");
		double midTermGrade = sc.nextDouble();
		System.out.println("Enter Week 5 Extra Credit Points: ");
		double week5EcGrade = sc.nextDouble();
		System.out.println("Enter Week 8 Extra Credit Points: ");
		double week8EcGrade = sc.nextDouble();
		System.out.println("____ / 22 Forum Posts");
		double forumPosts = sc.nextDouble();
		System.out.println("____ / 22 URL links");
		double urlLinks = sc.nextDouble();

		Calculator calc = new Calculator(grades, midTermGrade, week5EcGrade, week8EcGrade, forumPosts, urlLinks);
		calc.calculate();
	}

}
