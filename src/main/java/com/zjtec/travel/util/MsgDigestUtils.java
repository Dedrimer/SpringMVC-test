package com.zjtec.travel.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MsgDigestUtils {
    public static String encodeSHA256(String str, String salt) {
        String saltedStr = str + salt;
        String encdeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(saltedStr.getBytes("UTF-8"));
            encdeStr = byte2Hex(hash);
            for (int i = 0; i < 2; i++) { // 继续循环2次，总共3次
                hash = messageDigest.digest((encdeStr + salt).getBytes("UTF-8"));
                encdeStr = byte2Hex(hash);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    // 随机生成盐
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[4];//仅生成4个字符
        random.nextBytes(salt);//转换为8个字节以确保不超过设置的长度
        return byte2Hex(salt);
    }
}
