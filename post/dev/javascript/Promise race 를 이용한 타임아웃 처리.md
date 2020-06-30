---
layout: post
date: 2017-10-21
title: Promise race 를 이용한 타임아웃 처리
author: Jeon Yongtae
categories: [JavaScript]
tags: [JavaScript]
---

Promise.race로 생성된 Promise는 관리하는 Promise들 중에 하나만 resolve 또는 reject 되어도 해당 상태로 완료됩니다.

이를 이용해 특정 Promise 에 대해 timeout을 설정할 수 있습니다.

```
var timeout = 1000 * 3;//3초

var workPromise = new Promise( function( resolve, reject ){

    //... 시간이 걸릴 수 있는 로직을 넣어주세요 ...

    resolve();
} );

var timeoutPromise = new Promise( function( resolve, reject ){
    setTimeout( function(){
        reject( new Error( 'timeout' ) );
    }, timeout );
} );

var promise = Promise.race( [ workPromise, timeoutPromise ] );
promise.then( 
    function(){
        //workPromise가 제한시간 안에 resolve 되었을 때 호출됩니다.
    },
    function( err ){
        //workPromise가 제한시간 안에 resolve 되지 못했을 때 호출됩니다.
    } 
);
```
