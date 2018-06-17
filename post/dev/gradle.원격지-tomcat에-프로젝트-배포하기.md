# gradle. 원격지 tomcat에 프로젝트 배포하기

20180617



[ssh 플러그인](//https://gradle-ssh-plugin.github.io/docs/)을 사용하여 원격지 서버에 연결하는 방법을 사용합니다.



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
sudo chgrp -R www-data /some/tomcat/webapps/service/
sleep 5
sudo ls -l /some/tomcat/webapps/
```

tomcat manager에서 제공하는 인터페이스를 호출해서 컨텍스트를 다시 로드합니다.

사용자 xxxx는 manager-script 권한을 가지고 있어야 합니다.



그리고 war가 재배포 되면 사용자와 접근권한이 아파치에서 접근 불가능하게 초기화되는 문제가 있어서, 배포후 사용자 그룹을 www-data로 변경하도록 스크립트를 구성했습니다.



패스워드 입력 없이 sudo 기능을 사용할 수 있게 미리 설정해둔 상태입니다. 관련해서는 [이 포스트](https://askubuntu.com/a/147265)를 참고하세요.