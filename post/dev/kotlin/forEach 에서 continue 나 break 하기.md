---
layout: post
date: 2020-05-12
title: forEach 에서 continue 나 break 하기
author: Jeon Yongtae
categories: [Kotlin]
tags: [Kotlin]
---

# continue 하기

```kotlin
fun main(args: Array<String>) {
    val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    nums.forEach {
       if (it == 5) return@forEach
       println(it)
    }
}
```

# break 하기

```kotlin
fun main(args: Array<String>) {
    val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    run loop@{
        nums.forEach {
           if (it == 5) return@loop
           println(it)
        }
    }
}
```

# 참고

- [loops - `break` and `continue` in `forEach` in Kotlin - Stack Overflow](https://stackoverflow.com/questions/32540947/break-and-continue-in-foreach-in-kotlin)

- [for, foreach, foreachIndexed 루프탈출 하기 :: 프로그래밍좀비](https://soulduse.tistory.com/71)
