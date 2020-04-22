---
layout: post
date: 2020-03-04 
title: feature 활성화하기
author: Jeon Yongtae
categories: [rust]
tags: [rust]
---

다음과 같은 형식을 사용해서 빌드시에 특정 feature 들을 활성화시킬 수 있다.

```
cargo build --release --features "shumway pdf"
```

## 참고자료
<https://doc.rust-lang.org/cargo/reference/manifest.html#usage-in-end-products>
<https://doc.rust-lang.org/1.9.0/book/conditional-compilation.html>