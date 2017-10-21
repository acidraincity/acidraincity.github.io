# android. 반복되는 패턴 이미지 만들기

20170806



다음과 같은 방식으로 반복되는 패턴 이미지를 정의할 수 있습니다.

```java
Bitmap bitmap = ...
BitmapDrawable bitmapDrawable = new BitmapDrawable( getResources(), bitmap );
bitmapDrawable.setTileModeXY( Shader.TileMode.REPEAT, Shader.TileMode.REPEAT );
```



프로그램 코드가 아니라 xml로 리소스를 구성하려면 다음과 같이 하면 됩니다.

```
<?xml version="1.0" encoding="utf-8"?>
<bitmap xmlns:android="http://schemas.android.com/apk/res/android" 
       android:src="@drawable/ptn_wood" 
       android:tileMode="repeat" 
/>
```



