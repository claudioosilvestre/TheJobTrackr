package jobtrackr_api.exceptions;

public class CompanyAlreadyExistsException extends TheJobTrackerException{

    public CompanyAlreadyExistsException() {
        super("Company already exists");
    }
}
