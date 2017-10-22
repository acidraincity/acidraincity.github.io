# server. 2017 서버셋팅 노트

20171022





사용하는 서버 시스템을 conoha에서 google cloud로 다시 옮기기로 합니다.

서버 셋팅도 다시 해야겠죠.
서버 인스턴스는 우분투 16.04LTS 버전으로 선택했습니다.

이번에도 최대한 기본 패키지 매니저를 이용하는 방법으로 셋팅하려고 합니다.



#### 1. 구글 클라우드 VM인스턴스 SSH 연결


구글 클라우드 콘솔에서 클릭 한번으로 SSH 연결할 수 있지만, 브라우저상에 구현된 SSH 클라이언트는 사용성이 떨어지는 느낌입니다.
구글 클라우드 SDK의 gcloud 도구로 접속하기로 합니다.
[공식문서](https://cloud.google.com/sdk/docs/quickstart-mac-os-x)에서 가이드하는 방법으로 SDK를 설치합니다.

그런데 제 경우에는 gcloud init 를 했을 때 command not found 에러가 났는데요. google-cloud-sdk 폴더에서 아래 명령을 실행시키고 해결하였습니다.

```
source ./path.bash.inc
source ./completion.bash.inc
```

  cloud init로 클라우드 계정 정보를 연동시키면, 다음과 같이 SSH로 VM에 접속할 수 있습니다.

```
gcloud compute ssh instance-1 --zone asia-northeast1-c
```

최초 접속 과정에서 퍼블릭키가 생성되고 다음과 같이 저장됩니다.

```
Your identification has been saved in /Users/사용자명/.ssh/google_compute_engine.
Your public key has been saved in /Users/사용자명/.ssh/google_compute_engine.pub.
```



#### 2. root 패스워드 변경

Ubuntu 루트계정 패스워드를 변경할 수 있습니다.

```
sudo passwd
```



#### 2. JDK 설치

open jdk를 사용합니다.

[How To Install Java with Apt-Get on Ubuntu 16.04](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04)

```
sudo apt-get update
sudo apt-get install default-jre
sudo apt-get install default-jdk
sudo update-alternatives --config java
```

/etc/environment 에 JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 추가을 추가합니다.



#### 3. 아파치, 톰캣 설치

```
sudo apt-get install apache2
sudo apt-get install libapache2-mod-jk
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
sudo /etc/init.d/apache2 restart
```

#### 3. MariaDB 설치

다음 명령으로 설치하려는 OS 정보를 확인합니다.

```
lsb_release -a
```

저의 경우에는 다음과 같은 릴리즈 정보를 출력해주었습니다.

```
No LSB modules are available.
Distributor ID:	Ubuntu
Description:	Ubuntu 16.04.3 LTS
Release:	16.04
Codename:	xenial
```

그 후 아래 URL에서 확인된 정보대로 OS를 선택합니다.

 <https://downloads.mariadb.org/mariadb/repositories/#mirror=kaist> 

그러면 MariaDB repository 정보를 추가하고, 다운로드받아 설치하기 위한 명령어 순서를 친절하게 알려주죠. 시키는대로 하면 됩니다.

저의 경우에는 다음과 같이 실행했습니다.

```
#레파지토리 등록
sudo apt-get install software-properties-common
sudo apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xF1656F24C74CD1D8
sudo add-apt-repository 'deb [arch=amd64,i386,ppc64el] http://ftp.kaist.ac.kr/mariadb/repo/10.2/ubuntu xenial main'

#설치
sudo apt update
sudo apt install mariadb-server
```

혹시 레파지토리를 잘못 등록했을 때 지우고 싶으면 -r 옵션을 뒤에 붙여서 다시 실행하면 됩니다.

```
sudo add-apt-repository 'deb [arch=amd64,i386,ppc64el] http://ftp.kaist.ac.kr/mariadb/repo/10.2/ubuntu xenial main' -r
```

 

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
sudo service mysql restart
```

