# server. 2015 서버셋팅 노트

20151017





몇년간 사용하던 호스팅 서비스를 conoha로 옮기게 되었습니다.

새로 서버 설정한 내용을 정리해 놓습니다.

최대한 기본 패키지 매니저를 이용하는 방법으로 셋팅했습니다.

#### 1. JDK 설치

[How To Install Java on Ubuntu with Apt-Get](https://www.digitalocean.com/community/tutorials/how-to-install-java-on-ubuntu-with-apt-get)

```
apt-get install python-software-properties
add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install oracle-java7-installer
update-alternatives --config java
```

/etc/environment 에 JAVA_HOME=/usr/lib/jvm/java-7-oracle 추가을 추가합니다.

#### 2. 아파치, 톰캣 설치

```
apt-get install apache2
apt-get install libapache2-mod-jk

```

톰캣 host 엘레먼트 하위에 다음 내용을 추가합니다.

```
<Context docBase="/var/www/html" path="/" reloadable="true" />

```

/etc/libapache2-mod-jk/workers.properties 의 자바경로와 톰캣경로 수정합니다.

/etc/apache2/sites-enabled/000-default.conf 에 JkMount 설정을 추가합니다.

```
JkMount /*.jsp ajp13_worker
JkMount /WEB-INF/* ajp13_worker
```

아래는 톰캣과 아파치 연동에 관한 레퍼런스입니다.

<http://tomcat.apache.org/connectors-doc/webserver_howto/printer/apache.html>

여기서 가이드하는 내용으로는 보안상 다음과 같이 하는게 좋다고 합니다.

나중에 고쳐야 겠네요.

```
# All requests go to worker1 by default
JkMount /* worker1

# Serve html, jpg and gif using httpd
JkUnMount /*.html worker1
JkUnMount /*.jpg  worker1
JkUnMount /*.gif  worker1

```

/etc/apache2/apache2.conf 설정파일의 Directory 항목에서 Options 에 Indexs 제거합니다.

/etc/apache2/mods-enabled/dir.conf 파일의 DirectoryIndex 항목에 index.jsp 추가합니다.

아파치 서버를 재시작합니다.

```
/etc/init.d/apache2 restart

```

#### 3. MariaDB 설치

아래 메뉴얼의 내용대로 설치하면 됩니다.

<https://mariadb.com/kb/en/mariadb/installing-mariadb-deb-files/>

lsb_release -a 명령으로 설치하려는 OS정보를 확인한 다음에 <https://downloads.mariadb.org/mariadb/repositories/#mirror=kaist> 링크에서 알맞는 레파지토리를 선택해 진행하라는 내용을 유의하시기 바랍니다.

설치가 완료된 후 UTF-8 인코딩을 사용하도록 하기 위해서 /etc/mysql/my.cnf 파일에 다음 내용을 추가합니다.

```
[client]

default-character-set=utf8

[mysqld]

init_connect="SET collation_connection=utf8_general_ci"
init_connect="SET NAMES utf8"
character-set-server=utf8
collation-server=utf8_general_ci

skip-character-set-client-handshake

[mysql]

default-character-set=utf8

```

원격에서의 접속을 허용하려면 설정중 아래 라인을 주석처리합니다.(<https://opentutorials.org/module/1175/7779>)

```
#변경전
bind-address = 127.0.0.1
#변경후
#bind-address = 127.0.0.1
```

설정을 바꾼 후에는 서비스를 재시작해야 합니다.

```
service mysql restart

```

#### 4. SVN 설치

```
apt-get install subversion

```

편하고 좋네요.

레파지토리 생성 및 서비스 시작에 대한 내용은 [이전의 서버셋팅노트](http://blog.acidraincity.com/2015/01/svn.html)를 참고하면 될 것 같습니다.