package com.cao.myimages.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author cao123
 * @since 2021-10-29
 */
@TableName("tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标签名
     */
    private String name;
    private Date createTime;
    /**
     * 是否删除 0 - 未删除 1 - 已删除
     */
    private Integer isDelete;
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Tag{" +
        ", id=" + id +
        ", name=" + name +
        ", createTime=" + createTime +
        ", isDelete=" + isDelete +
        ", updateTime=" + updateTime +
        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(createTime, tag.createTime) && Objects.equals(isDelete, tag.isDelete) && Objects.equals(updateTime, tag.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createTime, isDelete, updateTime);
    }
}
