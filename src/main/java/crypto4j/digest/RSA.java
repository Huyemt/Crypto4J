package crypto4j.digest;

import crypto4j.Crypto4J;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * RSA加密与解密
 *
 * RSA encryption and decryption
 *
 * @author Huyemt
 */

public class RSA {
    public RSA() {

    }

    /**
     * 渲染密钥对
     *
     * Render key pairs
     *
     * @param keysize
     * @return KeyPair
     */
    public KeyPair initKey(int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keysize);
        return generator.generateKeyPair();
    }

    /**
     * 渲染公钥
     *
     * Render key
     *
     * @param publicKey
     * @return PublicKey
     */
    private PublicKey toPublicKey(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Crypto4J.Base64.decode(publicKey));
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    /**
     * 渲染密钥
     * @param privateKey
     * @return String
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey toPrivateKey(byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Crypto4J.Base64.decode(privateKey));
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    /**
     * 明文加密
     * @param content
     * @param publicKey
     * @param config
     * @return String
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public String encrypt(byte[] content, PublicKey publicKey, EncryptConfig config) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        if (config == null) {
            config = new EncryptConfig();
        }

        Cipher cipher = Cipher.getInstance("RSA/ECB/" + config.PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int content_length = content.length;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        int offSet = 0;
        int i = 0;
        byte[] cache;

        while (content_length - offSet > 0) {
            if (content_length - offSet > config.MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(content, offSet, config.MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(content, offSet, content_length - offSet);
            }
            stream.write(cache, 0, cache.length);
            i++;
            offSet = i * config.MAX_ENCRYPT_BLOCK;
        }
        stream.close();

        return Crypto4J.Base64.encrypt(stream.toByteArray());
    }

    public String encrypt(byte[] content, PublicKey publicKey, Padding padding, int max_encrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.PADDING = padding;
        config.MAX_ENCRYPT_BLOCK = max_encrypt_block;

        return encrypt(content, publicKey, config);
    }

    public String encrypt(byte[] content, PublicKey publicKey, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.PADDING = padding;

        return encrypt(content, publicKey, config);
    }

    public String encrypt(byte[] content, PublicKey publicKey, int max_encrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.MAX_ENCRYPT_BLOCK = max_encrypt_block;

        return encrypt(content, publicKey, config);
    }

    public String encrypt(byte[] content, PublicKey publicKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return encrypt(content, publicKey, new EncryptConfig());
    }

    public String encrypt(byte[] content, String publicKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException, InvalidKeySpecException {
        return encrypt(content, toPublicKey(publicKey.getBytes(StandardCharsets.UTF_8)), new EncryptConfig());
    }

    ///////////////////////////////////

    public String encrypt(String content, PublicKey publicKey, EncryptConfig config) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), publicKey, config);
    }

    public String encrypt(String content, PublicKey publicKey, Padding padding, int max_encrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.PADDING = padding;
        config.MAX_ENCRYPT_BLOCK = max_encrypt_block;

        return encrypt(content.getBytes(StandardCharsets.UTF_8), publicKey, config);
    }

    public String encrypt(String content, PublicKey publicKey, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.PADDING = padding;

        return encrypt(content.getBytes(StandardCharsets.UTF_8), publicKey, config);
    }

    public String encrypt(String content, PublicKey publicKey, int max_encrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        EncryptConfig config = new EncryptConfig();
        config.MAX_ENCRYPT_BLOCK = max_encrypt_block;

        return encrypt(content.getBytes(StandardCharsets.UTF_8), publicKey, config);
    }

    public String encrypt(String content, PublicKey publicKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), publicKey, new EncryptConfig());
    }

    public String encrypt(String content, String publicKey, EncryptConfig config) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException, InvalidKeySpecException {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), toPublicKey(publicKey.getBytes(StandardCharsets.UTF_8)), config);
    }

    public String encrypt(String content, String publicKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException, InvalidKeySpecException {
        return encrypt(content.getBytes(StandardCharsets.UTF_8), toPublicKey(publicKey.getBytes(StandardCharsets.UTF_8)), new EncryptConfig());
    }

    ///////////////////////////////////

    /**
     * RSA私钥解密
     * @param content
     * @param privateKey
     * @param config
     * @return String
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public String decrypt(byte[] content, PrivateKey privateKey, DecryptConfig config) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        if (config == null) {
            config = new DecryptConfig();
        }

        byte[] deBase64 = Crypto4J.Base64.decode(content);

        Cipher cipher = Cipher.getInstance("RSA/ECB/" + config.PADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int deBase65_length = deBase64.length;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        int offSet = 0;
        int i = 0;
        byte[] cache;

        while (deBase65_length - offSet > 0) {
            if (deBase65_length - offSet > config.MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(deBase64, offSet, config.MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(deBase64, offSet, deBase65_length - offSet);
            }
            stream.write(cache, 0, cache.length);
            i++;
            offSet = i * config.MAX_DECRYPT_BLOCK;
        }
        stream.close();

        return Crypto4J.Utf8.toString(stream.toByteArray());
    }

    public String decrypt(byte[] content, PrivateKey privateKey, Padding padding, int max_decrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.PADDING = padding;
        config.MAX_DECRYPT_BLOCK = max_decrypt_block;

        return decrypt(content, privateKey, config);
    }

    public String decrypt(byte[] content, PrivateKey privateKey, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.PADDING = padding;

        return decrypt(content, privateKey, config);
    }

    public String decrypt(byte[] content, PrivateKey privateKey, int max_decrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.MAX_DECRYPT_BLOCK = max_decrypt_block;

        return decrypt(content, privateKey, config);
    }

    public String decrypt(byte[] content, PrivateKey privateKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content, privateKey, new DecryptConfig());
    }

    public String decrypt(byte[] content, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content, toPrivateKey(privateKey.getBytes(StandardCharsets.UTF_8)), new DecryptConfig());
    }

    ///////////////////////////////////

    public String decrypt(String content, PrivateKey privateKey, DecryptConfig config) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content.getBytes(StandardCharsets.UTF_8), privateKey, config);
    }

    public String decrypt(String content, PrivateKey privateKey, Padding padding, int max_encrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.PADDING = padding;
        config.MAX_DECRYPT_BLOCK = max_encrypt_block;

        return decrypt(content.getBytes(StandardCharsets.UTF_8), privateKey, config);
    }

    public String decrypt(String content, PrivateKey privateKey, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.PADDING = padding;

        return decrypt(content.getBytes(StandardCharsets.UTF_8), privateKey, config);
    }

    public String decrypt(String content, PrivateKey privateKey, int max_decrypt_block) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        DecryptConfig config = new DecryptConfig();
        config.MAX_DECRYPT_BLOCK = max_decrypt_block;

        return decrypt(content.getBytes(StandardCharsets.UTF_8), privateKey, config);
    }

    public String decrypt(String content, PrivateKey privateKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content.getBytes(StandardCharsets.UTF_8), privateKey, new DecryptConfig());
    }

    public String decrypt(String content, String privateKey, DecryptConfig config) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content.getBytes(StandardCharsets.UTF_8), toPrivateKey(privateKey.getBytes(StandardCharsets.UTF_8)), config);
    }

    public String decrypt(String content, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        return decrypt(content.getBytes(StandardCharsets.UTF_8), toPrivateKey(privateKey.getBytes(StandardCharsets.UTF_8)), new DecryptConfig());
    }

    ///////////////////////////////////

    /**
     * 随机生成密钥对，并加密明文
     *
     * Randomly generate a key pair and encrypt the plaintext
     *
     * @param content
     * @param keysize
     * @param config
     * @return Result
     */
    public Result randomEncrypt(byte[] content, int keysize, EncryptConfig config) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException {
        KeyPair pair = initKey(keysize);
        return new Result(pair, encrypt(content, pair.getPublic(), config));
    }

    public Result randomEncrypt(byte[] content, int keysize) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return randomEncrypt(content, keysize, new EncryptConfig());
    }

    public Result randomEncrypt(byte[] content) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return randomEncrypt(content, 2048, new EncryptConfig());
    }

    public Result randomEncrypt(String content, int keysize, EncryptConfig config) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return randomEncrypt(content.getBytes(StandardCharsets.UTF_8), keysize, config);
    }

    public Result randomEncrypt(String content, int keysize) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return randomEncrypt(content.getBytes(StandardCharsets.UTF_8), keysize, new EncryptConfig());
    }

    public Result randomEncrypt(String content) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException {
        return randomEncrypt(content.getBytes(StandardCharsets.UTF_8), 2048, new EncryptConfig());
    }

    ///////////////////////////////////

    public static class Result {
        public final String publicKey;
        public final String privateKey;
        public final String result;

        public Result(KeyPair pair, String result) {
            publicKey = Crypto4J.Base64.encrypt(pair.getPublic().getEncoded());
            privateKey = Crypto4J.Base64.encrypt(pair.getPrivate().getEncoded());
            this.result = result;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "PublicKey:\n\t".concat(getPublicKey()).concat("\nPrivateKey:\n\t").concat(getPrivateKey()).concat("\nEncrypted:\n\t").concat(getResult());
        }
    }

    public static class EncryptConfig {
        public Padding PADDING;
        public int MAX_ENCRYPT_BLOCK;

        public EncryptConfig() {
            PADDING = Padding.PKCS1_PADDING;
            MAX_ENCRYPT_BLOCK = 117;
        }
    }

    public static class DecryptConfig {
        public Padding PADDING;
        public int MAX_DECRYPT_BLOCK = 256;

        public DecryptConfig() {
            PADDING = Padding.PKCS1_PADDING;

        }
    }

    public enum Padding {
        PKCS1_PADDING("PKCS1Padding"),
        PKCS1_OAEP_PADDING("OAEPPadding"),
        NO_PADDING("NoPadding");

        private final String padding;
        Padding(String padding) {
            this.padding = padding;
        }

        public String getPadding() {
            return padding;
        }

        @Override
        public String toString() {
            return getPadding();
        }
    }

}
