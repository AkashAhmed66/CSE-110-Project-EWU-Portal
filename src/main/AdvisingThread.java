package main;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdvisingThread extends Thread {

	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public void run() {
		Student s = new Student();
		DataInputStream data1 = null;
		try {
			data1 = new DataInputStream(new FileInputStream("advisedSubs.txt"));
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}

		try {
			while (data1.available() > 0) {
				int nid = data1.readInt();

				String g = data1.readLine();

				if (nid == id) {
					String str="";
					// System.out.println(nid);
					str=("Advised courses : " + g+"\n");
					s.Advised=str;
					data1.close();
					return;
				}
			}
			data1.close();
			String str="";
			str+=("you have not advised yet...\n");
			s.Advised=str;
			return;

		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
}
