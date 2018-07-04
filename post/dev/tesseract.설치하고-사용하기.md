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

