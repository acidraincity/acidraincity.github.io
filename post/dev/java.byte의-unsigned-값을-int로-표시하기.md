# java. byte의 unsigned 값을 int로 표시하기

20180314



다음과 같이 해서 byte 데이터의 unsigned 값을 표시할 수 있습니다.

```java
byte b = ...
int unsignedValue = b & 0xFF;
```



unsigned 값을 바이트로 변환하는 방법은 다음과 같습니다.

```java
int unsignedValue = ...
byte b = ( byte )( unsignedValue > 127 ? unsignedValue - 256 : unsignedValue );
```

