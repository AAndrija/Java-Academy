package java_academy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		try {
			@SuppressWarnings("resource")
			String content = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
			GlobalLogic gl = new GlobalLogic(args[2], content, args[1]);
			gl.theDude();
			gl.Report();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
