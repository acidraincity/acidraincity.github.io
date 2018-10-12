# kotlin. 재미있는 문법들

20181012



인덱스를 받는 함수를 사용해 리스트를 초기화할 수 있다.

```kotlin
val squares = List(5) { (it + 1) * (it + 1) }
println(squares) // [1, 4, 9, 16, 25]
```

<https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list.html>



