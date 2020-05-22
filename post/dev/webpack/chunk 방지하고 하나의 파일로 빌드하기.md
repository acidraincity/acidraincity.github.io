---

layout: post
date: 2020-05-22
title: chunk 방지하고 하나의 파일로 빌드하기
author: Jeon Yongtae
categories: [webpack]
tags: [webpack]

---

LimitChunkCountPlugin 을 사용한다.

```javascript
const webpack = require('webpack');

...

plugins: [
  new webpack.optimize.LimitChunkCountPlugin({
    maxChunks: 1,
  }), 
],
```


