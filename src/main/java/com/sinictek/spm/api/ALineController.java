package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.TestJavaDLLService;
import com.sinictek.spm.service.SPcbService;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import net.iharder.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.*;

/**
 * <p>
 * 线体总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
@Controller
//@RefreshScope
@RequestMapping("/aLine")
public class ALineController {


    @Autowired
    SPcbService sPcbService;
   /* @Autowired
    private ContextRefresher contextRefresher;*/
    /*@Autowired
    private ValueConfig valueConfig;*/

    @GetMapping("pcbLine")
    public ModelAndView showPcbLine()  {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/pcbLineData_aoi";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //ModelAndView mv = new ModelAndView("spi/pcbLineData");
        mv.addObject("hChartColor", ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        return  mv;
    }

    @GetMapping("pre/pcbfovView")
    public ModelAndView showPcbFovView(){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "/aoi/pcbfovImageView_aoi";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //valueConfig.setStaticLocations("classpath:/META-INF/resources/, classpath:/META-INF/resources/webjars/,classpath:/META-INF/static/,classpath:/static/,file://192.168.1.123\\aoi_db");
        //testYml.setStaticLocations("classpath:/META-INF/resources/, classpath:/META-INF/resources/webjars/,classpath:/META-INF/static/,classpath:/static/,file://192.168.1.148\\aoi_db");
        //System.out.println(valueConfig.getStaticLocations());
        //ImageIcon imageIcon = new ImageIcon("file://192.168.1.123/aoi_db/WholeImage/BoardImage.jpg");
        //Image image = imageIcon.getImage();
        //imageIcon.getImage().getHeight();
        //ConstParam.CONST_RESOUCE_IMAGEPATH="file://192.168.1.123/AOI_DB/";
        //new Thread(() -> contextRefresher.refresh()).start();

        return mv;
    }

    @GetMapping("getAoiImageByte")
    @ResponseBody
    public ApiResponse getImage(@RequestParam String path){
        //String Image3DPath = "\\\\127.0.0.1\\aoi_db\\20201028205455\\Component\\C328_ 1_774_92.3ddata_BF";
        if(StringUtils.isEmpty(path)){
            return  new ApiResponse(false,"PATH_IS_EMPTY",null);
        }else{
            try {
                //File file = new File(Image3DPath);
                //int[] arr = UtilLibrary.test(Image3DPath);
                //byte[] arrBy = new BinaryReaderIO(file).read(1000000);
                //int i1= BinaryReaderIO.readInt32();
                /*ObjectInputStream in = new ObjectInputStream(new FileInputStream(Image3DPath));
                System.out.println("obj1 " + in.readObject());    //读取字面值常量*/
                //System.out.println("obj1 " + in.readObject().toString());    //读取字面值常量*/
               byte[] arrByte =  ConstPublicClassUtil.getFileByte(path);

               return  new ApiResponse(true,"ok",Base64.encodeBytes(arrByte)) ;
            } catch (Exception  e) {
                e.printStackTrace();
                return ApiResponse.failed(e.getMessage());
            }
        }
    }

    @GetMapping("getAoiArray3DImage")
    @ResponseBody
    public ApiResponse getImageBy3D(@RequestParam String path,@RequestParam String heigthPath){

        if(StringUtils.isEmpty(path) ){
            return  new ApiResponse(false,"PATH_IS_EMPTY",null);
        }else{
            try {

                byte[] arrByte =  ConstPublicClassUtil.getFileByte(path);
                int[] arrDb=null;
                int iMax=0;
                int iMin=0;
                //get 3D height
                if(StringUtils.isEmpty(heigthPath) ==false){
                    IntByReference intByReferenceHeight = new IntByReference();
                    IntByReference intByReferenceImage = TestJavaDLLService.TestJavaDLL.instaneDll.get3DHeightByFilePath(heigthPath,intByReferenceHeight);
                    arrDb= intByReferenceImage.getPointer().getIntArray(0,intByReferenceHeight.getValue());
                     iMax=Arrays.stream(arrDb).max().getAsInt();
                     iMin=Arrays.stream(arrDb).min().getAsInt();
                }
                //double[] arrRealDb = new double[intByReferenceHeight.getValue()];

                /*for(int i=0;i<arrRealDb.length;i++){
                    arrRealDb[i] = arrDb[i];
                    if(arrRealDb[i]>dMax){
                        dMax = arrRealDb[i];
                    }
                    if(arrRealDb[i]<dMin){
                        dMin = arrRealDb[i];
                    }
                }*/
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("base64Image",Base64.encodeBytes(arrByte));
                map.put("arrayHeightBy3D",arrDb);
                map.put("dMaxHeight",iMax+iMax*5);
                map.put("dMinHeight",iMin-iMax*5);

                return  new ApiResponse(true,"ok",map) ;
            } catch (Exception  e) {
                e.printStackTrace();
                return ApiResponse.failed(e.getMessage());
            }
        }


    }


    //public ApiResponse get
}

