package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 线体总表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_line")
public class SLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String LineNo;
    private String lineContent;
    private Date createDate;
    private Date updateDate;
    private String remark;

    @TableField(exist = false)
    private String idStr;
    @TableField(exist = false)
    private String createDateStr;
    @TableField(exist = false)
    private String updateDateStr;

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

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
        return "SLine{" +
        ", id=" + id +
        ", LineNo=" + LineNo +
        ", lineContent=" + lineContent +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", remark=" + remark +
        "}";
    }
}
