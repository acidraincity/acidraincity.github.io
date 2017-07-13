# windows. 소프트링크 만들기

20170713



윈도우즈의 cli 프로그램인 mklink를 실행해서 리눅스처럼 소프트링크를 만들 수 있습니다.



```

c:\test> mklink /d linkname c:\Windows

```



위와 같이 실행하면 c:\Windows 폴더를 링크하는 c:\test\linkname 폴더가 생성됩니다. 