package jobtrackr_api.exceptions;

public class UserEmailAlreadyExistsException extends TheJobTrackerException {

    public UserEmailAlreadyExistsException() {
        super("This email is already registered");
    }
}
