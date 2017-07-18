# TypeScript

20170718



### node.js 모듈 사용시 require를 인식 못하는 경우의 대응

@types/node 모듈을 설치합니다.

```
yarn add @types/node --dev
```

tsconfig.json에 다음과 같은 설정을 추가합니다.

```
"compilerOptions" : {
  "types": [
  	"node"
  ]
}
```



