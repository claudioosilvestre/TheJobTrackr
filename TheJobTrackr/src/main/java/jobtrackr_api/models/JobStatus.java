package jobtrackr_api.models;

import java.util.EnumSet;
import java.util.Set;

public enum JobStatus {
    APPLIED,
    INTERVIEW,
    OFFER,
    REJECTED;

    private Set<JobStatus> validTransitions;

    JobStatus() {
    }

    static {
        APPLIED.validTransitions = EnumSet.of(INTERVIEW, REJECTED);
        INTERVIEW.validTransitions = EnumSet.of(OFFER, REJECTED);
        OFFER.validTransitions = EnumSet.noneOf(JobStatus.class);
        REJECTED.validTransitions = EnumSet.noneOf(JobStatus.class);
    }

    public boolean canTransitionTo(JobStatus newStatus) {
        return validTransitions != null && validTransitions.contains(newStatus);
    }
}
