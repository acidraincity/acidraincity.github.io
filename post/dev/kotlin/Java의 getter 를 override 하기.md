---
layout: post
date: 2020-04-10 
title: Java의 getter 를 override 하기
author: Jeon Yongtae
categories: [Kotlin]
tags: [Kotlin]
---
자바 getter 메소드를 kotlin 에서 재정의하는 지저분한 방법 중의 하나를 설명한다.
```java
class User{
    public abstract String getName();
}
```
```kotlin
class Student : User{
    private val name = "xxx"
    override fun getName() = name
}
```
