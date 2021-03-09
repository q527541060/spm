package com.sinictek.spm.model.queryBean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/11/26 10:24
 * @Version 1.0
 */
@Getter
@Setter
public class ThreePointAsCloseResponse1JsonBean {
        private String barcode;
        private List<LstPCB> LstPCB;
        private int spiPCBID;
        private int preAoiPCBID;
        private int postAoiPCBID;
}
