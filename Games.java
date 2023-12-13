import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Formatter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
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
	public void writeFile(String name, String won, int pPoints, int cPoints, int rounds) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		Formatter f = null;
		FileWriter fw = null;
		int id = readId();
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