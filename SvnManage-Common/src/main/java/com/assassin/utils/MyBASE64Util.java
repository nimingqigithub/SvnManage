package com.assassin.utils;

/**
 * Created by Administrator on 2017/2/7.
 */


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

public class MyBASE64Util
{
    private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };

    private static String encodeStr(byte[] str)
    {
        return new BASE64Encoder().encode(str);
    }

    private static byte[] decodeStr(String str)
    {
        byte[] result = null;
        try {
            result = new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static byte[] encryptBasedDes(String data)
    {
        byte[] result = null;
        try
        {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(DES_KEY);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(1, secretKey, random);
            result = cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static byte[] decryptBasedDes(byte[] data)
    {
        byte[] result = null;
        try
        {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(DES_KEY);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES");

            cipher.init(2, secretKey, random);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String customEncode(String str)
    {
        char[] charData = str.toCharArray();
        for (int i = charData.length - 1; i >= 0; i--) {
            charData[i] = ((char)(charData[i] ^ i * 10));
        }
        return new String(charData);
    }

    public static String encodeDataBase64AndDes(String str)
    {
        byte[] desData = encryptBasedDes(str);
        String result = encodeStr(desData);
        return result;
    }

    public static String decodeDataBase64AndDes(String str)
    {
        byte[] baseDecodeData = decodeStr(str);
        byte[] desDecodeData = decryptBasedDes(baseDecodeData);
        return new String(desDecodeData);
    }

    public static String encodeData(String str)
    {
        String customData = customEncode(str);
        String result = encodeStr(customData.getBytes());
        return result;
    }

    public static String decodeData(String str)
    {
        byte[] baseDecodeData = decodeStr(str);
        String customDecodeData = customEncode(new String(baseDecodeData));
        return new String(customDecodeData);
    }

    public static void main(String[] args)
    {
    }
}
