# javascript. 비동기로 호출되는 내부함수에서 외부의 지역변수값 캡쳐하기

20160519


비동기로 호출되는 내부함수에서 지역변수를 참조할 경우에 그 값은 이미 변경되어 있을 수 있습니다.
변수가 가지고 있는 값 자체를 내부함수로 전달하고 싶다면
다음과 같이 bind 함수를 사용할 수 있습니다.



```javascript
var all = [];
for( var i = 0; i < 10; i++ ){
 var promise = Promise.resolve()
  .then( function(){ return 'some data' } )
  .then( function(){
   console.log( i );//10이 10번 찍힙니다. 참조만 유지되는 것이죠.
   console.log( arguments[ 0 ] );//0에서 9까지가 차례로 찍힙니다.
   console.log( arguments[ 1 ] );//그냥 찍어보는 'some data'
  }
  .bind( this, i ) );
 all.push( promise );
}
Promise.all( all );

```



