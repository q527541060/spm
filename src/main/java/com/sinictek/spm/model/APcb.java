package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
@Getter
@Setter
@TableName("a_pcb")

public class APcb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String lineNo;
    private String jobName;
    private Integer aoiMode;
    private String jobversion;
    private Date jobmodifyDate;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private Integer inspectResult;
    private Integer laneNo;
    private String pcbIdLine;
    private String carrierbarcode;
    private String boardBarcode;
    private String arrayInfo;
    private String componentTableName;
    private String fovTableName;
    private String windowTableName;
    private Integer totalArrayCount;
    private Integer goodArrayCount;
    private Integer ngArrayCount;
    private Integer passArrayCount;
    private Integer otherArrayCount;
    private Integer totalComponentCount;
    private Integer goodComponentCount;
    private Integer passComponentCount;
    private Integer ngComponentCount;
    private Integer otherComponentCount;
    private Integer customCount;
    private Integer defaultCount;
    private Integer missingCount;
    private Integer shiftXCount;
    private Integer shiftYCount;
    private Integer rotationCount;
    private Integer bridgeCount;
    private Integer voidCount;
    private Integer tombStoneCount;
    private Integer pinLiftCount;
    private Integer solderBeadCount;
    private Integer smearCount;
    private Integer polarityCount;
    private Integer reverseCount;
    private Integer wrongPartCount;
    private Integer noSolderCount;
    private Integer copperExposureCount;
    private Integer excessSolderCount;
    private Integer solderingCount;
    private Integer excessPartsCount;
    private Integer barcodeCount;

    private Integer eNM_Defect_Type_ENMMaxLengthCount;
    private Double hCpk;
    private Double aCpk;
    private Double vcpk;
    private Double shithxCpk;
    private Double shithyCpk;
    private String lcl;
    private String ucl;
    private String remark;
    private  String create_time;

    @TableField(exist = false)
    private String totalAoi;
    @TableField(exist = false)
    private String ngPcbYeildAoi;
    @TableField(exist = false)
    private String goodPcbYeildAoi;
    @TableField(exist = false)
    private String passPcbYeildAoi;
    @TableField(exist = false)
    private String ngPcbCountAoi;
    @TableField(exist = false)
    private String goodPcbCountAoi;
    @TableField(exist = false)
    private String passPcbCountAoi;

    @TableField(exist = false)
    private String ngcomponentYeildAoi;
    @TableField(exist = false)
    private String goodcomponentYeildAoi;
    @TableField(exist = false)
    private String passcomponentYeildAoi;
    //ngarrayCount
    @TableField(exist = false)
    private String ngarrayCountAoi;
    @TableField(exist = false)
    private String passarrayCountAoi;
    @TableField(exist = false)
    private String goodarrayCountAoi;


}
