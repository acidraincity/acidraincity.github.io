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







