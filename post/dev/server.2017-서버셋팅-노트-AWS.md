# server. 2017 서버셋팅 노트 - AWS

20171212



2017년이 며칠 남지도 않은 한 해의 끝자락에서 AWS에 서버를 다시 셋팅해 봅니다. 이번에는 꼭 오래 안주할 수 있기를 바라며.



#### 1. 루트 계정 활성화

Ubuntu 루트계정 패스워드를 변경하며 root 계정을 활성화합니다.

```
sudo passwd root
```

앞으로의 작업은 루트계정으로 로그인해서 진행합니다.

```
su
```



#### 2. JDK 설치

open jdk를 사용합니다.

[How To Install Java with Apt-Get on Ubuntu 16.04](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04)

```
apt-get update
apt-get install default-jre
apt-get install default-jdk
update-alternatives --config java
```

/etc/environment 에 JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 추가을 추가합니다.



#### 3. 아파치 설치

```
apt-get install apache2
apt-get install libapache2-mod-jk
```

아파치 서버를 재시작합니다.

```
/etc/init.d/apache2 restart
```



#### 4. 톰캣 설정

매니저 컨텍스트에 원격 호스트에서도 접속할 수 있기 위해서 webapp/manager/META-INF/context.xml 의 다음 항목을 주석처리합니다.

```
<Context antiResourceLocking="false" privileged="true" >
  <!--<Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />-->
</Context>
```

관리콘솔을 이용할 수 있는 계정을 conf/tomcat-user.xml 파일에 등록합니다.

```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<role rolename="manager-jmx"/>
<role rolename="manager-status"/>
<user username="사용자명" password="패스워드" roles="manager-gui,manager-script,manager-jmx,manager-status"/>

```

/etc/libapache2-mod-jk/workers.properties 의 자바경로와 톰캣경로 수정합니다.

```
workers.tomcat_home=/home/app/apache-tomcat-8.5.24
workers.java_home=/usr/lib/jvm/java-8-openjdk-amd64
```

/etc/apache2/sites-enabled/000-default.conf 에 다음과 같은 처리를 합니다.

- JkMount 설정을 추가합니다.
- DocumentRoot를 톰캣 웹앱 폴더로 맞춰주고 해당 디렉토리에 대한 설정을 추가합니다.

```
DocumentRoot /home/app/apache-tomcat-8.5.24/webapps

#기본적으로 모든 요청을 톰캣에 전달
JkMount /* ajp13_worker

#아래 요청은 아파치 웹서버에서 처리
JkUnMount /*.html ajp13_worker
JkUnMount /*.jpg ajp13_worker
JkUnMount /*.png ajp13_worker
JkUnMount /*.gif ajp13_worker
JkUnMount /*.svg ajp13_worker
JkUnMount /*.css ajp13_worker
JkUnMount /*.js ajp13_worker

<Directory /home/app/apache-tomcat-8.5.24/webapps>
  Options FollowSymLinks
  AllowOverride None
  Require all granted
</Directory>
```

/etc/apache2/apache2.conf 설정파일의 Directory 항목에서 Options 에 Indexs 제거합니다.

/etc/apache2/mods-enabled/dir.conf 파일의 DirectoryIndex 항목에 index.jsp 추가합니다.

아파치 서버를 재시작합니다.

```
/etc/init.d/apache2 restart
```



#### 5. MariaDB 설치

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
apt-get install software-properties-common
apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xF1656F24C74CD1D8
add-apt-repository 'deb [arch=amd64,i386,ppc64el] http://ftp.kaist.ac.kr/mariadb/repo/10.2/ubuntu xenial main'

#설치
apt update
apt install mariadb-server
```

혹시 레파지토리를 잘못 등록했을 때 지우고 싶으면 -r 옵션을 뒤에 붙여서 다시 실행하면 됩니다.

```
add-apt-repository 'deb [arch=amd64,i386,ppc64el] http://ftp.kaist.ac.kr/mariadb/repo/10.2/ubuntu xenial main' -r
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
service mysql restart
```



데이터베이스를 생성합니다.

```
mysql -uroot -p
create database 데이터베이스이름
```



사용자를 생성하고 권한을 부여합니다. 사용자는 원격 호스트에서 접속할 수 있도록 설정하였습니다.

```
create user '사용자이름'@'%' identified by '패스워드';
```

로컬에서만 접속하려면 '%'가 아니라 'localhost'를 사용해야 합니다.

혹시 잘못 입력했을 때는 다음과 같이 사용자를 삭제할 수 있습니다.

```
drop user '사용자이름'@'%';
```

사용자가 잘 생성되었는지 확인해봅니다.

```
use mysql;
select host, user from user;
```

사용자에게 특정 데이터베이스에 대한 권한을 부여합니다.

```
grant all privileges on 데이터베이스이름.* to 사용자이름@'%';
```



이전에 사용하고 있던 서버의 데이터베이스를 백업합니다.

```
mysqldump -uroot -p 데이터베이스이름 > ./backup.sql
```

새로 구축한 MariaDB에 데이터를 이전합니다.

```
mysql -uroot -p 데이터베이스이름 < ./backup.sql
```



다음과 같은 내용으로 백업 스크립트를 만들었습니다.

```
#!/bin/bash
mysqldump -u사용자이름 -p패스워드 데이터베이스이름 > /some/path/backup_$(date +%Y%m%d).sql
find /some/path/ -ctime +5 -exec rm -f {} \;
mysql -u사용자이름 -p패스워드 백업데이터베이스이름 < /some/path/backup_$(date +%Y%m%d).sql
```



