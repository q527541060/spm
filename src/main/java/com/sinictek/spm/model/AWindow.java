package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("a_window")
@Getter
@Setter
public class AWindow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long comid;
    private String pcbIdLine;
    private Integer aoiMode;
    private Integer arrayIndex;
    private Integer fovIndex;
    private String partdesignate;
    private Integer windowIndex;
    private Integer result;
    private String windowposition;
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
    private Double pervolume;
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
    private String algorithmparam;
    private String winImageBase64;
    private String win3dImageBase64;
    private String historyDefectRecord;

    private  String create_time;

}
