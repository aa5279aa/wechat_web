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

    public static void writerStrByCode(OutputStream os, String outcode,
                                       String str) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(os, outcode));
            writer.write(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
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

    public static String readStrByCode(InputStream is, String code) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(is, code));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String fromIputStreamToString(InputStream is) {
        if (is == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        try {
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toString();
    }
}
