package application.model;

import java.net.URL;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Discussion {
    @GraphId Long id;

    public Discussion() { }
    public Discussion(URL permlink) {
        this.permlink = permlink;
    }
    public URL permlink;
}