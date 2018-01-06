package application.model.steemj;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public enum AssetSymbolType {
    VESTS,
    STEEM,
    SBD,
    STMD,
    TESTS,
    TBD,
    TSTD
}
