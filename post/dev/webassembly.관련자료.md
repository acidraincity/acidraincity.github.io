# webassembly. 관련자료

20171227



[Loading and running WebAssembly code](https://developer.mozilla.org/en-US/docs/WebAssembly/Loading_and_running)

wasm 을 로딩하고 모듈과 인스턴스를 획득하는 표준절차를 설명합니다.



[Understanding the JS API](http://webassembly.org/getting-started/js-api/)

wasm 통신 인터페이스와 관련 API에 대한 설명글입니다.



[[번역] 자바스크립트로 웹어셈블리 모듈 인스턴스 생성하기](https://blog.kesuskim.com/2017/07/%E1%84%87%E1%85%A5%E1%86%AB%E1%84%8B%E1%85%A7%E1%86%A8-Create-WebAssembly-Module-Instance-with-JavaScript/)

importObj 에 대한 설명글입니다.



[Standalone WebAssembly Example](https://gist.github.com/kripken/59c67556dc03bb6d57052fedef1e61ab)

**가장 기본적인 wasm 샘플 코드입니다.**



[Calling alert from WebAssembly (WASM)](https://gist.github.com/cure53/f4581cee76d2445d8bd91f03d4fa7d3b)

**자바스크립트 funciton을 임포트해서, wasm에서 호출할 수 있도록 하는 샘플 코드입니다.**



------



<https://davidmcneil.gitbooks.io/the-rusty-web/content/setup-and-hello-world.html>

RUST와 Emscripten을 설치하고 기본적인 코드를 빌드하는 방법을 설명합니다.

아래 두개의 포스트는 거의 비슷한 내용을 담고 있습니다.

- [Compiling Rust to WebAssembly Guide](https://hackernoon.com/compiling-rust-to-webassembly-guide-411066a69fde)
- [Compiling to the web with Rust and emscripten](https://users.rust-lang.org/t/compiling-to-the-web-with-rust-and-emscripten/7627)




[The Path to Rust on the Web](https://hoverbear.org/2017/04/06/the-path-to-rust-on-the-web/)

위에 더해, 러스트와 자바스크립트의 상호 호출 코드를 보여줍니다. Emscripten의 템플릿 코드에 의존적입니다.



[WebAssembly and Rust](https://github.com/raphamorim/wasm-and-rust)

비슷한 내용입니다.



[stdweb](https://github.com/koute/stdweb)

RUST로 wasm을 구현할 때의 WebAPI접근에 대한 기본 라이브러리 구현입니다.



<https://davidmcneil.gitbooks.io/the-rusty-web/content/creating-a-library.html>

RUST로 wasm을 작성할 때 인터페이스를 노출(export)하는 방법과 cargo라이브러리 구성 방법을 설명합니다.



[Get Started with Rust, WebAssembly, and Webpack](https://medium.com/@ianjsikes/get-started-with-rust-webassembly-and-webpack-58d28e219635)

RUST, Webpack, Webassembly를 연동해서 사용하는 방법을 설명합니다.



[rust-native-wasm-loader](https://github.com/dflemstr/rust-native-wasm-loader)
위와 같은 용도의 또 다른 Webpack 로더 구현입니다.



[rustify](https://github.com/browserify/rustify)

위와 비슷하지만 Webpack대신에 browserify를 사용합니다. 



------



[웹 어셈블리를 보다 쉽게 웹 어플리케이션에 적용하는 방법](http://meetup.toast.com/posts/121)

AssemblyScript 에 대한 설명글입니다.



------



[Awesome Wasm](https://github.com/mbasso/awesome-wasm)

웹어셈블리에 대한 유용한 자료들이 모아져 있습니다.



<https://developer.mozilla.org/ko/docs/WebAssembly>

모질라의 웹어셈블리 소개페이지입니다.



<http://webassembly.org/>

웹어셈블리 공식사이트입니다.



<http://emscripten.org/>

LLVM 기반 코드를 브라우저에서 구동시키기 위한 프로젝트입니다.



[Emscripten SDK](https://github.com/juj/emsdk)

wasm을 빌드하기 위한 툴을 제공합니다.



