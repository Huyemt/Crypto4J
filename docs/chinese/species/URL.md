## [返回开发手册](../start.md)
***
```java
import crypto4j.Crypto4J;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        // encode
        String pwd1 = Crypto4J.URL.encode("abc , def", true);
        String pwd2 = Crypto4J.URL.encode("abc , def", false);
        System.out.println(pwd1);
        System.out.println(pwd2);
        
        // decode
        System.out.println(Crypto4J.URL.decode(pwd1));
        System.out.println(Crypto4J.URL.decode(pwd2));
    }
}
```