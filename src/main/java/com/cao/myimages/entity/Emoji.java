package com.cao.myimages.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;


import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 表情
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@TableName("emoji")
public class Emoji implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String url;
    private String name;
    /**
     * 上传用户 id
     */
    private Integer userId;
    /**
     * 标签数组 json
     */
    private String tag;
    /**
     * 0 - 待审核, 1 - 通过, 2 - 拒绝
     */
    private Integer reviewStatus;
    private Date createTime;
    /**
     * 是否删除 0 - 未删除 1 - 已删除
     */
    @TableLogic
    private Integer isDelete;
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTags(String tag) {
        this.tag = tag;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                ", id=" + id +
                ", url=" + url +
                ", name=" + name +
                ", userId=" + userId +
                ", tag=" + tag +
                ", reviewStatus=" + reviewStatus +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", updateTime=" + updateTime +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emoji emoji = (Emoji) o;
        return Objects.equals(id, emoji.id) && Objects.equals(url, emoji.url) && Objects.equals(name, emoji.name) && Objects.equals(userId, emoji.userId) && Objects.equals(tag, emoji.tag) && Objects.equals(reviewStatus, emoji.reviewStatus) && Objects.equals(createTime, emoji.createTime) && Objects.equals(isDelete, emoji.isDelete) && Objects.equals(updateTime, emoji.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, userId, tag, reviewStatus, createTime, isDelete, updateTime);
    }
}
