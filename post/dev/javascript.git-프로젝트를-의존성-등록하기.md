# javascript. git 프로젝트를 의존성 등록하기

20190801

git 프로젝트를 package.json 에 의존성 등록하기 위해서는 다음과 같이 할 수 있습니다. 

npm 툴은 원격 레파지토리상의 모듈을 설치하는 것과 같은 방식으로 로컬 모듈을 설치할 수 있는 방법을 제공합니다. package.json이 존재하는 대상 프로젝트 루트 디렉토리 경로를 모듈 이름 자리에 기입하면 됩니다.

예를 들어 다음과 같습니다.

```bash
npm install --save https://github.com/jeon-studio/xmlview/
```



설치 후에 package.json에는 다음과 같이 의존성이 등록되고, 실제적으로는 node_modules 하위에 링크로 연결됩니다.

```json
{
  ...
  "dependencies": {
    "xmlview": "git+https://github.com/jeon-studio/xmlview.git"
  }
}
```



다음 포스트를 참고하세요.

<https://stackoverflow.com/questions/17509669/how-to-install-an-npm-package-from-github-directly>

