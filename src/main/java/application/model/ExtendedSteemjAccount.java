package application.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import eu.bittrade.libs.steemj.base.models.ExtendedAccount;

@NodeEntity
public class ExtendedSteemjAccount extends ExtendedAccount {
    @GraphId Long id;

    
}