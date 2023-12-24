import java.nio.file.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Formatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.File;
import java.io.FileWriter;
public class Games {
	//..........................PRINT LATEST GAMES................................................
	public void readFile() {
		File tempFile = new File("latestgames.txt");
		boolean exists = tempFile.exists();
		if(exists) {
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
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(reader != null) {
					reader.close();
				}
			}
		}else {
			System.out.println("There are no past games available.");
		}
	}
	//..........................GET FILE MAXIMUM ID................................................
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
	//..........................SAVE PLAYED GAME TO FILE................................................
	public void writeFile(String name, String won, int pPoints, int cPoints, int rounds) {
		Formatter f = null;
        FileWriter fw = null;
        Scanner reader = null;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
		
		File tempFile = new File("latestgames.txt");
		boolean exists = tempFile.exists();
		if(!exists) {
			try {
				File file = new File("latestgames.txt");
				file.createNewFile();
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.print(" latestgames.txt file created.");
		}
		
        File file = new File("latestgames.txt");
		Path path = Paths.get("latestgames.txt");

		int id = readId();
		long lines = 0;
		
		try {
            fw = new FileWriter("latestgames.txt", true);
            f = new Formatter(fw);
            f.format("%s, %s, %s, %s, %s, %s, %s, %s\n", id+1, name, won, pPoints, cPoints, rounds, date, time);
            fw.close();
        } catch (Exception e) {
            System.err.println("Something went wrong..");
        } finally {
            if (f != null) {
                f.close();
            }
        }
		
		try {
          lines = Files.lines(path).count();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] arr = new String[(int)lines];
		try {
            reader = new Scanner(Paths.get("latestgames.txt"));
            while (reader.hasNextLine()) {
                for (int i = 0; i < arr.length; i++) {
						arr[i] = reader.nextLine();
					
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		if(file.delete()) {
			System.out.print("..");
		} else {
			System.out.print("!");
		}
		try {
			fw = new FileWriter("latestgames.txt", true);
			f = new Formatter(fw);
			for (int i = 0; i < arr.length; i++) {
				if (i == 0 && arr.length == 11) {
					continue;
				}
			f.format("%s\n", arr[i]);
			}
		} catch (Exception e) {
        System.err.println("Something went wrong..");
		} finally {
			if (f != null) {
				f.close();
			}
		}
		

	}
}