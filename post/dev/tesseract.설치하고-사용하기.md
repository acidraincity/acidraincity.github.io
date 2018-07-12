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



그런데 문제(Error in pixCreateHeader: height must be > 0라는 런타임 오류 발생)가 있어서 32비트 버전으로 다시 설치했습니다.

```
>vcpkg install tesseract:x86-windows --head
```



의존하는 라이브러리들을 별개의 dll이 아니라 exe 파일 안에 포함하려면 다음과 같이 빌드합니다.

```
>vcpkg install tesseract:x86-windows-static --head
```



vcpkg\installed\x86-windows\tools\tesseract 경로에 실행 프로그램이 생성되는데요. 

tesseract.exe 프로그램이 위치하는 폴더 하위에 tessdata 라는 이름의 폴더를 만들고 데이터 파일을 배치해야 합니다.

<https://github.com/tesseract-ocr/tesseract/wiki/Data-Files>



그리고 아래의 파일들도 tessdata 하위에 배치하는게 좋습니다.

<https://github.com/tesseract-ocr/tesseract/blob/master/tessdata/pdf.ttf>

<https://github.com/tesseract-ocr/tessdata/blob/master/osd.traineddata>



이렇게 빌드된 프로그램은 VC++ redistributable 패키지에 의존성을 가집니다. 안좋은 점이네요.



다음과 같이 실행할 수 있습니다.

```
>tesseract.exe D:\#_reservation\n.png output --oem 1 -l kor
```



아웃풋을 pdf로 생성하려면 다음과 같이 실행합니다.

```
>tesseract.exe C:\path\list.txt C:\path\output --oem 1 -l kor -c textonly_pdf=1 -c tessedit_create_pdf=1
```

list.txt 에는 다음과 같이 OCR 대상 이미지의 리스트를 기입합니다.

```
C:\path\1.png
C:\path\2.png
C:\path\3.png
```



아웃풋을 pdf로 할 때, 생성되는 pdf 문서의 페이지 사이즈는 인풋 이미지의 DPI에 의해 결정됩니다.

<https://github.com/tesseract-ocr/tesseract/issues/150>

>Resolution (DPI) is extracted from the header of the input image. If missing, then Tesseract has no choice but to make something up. Don't do that! Many tools can be used to inspect and adjust DPI for an input image file. If you want to use ImageMagick, the commands are "identify -verbose" to inspect and "mogrify -density 300x300 -units PixelsPerInch" to set. 



아래 포스트는 png 파일의 DPI를 설정할 수 있는 자바 코드를 보여줍니다.

<https://stackoverflow.com/a/4833697>

