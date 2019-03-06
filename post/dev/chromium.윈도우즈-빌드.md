# chromium. 윈도우즈 빌드

20190102



<https://chromium.googlesource.com/chromium/src/+/master/docs/windows_build_instructions.md>

위의 가이드 문서대로 진행하였습니다.



`PATH_TO_INSTALLER.EXE` 의 로컬 시스템상 경로가 어떻게 되는지 파악하기 어려웠는데요.

아래 문서에 가이드되어 있습니다.

<https://docs.microsoft.com/ko-kr/visualstudio/install/modify-visual-studio?view=vs-2017>

```
C:\Program Files (x86)\Microsoft Visual Studio\Installer\vs_installer.exe
```

라고 말이죠.



`gn gen out/Default` 를 실행했을 때 다음과 같은  오류가 발생하는 경우가 있었습니다.

```
Exception: dbghelp.dll not found in "C:\Program Files (x86)\Windows Kits\10\Debuggers\x64\dbghelp.dll"
```

이 때에는 아래 링크를 참고해서 Windows SDK (ver. 10.0.16299.91) 을 설치해야 합니다.

<https://groups.google.com/a/chromium.org/forum/#!msg/chromium-dev/PsqFiJ-j5B4/9wO3wflWCQAJ>

<https://developer.microsoft.com/en-us/windows/downloads/sdk-archive>