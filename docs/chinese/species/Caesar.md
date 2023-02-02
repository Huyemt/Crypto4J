## [返回开发手册](../start.md)
***
## 什么是凯撒密码？
在密码学中，恺撒密码（英语：Caesar cipher），或称恺撒加密、恺撒变换、变换加密，是一种最简单且最广为人知的加密技术。它是一种替换加密的技术，明文中的所有字母都在字母表上向后（或向前）按照一个固定数目进行偏移后被替换成密文。例如，当偏移量是3的时候，所有的字母A将被替换成D，B变成E，以此类推。
<br><br>
在`Crypto4J`中，支持您通过两种方式对凯撒密码进行操作。
## 偏移量替换
```java
import crypto4j.Crypto4J;
import crypto4j.digest.Caesar;

public class Main {
    public static void main(String[] args) {
        Caesar.OffsetKey offsetKey = new Caesar.OffsetKey(10);
        // 是否为正向偏移
        offsetKey.forwardR = true;

        // encrypt
        String pwd = Crypto4J.Caesar.encrypt("admin123", offsetKey);
        System.out.println(pwd);
        // decrypt
        String origin = Crypto4J.Caesar.decrypt(pwd, offsetKey);
        System.out.println(origin);
    }
}
```
## 字典替换
```java
import crypto4j.Crypto4J;
import crypto4j.digest.Caesar;

public class Main {
    public static void main(String[] args) {
        Caesar.ReplaceKey replaceKey = new Caesar.ReplaceKey();
        replaceKey.add('a', 'b')
                .add('d', 'e')
                .add('m', 'n')
                .add('i', 'j')
                .add('n', 'm')
                .add('1', '2')
                .add('2', '3')
                .add('3', '4');

        // encrypt
        String pwd = Crypto4J.Caesar.encrypt("admin123", replaceKey);
        System.out.println(pwd);
        // decrypt
        String origin = Crypto4J.Caesar.decrypt(pwd, replaceKey);
        System.out.println(origin);
    }
} 
```