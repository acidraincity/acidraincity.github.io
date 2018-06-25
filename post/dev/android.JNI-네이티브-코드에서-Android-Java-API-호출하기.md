# android. JNI 네이티브 코드에서 Android Java API 호출하기

20131118



JNI C++ 네이티브 코드에서 Java 클래스 객체를 생성하거나 메소드를 호출해야 하는 등의 필요가 생기는 경우가 있습니다.

Java 코드에서는 한줄로 끝나는 간단한 메소드 콜도 여러가지 리플렉션 처리가 들어가게 되기 때문에 코딩하기 지겹고 귀찮다는 생각이 먼저 들게 되죠.

아무튼, 다음은 C++ 에서 안드로이드 시스템 API를 호출하는 몇가지 코드 케이스입니다.

 

- 어플리케이션 패키지네임 조회

```cpp
jstring GetApplicationPackageName( JNIEnv* env, jobject context ){
 jclass cls = env->GetObjectClass( context );
 jmethodID mId = env->GetMethodID( cls, "getPackageName", "()Ljava/lang/String;" );
 return ( jstring ) env->CallObjectMethod( context, mId );
}
```

 

 

- 빌드 정보 조회

```cpp
jstring GetBuildInfo( JNIEnv* env, const char* fieldName ){
 jclass cls = env->FindClass( "android/os/Build" );
 jfieldID fid = env->GetStaticFieldID( cls, fieldName, "Ljava/lang/String;" );
 jstring rtn = ( jstring ) env->GetStaticObjectField( cls, fid );
 env->DeleteLocalRef( cls );
 return rtn;
}
```

 

 

- 디바이스 아이디 조회

```cpp
jstring GetDeviceId( JNIEnv* env, jobject context ){
 jclass cls = env->GetObjectClass( context );
 jmethodID mid = env->GetMethodID( cls, "getSystemService", "(Ljava/lang/String;)Ljava/lang/Object;" );
 jobject obj = env->CallObjectMethod( context, mid, env->NewStringUTF( "phone" ) );

 env->DeleteLocalRef( cls );

 cls = env->GetObjectClass( obj );
 mid = env->GetMethodID( cls, "getDeviceId", "()Ljava/lang/String;" );
 jstring deviceId = ( jstring ) env->CallObjectMethod( obj, mid );

 env->DeleteLocalRef( cls );
 env->DeleteLocalRef( obj );

 return deviceId;
}
```

 

- 안드로이드 아이디 조회

```cpp
jstring GetAndroidId( JNIEnv* env, jobject context ){
 jclass cls = env->GetObjectClass( context );

 jmethodID mid = env->GetMethodID( cls, "getContentResolver", "()Landroid/content/ContentResolver;" );
 jobject obj = env->CallObjectMethod( context, mid );

 env->DeleteLocalRef( cls );

 cls = env->FindClass( "android/provider/Settings$Secure" );
 mid = env->GetStaticMethodID( cls, "getString", "(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;" );
 jstring androidId = ( jstring ) env->CallStaticObjectMethod( cls, mid, obj, env->NewStringUTF( "android_id" ) );

 env->DeleteLocalRef( cls );
 env->DeleteLocalRef( obj );

 return androidId;
}
```

 

 

- assets 폴더의 리소스 파일 데이터 읽기

```cpp
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>

//리턴받은 char* 는 delete 해야 합니다.
char* ReadFromAsset( JNIEnv* env, jobject context, const char* filename ){
 jclass clsCtx = env->GetObjectClass( context );
 jmethodID mId = env->GetMethodID( clsCtx, "getResources", "()Landroid/content/res/Resources;" );
 jobject res = env->CallObjectMethod( context, mId );
 env->DeleteLocalRef( clsCtx );
 
 jclass clsRes = env->GetObjectClass( res );
 mId = env->GetMethodID( clsRes, "getAssets", "()Landroid/content/res/AssetManager;" );
 jobject am = env->CallObjectMethod( res, mId );
 env->DeleteLocalRef( clsRes );
 env->DeleteLocalRef( res );

 char* rtn = NULL;
 AAssetManager* mgr = AAssetManager_fromJava( env, am );
 AAsset* asset = AAssetManager_open( mgr, ( const char *) filename, AASSET_MODE_UNKNOWN );
 if( asset ){
  long size = AAsset_getLength( asset );
  rtn = new char[ size ];
  AAsset_read ( asset, rtn, size );
  AAsset_close( asset );
 }

 env->DeleteLocalRef( am );

 return rtn;
}
```

 

- 네트워크 연결상태 확인

```cpp
jboolean IsNetworkConnected( JNIEnv* env, jobject context ){

 jclass cls = env->GetObjectClass( context );
 jmethodID mid = env->GetMethodID( cls, "getSystemService", "(Ljava/lang/String;)Ljava/lang/Object;" );
 jobject obj1 = env->CallObjectMethod( context, mid, env->NewStringUTF( "connectivity" ) );
 env->DeleteLocalRef( cls );
 cls = env->GetObjectClass( obj1 );
 mid = env->GetMethodID( cls, "getActiveNetworkInfo", "()Landroid/net/NetworkInfo;" );
 jobject obj2 = env->CallObjectMethod( obj1, mid );
 env->DeleteLocalRef( cls );
 env->DeleteLocalRef( obj1 );
 jboolean rtn = false;
 if( obj2 != NULL ){
  cls = env->GetObjectClass( obj2 );
  mid = env->GetMethodID( cls, "isConnected", "()Z" );
  rtn = env->CallBooleanMethod( obj2, mid );
  env->DeleteLocalRef( cls );
  env->DeleteLocalRef( obj2 );
 }
 return rtn;
}
```

 

위의 코드를 사용하는 어플리케이셔은 다음 퍼미션을 가지고 있어야 합니다.

```
android.permission.ACCESS_NETWORK_STATE
```