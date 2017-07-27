# gradle

20170727



### jar 파일을 실행하는 타스크



jar 파일을 실행하는 타스크를 아래와 같이 구성할 수 있습니다.

```
task pack( type : JavaExec, dependsOn : build ){
    classpath = files( 'build/libs/app-1.0-SNAPSHOT.jar' )
    classpath += sourceSets.main.runtimeClasspath
    main = 'com.acidraincity.Application'
}
```



### application 플러그인



gradle 프로젝트에 application 플러그인을 적용하면 run, distZip 등의 타스크를 프로젝트에 추가해줍니다.

```
apply plugin: 'java'
apply plugin:'application'
```

어플리케이션으로 실행될 메인클래스를 지정해야 합니다.

```
mainClassName = "com.docscon.wbm.App"
```

jvm옵션을 지정할 수 있습니다.

```
applicationDefaultJvmArgs = [ "-Xms2G", "-Xmx4G" ]
```

run 타스크는 프로젝트를 빌드하고 어플리케이션을 실행해줍니다.

distZip 타스크는 의존하는 라이브러리와 실행 스크립트를 포함해 앱을 패키징해줍니다.



유사한 플러그인으로 이런 것도 있었습니다.

[gradle-getdown-plugin](https://github.com/davidB/gradle-getdown-plugin)

jre 번들링까지 해주는 것 같은데요. 실제로 사용해보지는 않았습니다.



### java 플러그인만 사용할 때, jar 파일의 메인클래스 지정하기



java 플러그인만 사용할 때, 다음과 같이 jar에 포함될 manifest 항목을 설정할 수 있습니다.

```
apply plugin: 'java'

jar {
    manifest {
        attributes 'Main-Class': 'com.acidraincity.MainClass'
    }
}
```



### 사용자 타스크에서 파일 복사하기



다음과 같은 형식으로 copy 타스크를 호출해 복사를 실행합니다.

```
task userTask( dependsOn : build ) << {

    copy{
        from 'some/libs/'
        into '../some/some/libs/'
        include '*.jar'
    }

    copy{
        from 'some/assets'
        into '../some/some/assets'
    }

}
```



