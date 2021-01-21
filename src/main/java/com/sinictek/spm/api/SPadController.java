package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SPad;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.Base64Helper;
import com.sinictek.spm.model.utils.QuickLZ;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SPadService;
import com.sinictek.spm.service.SPcbService;
//import it.sauronsoftware.base64.Base64;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * <p>
 * 焊盘 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
@Controller
@RequestMapping("/sPad")
public class SPadController {
    @Autowired
    SPadService sPadService;

    @Autowired
    SPcbService sPcbService;

    @ResponseBody
    @GetMapping("/addPad")  //@Param("pcbIdLine") String pcbIdLine
    public ApiResponse<String> addPadData () throws Exception{

        File file = new File("D:\\EYSPI\\DataExport\\PCBA\\20200605\\CJ19003-1CR_hhhhh_20200605141428.jpg");


       byte[] data = new byte[1024];//定义一个1K大小的数组
        FileInputStream stream = null;//指定要读取的图片
        try {
            stream = new FileInputStream(file);
            stream.read(data);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<List<SPad>> lst = new ArrayList<List<SPad>>();
        System.out.println("10万 add start.."+ StringTimeUtils.getTimeDateToString(new Date()));
        List<SPad> intLst = new ArrayList<SPad>();
        for (int i = 0; i < 10000; i++) {
            SPad sPad = new SPad();
            sPad.setPadId(i+"");
            sPad.setPcbidLine("124#SPI02");
            //sPad.setPad2dImage( data );
            intLst.add(sPad);
        }
        System.out.println("--------------------."+StringTimeUtils.getTimeDateToString(new Date()));
            sPadService.insertBatch(intLst,2000);
    //intLst.parallelStream().forEach(i->{
    // });
        System.out.println("10万 add end.."+StringTimeUtils.getTimeDateToString(new Date()));
        return new ApiResponse<String>(true,null,"成功插入");

    }





    @ResponseBody
    @GetMapping("padList")
    public ApiResponse getPadListWithPcbIDline(@RequestParam("pcbIdLine") String pcbIdLine,
                                               @RequestParam("defectTypeCode") String defectTypeCode) throws Exception{

        pcbIdLine = pcbIdLine.replace("=====","#");
        Map<String,Object> pcbListMap = new HashMap<String,Object>();
        pcbListMap.put("pcbIdLine",pcbIdLine);
        List<SPcb> sPcblst = sPcbService.selectByMap(pcbListMap);
        String padTableName =null;
        if(sPcblst!=null&&sPcblst.size()>0){
            padTableName = sPcblst.get(0).getPadTableName();
        }else{
            return new ApiResponse(true,null,null,null);
        }
        List<SPad> lstPad = sPadService.getPadListWithPCbidLineService(padTableName ,pcbIdLine,defectTypeCode);

        if(lstPad!=null && lstPad.size()>0){
            String base64Str =null;
            switch (ConstParam.DEFAULTSETTING_showPad2DImageMode){
                case 1:
                    //base64Str = lstPad.getPad2dImageBase64();//Base64Helper.decompressData(sPad.getPad2dImageBase64());//
                    //break;
                case 2:
                    //调用路径
                    for (int i = 0; i < lstPad.size(); i++) {
                        if(StringUtils.isEmpty(lstPad.get(i).getPad2dImageBase64()) ==false){
                            try {
                                //net.iharder.Base64.decode()
                                //Base64Utils.decodeFromString(lstPad.get(i).getPad2dImageBase64());
                                //byte[] byte64 = ;
                                //byte[] byte641 = Base64Utils.decodeFromString(lstPad.get(i).getPad2dImageBase64());
                                lstPad.get(i).setPad2dImageBase64(
                                        Base64.getEncoder().encodeToString(QuickLZ.decompress(
                                                net.iharder.Base64.decode(net.iharder.Base64.decode(lstPad.get(i).getPad2dImageBase64()))
                                        ))
                                );
                                //Base64.Decoder()
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    break;
                case 0:
                default:
                    for (int i = 0; i < lstPad.size(); i++) {
                        if(lstPad.get(i).getPad2dImage()!=null && lstPad.get(i).getPad2dImage().length>0){
                            try {
                                //net.iharder.Base64.decode()
                                //byte[] byte64 = net.iharder.Base64.decode(lstPad.get(i).getPad2dImage());
                                lstPad.get(i).setPad2dImageBase64(
                                        Base64.getEncoder().encodeToString(QuickLZ.decompress(
                                                net.iharder.Base64.decode(lstPad.get(i).getPad2dImage())
                                        ))
                                );
                                //Base64.Decoder()
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    //byte[] byte64 = net.iharder.Base64.decode(sPad.getPad2dImage()); //net.iharder.Base64.decode(sPad.getPad2dImage());
                    //base64Str = Base64.getEncoder().encodeToString(QuickLZ.decompress(byte64));//QuickLZ.decompress(byte64));
                    break;
            }
        }
        return  new ApiResponse(true,null,null,lstPad);
    }

    @ResponseBody
    @PostMapping("padImage")
    public ApiResponse getPad2DImage(@RequestParam("pcbidLine") String pcbidLine,
                                     @RequestParam("padId") String padId) throws IOException, DataFormatException {

        /*byte[] byte64 = net.iharder.Base64.decode(pad2dImage);
        String base64Str = Base64.getEncoder().encodeToString(QuickLZ.decompress(byte64));
        return new ApiResponse(true,null,base64Str,null);*/
        //UnJzSkFBQ3lDUUFBLzlqLzRBQVFTa1pKUmdBQkFRRUFZQUJnQUFELzJ3QkRBQWdHQmdjR0JRZ0hCd2NKQ1FnS0RCUU5EQXNMREJrU0V3OFVIUm9mSGgwYUhCd2dKQzRuSUNJc0l4d2NLRGNwTERBeE5EUTBIeWM1UFRneVBDNHpOREwvMndCREFRa0pDUXdMREJnTkRSZ3lJUndoTWpJeU1qSXlNakl5TWpJeU1qSXlNakl5TWpJeU1qSXlNakl5TWpJeU1qSXlNakl5TWpJeU1qSXlNakl5TWpJeU1qTC93QUFSQ0FDWkFERURBU0lBQWhFQkF4RUIvOFFBSHdBQUFRVUJBUUVCQVFFQUFBQUFBQUFBQUFFQ0F3UUZCZ2NJQ1FvTC84UUF0UkFBQWdFREF3SUVBd1VGQkFRQUFBRjlBUUlEQUFRUkJSSWhNVUVHRTFGaEJ5SnhGREtCa2FFSUkwS3h3UlZTMGZBa00ySnlnZ2tLRmhjWUdSb2xKaWNvS1NvME5UWTNPRGs2UTBSRlJrZElTVXBUVkZWV1YxaFpXbU5rWldabmFHbHFjM1IxZG5kNGVYcURoSVdHaDRpSmlwS1RsSldXbDVpWm1xS2pwS1dtcDZpcHFyS3p0TFcydDdpNXVzTER4TVhHeDhqSnl0TFQxTlhXMTlqWjJ1SGk0K1RsNXVmbzZlcng4dlAwOWZiMytQbjYvOFFBSHdFQUF3RUJBUUVCQVFFQkFRQUFBQUFBQUFFQ0F3UUZCZ2NJQ1FvTC84UUF0UkVBQWdFQ0JBUURCQWNGQkFRQUFRSjNBQUVDQXhFRUJTRXhCaEpCVVFkaGNSTWlNb0VJRkVLUm9iSEJDU016VXZBVlluTFJDaFlrTk9FbDhSY1lHUm9tSnlncEtqVTJOemc1T2tORVJVWkhTRWxLVTFSVlZsZFlXVnBqWkdWbVoyaHBhbk4wZFhaM2VIbDZnb09FaFlhSGlJbUtrcE9VbFphWG1KbWFvcU9rcGFhbnFLbXFzck8wdGJhM3VMbTZ3c1BFeGNiSHlNbkswdFBVMWRiWDJObmE0dVBrNWVibjZPbnE4dlAwOWZiMytQbjYvOW9BREFNQkFBSVJBeEVBUHdEeWJhVHhpbWtZNjEwYTZQRktZL011Skl6c3lWamkzRWZyV1RjV3lKdkpsQkdjS2RoR2FocXg1RUo4eXVrVWdjVXZiUGVuT3haVlVnWlhqTlRRUTc1VlJjYmoxYis3U0x1VnlENlVjOUszRzB5M0tFaThZRlY2TEdXR2ZjOXF6SnJXV0lzSFFncnpuRk94S2xjcTVQOEFrVVV1NGYzYUtSWnZ5M1VyWHUxWGorV0ptSzdpVDE2WjlhZFBJbDRybzVKSlVmS1A0Q2UvNlZMSGJ4ZmFDME1hSXJEL0FGaE9EK0hUMi9Pb3BJMTg3ZkhJTjJPWlc0MkgxOVAxcnB0Zlk0K2FMdGJTeGd5UnRGT1VjQU1wclYwZ1JyRGN5dUJqSVhyMDcxbmFoZW0rdWhMc1ZPT3E5Vzl6MkZXOU5qdTN0NXhiTXJBOE1qOXg2NTlldnIxclI0UlJxY2twcFB0cjkxMG12eHQzWnZPL0pkNkZzWEN5TXl3bGQyUVI2QnZ6eFV5S2JpT1FzeU53V0hBSENna2puMTlxWFRvb0JhR1FxaU52MnVOK1dCSDA5OGZYMnA5M0dGVWt4NWJEWXlCazhkZVB6L0tsT2xLbkp4bnVqbWxKTnVLTVB6N1AvbmczNTBWVTIrNG9ybnV6czltdTUxYTNnZ1pta3Q5K0J1VmlOd0ovem4vNjFaa3Q3SkpIdGRSc2taQzhZVVlQekE5T1I2L25XaE5KRmRXeXpEZVFxbkh5OE1jWXg3ZHFwWEZpd2laeXVHZVJBQ1c5V0ZkdUdiOXRCcnV2ek9PQ2l2aVdwaHluL1NYT09OeDZWc2FQOHNjaDNBRU1DTTlEN1Zpdi9yMjQvaU9LMDlNWlVndUdkUXdCR0I2R3VGZkdkZFZlNVkxN1Iya2U0Q01Hek5uSkpJd0VVZy9rS1M5WkJHeXhxc1lLYzViazhlbjRWbjJsMFZhWkNHQ3ZJdmZHT0J6K2dyUXVKb1pkUFpreHVaVDkwOUIweDYrdjRWM1YzZWE5SS84QXBLT1NVSEdXeHpXQjYwVWVVLzhBZUg1MFZ3bmVkVXA4cURBTEJjOGc4SEk1WHZ4NlVYczhSMCtNTW9FcXlSdXdNWUpIektNRStuWDIvT2xMcThHNlBBWlZJSXh4ajgrZW4xNXJNbnVWQVdOVVoyQlJ2bE9RZm5IeTEzWVoyclE5VitaNTBZY3p1KzVsWE1USk41anNoV1FramFjNC93QUt2MklhM1Z3c2ZtYnlNRWpnL2orTlZ0U3VMZDdsa3MwS1E4Y01PYy8vQUZzNHEvbzZUdks2elp5aWdxSDQ0N1lyalh4YUhWTnYyZDJPOGc0dUZ3cTRkY3AyKzR1TWZyVWE0aEV6Qk1NRlBiSXEwdHZLdHhPcU1vUG5ESVk5ZmtVNC93QStsVFRCSTdXVHpObTVvMkIyS0FBTWRxN01RdmZYcEgvMGxHUFAwT2QrMm4vbmpGLzN4UlZmOGFLNGVaOXp0NUk5anFyWVRYTHllZU9BeWxTVnlSeVIyeWNmMXFHNGpWTFpXRzBLc2k3d01ObjUxNzhaL2wycDBoZFo1Y0lyYlZCQjU1eHp1SnpnOXgrRlJYakZWTEVyNWJTS2NJTnArOENjRDYxM1lYU3ZEMVg1bkJadVJpd3hKYzNwUXlMR09jTTJjY2ZTdDkxbUVpdEpKaUlLRlprSVhzTVlKNjlLcldOcjVVb1J2blg3ek9pZ2xlK00vU3I4bTEwTXNhc3pSc3dYR091Ty9IUGF1V0tOS2srYVN0c1ZyYVRMTjVnWU9aZWRveWNiRUdTZjEvR3BMdUF4MjBpUnpiZ3VjcytDYytnQTU5ZmFvdE9sSGt5WmtrVlZtM01nVGRuQ3IyUHVLdW1JUitkTk8vbGNmZVVjcVFRY2cvaCt0ZFZmNGw2Ui93RFNVWlRmTEszOWYxOTV6UDJRL3dEUFFmclJXaDl1Zy81Kzd2OEFJVVZ4MmozT24yayszOWZjYVl1RlZwSXJkRlVFZGVveWYxNkhIOUtvMzdJRUErK0N5WU9mOXJKRk1uYVQ3T3p4dmxneDNLZWdHUjJQMXJQRjRacGxWMUFHNVFSakc3a2RhNjhOTDkvRDFYNWtVNlAya1h0THRyeUtBdXptTzNtempqSVU5aVIrQnEzSkxtM2tqbEFTWXNBR0JQSjdFSDZmMHJPMDk0clc1VkpKSldpa0k4bzdzQlFEemtjMXAzTWFvc2tiZ01wSmFKWS9tNEhyOUIycm1XenNQVDJxY3RpcllyTmF5emxzdXhQbEhhM3pjcU9mNVZOTGVvOXRPQVR0MkViaDNQOEErdW80bmtXT1lsTjh5dUR3T2NiRkFxZHlIc3A5MnhGd2NLaDc0UFd1aXNyU1hwSC9BTkpSTlZxVXVabzV2Qjk2S01mVDhxSzRUdE55SzNUY2ZtYmVGSnd4eUhQdngvbkZWR3NIaWxpazJydFoweGs4akpIV3J5bnljSWtUcm1RZ2ZNUHpBL3owb3V3VnN2bmNzVVpHQlBHN0xEK245YTdzTC9HaDZyOHpqVTJuWmRTQzAwKzNZQlptWU9Hd0pGNUFQb2ZvZnpyUlBscmQ3anZhVDVkeGpJWStvd01ZQXp4V0RiMzB1UW1ZOEZzbG5IWFBxYTFNZ0lxSXdkM1hpUU1DRUgvNnpYTkdTRlVqTG05NWhEY01aYmgyVXFESmpCWEpIeXJqbjhPS3VSQ0ZiZDNiTG9WS2tNQ0NPT2VuVCt0WjBxdkZjVFJ0Szduek5oSlhxUWkvalV0emN0YjJNeXRLU3h3aWgxSVkrL1ArZWE2c1E3VFRmYVAvQUtTaUpSNWt1WHFaZmwydi9QUS85OGYvQUY2S3A3ajYwVng4L2tkbksrNVlTNGxVajk0M3k5Qm1uWHQ5UGU3UE8yL0xnRGFvSHA2VkFPRG1uTXdhSUlGQWJPZDlYaHBLRldMazdKTmZtSnhWNzJHZGdNVTlKSkV5RVlqSXdjSHRUTXNPby9JMFpKNEMvblYvVlozdGVQOEE0RkgvQURHVHczbHhIdTJ5dU1ua2pyMEgrQXFGaVdKSkpKOXpUbzNraWpkQWZsazY4ZGFUSEdlYWpFU2pLcDdydWxaZXRsYS96c0phTVpnZjNUK2RGUHo5S0t4S0RwMHBQVHJUaDM0NHBLUklsTDM5NlQwb3llYUJoNzB2WGlrcFIxR0tBRndmUVVVdTRlbEZBaG80SE5LVGdmMW8vaEZKUUFaOTZNZTRvSDNhVWQ2QUV4enpSMHB6ZGFUK0w4YUFEY2FLbm9wMkZjLy8yUT09
        //RrsJAACyCQAA/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCACZADEDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDybaTximkY610a6PFKY/MuJIzsyVji3EfrWTcWyJvJlBGcKdhGahqx5EJ8yukUgcUvbPenOxZVUgZXjNTQQ75VRcbj1b+7SLuVyD6Uc9K3G0y3KEi8YFV6LGWGfc9qzJrWWIsHQgrznFOxKlcq5P8AkUUu4f3aKRZvy3UrXu1Xj+WJmK7iT16Z9adPIl4ro5JJUfKP4Ce/6VLHbxfaC0MaIrD/AFhOD+HT2/OopI187fHIN2OZW42H19P1rptfY4+aLtbSxgyRtFOUcAMprV0gRrDcyuBjIXr071nahem+uhLsVOOq9W9z2FW9Nju3t5xbMrA8Mj9x659evr1rR4RRqckppPtr910mvxt3ZvO/Jd6FsXCyMywld2QR6BvzxUyKbiOQsyNwWHAHCgkjn19qXTooBaGQqiNv2uN+WBH098fX2p93GFUkx5bDYyBk8dePz/KlOlKnJxnujmlJNuKMPz7P/ng350VU2+4ornuzs9mu51a3ggZmkt9+BuViNwJ/zn/61Zkt7JJHtdRskZC8YUYPzA9OR6/nWhNJFdWyzDeQqnHy8McYx7dqpXFiwiZyuGeRACW9WFduGb9tBruvzOOCiviWphyn/SXOONx6VsaP8sch3AEMCM9D7Viv/r24/iOK09MZUguGdQwBGB6GuFfGddVe5Y17R2ke4CMGzNnJJIwEUg/kKS9ZBGyxqsYKc5bk8en4Vn2l0VaZCGCvIvfGOBz+grQuJoZdPZkxuZT909B0x6+v4V3V3ea9I/8ApKOSUHGWxzWB60UeU/8AeH50VwnedUp8qDALBc8g8HI5Xvx6UXs8R0+MMoEqyRuwMYJHzKME+nX2/OlLq8G6PAZVIIxxj8+en15rMnuVAWNUZ2BRvlOQfnHy13YZ2rQ9V+Z50Yczu+5lXMTJN5jshWQkjac4/wAKv2Ia3VwsfmbyMEjg/j+NVtSuLd7lks0KQ8cMOc//AFs4q/o6TvK6zZyigqH447YrjXxaHVNv2d2O8g4uFwq4dcp2+4uMfrUa4hEzBMMFPbIq0tvKtxOqMoPnDIY9fkU4/wA+lTTBI7WTzNm5o2B2KAAMdq7MQvfXpH/0lGPP0Od+2n/njF/3xRVf8aK4eZ9zt5I9jqrYTXLyeeOAylSVyRyR2ycf1qG4jVLZWG0Ksi7wMNn5178Z/l2p0hdZ5cIrbVBB55xzuJzg9x+FRXjFVLEr5bSKcINp+8CcD613YXSvD1X5nBZuRiwxJc3pQyLGOcM2ccfSt91mEitJJiIKFZkIXsMYJ69KrWNr5UoRvnX7zOigle+M/Sr8m10MsaszRswXGOuO/HPauWKNKk+aStsVraTLN5gYOZedoycbEGSf1/GpLuAx20iRzbgucs+Cc+gA59faotOlHkyZkkVVm3MgTdnCr2PuKumIR+dNO/lcfeUcqQQcg/h+tdVf4l6R/wDSUZTfLK39f195zP2Q/wDPQfrRWh9ug/5+7v8AIUVx2j3On2k+39fcaYuFVpIrdFUEdeoyf16HH9Ko37IEA++CyYOf9rJFMnaT7Ozxvlgx3KegGR2P1rPF4ZplV1AG5QRjG7kda68NL9/D1X5kU6P2kXtLtryKAuzmO3mzjjIU9iR+Bq3JLm3kjlASYsAGBPJ7EH6f0rO094rW5VJJJWikI8o7sBQDzkc1p3MaoskbgMpJaJY/m4Hr9B2rmWzsPT2qctirYrNayzlsuxPlHa3zcqOf5VNLeo9tOATt2Ebh3P8A+uo4nkWOYlN8yuDwOcbFAqdyHsp92xFwcKh74PWuisrSXpH/ANJRNVqUuZo5vB96KMfT8qK4TtNyK3TcfmbeFJwxyHPvx/nFVGsHilik2rtZ0xk8jJHWrynycIkTrmQgfMPzA/z0ouwVsvncsUZGBPG7LD+n9a7sL/Gh6r8zjU2nZdSC00+3YBZmYOGwJF5APofofzrRPlrd7jvaT5dxjIY+owMYAzxWDb30uQmY8FslnHXPqa1MgIqIwd3XiQMCEH/6zXNGSFUjLm95hDcMZbh2UqDJjBXJHyrjn8OKuRCFbd3bLoVKkMCCOOenT+tZ0qvFcTRtK7nzNhJXqQi/jUtzctb2MytKSxwih1IY+/P+ea6sQ7TTfaP/AKSiJR5kuXqZfl2v/PQ/98f/AF6Kp7j60Vx8/kdnK+5YS4lUj943y9BmnXt9Pe7PO2/LgDaoHp6VAODmnMwaIIFAbOd9XhpKFWLk7JNfmJxV72GdgMU9JJEyEYjIwcHtTMsOo/I0ZJ4C/nV/VZ3teP8A4FH/ADGTw3lxHu2yuMnkjr0H+AqFiWJJJJ9zTo3kijdAflk68daTHGeajESjKp7rulZetla/zsJaMZgf3T+dFPz9KKxKDp0pPTrTh344pKRIlL396T0oyeaBh70vXikpR1GKAFwfQUUu4elFAho4HNKTgf1o/hFJQAZ96Me4oH3aUd6AExzzR0pzdaT+L8aADcaKnop2Fc//2Q==
        pcbidLine = pcbidLine.replace("=====","#");
        Map<String,Object> pcbListMap = new HashMap<String,Object>();
        pcbListMap.put("pcbidLine",pcbidLine);
        List<SPcb> sPcblst = sPcbService.selectByMap(pcbListMap);
        String padTableName =null;
        if(sPcblst!=null&&sPcblst.size()>0)
        {

            padTableName = sPcblst.get(0).getPadTableName();

            SPad sPad = sPadService.getPadWithPCbidLineService(padTableName,pcbidLine,padId);
            if(sPad!=null )
            {
                String base64Str =null;
                switch (ConstParam.DEFAULTSETTING_showPad2DImageMode){
                    case 1:
                        base64Str = sPad.getPad2dImageBase64();//Base64Helper.decompressData(sPad.getPad2dImageBase64());//
                        break;
                    case 2:
                        //调用路径
                        break;
                    case 0:
                    default:
                        byte[] byte64 = net.iharder.Base64.decode(sPad.getPad2dImage()); //net.iharder.Base64.decode(sPad.getPad2dImage());
                        base64Str = Base64.getEncoder().encodeToString(QuickLZ.decompress(byte64));//QuickLZ.decompress(byte64));
                        break;
                }

                return new ApiResponse(true,null,base64Str,null);
            }
            else{
                return new ApiResponse(false,"IMAGE_IS_NOT_EXIST",null,null);
            }

        }else{
            return new ApiResponse(true,null,null,null);
        }
    }

    @ResponseBody
    @GetMapping("padDefaultTypeInfo")
    public ApiResponse getPadDefaultTypeInfoWithPCBtable(@RequestParam("id") Integer id){
        SPcb sPcb = sPcbService.selectById(id);
        String padSeriesData=null;
        padSeriesData = getPadDefaultTypeContent(sPcb);
        return  new ApiResponse(true,null,padSeriesData);
    }
    private String getPadDefaultTypeContent(SPcb sPcb){
        String padSeriesData="";
        padSeriesData+="[";
        if(sPcb!=null ){
            if(sPcb.getMissingCount()!=null && sPcb.getMissingCount()>0){
                padSeriesData+="[\"Missing\","+    sPcb.getMissingCount()+"],";
            }else{
                padSeriesData+="[\"Missing\","+    null+"],";
            }
            if(sPcb.getInsufficientCount()!=null && sPcb.getInsufficientCount()>0){
                padSeriesData+="[\"Insufficient\","+    sPcb.getInsufficientCount()+"],";
            }else{
                padSeriesData+="[\"Insufficient\","+    null+"],";
            }
            if(sPcb.getExcessCount()!=null && sPcb.getExcessCount()>0){
                padSeriesData+="[\"Excess\","+    sPcb.getExcessCount()+"],";
            }else{
                padSeriesData+="[\"Excess\","+    null+"],";
            }
            if(sPcb.getOverheightCount()!=null && sPcb.getOverheightCount()>0){
                padSeriesData+="[\"OverHeight\","+    sPcb.getOverheightCount()+"],";
            }else{
                padSeriesData+="[\"OverHeight\","+    null+"],";
            }
            if(sPcb.getLowheightCount()!=null && sPcb.getLowheightCount()>0){
                padSeriesData+="[\"LowHeight\","+    sPcb.getLowheightCount()+"],";
            }else{
                padSeriesData+="[\"LowHeight\","+    null+"],";
            }
            if(sPcb.getOverareaCount()!=null && sPcb.getOverareaCount()>0){
                padSeriesData+="[\"OverArea\","+    sPcb.getOverareaCount()+"],";
            }else{
                padSeriesData+="[\"OverArea\","+    null+"],";
            }
            if(sPcb.getLowareaCount()!=null && sPcb.getLowareaCount()>0){
                padSeriesData+="[\"LowArea\","+    sPcb.getLowareaCount()+"],";
            }else{
                padSeriesData+="[\"LowArea\","+    null+"],";
            }
            if(sPcb.getShiftxCount()!=null && sPcb.getShiftxCount()>0){
                padSeriesData+="[\"ShiftX\","+    sPcb.getShiftxCount()+"],";
            }else{
                padSeriesData+="[\"ShiftX\","+    null+"],";
            }
            if(sPcb.getShiftyCount()!=null && sPcb.getShiftyCount()>0){
                padSeriesData+="[\"ShiftY\","+    sPcb.getShiftyCount()+"],";
            }else{
                padSeriesData+="[\"ShiftY\","+    null+"],";
            }
            if(sPcb.getBridgeCount()!=null && sPcb.getBridgeCount()>0){
                padSeriesData+="[\"Bridge\","+    sPcb.getBridgeCount()+"],";
            }else{
                padSeriesData+="[\"Bridge\","+    null+"],";
            }
            if(sPcb.getShapeerrorCount()!=null && sPcb.getShapeerrorCount()>0){
                padSeriesData+="[\"ShapeError\","+    sPcb.getShapeerrorCount()+"],";
            }else{
                padSeriesData+="[\"ShapeError\","+    null+"],";
            }
            if(sPcb.getSmearedCount()!=null && sPcb.getSmearedCount()>0){
                padSeriesData+="[\"Smeared\","+    sPcb.getSmearedCount()+"],";
            }else{
                padSeriesData+="[\"Smeared\","+    null+"],";
            }
            if(sPcb.getPrebridgeCount()!=null && sPcb.getPrebridgeCount()>0){
                padSeriesData+="[\"PreBridge\","+    sPcb.getPrebridgeCount()+"],";
            }else{
                padSeriesData+="[\"PreBridge\","+    null+"],";
            }
            if(sPcb.getCoplanarityCount()!=null && sPcb.getCoplanarityCount()>0){
                padSeriesData+="[\"Coplanarity\","+    sPcb.getCoplanarityCount()+"],";
            }else{
                padSeriesData+="[\"Coplanarity\","+    null+"],";
            }
            if(sPcb.getPadareapercentCount()!=null && sPcb.getPadareapercentCount()>0){
                padSeriesData+="[\"PadAreaError\","+    sPcb.getPadareapercentCount()+"],";
            }else{
                padSeriesData+="[\"PadAreaError\","+    null+"],";
            }

            padSeriesData+="[\"WarpError\","+    null+"]";  //板弯错误  新增

        }
        padSeriesData+="]";

        return padSeriesData;
    }

}

