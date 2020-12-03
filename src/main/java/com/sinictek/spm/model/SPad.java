package com.sinictek.spm.model;

import java.sql.Blob;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 焊盘
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
@TableName("s_pad")
@Getter
@Setter
@ToString
public class SPad implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    private String padId;
    private String pcbidLine;
    private Long padIndex;
    private String componentId;
    private String arrayId;
    private String padInspectResult;
    private String defectTypeCode;
    private String defectTypeName;
    private String padImagePath;
   // @TableField(el = "pad2dImage,jdbcType=BLOB")
    private byte[] pad2dImage;
    private Blob pad3dImage;
    private Double height;
    private Double area;
    private Double volume;
    private Double offsetx;
    private Double offsety;
    private Double perHeight;
    private Double perArea;
    private Double perVolume;
    private Double perOffsetx;
    private Double perOffsety;
    private Double shape;
    private Integer bridgeType;
    private Double uHeight;
    private Double lHeight;
    private Double uArea;
    private Double lArea;
    private Double uVolume;
    private Double lVolume;
    private Double uOffsetx;
    private Double uOffsety;
    private Double uPerHeight;
    private Double lPerHeight;
    private Double uPerArea;
    private Double lPerArea;
    private Double uPerVolume;
    private Double lPerVolume;
    private Double uPerOffsetx;
    private Double uPerOffsety;
    private Long padTableID;
    private Long componentTableID;
    private String remark;
    //@TableField(exist = false)
    private String pad2dImageBase64;
    //@TableField(exist = false)
    private String pad3dImageBase64;



}
