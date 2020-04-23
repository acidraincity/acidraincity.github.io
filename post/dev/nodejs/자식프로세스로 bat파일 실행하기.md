---
layout: post
date: 2017-07-19
title: 자식프로세스로 bat파일 실행하기
author: Jeon Yongtae
categories: [Node.js]
tags: [Node.js]
---


아래는 윈도우즈OS에서 bat 파일을 실행하고 표준출력을 읽어오는 코드 템플릿입니다.

stdout에 특정한 인코딩을 지정할 수 있습니다.

```
const child = require( 'child_process' ).spawn;
const process = child( 'cmd.exe', [ '/c', 'my.bat' );
process.stdout.setEncoding( 'utf8' );
process.stderr.setEncoding( 'utf8' );
process.stdout.on( 'data', ( data )=>{
	console.log( 'stdout : ' + data );
} );
process.stderr.on( 'data', ( data )=>{
	console.log( 'stderr : ' + data );
} );
process.on( 'exit', ( exitCode )=>{
	console.log( 'exit : ' + exitCode );
} );
```

child_process 의 exec나 execFile이 아니라 spawn을 사용한 이유에 대해서는 아래 설명을 참고해주세요.

[https://nodejs.org/api/child_process.html#child_process_spawning_bat_and_cmd_files_on_windows](https://nodejs.org/api/child_process.html#child_process_spawning_bat_and_cmd_files_on_windows)

