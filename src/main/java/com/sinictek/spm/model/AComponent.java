package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("a_component")
@Getter
@Setter
public class AComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private Integer aoiMode;
    private Integer arrayIndex;
    private Integer fovIndex;
    private String partdesignate;
    private String partno;
    private String packagetype;
    private String componentposition;
    private String componentType;
    private Integer result;
    private String defectType;
    private String imageInfo;
    private String valueInfo;
    private Double height;
    private Double perheight;
    private Double xshift;
    private Double perxshift;
    private Double yshift;
    private Double peryshift;
    private Double angle;
    private Double perangle;
    private Double volume;
    private Double bigvolume;
    private Double planeness;
    private Double uplanenesswindowid;
    private Double lplanenesswindowid;
    private Double linearity;
    private Double ulinearitywindowid;
    private Double llinearitywindowid;
    private Double similarity;
    private Double polarity;
    private Double area;
    private Double bigarea;
    private Double perarea;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private String comImageBase64;
    private String com3dImageBase64;
    private String historyDefectRecord;
    private String remark;



}
