package application.model.steemj;

import eu.bittrade.libs.steemj.util.SteemJUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by marcel on 06-01-18.
 */
@NodeEntity
public class TimePointSec {
    @GraphId
    Long id;
    private long dateTime;

    public TimePointSec() {
    }

    public String getDateTime() {
        return SteemJUtils.transformDateToString(this.getDateTimeAsDate());
    }

    public Date getDateTimeAsDate() {
        return new Date(this.dateTime);
    }

    public int getDateTimeAsInt() {
        return (int) (this.dateTime / 1000);
    }

    public long getDateTimeAsTimestamp() {
        return this.dateTime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
