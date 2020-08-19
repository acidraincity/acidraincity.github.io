---
layout: post
date: 2018-06-17
title: 원격서버 tomcat 에 프로젝트 배포하기
author: Jeon Yongtae
categories: [Gradle]
tags: [Gradle]
---

[ssh 플러그인](https://gradle-ssh-plugin.github.io/docs/)을 사용해서 원격지 서버에 연결하고 명령을 실행시킬 수 있습니다.



```groovy
buildscript {
  dependencies {
    ...
    classpath 'org.hidetake:gradle-ssh-plugin:2.9.0'
  }
}

apply plugin: 'war'
apply plugin: 'org.hidetake.ssh'

...

remotes {
  jeon{
    host = 'xxx.xxx.xxx.xxx'
    user = 'username'
    identity = file('/some/path/cert.pem')
  }
}

task deployToAcidraincity( dependsOn: 'war' ){
  doLast{
    ssh.run{
      session( remotes.jeon ){
        put from: 'build/libs/service.war', into: '/some/tomcat/webapps/'
        execute './reload-service.sh'
      }
    }
  }
}
```

빌드 결과로 생성된 war 파일을 원격 서버의 webapps 위치로 업로드하고, 원격 서버상에 미리 준비해놓은 스크립트를 실행시키게 됩니다. 



reload-service.sh 파일은 다음과 같이 작성하였습니다.

```shell
#!/bin/bash
wget --http-user=xxxx --http-password=yyyy "http://localhost/manager/text/reload?path=/service" -O -
sleep 5
sudo chmod -R 755 /some/tomcat/webapps/service/
sudo ls -l /some/tomcat/webapps/
```

tomcat manager에서 제공하는 인터페이스를 호출해서 컨텍스트를 다시 로드합니다.

사용자 xxxx는 manager-script 권한을 가지고 있어야 합니다.



war가 재배포된 후, 포함하는 정적 리소스 파일을 아파치 웹서버에서 접근할 수 없게 되는 문제가 있어서, 해당 파일에 대한 접근 권한을 변경하는 내용이 포함되어 있습니다.



패스워드 입력 없이 sudo 기능을 사용할 수 있게 미리 설정해둔 상태입니다. 관련해서는 [이 포스트](https://askubuntu.com/a/147265)를 참고하세요.

