import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Formatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;
import java.io.FileWriter;
public class Games {
	public void readFile() {
		Scanner reader = null;
		String[] fields = {"Id: ", "Player Name: ", "Won: ", "Player Points: ", "CPU points: ", "Number of Rounds Played: ", "Date: ", "Time: "};
		try {
			reader = new Scanner(Paths.get("latestgames.txt"));
			while(reader.hasNextLine()) {
				String[] info = reader.nextLine().split(",");
				for(int i=0; i< info.length; i++) {
					System.out.println(fields[i] + info[i].trim());
				}
				System.out.println("---------------------------------------------------------------------------------------------------------");

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
	}
	public void fileDeleteRename() {
		Scanner reader = null;
		Formatter f = null;
		FileWriter fw = null;
		
		int c = 0;
		String[] arr = new String[11];
		try {
			reader = new Scanner(Paths.get("latestgames.txt"));
			while(reader.hasNextLine()) {
				String info = reader.nextLine();
				arr[c] = info;
				c++;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		int id = readId();
		try{
			fw = new FileWriter("temp.txt", true);
			f = new Formatter(fw);
			for(int i = 0; i < c; i++) {
				if(i == 0) {
					continue;
				}
				f.format("%s\n", arr[i]);
			}
			fw.close();
		} catch (Exception e) {
			System.err.println("Somethings went wrong.");
		} finally {
			if (f != null) {
			 f.close();	
			}
		}
		
		File rename = new File("temp.txt");
		File file = new File("latestgames.txt");
		
		if(file.delete()) {
			System.out.print(".");
		} else {
			System.out.print("!");
		}

		boolean flag = rename.renameTo(file);
		
		if(flag == true) {
			System.out.println(".");
		} else {
			System.out.println("!");
		}
	}
	public int readId() {
		Scanner reader = null;
		int sum = -1;
		try {
			reader = new Scanner(Paths.get("latestgames.txt"));
			while(reader.hasNextLine()) {
				String[] info = reader.nextLine().split(",");
				int x = Integer.parseInt(info[0].trim());
				if(x > sum) {
					sum = x;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		return sum;
	}
	public int readMinId() {
		Scanner reader = null;
		int sum = 10000;
		try {
			reader = new Scanner(Paths.get("latestgames.txt"));
			while(reader.hasNextLine()) {
				String[] info = reader.nextLine().split(",");
				int x = Integer.parseInt(info[0].trim());
				if(x < sum) {
					sum = x;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		return sum;
	}
	public void writeFile(String name, String won, int pPoints, int cPoints, int rounds) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Formatter f = null;
		FileWriter fw = null;
		Scanner reader = null;

		int id = readId();
		int c = 0;
		try{
			fw = new FileWriter("latestgames.txt", true);
			f = new Formatter(fw);
			f.format("%s, %s, %s, %s, %s, %s, %s, %s\n", id+1, name, won, pPoints, cPoints, rounds, date, time);
			fw.close();
		} catch (Exception e) {
			System.err.println("Somethings went wrong.");
		} finally {
			if (f != null) {
			 f.close();	
			}
		}
		
	}
}