---
layout: post
date: 20200410
title: Java의 getter를 override하기
author: Jeon Yongtae
categories: [kotlin]
tags: [kotlin]
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
