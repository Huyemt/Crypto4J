## [Back to Development Manual](../start.md)
***
## What is the Caesar cipher?
In cryptography, the Caesar cipher, or Caesar encryption, Caesar transform, or transform encryption, is one of the simplest and most well-known encryption techniques. It is a substitution encryption technique in which all the letters of the plaintext are shifted backwards (or forwards) in the alphabet by a fixed number and replaced with ciphertext. For example, when the offset is 3, all the letters A will be replaced by D, B by E, and so on.
<br><br>
In `Crypto4J`, you are allowed to manipulate Caesar passwords in two ways.
## Offset substitution
```java
import crypto4j.Crypto4J;
import crypto4j.digest.Caesar;

public class Main {
    public static void main(String[] args) {
        Caesar.OffsetKey offsetKey = new Caesar.OffsetKey(10);
        // Whether it is a positive migration
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
## Dictionary substitution
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