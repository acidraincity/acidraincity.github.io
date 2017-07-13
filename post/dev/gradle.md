# gradle

20170713



jar 파일을 실행하는 타스크를 아래와 같이 구성할 수 있습니다.

```

task pack( type : JavaExec, dependsOn : build ){
    classpath = files( 'build/libs/app-1.0-SNAPSHOT.jar' )
    classpath += sourceSets.main.runtimeClasspath
    main = 'com.acidraincity.Application'
}

```

