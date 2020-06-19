import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileSystemTestAppl {
	private static final int SPACES_PER_LEVEL = 2;

	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.getDefault();

		// fs.getRootDirectories().forEach(System.out::println);
		// Path current = fs.getPath("..");
//	System.out.printf("isAbsolute: %s; file name: %s; absolute path: %s "
//			,current.isAbsolute(),
//			current.getFileName(),
//			current.toAbsolutePath().normalize());

		displayDirectoryContent(fs.getPath("/"), 1);

		Path filePathSrc = fs.getPath("src/FileSystemTestAppl.java");

		InputStream input = Files.newInputStream(filePathSrc);
		OutputStream output = new FileOutputStream("CopyFileSysteTestAppl");

		byte[] buffer = new byte[input.available()];

		System.out.println("\nlength of buffer array: " + buffer.length);
		System.out.println(input.read(buffer));

		output.write(buffer);
		output.close();
		
		copyFileStandard();
		copyBigFile();

	}

// prints directory structure of the given path directory and depth
// if depth equals 0 it prints all directory structure from the given start
// output:
// dir_name       level 1
//     file_name
//     ......
//     dir_name
//         file_name level 2
//         ....
//         dir_name
//            ......    level 3
	private static void displayDirectoryContent(Path path, int depth) {

		if (!path.isAbsolute()) {
			path = path.toAbsolutePath().normalize();
		}
		if (Files.isDirectory(path)) {
			System.out.println(path);
			try {
				Files.list(path).forEach(p -> displayRecursion(p, 1, depth));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	private static void displayRecursion(Path path, int level, int depth) {

		if (level > depth && depth != 0)
			return;
		System.out.print(" ".repeat(level * SPACES_PER_LEVEL));
		System.out.println(path.getFileName());
		if (Files.isDirectory(path)) {
			try {
				Files.list(path).forEach(p1 -> displayRecursion(p1, level + 1, depth));
			} catch (IOException e) {

			}
		}

	}
	
	
	
	private static void copyFileStandard() {
		
		FileSystem fSystem = FileSystems.getDefault();
		Path path = fSystem.getPath("src/FileSystemTestAppl.java");
		Path pathDestination = fSystem.getPath("src/FileSystemTestAppl_COPY_STANDARD.java");
		
		try {
			Files.copy(path, pathDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private static void copyBigFile() {
		FileSystem fSystem = FileSystems.getDefault();
		Path path = fSystem.getPath("src/FileSystemTestAppl.java");
		Path pathDestination = fSystem.getPath("src/FileSystemTestAppl_COPY_BIG.java");
		
		try {
			InputStream inputStream = Files.newInputStream(path);
			Scanner scanner = new Scanner(inputStream);
			OutputStream outputStream = new FileOutputStream(pathDestination.toFile());
			
			while (scanner.hasNext()) {
				outputStream.write(scanner.nextLine().getBytes());				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
