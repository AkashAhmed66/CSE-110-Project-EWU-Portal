package main;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CgpaThread extends Thread{
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public void run() {
		Student s = new Student();
		DataInputStream data2 = null;
		try {
			data2 = new DataInputStream(new FileInputStream("cgpa.txt"));
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}

		try {
			while (data2.available() > 0) {
				int nid = data2.readInt();
				double g = data2.readDouble();

				if (nid == id) {
					String str="";
					// System.out.println(nid);
					str+=("Your CGPA : " + g+"\n");
					s.Cgpa=str;
					data2.close();
					return;
				}
			}
			data2.close();
			String str="";
			str+=("your cgpa has not been updated...\n");
			s.Cgpa=str;
			return;
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
}
