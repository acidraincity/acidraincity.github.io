---
layout: post
date: 2020-08-19
title: Task 의존관계 순서 지정하기
author: Jeon Yongtae
categories: [Gradle]
tags: [Gradle]
---



mustRunAfter 로 선행 Task 들의 실행 순서를 지정할 수 있다.

```kotlin
tasks.register("packageForRelease") {

    dependsOn("clean")
    dependsOn("build").mustRunAfter("clean")

    doLast {

    }
}

```
