## [返回开发手册](../start.md)
***
```java
import crypto4j.Crypto4J;

public class Main {
    public static void main(String[] args) {
        System.out.println(Crypto4J.SHA1.encrypt("admin123"));
        System.out.println(Crypto4J.SHA256.encrypt("admin123"));
        System.out.println(Crypto4J.SHA512.encrypt("admin123"));
    }
}
```