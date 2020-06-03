package util.files;


import java.io.IOException;
import java.nio.file.*;
public class CopyFilesJava implements ICopyFiles {

	@Override
	public void copy(String sourceFile, String destFile) {
		Path source = Paths.get(sourceFile);
		Path dest = Paths.get(destFile);
		try {
			Files.copy(source, dest,
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			copyError(e, sourceFile, destFile);
		}

	}

}
