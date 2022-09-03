package hu.multipledatasource.exception;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String detail) {
        super(detail);
    }
}
