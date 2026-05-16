package jobtrackr_api.exceptions;

public class InvalidStatusException extends TheJobTrackerException {

    public InvalidStatusException() {
        super("Cannot change to that status");
    }
}
