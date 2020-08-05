package com.sinictek.spm.model.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

/**
 * @Author sinictek-pd
 * @Date 2020/6/23 10:51
 * @Version 1.0
 */

public class BlobImpl  {

    public static byte[] blobToBytes(Blob blob) {

        BufferedInputStream is = null;

        try {

            is = new BufferedInputStream(blob.getBinaryStream());

            byte[] bytes = new byte[(int) blob.length()];

            int len = bytes.length;

            int offset = 0;

            int read = 0;

            while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {

                offset += read;

            }

            return bytes;

        } catch (Exception e) {

            return null;

        } finally {

            try {

                is.close();

                is = null;

            } catch (IOException e) {

                return null;

            }

        }

    }

    public static byte[] InputStreamToByte(InputStream is) throws IOException {

        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();

        int ch;

        while ((ch = is.read()) != -1) {

            bytestream.write(ch);

        }

        byte imgdata[] = bytestream.toByteArray();

        bytestream.close();

        return imgdata;

    }

}
