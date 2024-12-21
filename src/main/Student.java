package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student extends Member{
    private String depertment="";
    public static String Info="";
    public static String Cgpa="";
    public static String Advised="";
    
    public Student(){    
    }
    public Student(String name, int id, String contact, String dob, String address, String bloodGroup, String depertment){
        super(name, id, contact, dob, address, bloodGroup);
        this.depertment=depertment;
    }
    void setUp(int id){
    	
    	Scanner input = new Scanner(System.in);

    	DataInputStream dataInputStream=null;
		try {
			dataInputStream = new DataInputStream(
					new FileInputStream("studentInfo.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while (dataInputStream.available() > 0) {
				int nid=dataInputStream.readInt();
				if(nid == id){
					System.out.println("You already updated your account...");
					profile(id);
				}
				 String g=dataInputStream.readLine();
				 g=dataInputStream.readLine();
				 g=dataInputStream.readLine();
				 g=dataInputStream.readLine();
				 g=dataInputStream.readLine();
				 //g=dataInputStream.readLine();
				
			}
			dataInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	String name="";
    	String contact="";
    	String dob="";
    	String address="";
    	String bloodGroup="";
    	String depertment="";
        
    	
    	try{
    		System.out.println("Enter name : ");
            name=input.nextLine();
            
            System.out.println("Enter id : ");
            int nid=input.nextInt();
            String g = input.nextLine();
            if(nid != id){
            	System.out.println("Wrong id...");
            	profile(id);
            }
            System.out.println("Enter contact : ");
            contact=input.nextLine();
            System.out.println("Enter namdobe : ");
            dob=input.nextLine();
            System.out.println("Enter address : ");
            address=input.nextLine();
            System.out.println("Enter bloodGroup : ");
            bloodGroup=input.nextLine();
            System.out.println("Enter depertment : ");
            depertment=input.nextLine();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			setUp(id);
		}
    	
    	
    	
        
        
        FileOutputStream file=null;
		try {
			file = new FileOutputStream("studentInfo.txt",true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
        
        
        try {
        	data.writeInt(id);
			data.writeBytes(contact+"\n");
			data.writeBytes(dob+"\n");
			data.writeBytes(address+"\n");
			data.writeBytes(bloodGroup+"\n");
			data.writeBytes(depertment+"\n");
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		profile(id);
        
    }
    void seeInfo(int id){
    	
    	
    	InfoThread it = new InfoThread();
    	AdvisingThread at = new AdvisingThread();
    	CgpaThread ct = new CgpaThread();
    	
    	
    	it.start();
    	at.start();
    	ct.start();
    	
    	it.setId(id);
    	at.setId(id);
    	ct.setId(id);
    	
    	try {
			it.join();
			at.join();
			ct.join();
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(Info);
		System.out.print(Cgpa);
		System.out.print(Advised);
		profile(id);
		
    }
    void advising(int id){
    	
    	
    	Date date = new Date();
		int d=date.getDate();
		int mon=date.getMonth();
		int hour =date.getHours();
		//System.out.println(d+" "+mon+" "+hour);
		if(d!=2 || mon!=8 || hour<18 || hour>23){
			System.out.println("You cannot advise now...");
			profile(id);
		}
    	
    	DataInputStream dataInputStream=null;
 		try {
 			dataInputStream = new DataInputStream(
 					new FileInputStream("advisedSubs.txt"));
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		}

 		try {
 			while(dataInputStream.available()>0){
 				int nid = dataInputStream.readInt();
 				if(nid==id){
 					System.out.println("You have either done your advising or this is not your advising time...");
 					profile(id);
 				}
 				String str=dataInputStream.readLine();
 			}
 			dataInputStream.close();
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		boolean[] check = new boolean[4];
 		for(int i=0; i<4; i++){
 			check[i]=false;
 		}
 		
    	System.out.println("Press 1 to enroll CSE 200");
    	System.out.println("Press 2 to enroll CSE 207");
    	System.out.println("Press 3 to enroll CSE 209");
    	System.out.println("Press 4 to enroll GEN 226");

    	
    	Scanner input = new Scanner(System.in);	
    	int counter=0;
    	String courses="";
    	while(counter<3){
    		
    		int c=0;
    		try{
    			c = input.nextInt();
    		}catch(InputMismatchException e){
    			System.out.println("You have entered wrong key...");
    			advising(id);
    		}
    		
        	if(c==1){
        		if(check[0]){
        			System.out.println("You already took the course...");
        		}else if(takeCourse("subjects\\CSE 200")){
        			System.out.println("CSE 200 taken...");
        			check[0]=true;
        			counter++;
        			courses+=" CSE 200 ";
        		}else{
        			System.out.println("No seat avialable...");
        		}
    		}else if(c==2){
    			if(check[1]){
        			System.out.println("You already took the course...");
        		}else if(takeCourse("subjects\\CSE 207")){
        			System.out.println("CSE 207 taken...");
        			check[1]=true;
    				counter++;
    				courses+=" CSE 207 ";
    			}else{
        			System.out.println("No seat avialable...");
        		}
    		}else if(c==3){
    			if(check[2]){
        			System.out.println("You already took the course...");
        		}else if(takeCourse("subjects\\CSE 209")){
        			System.out.println("CSE 209 taken...");
        			check[2]=true;
    				counter++;
    				courses+=" CSE 209 ";
    			}else{
        			System.out.println("No seat avialable...");
        		}
    		}else if(c==4){
    			if(check[3]){
        			System.out.println("You already took the course...");
        		}else if(takeCourse("subjects\\GEN 226")){
        			System.out.println("GEN 226 taken...");
        			check[3]=true;
    				counter++;
    				courses+=" GEN 226 ";
    			}else{
        			System.out.println("No seat avialable...");
        		}
    		}   	
        	
    	}
    	FileOutputStream file=null;
		try {
			file = new FileOutputStream("advisedSubs.txt",true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
           
        try {
			data.writeInt(id);
			//data.writeBytes("\n");
			data.writeBytes(courses+"\n");
			data.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	profile(id);
    }
    void profile(int id){
		Scanner input = new Scanner(System.in);
		System.out.println("Press 1 to Setup Profile...");
		System.out.println("Press 2 for Advicing...");
		System.out.println("Press 3 to see info...");
		System.out.println("Press 4 to log out...");
		
		int c=0;
		try{
			c = input.nextInt();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			profile(id);
		}
		//String g = input.nextLine();
		
		if(c==1){
			setUp(id);
		}else if(c==2){
			advising(id);
		}else if(c==3){
			seeInfo(id);
		}else if(c==4){
			FirstPage f = new FirstPage();
			try {
				f.main(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
    boolean takeCourse(String filenm){
		int seat=0;
		DataInputStream dataInputStream=null;
 		try {
 			dataInputStream = new DataInputStream(
 					new FileInputStream(filenm+".txt"));
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		}

 		try {
 			seat=dataInputStream.readInt();
 			if(seat==0){
 				return false;
 			}
 			dataInputStream.close();
 			
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		FileOutputStream file=null;
		try {
			file = new FileOutputStream(filenm+".txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
           
        try {
			data.writeInt(seat-1);
			data.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
    }
    public void logIn(){
    	
    	Scanner input = new Scanner(System.in);
    	
    	int id=0;
    	String pass="";
    	
    	try{
    		System.out.println("Enter user id...");
            id = input.nextInt();
            String g = input.nextLine();

            System.out.println("Enter password...");
            pass = input.nextLine();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			logIn();
		}
   
        DataInputStream dataInputStream=null;
		try {
			dataInputStream = new DataInputStream(
					new FileInputStream("studentRegister.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while (dataInputStream.available() > 0) {
				int idd = dataInputStream.readInt();
				String passd = dataInputStream.readLine();
				if(idd==id){
					if(passd.equals(pass)){
						System.out.println("Logged in...");
						profile(id);
					}else{
						System.out.println("Wrong password...");
						logIn();
					}
				}
				
			}
			dataInputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("No id found...");
		logIn();

    }
    public void register(){
    	
    	Scanner input = new Scanner(System.in);
    	
    	
    	int id=0;
    	try{
    		System.out.println("Enter user id...");
            id = input.nextInt();
            String g = input.nextLine();
            if(id<2021360000 || id>2021360200){
            	System.out.println("Invalid ID...");
            	register();
            }
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			register();
		}
    	
		DataInputStream dataInputStream=null;
		try {
			dataInputStream = new DataInputStream(
					new FileInputStream("studentRegister.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (dataInputStream.available() > 0) {
				int idd = dataInputStream.readInt();
				String pass = dataInputStream.readLine();
				
				if(idd==id){
					System.out.println("This id is already taken...");
					register();
				}
				
			}
			dataInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Enter password...");
        String pass = input.nextLine();
        
		FileOutputStream file=null;
		try {
			file = new FileOutputStream("studentRegister.txt",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
           
        try {
			data.writeInt(id);
			data.writeBytes(pass+"\n");
			data.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		profile(id);
    }

}
