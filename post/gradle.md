그래들  
그래

    task pack( type : JavaExec, dependsOn : build ){
    classpath = files( 'build/libs/app-1.0-SNAPSHOT.jar' )
    classpath += sourceSets.main.runtimeClasspath
    main = 'com.acidraincity.Application'
}
