
package com.sinictek.spm.model.utils;


/*import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;*/

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

import java.lang.ref.Reference;

/**
 * @Author sinictek-pd
 * @Date 2020/7/13 18:36
 * @Version 1.0
 */

public class TestJavaDLLService   {
   public interface TestJavaDLL extends Library {
        TestJavaDLL instaneDll =  Native.loadLibrary("lib/TestJavaDLL", TestJavaDLL.class);
        int test(int a,int b);
        IntByReference get3DHeightByFilePath(String path, IntByReference iLength);
    }
    //ShapeMaskToPointCPP
    //TestJavaDLL instanceDll =  Native.loadLibrary("TestJavaDLL", TestJavaDLL.class);
    //public IntPtr qlz_decompress
   /* int CPPadd(int a,int b);
    void CPPsay(String str);
    boolean isCOM1(String portName);
    public int AddTest(int a,int b);*/
    //public int Add(int a,int b);

    //public int[] Get3DHeightByteByFilePath(String path);
    //public FloatByReference ConvertToPoint(byte[] bytes, int charLength, int[] rLength);
    //public Object DoDeserializeObject(byte[] bytes);
}

