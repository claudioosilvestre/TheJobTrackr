package jobtrackr_api.exceptions;

public class UserNotFoundException extends TheJobTrackerException {

    public UserNotFoundException() {
        super("User not found");
    }
}
