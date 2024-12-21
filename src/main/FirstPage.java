package main;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FirstPage {
	
	
	void logIn() throws IOException{
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Press 1 to log in as Student...");
		System.out.println("Press 2 to log in as Admin...");
		System.out.println("Press 0 to go back...");
		//System.out.println("Press 3 to log out...");
		int c=0;
		try{
			c = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			logIn();
		}
		
		
		if(c==1){
			Student s = new Student();
			s.logIn();
		}else if(c==2){
			Admin a = new Admin();
			a.logIn();
		}else if(c==0){
			FirstPage f = new FirstPage();
			f.main(null);
		}else{
			System.out.println("You have entered wrong command...");
			logIn();
		}
	}
	void register() throws IOException{
		
		Scanner input = new Scanner(System.in);
		System.out.println("Press 1 to register as Student...");
		System.out.println("Press 2 to register as Admin...");
		System.out.println("Press 0 to go back...");
		//System.out.println("Press 3 to log out...");
		
		int c=0;
		try{
			c = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			register();
		}
		
		if(c==1){
			Student s = new Student();
			s.register();
		}else if(c==2){
			Admin a = new Admin();
			a.register();
		}else if(c==0){
			FirstPage f = new FirstPage();
			f.main(null);
		}else{
			System.out.println("You have entered wrong command...");
			System.out.println("Closed");
		}
		 
	}
	public static void main(String[] args) throws IOException{
		System.out.println("\t\t\t\tEast West University");
		System.out.println("\t\t\t\t     Advising");
		FirstPage f = new FirstPage();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Press 1 to register...");
		System.out.println("Press 2 to log in...");
		System.out.println("Press 0 to close...");
		
		int c=0;
		try{
			c = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			main(null);
		}
		//String g = input.nextLine();
		
		if(c==1){
			f.register();
		}else if(c==2){
			f.logIn();
		}else if(c==0){
			System.out.println("Closed...");
			System.exit(0);
		}else{
			System.out.println("You have entered wrong command...");
		}

	}
}
