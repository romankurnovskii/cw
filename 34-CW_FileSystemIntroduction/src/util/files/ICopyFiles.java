package util.files;

public interface ICopyFiles {
void copy (String sourceFile, String destFile);
default void copyError (Exception e, String sourceFile,
		String destFile) {
	System.out.printf("Copy error: %s\n"
			+ " Source: %s, Destination: %s",
			e.getMessage(), sourceFile, destFile);
}
}
