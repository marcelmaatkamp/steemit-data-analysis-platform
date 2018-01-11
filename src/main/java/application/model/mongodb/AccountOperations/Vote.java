package application.model.mongodb.AccountOperations;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AccountOperations")
public class Vote {

    private String id;
    private String voter;
    private String author;
    private String permlink;
    private Integer weight;
    private String trxId;
    private Integer block;
    private Integer trxInBlock;
    private Integer opInTrx;
    private Integer virtualOp;
    private String timestamp;
    private String account;
    private String type;
    private Integer index;

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPermlink() {
        return permlink;
    }

    public void setPermlink(String permlink) {
        this.permlink = permlink;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getTrxInBlock() {
        return trxInBlock;
    }

    public void setTrxInBlock(Integer trxInBlock) {
        this.trxInBlock = trxInBlock;
    }

    public Integer getOpInTrx() {
        return opInTrx;
    }

    public void setOpInTrx(Integer opInTrx) {
        this.opInTrx = opInTrx;
    }

    public Integer getVirtualOp() {
        return virtualOp;
    }

    public void setVirtualOp(Integer virtualOp) {
        this.virtualOp = virtualOp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}

