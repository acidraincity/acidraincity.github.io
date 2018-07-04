# tesseract. 설치하고 사용하기

20180704



vcpkg 를 통해서 tesseract를 설치합니다.

먼더 vcpkg 환경을 구성합니다.



```shell
>git clone https://github.com/Microsoft/vcpkg.git
>.\bootstrap-vcpkg.bat
>.\vcpkg integrate install
```



환경 구성이 완료되면, 다음과 같은 안내문구가 표시됩니다.

```
Applied user-wide integration for this vcpkg root.

All MSBuild C++ projects can now #include any installed libraries.
Linking will be handled automatically.
Installing new libraries will make them instantly available.

CMake projects should use: "-DCMAKE_TOOLCHAIN_FILE=C:/Users/acidr/app/vcpkg/scripts/buildsystems/vcpkg.cmake"
```

비주얼 프로젝트와 CMake 프로젝트에서 편하게 사용할 수 있을 것 같습니다.



vcpkg를 통해서 tesseract를 설치합니다.

```shell
>vcpkg install tesseract:x64-windows --head
```

50분의 빌드 진행 후에 tesseract가 설치되었습니다.

다음과 같은 안내메시지가 친절하게 표시됩니다.

```
The package tesseract:x64-windows provides CMake targets:

    find_package(Tesseract CONFIG REQUIRED)
    target_link_libraries(main PRIVATE libtesseract)
```



그런데 문제가 있어서 32비트 버전으로 다시 설치했습니다.

(Error in pixCreateHeader: height must be > 0라는 런타임 오류 발생)

```
>vcpkg install tesseract:x86-windows --head
```



vcpkg\installed\x86-windows\tools\tesseract 경로에 실행 프로그램이 생성되는데요. 

tesseract.exe 프로그램이 위치하는 폴더 하위에 tessdata 라는 이름의 폴더를 만들고 데이터 파일을 배치해야 합니다.

<https://github.com/tesseract-ocr/tesseract/wiki/Data-Files>



다음과 같이 실행할 수 있습니다.

```
>tesseract.exe D:\#_reservation\n.png output --oem 1 -l kor
```









