# C++. unique_ptr

20171021



생성

```
std::unique_ptr< int > ptr( new int( 6  ) );

//또는

auto ptr = std::unique_ptr< int >( new int( 6 ) );
```

전달

```
std::unique_ptr< int > func(){
 return std::unique_ptr< int > ptr( new int( 6  ) );
}

//또는

std::unique_ptr< int > func(){
 auto ptr = std::unique_ptr< int >( new int( 6 ) );
 return std::move( ptr ); 
}
```

메모리 해제하고 소유권 반납

```
std::unique_ptr< int > ptr( new int( 6  ) );
ptr.reset();
```

메모리 해제 없이 소유권 반납

```
std::unique_ptr< int > ptr( new int( 6  ) );
int* p = ptr.release();
delete p;
```

기존 객체의 메모리 해제하고 다른 객체로 초기화

```
std::unique_ptr< int > ptr( new int( 6  ) );
ptr.reset( new int( 7 ) );
printf( “%d\n”, *( ptr.get() ) );//7
```