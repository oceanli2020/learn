package com.lihaiyang.learn.core.utils;


import com.lihaiyang.learn.core.config.MobileProperties;
import org.apache.commons.text.StringEscapeUtils;

public class PasswordUtils {

    private static MobileProperties mobileProperties = SpringContextHolder.getBean(MobileProperties.class);


    /**
     * 将明文转化成密文进行比对
     *
     * @param plainPassword 输入的密码  明文
     * @param password 数据库存储的密码 密文
     * */
    public static boolean validatePassword(String plainPassword, String password) {
       String plain = StringEscapeUtils.unescapeHtml4(plainPassword);
        byte[] salt = DigestUtils.decodeHex(password.substring(0, 16));
        byte[] hashPassword = DigestUtils.sha1(plain.getBytes(), salt, mobileProperties.getPasswordIterations());
        return password.equals(DigestUtils.encodeHex(salt) + DigestUtils.encodeHex(hashPassword));

    }

    public static String entryptPassword(String plainPassword) {
        String plain = StringEscapeUtils.unescapeHtml4(plainPassword);
        byte[] salt = DigestUtils.generateSalt(8);
        byte[] hashPassword = DigestUtils.sha1(plain.getBytes(), salt, mobileProperties.getPasswordIterations());
        return DigestUtils.encodeHex(salt) + DigestUtils.encodeHex(hashPassword);
    }
}


