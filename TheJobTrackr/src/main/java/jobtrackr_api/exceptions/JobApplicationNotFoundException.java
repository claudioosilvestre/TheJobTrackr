package jobtrackr_api.exceptions;

public class JobApplicationNotFoundException extends TheJobTrackerException {
    public JobApplicationNotFoundException() {
        super("Job application not found");
    }
}
