package application.model.steemj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by marcel on 06-01-18.
 */
public class AccountVote {

    private String authorperm;
    private long weight;
    private long rshares;
    private TimePointSec time;

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
