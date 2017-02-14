package com.wechatweb.util;

import java.io.*;

/**
 * Created by xiangleiliu on 2017/2/14.
 */
public class IOHelper {

    public static void writeToOutpusStream(OutputStream os, InputStream fis) throws IOException {
        byte[] bis = new byte[1024];
        while (-1 != fis.read(bis)) {
            os.write(bis);
        }
    }

    public static boolean fromIputStreamToFile(InputStream is,
                                               String outfilepath) {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;

        try {
            inBuff = new BufferedInputStream(is);

            outBuff = new BufferedOutputStream(
                    new FileOutputStream(outfilepath));

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (inBuff != null)

                    inBuff.close();
                if (outBuff != null)
                    outBuff.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
