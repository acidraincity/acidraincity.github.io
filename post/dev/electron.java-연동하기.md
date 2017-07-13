20170713



[node-java](https://github.com/joeferner/node-java) 라이브러리를 사용해서 java 클래스 인스턴스를 생성하고 메소드를 호출하는 등의 처리를 할 수 있습니다.

비동기 호출시에 promise 형식을 사용하기 위해서 다음과 같이 초기화합니다.

when 라이브러리에 대한 의존성을 가지고 있습니다.

```
const java = require( 'java' )
java.asyncOptions = {
	asyncSuffix: undefined,
	syncSuffix: '',
	promiseSuffix: 'Promise',
	promisify: require('when/node').lift
};
```



jvm 옵션을 다음과 같이 설정할 수 있습니다.

```
java.options.push( '-Xms2G' );
java.options.push( '-Xmx4G' );
```



node-java는 시스템상에 설치되어있는 jre를 탐색해 사용하며, jre를 번들링하는 방법을 제공하지 않습니다.

jre를 번들링하고자 할 때는 open-jdk등에서 제공하는 jre를 프로그램 리소스에 포함시켜야 하며, 다음과 같은 편법으로 node-java가 해당 위치의 jre를 사용해 동작하도록 할 수 있습니다.



```
//jre가 __dirname 하위의 jre 폴더에 준비되어있는 상황을 전제합니다.

function loadJVM(){
    var path = require( 'path' );
    var fs   = require( 'fs' );

    var jvm_path = path.join( __dirname, 'jre/bin' ); 
    var dll_path = path.join( __dirname, 'node_modules/java/build/jvm_dll_path.json' );
    var client_path = path.join( jvm_path, 'server' );
    process.env.PATH = jvm_path + path.delimiter + process.env.PATH;

    client_path = JSON.stringify( path.delimiter + path.resolve( client_path ) );
    fs.writeFileSync( dll_path, client_path );
}
```







