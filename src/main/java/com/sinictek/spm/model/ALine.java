package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 线体总表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
@TableName("a_line")
public class ALine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线体ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 线体编号
     */
    private String LineNo;
    /**
     * 线体说明
     */
    private String lineContent;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * remark
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineNo() {
        return LineNo;
    }

    public void setLineNo(String LineNo) {
        this.LineNo = LineNo;
    }

    public String getLineContent() {
        return lineContent;
    }

    public void setLineContent(String lineContent) {
        this.lineContent = lineContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ALine{" +
        ", id=" + id +
        ", LineNo=" + LineNo +
        ", lineContent=" + lineContent +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", remark=" + remark +
        "}";
    }
}
