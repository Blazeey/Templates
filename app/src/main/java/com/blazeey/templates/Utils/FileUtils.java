package com.blazeey.templates.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;

public class FileUtils {

    public static void copyFolder(String sourcePath, String destPath) {
        File folder = new File(destPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File sFile = new File(sourcePath);
        if (sFile.exists()) {
            for (File inFile : sFile.listFiles()) {
                if (inFile.isDirectory()) {
                    copyFolder(inFile.getAbsolutePath(), destPath + "/" + inFile.getName());
                } else {
                    copyFile(inFile.getAbsolutePath(), destPath + "/" + inFile.getName());
                }
            }
        }
    }

    public static void copyFile(String source, String destination) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            myInput = new FileInputStream(source);

            File file = new File(destination);
            file.getParentFile().mkdirs();
            myOutput = new FileOutputStream(destination);

            byte[] buffer = new byte[1024 * 4];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                myOutput.flush();
                myOutput.close();
                myInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFileFromAssests(Context myContext, String assetfile, String destination) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            myInput = myContext.getAssets().open(assetfile);
            myOutput = new FileOutputStream(destination);
            byte[] buffer = new byte[1024 * 4];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                myOutput.flush();
                myOutput.close();
                myInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void deleteFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            for (File inFile : folder.listFiles()) {
                if (inFile.isDirectory())
                    deleteFolder(inFile.getAbsolutePath());
                else
                    inFile.delete();
            }
            folder.delete();
        }
    }

    public static String realAllText(String path) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = new FileInputStream(path);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
                returnString.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static void writeText(String filePath, String text) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileOutputStream is = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            w.write(text);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileChecksum(String filePath) {
        if ((new File(filePath)).exists()) {
            return getMD5Checksum(filePath);
        }
        return "";
    }

    public static byte[] createChecksum(String filename) {
        try {
            MessageDigest complete = MessageDigest.getInstance("MD5");
            InputStream fis = new FileInputStream(filename);
            byte[] buffer = new byte[1024];
            int numRead;
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            fis.close();
            return complete.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static String getMD5Checksum(String filename) {
        byte[] b = createChecksum(filename);
        String result = "";

        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
