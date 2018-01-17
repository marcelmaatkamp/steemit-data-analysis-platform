package application.model.neo4j;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Permlink {
    @GraphId
    Long id;

    @Relationship(type = "AUTHOR", direction = Relationship.OUTGOING)
    Account author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public String link;

    public Permlink() {
    }

    public Permlink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
