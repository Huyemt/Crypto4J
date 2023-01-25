## [Back to Development Manual](../start.md)
***
```java
import crypto4j.Crypto4J;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        // string
        String pwd = Crypto4J.Base64.encrypt("admin123");
        System.out.println(pwd);
        System.out.println(Crypto4J.Base64.decrypt(pwd));

        // bytes
        pwd = Crypto4J.Base64.encrypt("admin123".getBytes(StandardCharsets.UTF_8));
        System.out.println(pwd);
        System.out.println(Crypto4J.Base64.decrypt(pwd));
    }
}
```