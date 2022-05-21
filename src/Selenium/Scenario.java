package Selenium;

public class Scenario {

	public static void main(String[] args) {
		
		System.out.println("Hello World");
		
		String name = "Did you score 45 runs?";
		
		name = name.replaceAll("[0-9]", "");
		
		System.out.println(name);
	}
}
