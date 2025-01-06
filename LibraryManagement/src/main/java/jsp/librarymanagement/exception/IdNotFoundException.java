package jsp.librarymanagement.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Id not available in DB";
	}
}
