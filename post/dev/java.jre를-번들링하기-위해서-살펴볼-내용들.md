# java. jre를 번들링하기 위해 살펴볼 내용들

20170806


### node-java

[https://github.com/joeferner/node-java](https://github.com/joeferner/node-java)

node-java 모듈을 사용하면 node.js 코드에서 java 클래스 객체를 생성하고 메소드를 호출하는 등의 기능을 구현할 수 있다.
node-java는 jre를 포함하는 방법을 제공하지 않고, 시스템에 이미 설치되어있는 jre를 찾아서 동작한다.
그렇기 때문에 jre를 배포할 앱에 포함하기 위해서는 아래와 같은 편법을 이용해야 한다.

https://github.com/joeferner/node-java/issues/132


### node-jre

https://github.com/schreiben/node-jre

node.js 앱에서 jre를 임베딩하고 특정 클래스의 main 메소드를 호출하는 인터페이스를 제공한다.


### packr

https://github.com/libgdx/packr

jar 파일과 부가 파일들을 jre와 묶어 OS별 실행파일로 패키징해준다.


### Avian

java 코드를 호스팅할 수 있는 경랑 VM 구현체라고 한다.

http://readytalk.github.io/avian/

https://github.com/ReadyTalk/avian

https://github.com/bigfatbrowncat/avian-pack


### launch4j

http://launch4j.sourceforge.net

자바 프로그램에 jre를 번들링하고 각 플랫폼별에서 실행할 수 있는 네이티브 실행파일로 패키징해준다.


### Oracle JRE

http://www.oracle.com/technetwork/java/javase/downloads/index.html

오라클 사이트에서 클라이언트용 jre와 서버용 jre를 다운로드 받을 수 있다.
클라이언트용 jre의 초기 실행 속도가 조금 더 빠르다고 한다.


### Open JDK

https://github.com/alexkasko/openjdk-unofficial-builds
https://github.com/ojdkbuild/ojdkbuild

위의 사이트들에서 Open JDK를 다운로드 받을 수 있으며, 그 안에 jre가 들어있다.