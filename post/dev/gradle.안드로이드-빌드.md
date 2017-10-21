# gradle. 안드로이드 빌드

20170726



### c/c++ 지원



cmake 형식인 경우 build.gradle 의 android 항목에 다음과 같이 설정합니다.

```
android {
	...
	externalNativeBuild {
		cmake {
			path 'CMakeLists.txt'
		}
	}
	...
}
```



ndkBuild 형식인 경우 build.gradle 의 android 항목에 다음과 같이 설정합니다.

```
android {
	...
	externalNativeBuild {
		ndkBuild {
			path 'src/main/jni/Android.mk'
		}
	}
	...
}
```



