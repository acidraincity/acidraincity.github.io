# 7zip. 쉘에서 사용하기

20131010



윈도우 상에서 압축작업을 수행하는 배치 프로세스를 구성할 일이 있어서 찾아보니 7zip이 쉘 커맨드 인터페이스를 지원하고 있습니다.

사용법은 대략 다음과 같습니다.

```
7z a -t7z -mx9 -xr!.svn\ -p1234 -mhe d:\``test``.7z .\src1\ .\src2\
```

- **7z** 실행프로그램
- **a** 압축실행
- **-t7z** 압축방식을 7z으로 설정
- **-mx9** 압축효율 지정, 0(빠름,효율낮음)~9(느림,효율높음)
- **-xr!.svn** .svn 폴더들을 압축에서 제외
- **-p1234** 압축암호를 1234로 지정
- **-mhe** 헤더도 인크립션한다. 엔트리 파일명 탐색이 불가능하게 만든다
- **d:\test.zip** 생성될 압축파일
- **.\src1** 압축파일에 포함될 디렉토리
- **.\src2** 압축파일에 포함될 디렉토리




자세한 레퍼런스는 아래 포스트를 참고하세요.

<http://www.dotnetperls.com/7-zip-examples>



인크립션 관련해서는 아래 포스트도 참고하세요.

<http://www.cnx-software.com/2011/02/22/aes-256-encryption-and-file-names-encryption-with-7-zip-7z/>

-t7z 옵션으로 7z 형식 압축을 할 경우에 암호화 방식은 AES256만 사용됩니다.
-tzip 옵션을 줘서 zip 형식으로 압축할 경우에 AES256 방식으로 암호화 하려면 -mem=AES256 옵션을 추가해야 한다고 합니다.
아래 포스트를 참고하세요.

<http://sourceforge.net/p/sevenzip/discussion/45797/thread/bdc0378e/>



그런데 볼륨을 나눠준다는 -v 옵션은 설명과 달리 제대로 동작하지 않았습니다. 아직 구현되지 않았다는 오류메시지를 보여줍니다.



Mac 콘솔에서 7zip을 사용하는 방법에 대해서는 아래 포스트를 참고하세요.

<http://www.koozie.org/blog/2014/08/creating-aes256-encrypted-zip-archive-files-mac-command-line/>

