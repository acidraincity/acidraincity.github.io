# android. Activity의 ContentView를 특정 Fragment로 초기화하는 코드

20171021



다음과 같은 코드를 사용해서 Fragment가 Activity의 ContentView로 사용되도록 초기화할 수 있습니다.

```
public class SomeActivity extends FragmentActivity{
 @Override
 protected void onCreate( @Nullable Bundle savedInstanceState ){
  super.onCreate( savedInstanceState );

  SomeFragment fragment = new SomeFragment();
  FragmentTransaction trans = getFragmentManager().beginTransaction();
  trans.replace( android.R.id.content, fragment );
  trans.commit();

 }
}
```