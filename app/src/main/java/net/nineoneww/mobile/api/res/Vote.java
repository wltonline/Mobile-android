package net.nineoneww.mobile.api.res;

/**
 * Created by lilian on 2017/8/10.
 */

public class Vote {
    private String voteTitle;//书籍标题
    private String voteAuthor; //书籍作者
    private String votePrice; //书籍价格
    private String votePubDate; //书籍出版时间
    private String voteImgUrl; // 书籍图片Url地址
    private String voteUrl; //书籍详情Url地址

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
