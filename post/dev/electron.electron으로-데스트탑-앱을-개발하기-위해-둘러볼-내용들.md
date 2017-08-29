# electron. electron으로 데스크탑 앱을 개발하기 위해 둘러볼 내용들

20170829



1.

electron은 Javascript, CSS, HTML5 등의 웹기술을 사용해 크래스플랫폼(Window, Mac, Linux)에서 동작하는 데스크탑용 앱을 개발할 수 있도록 하는 프레임워크입니다.



크롬부라우저를 기반으로 하고 있기 때문에 HTML, CSS로 화면을 구성해 표시하고 Javascript로 Document 요소를 제어할 수 있습니다.

node.js API를 통해 사용자 시스템 자원에 접근할 수 있습니다.



다음과 같은 앱들이 Electron 프레임워크를 이용해 개발되었습니다.

[http://electron.atom.io/apps/](http://electron.atom.io/apps/)



2.

electron으로 개발하기 위해서는 다음 요소들에 대한 이해가 필요할 수 있습니다.



NPM - 원격 저장소를 가지는 패키지 의존성 관리자

[https://www.npmjs.com](https://www.npmjs.com)



Node.js 모듈 규칙

[http://theeye.pe.kr/archives/1667](http://theeye.pe.kr/archives/1667)



Node.js가 제공하는 시스템 API

[https://nodejs.org/dist/latest-v7.x/docs/api](https://nodejs.org/dist/latest-v7.x/docs/api)



ECMAScript 6의 Promise 등 새로운 기능들에 대해서도 봐두면 좋습니다.

[http://es6-features.org/](http://es6-features.org/)



3.

빌드된 electron 을 다음 링크에서 다운로드 받을 수 있습니다.

[http://electron.atom.io/releases](http://electron.atom.io/releases)



intellij IDE로 Electron 개발환경을 구성하는 방법에 대해서는 아래 링크를 참고하세요.

[https://blog.jetbrains.com/webstorm/2016/05/getting-started-with-electron-in-webstorm](https://blog.jetbrains.com/webstorm/2016/05/getting-started-with-electron-in-webstorm)

github-electron.d.ts 라이브러리를 다운로드받아 프로젝트에 적용하고,

node.js 실행설정을 Electron 환경에 맞게 구성하면 됩니다.



그리고 아래 가이드를 따라서 간단한 앱을 구성하고 실행해 볼 수 있습니다.

[https://github.com/electron/electron/blob/master/docs-translations/ko-KR/tutorial/quick-start.md](https://github.com/electron/electron/blob/master/docs-translations/ko-KR/tutorial/quick-start.md)



4.

개발한 앱을 패키징해 배포하기 위해서

electron builder를 사용합니다.

[https://github.com/electron-userland/electron-builder](https://github.com/electron-userland/electron-builder)

electron builder 에서 요구하는 환경을 잘 구성한 후 npm run dist 명령으로

각 플랫폼에 맞는 앱 설치 패키지를 생성할 수 있습니다.



5.

electron의 동작원리를 이해하기 위해서는 다음 개념을 이해하여야 합니다.

메인 프로세스와 렌더러 프로세스 구분 구조

메인 프로세스와 렌터러 프로세스의 통신방법



ipcMain

[https://github.com/electron/electron/blob/master/docs-translations/ko-KR/api/ipc-main.md](https://github.com/electron/electron/blob/master/docs-translations/ko-KR/api/ipc-main.md)

ipcRenderer

[https://github.com/electron/electron/blob/master/docs-translations/ko-KR/api/ipc-renderer.md](https://github.com/electron/electron/blob/master/docs-translations/ko-KR/api/ipc-renderer.md)

각각의 BrowserWindow는 각각의 프로세스를 가지며 ipc를 통해 메인 프로세스와 통신하게 됩니다.



6.

electron 개발에 사용할 수 있는 유용한 리소스들을 아래 URL에서 찾아볼 수 있습니다.

https://github.com/sindresorhus/awesome-electron



7.

필요로하는 기능을 electron이 API로 제공하지 않을 경우에는

electron 코드를 수정하고 빌드해 사용할 수 있습니다.

electron이 오픈소스 프로젝트이기 때문입니다.