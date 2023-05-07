## [返回开发手册](../start.md)
***
```java
import crypto4j.Crypto4J;
import crypto4j.digest.RSA;

public class Main {
    public static void main(String[] args) {
        try {
            // Default
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjNBH/wFyR7yUxv3rAaoRgrvm3/HEyGDe7Vu/ckV0ka+0gGiJ8BlvKLuVL5AImMej0WRLul98uJ0grFxwLJSxETmlq/87TYNIzCSPGTZofdtNqKGeEdBUkc6ICo3yb8OTzdl9pkMLkO8yyDxXk5IFaeW9F9m8O78UxWk3vBN13xpuwctri/lwZzIWsu6e09nFiCGoK3yqp9L9sndRXXdly6vGtQLfzPMwY4GYDVz91nSaoHwl8avH4TG+ByPCEsUD0hu/ROebemMdU5rdJkUu/R1yrF+r/8BO3U+iH5ENnXHVb6TFF/bETR5IJmhRwvE7GkDbMX3BnQXx5Zelgd3fMQIDAQAB";
            String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCM0Ef/AXJHvJTG/esBqhGCu+bf8cTIYN7tW79yRXSRr7SAaInwGW8ou5UvkAiYx6PRZEu6X3y4nSCsXHAslLEROaWr/ztNg0jMJI8ZNmh9202ooZ4R0FSRzogKjfJvw5PN2X2mQwuQ7zLIPFeTkgVp5b0X2bw7vxTFaTe8E3XfGm7By2uL+XBnMhay7p7T2cWIIagrfKqn0v2yd1Fdd2XLq8a1At/M8zBjgZgNXP3WdJqgfCXxq8fhMb4HI8ISxQPSG79E55t6Yx1Tmt0mRS79HXKsX6v/wE7dT6IfkQ2dcdVvpMUX9sRNHkgmaFHC8TsaQNsxfcGdBfHll6WB3d8xAgMBAAECggEASzxdu1pCfSG9PDUfA1yizn8m9DBxO77GXXvP5nZFujFu98zjJm3wfor7QtZqQXcpiA9WhtXOBbWuZ2lcZfVsRg/O/P756IhktO4zpvzzB4vNCnSPyGTPSrhW54zAJAcTScES6WqZG28yatoS0L4MNnCRf9TORf3qUy/Mn6imKfVdowmjjvsgDcmRiXMeZEiU2nuk2bvfrIua3lsjOscUfhmZfYJfSS0WpEi3InILOEkoTYFkIGnn3DNg01WkTKQi1RZcinFgq9L1S3gzuaWedI5w1N4kAywMSbOEKtW+cBTJtN5JOBYnLhA34BqUVnDTtNNgydBez1HRTq0n4NJjgQKBgQDs+5sN8epMYqcG8SI7OsLpUUsJjcTyVlUkgMDh84OlLxYw5vYMyKMgObjVZWnPlXnMLwlkYQOfoq6xenaERo71BCGAb76Eje/ZBKcZfp4ycJRyZNYlQbSTzqLf45ERgUNiWJgqQOSE5d7DyeNPh3oMaK0mxrTASyxBFb54vm4tZQKBgQCYHQxY+gv9StVYflQF2MosuqpoatQEhkgpP9KdV4L+ZSbuArgZaPAWHnE87i9SrK6Iy1TMbjjaeDcnZ5tYzWCy+F9Mzig/edi8Cz3usC6AXS7VF2jTSwAYengEvyxtEMC2v35RkS7pdEuUnlvND0UK+ZTzT9BeugMYY8ZLQA6D3QKBgQDd1dkbgK02u9JmqSrGDweNqM95LgCCaVZiSTRFTtKYui8nr8v7w4UQEXh4KeqWqvHMtOsxz664rGyjLmPrBrGjoi5KMFBDfSpcGALj1qb3yHLtcn8TeSI0imXk9v1BSaNuWEh4pS5FSXjyBfaG5+bT3tjXR4LDzpqqglQ+auRwEQKBgAmR0kQKvxRZT+C4WdrgT5/Owoe9KMd8FzAZJsvlRIVyoadZnnFiy6/ojGEz7uA5ouVSPWx7BrKf24BsITbN/OZlf8FaOuf7Z2o1HtlUViiXNCoNxfd9AW2IzuIlkMrvwd/9XhoNkzD2swaPi+Dzs/lDyF4iSE6dTzKKnGABkKg1AoGAHV61vHYDBMkTGpLB4dNvVFI2xV7zXYexp9R3OyW/e9ungU4ZD08rUfiBKhuDm/MVsXUIuKCLSnLYvRYlMDqoAFWsh33gAD2vr2Rj7dLEzjZFjxrgdyZDGmOVU9omoRHCWI4kfqDDZy2YaxBzAFNE2LiRFluU7Ke64NJn6q7Np14=";
            String result = Crypto4J.RSA.encrypt("admin123", publicKey);
            System.out.println(result);
            System.out.println(Crypto4J.RSA.decrypt(result, privateKey));

            // Random KeyPair
            RSA.Result randomResult = Crypto4J.RSA.randomEncrypt("admin123");
            System.out.println(randomResult.publicKey);
            System.out.println(randomResult.privateKey);
            System.out.println(randomResult.result);
            System.out.println(Crypto4J.RSA.decrypt(randomResult.result, randomResult.privateKey));

            // Senior
            RSA.EncryptConfig config = new RSA.EncryptConfig();
            config.PADDING = crypto4j.digest.RSA.Padding.PKCS1_OAEP_PADDING;
            result = Crypto4J.RSA.encrypt("admin123", publicKey, config);
            System.out.println(result);

            RSA.DecryptConfig config1 = new RSA.DecryptConfig();
            config1.PADDING = RSA.Padding.PKCS1_OAEP_PADDING;
            System.out.println(Crypto4J.RSA.decrypt(result, privateKey, config1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```