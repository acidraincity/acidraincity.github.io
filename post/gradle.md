jar 파일을 실행하는 타스크를 아래와 같이 구성할 수 있습니다.

<script src="https://gist.github.com/acidraincity/5da724dbf65255f37f7587d23c063389.js"></script>

```

task pack( type : JavaExec, dependsOn : build ){
    classpath = files( 'build/libs/app-1.0-SNAPSHOT.jar' )
    classpath += sourceSets.main.runtimeClasspath
    main = 'com.acidraincity.Application'
}

```

