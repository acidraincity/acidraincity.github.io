---
layout: post
date: 2014-04-24
title: DOM 메소드와 프로퍼티
author: Jeon Yongtae
categories: [JavaScript]
tags: [JavaScript]
---

## 노드 생성



#### 1. 엘레먼트 생성

- document.createElement( 태그명 );
- 생성된 엘레먼트를 리턴
- 생성된 노드의 nodeType 은 1

```javascript
var divE = document.createElement( "div" );
```



#### 2. 텍스트노드 생성

- document.createTextNode( 텍스트문자열 );
- 생성된 텍스트노드를 리턴
- 생성된 텍스트노드의 nodeType 은 3, nodeName 은 #text

```javascript
var messageN = document.createTextNode( "hey!!" );
```

 

## 노드 복사

- 노드.cloneNode( 깊은복사여부 );
- 복사된 노드를 리턴
- 깊은복사여부가 true 이면 자식노드들까지 통째로 복사한다

```javascript
var divCE = divE.cloneNode( false );
```



## 노드의 삽입,이동



#### 1. 마지막 자식으로 추가

- 부모노드.appendChild( 추가할노드 );
- 추가된 노드를 리턴

```javascript
document.body.appendChild( divE ); 
```



#### 2. 특정 자식앞에 추가,이동

- 부모노드.insertBefore( 추가할노드, 대상자식노드 );
- 추가된 노드를 리턴
- 대상자식노드가 유효하지 않으면, 추가하는 노드는 부모의 마지막 자식노드로 추가됨

```javascript
document.body.insertBefore( divCE, divE );
```



#### 3. appendChild, insertBefore 이용시에 대상 엘레먼트가 이미 문서구조상에 존재할 경우, 기존 위치에서는 삭제되게 된다 ( 이동의 효과 )



## 노드의 삭제

- 부모노드.removeChild( 삭제할노드 );
- 삭제된 노드를 리턴

```
document.body.removeChild( divCE );
```



## 노드의 교체

- 부모노드.replaceChild( 교체할노드, 교체될노드 );
- 교체할노드가 문서상에 존재하면, 먼저 삭제된 후 교체가 진행된다

```javascript
document.body.replaceChild( divCE, divE );
```



## 엘레먼트의 속성 생성,변경,참조



#### 1. 엘레먼트의 속성 생성,변경

- 엘레먼트.setAttribute( 속성명, 속성값 );

```javascript
divE.setAttribute( "id", "firstDiv" );
```



#### 2. 엘레먼트의 속성 참조

- 엘레먼트.getAttribute( 속성명 );
- 해당 엘레먼트의 해당 속성을 리턴한다

```javascript
var divId = divE.getAttribute( "id" );
```



## 하위 엘레먼트 획득



#### 1. 하위 엘레먼트중 특정 아이디를 가진 엘레먼트를 획득

- 엘레먼트.getElementById( 아이디 );

```javascript
var searchedDiv = document.getElementById( "firstDiv" );
```



#### 2. 하위 엘레먼트중 특정 태그명을 가진 엘레먼트의 배열을 획득

- 엘레먼트.getElementsByTagName( 태그명 );

```javascript
var searchedDivArray = document.getElementsByTagName( "div" );
```



## 노드 프로퍼티



#### 1. 노드.nodeType

- 1 : ELEMENT_NODE
- 2 : ATTRIBUTE_NODE
- 3 : TEXT_NODE
- 4 : CDATA_SECTION_NODE
- 5 : ENTITY_REFERENCE_NODE
- 6 : ENTITY_NODE
- 7 : PROCESSING_INSTRUCTION_NODE
- 8 : COMMENT_NODE
- 9 : DOCUMENT_NODE
- 10 : DOCUMENT_TYPE_NODE
- 11 : DOCUMENT_FRAGMENT_NODE
- 12 : NOTATION_NODE
- nodeType 프로퍼티는 읽기 전용이며 임의로 값을 변경할 수 없음

노드타입 관련한 다음 정리글을 참고하자

<https://developer.mozilla.org/ko/docs/Web/API/Node/nodeType>



#### 2. 노드.nodeName

- nodeType이 ELEMENT_NODE일 경우 nodeName 은 tagName 과 같은 값을 가짐
- nodeType이 ATTRIBUTE_NODE일 경우 nodeName은 속성의 이름을 가짐
- nodeType이 TEXT_NODE일 경우 nodeName 은 #text 라는 문자열을 가짐
- nodeName 프로퍼티는 읽기 전용이며 임의로 값을 변경할 수 없음



#### 3. 노드.nodeValue

- 노드가 ELEMENT_NODE일 경우 nodeValue는 null 값을 가지며 읽기전용이다
- 노드가 ATTRIBUTE_NODE일 경우 nodeValue는 속성의 값을 가지며 읽기,쓰기 가능하다
- 노드가 TEXT_NODE일 경우 nodeValue는 텍스트값을 가지며 읽기,쓰기 가능하다

```javascript
var findedDivE = document.getElementById( "firstDiv" );
if( findedDivE != null && findedDivE.firstChild.nodeType == 3 ){ // 3은 TEXT_NODE임
    findedDIvE.firstChild.nodeValue = "이런식으로 주로 사용합니다";
}
```



#### 4. 노드.hasChildNodes

- 자식노드를 가지고 있는지 여부를 boolean값으로 가지며, 읽기전용이다



#### 5. 노드.childNodes

- 자식 노드들의 배열을 가지며, 읽기 전용이다



#### 6. 노드.firstChild

- 첫번째 자식노드에 대한 참조를 가지며, 읽기 전용이다



#### 7. 노드.lastChild

- 마지막 자식노드에 대한 참조를 가지며, 읽기 전용이다



#### 8. 노드.nextSibling

- 같은 parentNode 를 가지는 동일수준 노드들 중 다음 노드에 대한 참조를 가지며, 읽기 전용이다



#### 9. 노드.previousSibling

- 같은 parentNode 를 가지는 동일수준 노드들 중 이전 노드에 대한 참조를 가지며, 읽기 전용이다



#### 10. 노드.parentNode

- 부모노드의 참조를 가지며, 읽기전용이다

