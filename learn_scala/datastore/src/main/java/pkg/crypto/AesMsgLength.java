package pkg.crypto;

import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesMsgLength {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        run("s3cret_p4assw0rd");
        run("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                "\nlaboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

    private static void run(String msg) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {// 128 bit
        // a GUID converted to hex bytes (translated for a sign bit)
        byte[] key = {-128 + 0x4c, -128 + 0xf8, -128 + 0xa5, -128 + 0x33, -128 + 0xbc, -128 + 0x83, -128 + 0x46,
                -128 + 0x17, -128 + 0x9a, -128 + 0x62, -128 + 0xa4, -128 + 0xbb, -128 + 0x10, -128 + 0x32, -128 + 0x2a, -128 + 0x75}; // Just need some bytes
        byte[] dataToSend = msg.getBytes("UTF8");

        Cipher c = Cipher.getInstance("AES");
        SecretKeySpec k =
                new SecretKeySpec(key, "AES");
        c.init(Cipher.ENCRYPT_MODE, k);
        byte[] encryptedData = c.doFinal(dataToSend);
        BASE64Encoder enc = new BASE64Encoder();
        String payload = enc.encode(encryptedData);
        System.out.println("\nINPUT is (" + msg.length() + ")'" + msg + "', \nOUTPUT is (" + payload.length() + ") " + payload);
    }
}
