package com.exza.tech.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecretService {
    private final static char[] hexArray ="0123456789ABCDEF".toCharArray();

    private final static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private String ALGO = "SHA-256" ;

    public String encryptAadahr(String aadharnumber,String secret) {
        return AES.encrypt(aadharnumber,secret);
    }
    public String decryptAadhar(String aadharnumber,String secret) {
        return AES.decrypt(aadharnumber,secret);
    }
    public Map<String,String> encryptMyKey(String secret) throws NoSuchAlgorithmException {
        String data = secret;
        byte[] salt = createSalt();
        String hash = generatehash(salt,data,ALGO);
        String  encoded = Base64.getEncoder().encodeToString(salt);
        Map<String,String> map =new HashMap<>();
        map.put(encoded,hash);
        //byte[] decoded = Base64.getDecoder().decode(encoded);
        return  map;
    }

    public  String decryptMyKey(String salt, String secret) throws NoSuchAlgorithmException {
        byte[]  encoded = Base64.getDecoder().decode(salt);
        String data = secret;
        String hash = generatehash(encoded,data,ALGO);
        return  hash;
    }

    private static byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }

    private static String generatehash(byte[] salt , String data, String aldo) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance(aldo);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(data.getBytes());
        return bytestoString(hash);
    }

    private static String bytestoString(byte[] hash) {
        char[] hexChars = new char[hash.length*2];
        for (int j=0 ;j<hash.length ;j++){
            int v= hash[j] & 0xFF ;
            hexChars[j*2] = hexArray[v >>> 4];
            hexChars[j*2+1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);


    }
    public static Map<String,String> generateWithRsa() throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        OutputStream out;
        //set size of key
        kpg.initialize(2048);
        //generate pair of public and private keys
        KeyPair kp = kpg.generateKeyPair();
        //make public and private keys
        Key pub = kp.getPublic();
        Key pvt = kp.getPrivate();
        String encodedPublicKey = Base64.getEncoder().encodeToString(pub.getEncoded());
        String encodedPrivateKey = Base64.getEncoder().encodeToString(pvt.getEncoded());
        String publickey=convertToPublicKey(encodedPublicKey);
        String privatekey= convertToPrivateKey(encodedPrivateKey);
        
        //saving keys in binary format
//        File pvtfile = new File("private.key");
//        FileWriter writer = new FileWriter(pvtfile);
//        writer.write(privatekey);
//        writer.flush();
//        writer.close();
//        File pubfile = new File("public.key");
//        FileWriter writer1 = new FileWriter(pubfile);
//        writer1.write(publickey);
//        writer1.flush();
//        writer1.close();
        // prints "Private key format"
        Map<String,String> map =new HashMap<>();
        map.put("publickey",publickey);
        map.put("privatekey",privatekey);
        return map;
    }

    // Add BEGIN and END comments
    private static String convertToPublicKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(key);
        result.append("\n-----END PUBLIC KEY-----");
        return result.toString();
    }

    private static String convertToPrivateKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PRIVATE KEY-----\n");
        result.append(key);
        result.append("\n-----END PRIVATE KEY-----");
        return result.toString();
    }


    public String  encodepassword(String password){
        CharSequence passwordSequence = password ;

        return bCryptPasswordEncoder.encode(passwordSequence);
    }
    public boolean validatePassword(String rawpassword , String hashedPassword){
        CharSequence passwordSequence = rawpassword ;

        return bCryptPasswordEncoder.matches(passwordSequence,hashedPassword);
    }

    
    
  public  File generateFile(String privatekey) throws IOException {
       File pvtfile = new File("private.key");
       FileWriter writer = new FileWriter(pvtfile);
       writer.write(privatekey);
       writer.flush();
       writer.close();
       return pvtfile ;
    }

}
