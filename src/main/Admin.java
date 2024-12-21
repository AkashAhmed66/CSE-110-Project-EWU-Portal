package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Member{
    private String joinnigDate="";
    private String roomNum="";
    public Admin(){   
    }
    void setUp(int id){
    	
    	Scanner input = new Scanner(System.in);

    	DataInputStream dataInputStream=null;
		try {
			dataInputStream = new DataInputStream(
					new FileInputStream("adminInfo.txt"));
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
    	//String depertment="";
        
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
        
        //System.out.println("Enter depertment : ");
        //depertment=input.nextLine();
        
        
        FileOutputStream file=null;
		try {
			file = new FileOutputStream("adminInfo.txt",true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
        
        
        try {
        	data.writeInt(id);
        	data.writeBytes(name+"\n");
			data.writeBytes(contact+"\n");
			data.writeBytes(dob+"\n");
			data.writeBytes(address+"\n");
			data.writeBytes(bloodGroup+"\n");
			//data.writeBytes(depertment+"\n");
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		profile(id);
        
    }
    void seeInfo(int id){
    	
    	DataInputStream dataInputStream=null;
		try {
			dataInputStream = new DataInputStream(
					new FileInputStream("adminInfo.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while (dataInputStream.available() > 0) {
				int nid=dataInputStream.readInt();
				
				//System.out.println(nid);
				if(nid == id){
					System.out.println("ID : "+nid);
					System.out.println("Name : " + dataInputStream.readLine());
					System.out.println("Contact : " + dataInputStream.readLine());
					System.out.println("Dob : " + dataInputStream.readLine());
					System.out.println("Address : " + dataInputStream.readLine());
					System.out.println("Blood group : " + dataInputStream.readLine());
					//System.out.println("Depertment : " + dataInputStream.readLine());
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
		System.out.println("You have not updated Your profile...");
		profile(id);
		
    }
    void profile(int id){
		Scanner input = new Scanner(System.in);
		System.out.println("Press 1 to Setup Profile...");
		System.out.println("Press 2 to see info...");
		System.out.println("Press 3 to see update cgpa...");
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
			seeInfo(id);
		}else if(c==3){
			updateCgpa(id);
		}else if(c==4){
			FirstPage f = new FirstPage();
			try {
				f.main(null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    public void updateCgpa(int id){
    	
    	
    	Scanner input = new Scanner(System.in);
    	int nid=0;
    	double cgpa=0.0;
    	try{
    		System.out.println("Enter id...");
            nid = input.nextInt();
            //String g = input.nextLine();

            System.out.println("Enter cgpa...");
            cgpa = input.nextDouble();
		}catch(InputMismatchException e){
			System.out.println("You have entered wrong key...");
			updateCgpa(id);
		}
    	
    	FileOutputStream file=null;
		try {
			file = new FileOutputStream("cgpa.txt",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        DataOutputStream data = new DataOutputStream(file); 
        
        try {
			data.writeInt(nid);
			data.writeDouble(cgpa);
			data.close();
			profile(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					new FileInputStream("adminRegister.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("No id found...");
		logIn();
		
		try {
			dataInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void register(){
    	Scanner input = new Scanner(System.in);
    	int id=0;
    	try{
    		System.out.println("Enter user id...");
            id = input.nextInt();
            String g = input.nextLine();
            if(id<197100 || id>197150){
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
					new FileInputStream("adminRegister.txt"));
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
			file = new FileOutputStream("adminRegister.txt",true);
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
    public Admin(String name, int id, String contact, String dob, String address, String bloodGroup, String joinnigDate, String roomNum){
        super(name, id, contact, dob, address, bloodGroup);
        this.joinnigDate=joinnigDate;
        this.roomNum=roomNum;
    }

}
