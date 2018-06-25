# css. flexbox 남은 공간 전체를 차지하기

20180528



세로 레이아웃

```html
<div style="width:300px;height:300px;display:flex;flex-direction:column">
    <div style="flex:1;display:flex;padding:5px;background: red">
        <div style="height:100%;width:100%;background: blue;"></div>
    </div>
    <div style="height: 100px;background: aqua"></div>
</div>
```



가로 레이아웃

```html
<div style="width:300px;height:300px;display:flex;">
    <div style="flex:1;display:flex;padding:5px;background: red">
        <div style="height:100%;width:100%;background: blue;"></div>
    </div>
    <div style="width: 100px;background: aqua"></div>
</div>
```



플렉스 콘테이너의 아이템이 되는, 나머지 공간을 채울 엘레먼트의 display 속성도 flex 로 설정해 주어야 합니다.