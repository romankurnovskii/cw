package tests.performance;
import util.files.ICopyFiles;

public class PerformanceCopyFiles extends PerformanceTest {
ICopyFiles copyFiles;
String sourceFile;
String destFile;

	

	public PerformanceCopyFiles(String name, int nRuns, ICopyFiles copyFiles, String sourceFile, String destFile) {
	super(name, nRuns);
	this.copyFiles = copyFiles;
	this.sourceFile = sourceFile;
	this.destFile = destFile;
}



	@Override
	protected void runTest() {
		copyFiles.copy(sourceFile, destFile);

	}

}
