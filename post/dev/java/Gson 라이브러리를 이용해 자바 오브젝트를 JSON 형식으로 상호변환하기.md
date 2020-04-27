---
layout: post
date: 2014-04-17 
title: Gson 라이브러리를 이용해 자바 오브젝트를 JSON 형식으로 상호변환하기
author: Jeon Yongtae
categories: [Java]
tags: [Java]
---


기기간 데이터 전송이 필요한 네트워크 프로그램에서
다음과 같은 기본적인 직렬화 방법을 통해 자바 객체를 전달하고 있었습니다.

```
private byte[] serialize( Object object )throws Exception{
 ByteArrayOutputStream bios = new ByteArrayOutputStream();
 ObjectOutputStream oos = null;
 try{
  oos = new ObjectOutputStream( bios );
  oos.writeObject( object );
  oos.flush();
  return bios.toByteArray();
 }finally{
  oos.close();
 }
}

private Object unserialize( byte[] bytes )throws Exception{
 Object rtn = null;
 ObjectInputStream ois = null;
 try{
  ois = new ObjectInputStream( new ByteArrayInputStream( bytes ) );
  rtn = ois.readObject();
 }finally{
  ois.close();
 }
 return rtn;
}
```

하지만 이 프로그램이 고도화되면서 다른 언어로 구현된 타 플랫폼상의 프로그램과도 데이터를 교환해야 할 필요성이 생겼습니다.

이기종 플랫폼간 교환 가능한 중립 데이터 포맷으로 JSON을 사용하기로 결정합니다.

그렇다면 자바와 JSON 객체를 상호변환하기 위해서 어떤 방법을 사용할 수 있을까요.
Google이 오픈소스로 제공하는 Gson 라이브러리는 이 경우에 아주 훌륭한 해결책이 되었습니다.

위의 코드는 Gson 라이브러리를 사용하는 다음 코드로 대치할 수 있습니다.
전달의 대상이 되는 자바 오브젝트는 아무런 추가 구현 없이 그대로 사용가능합니다.

```
private byte[] serializeToJSON( Object obj )throws Exception{
 Gson gson = new Gson();
 String json = gson.toJson( obj );
 return json.getBytes();
}

private Object unserialzeFromJSON( byte[] bytes, Type type )throws Exception{
 String json = new String( bytes );
 Gson gson = new Gson();
 return gson.fromJson( json, type );
}
```

예를들어 SomeTypeData 클래스타입의 객체를 JSON 데이터로부터 복원할 때는 다음과 같이 호출할 수 있습니다.

```
byte[] bytes = ...
SomeTypeData data = unserialzeFromJSON( bytes, SomeTypeData.class );
```

객체 타입에 제너릭이 포함되어 있을 경우에는 다음과 같이 타입 정보를 넘겨주면 됩니다.

```
byte[] bytes = ...
Type type = new TypeToken< ArrayList< SomeTypeData > >(){}.getType();
ArrayList< SomeTypeData > data = unserialzeFromJSON( bytes, type );
```

GSon 라이브러리를 사용하는 프로젝트에 프로가드를 적용하면 다음과 같은 오류가 발생할 수 있습니다.

```
Missing type parameter.
java.lang.RuntimeException: Missing type parameter.
at com.google.gson.reflect.TypeToken.getSuperclassTypeParameter(Unknown Source)
at com.google.gson.reflect.TypeToken.<init>(Unknown Source)
```

이 문제를 해결하기 위해서, 프로가드 설정에 다음을 추가해줍니다.



```
##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature


# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }


# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }


##---------------End: proguard configuration for Gson  ----------

```


