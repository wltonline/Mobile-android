package net.nineoneww.mobile.model.entity;

/**
 * Created by lilian on 2017/8/10.
 */

public class Vote {
    private String voteTitle;
    private String voteAuthor;
    private String votePrice;
    private String votePubDate;
    private String voteImgUrl;
    private String voteUrl;

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getVoteAuthor() {
        return voteAuthor;
    }

    public void setVoteAuthor(String voteAuthor) {
        this.voteAuthor = voteAuthor;
    }

    public String getVotePrice() {
        return votePrice;
    }

    public void setVotePrice(String votePrice) {
        this.votePrice = votePrice;
    }

    public String getVotePubDate() {
        return votePubDate;
    }

    public void setVotePubDate(String votePubDate) {
        this.votePubDate = votePubDate;
    }

    public String getVoteImgUrl() {
        return voteImgUrl;
    }

    public void setVoteImgUrl(String voteImgUrl) {
        this.voteImgUrl = voteImgUrl;
    }

    public String getVoteUrl() {
        return voteUrl;
    }

    public void setVoteUrl(String voteUrl) {
        this.voteUrl = voteUrl;
    }
}
