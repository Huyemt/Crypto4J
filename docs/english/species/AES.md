## [Back to Development Manual](../start.md)
***
```java
import crypto4j.Crypto4J;
import crypto4j.digest.AES;

public class Main {
    public static void main(String[] args) {
        // Default
        String key = "Huyemt--Crypto4J";
        String pwd = Crypto4J.AES.encrypt("admin123", key);
        System.out.println(pwd);
        System.out.println(Crypto4J.AES.decrypt(pwd, key));

        // Senior
        key = "1234567890abcdef";
        pwd = Crypto4J.AES.encrypt("admin123", key, AES.Mode.CBC, AES.Padding.ISO10126_PADDING, "0000000000000000");
        System.out.println(pwd);
        System.out.println(Crypto4J.AES.decrypt(pwd, key, AES.Mode.CBC, AES.Padding.ISO10126_PADDING, "0000000000000000"));
    }
}
```