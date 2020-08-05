package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * job总表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_job")
public class SJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "jobId", type = IdType.AUTO)
    private Long jobId;
    private String jobName;
    private String jobVersion;
    private String lineNo;
    private Date creatDate;
    private Date updateDate;
    private String remark;


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobVersion() {
        return jobVersion;
    }

    public void setJobVersion(String jobVersion) {
        this.jobVersion = jobVersion;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
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
        return "SJob{" +
        ", jobId=" + jobId +
        ", jobName=" + jobName +
        ", jobVersion=" + jobVersion +
        ", lineNo=" + lineNo +
        ", creatDate=" + creatDate +
        ", updateDate=" + updateDate +
        ", remark=" + remark +
        "}";
    }
}
