# gradle. 환경변수에서 값을 가져오기

20190619



`System.getenv()` 함수를 사용해서 환경변수 값을 읽어올 수 있습니다.

다음과 같은 식으로 사용합니다.



```groovy
signingConfigs {
		release {
			storeFile file( "/some/path/private.keystore" )
			storePassword System.getenv( "ANDROID_CERT_PASSWORD" )
			keyAlias "somealias"
			keyPassword System.getenv( "ANDROID_CERT_PASSWORD" )
		}
	}
```

