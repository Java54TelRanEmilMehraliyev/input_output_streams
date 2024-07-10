package telran.io;

import java.io.*;

public class CodeCommentsSeparation {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Usage: java CodeCommentsSeparation <inputFilePath> <codeFilePath> <commentsFilePath>");
			System.exit(1);
		}

		String inputFilePath = args[0];
		String codeFilePath = args[1];
		String commentsFilePath = args[2];

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
				BufferedWriter codeWriter = new BufferedWriter(new FileWriter(codeFilePath));
				BufferedWriter commentsWriter = new BufferedWriter(new FileWriter(commentsFilePath))) {

			String line;
			boolean isCommentBlock = false;

			while ((line = reader.readLine()) != null) {
				line = line.trim();

				if (line.startsWith("//") || isCommentBlock || line.startsWith("/*")) {
					commentsWriter.write(line);
					commentsWriter.newLine();
					if (line.startsWith("/*")) {
						isCommentBlock = true;
					}
					if (line.endsWith("*/")) {
						isCommentBlock = false;
					}
				} else {
					codeWriter.write(line);
					codeWriter.newLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}