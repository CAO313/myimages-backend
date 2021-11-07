package com.cao.myimages.request;

import java.io.Serializable;
import java.util.Objects;

public class EmojiQueryRequest implements Serializable {
    private String name;
    private String tag;
    private Integer reviewStatus;
    private int pageNum = 1;
    private int pageSize = 10;

    public EmojiQueryRequest(String name, String tag, Integer reviewStatus, int pageNum, int pageSize) {
        this.name = name;
        this.tag = tag;
        this.reviewStatus = reviewStatus;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmojiQueryRequest that = (EmojiQueryRequest) o;
        return pageNum == that.pageNum && pageSize == that.pageSize && Objects.equals(name, that.name) && Objects.equals(tag, that.tag) && Objects.equals(reviewStatus, that.reviewStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag, reviewStatus, pageNum, pageSize);
    }

    @Override
    public String toString() {
        return "EmojiQueryRequest{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", reviewStatus=" + reviewStatus +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
