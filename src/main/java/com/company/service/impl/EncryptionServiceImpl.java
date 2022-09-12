package com.company.service.impl;

import com.company.service.EncryptionService;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptionServiceImpl implements EncryptionService {

    public static final String ALGORITHM = "SHA-1";
    public static final int RADIX = 16;
    public static final int SIGNUM = 1;


    @Override
    public String digest(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] bytes = messageDigest.digest(input.getBytes());
            BigInteger number = new BigInteger(SIGNUM, bytes);
            return number.toString(RADIX);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
