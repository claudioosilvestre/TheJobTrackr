package jobtrackr_api.exceptions;

public class CompanyNotFoundException extends TheJobTrackerException {

    public CompanyNotFoundException() {
        super("Company not found exception");
    }
}
