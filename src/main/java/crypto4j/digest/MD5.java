package crypto4j.digest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 *
 * @author Huyemt
 */

public class MD5 extends Digest {
    public MD5() throws NoSuchAlgorithmException {
        super("MD5");
    }

    public String encrypt(String value) {
        String result = new BigInteger(1, digest.digest(value.getBytes(StandardCharsets.UTF_8))).toString(16);
        return result.length() != 32 ? "0" + result : result;
    }
}
