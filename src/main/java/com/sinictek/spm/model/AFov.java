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
@TableName("a_fov")
@Getter
@Setter
public class AFov implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private Integer aoiMode;
    private String pcbImagePath;
    private String boardposition;
    private String pcbImageBase64;
    private Integer fovIndex;
    private String fovposition;
    private String fovimageInfo;
    private String fovimageBase64;
    private String fov3dImageBase64;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private String remark;

    private  String create_time;

}
