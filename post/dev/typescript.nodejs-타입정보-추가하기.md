# typescript. nodejs 타입정보 추가하기

20171101



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



레가시 javascript 라이브러리들에 대해서도 타입 정보를 지원할 경우에 추가할 수 있습니다.

다음 사이트에서 라이브러리 이름으로 검색해볼 수 있습니다.

<https://microsoft.github.io/TypeSearch/>