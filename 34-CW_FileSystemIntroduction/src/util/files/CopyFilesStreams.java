package telran.util.files;
import java.io.*;
public class CopyFilesStreams implements ICopyFiles {
int bufferSize;
public CopyFilesStreams() {
	bufferSize = (int)Runtime.getRuntime().freeMemory();
}
	public CopyFilesStreams(int bufferSize) {
	super();
	this.bufferSize = bufferSize;
}

	@Override
	public void copy(String sourceFile, String destFile) {
		try (InputStream input = new FileInputStream(sourceFile);
			OutputStream output = new FileOutputStream(destFile);){
			byte[] buffer = new byte[bufferSize];
			int nBytes=0;
			while ((nBytes = input.read(buffer)) > 0) {
				output.write(buffer, 0, nBytes);
			}
			
		} catch (Exception e) {
			copyError(e, sourceFile, destFile);
		}

	}

}
