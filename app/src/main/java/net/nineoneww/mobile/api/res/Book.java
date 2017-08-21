package net.nineoneww.mobile.api.res;

import java.util.ArrayList;

/**
 * Created by lilian on 2017/8/10.
 */

public class Book {
    private String bookTitle;//书籍标题
    private String bookAuthor; //书籍作者
    private String bookPrice; //书籍价格
    private String bookPubDate; //书籍出版时间
    private String bookImgUrl; // 书籍图片Url地址
    private String bookUrl; //书籍详情Url地址

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookPubDate() {
        return bookPubDate;
    }

    public void setBookPubDate(String bookPubDate) {
        this.bookPubDate = bookPubDate;
    }

    public String getBookImgUrl() {
        return bookImgUrl;
    }

    public void setBookImgUrl(String bookImgUrl) {
        this.bookImgUrl = bookImgUrl;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
