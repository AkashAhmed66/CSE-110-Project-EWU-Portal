package main;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InfoThread extends Thread {
	
	private int id;
	
	public void setId(int id){
		this.id=id;
	}
	public void run() {
		Student s = new Student();
		DataInputStream dataInputStream = null;
		try {
			dataInputStream = new DataInputStream(new FileInputStream(
					"studentInfo.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while (dataInputStream.available() > 0) {
				int nid = dataInputStream.readInt();
				String contact = dataInputStream.readLine();
				String dob = dataInputStream.readLine();
				String address = dataInputStream.readLine();
				String bloodGroup = dataInputStream.readLine();
				String depertment = dataInputStream.readLine();
				// System.out.println(nid);
				if (nid == id) {
					String str="";
					str+=("ID : " + nid+"\n");
					str+=("Name : " + contact+"\n");
					str+=("Contact : " + dob+"\n");
					str+=("Dob : " + address+"\n");
					str+=("Address : " + bloodGroup+"\n");
					str+=("Blood group : " + depertment+"\n");
					s.Info=str;
					// System.out.println("Depertment : " +
					// dataInputStream.readLine());
					dataInputStream.close();
					return;
				}

			}
			dataInputStream.close();
			String str="";
			str=("You have not updated your account...\n");
			s.Info=str;
			return;
		} catch (IOException e) {
			// System.out.println("Here");
			// e.printStackTrace();
		}
	}
}
