# css. 옆 컬럼 넓이의 나머지만큼 넓이 지정하기

20171222



다음과 같이 합니다.

```
<div style="position:relative;width:400px;height:400px;background-color:gray;color:white;">
	<div style="float:left;width:calc(100% - 100px);height:100%;position:relative;background-color:purple;">
		100% - 100px
		<div style="position:relative;width:50%;height:50%;left:0px;top:0px;background-color:blue;">
			half size of parent
		</div>
	</div>
	<div style="float:left;position:relative;width:100px;background-color:red;">
		100px
	</div>
</div>
```



calc 를 사용해서 전체 넓이에 대한 연산값을 지정할 수 있습니다.

<http://techbug.tistory.com/215>



position을 absolute로 지정할 경우에 display가 static인 부모는 무시됩니다. 부모 요소 기준으로 자식의 레이아웃 요소(넓이 등)를 지정하려면 부모 요소의 display속성을 relative로 지정해야 합니다.

<https://www.codecademy.com/ko/forum_questions/559109be76b8feb4970005ad>



두개의 div 요소가 옆으로 나란히 위치해야 하므로 두 div 요소에 모두 float:left 속성을 지정하였습니다.

