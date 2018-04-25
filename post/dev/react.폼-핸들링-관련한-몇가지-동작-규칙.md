# react. 폼 핸들링 관련한 몇가지 동작 규칙

20180425



1. 리엑트에서 폼 컨트롤은  value 와 defalueValue 속성을 가진다.
2. <textarea></textarea> 태그 사이에 들어가는 텍스트는 defaultValue 로  처리된다.
3. 폼 컨트롤에 value 값을 지정하면 사용자는 그 값을 변경할 수 없다. 이 경우에 값을 변경하려면 onChange 이벤트를 핸들링해서 해당 value 값을 다시 셋팅해 주어야 한다. (Controlled Component)

```react
render() {
    return (
        <div>
        <input 
        	type="text" 
        	value={ this.state.textValue } 
        	onChange={
        		()=>this.setState( 
        			Object.assign( 
        				{},
                        this.state, 
                        { textValue : e.target.value }
					) 
				)
        	}
        	/>
        </div>
    );
}
```

4. 폼 컨트롤에 지정된 defaultValue 값은 마운트된 이후에는 다시 렌더링되어도 새로 지정한 값으로 변경되지 않는다. 사용자 입력에 의해서만 그 값이 변경된다. (Uncontrolled Component)
5. 컴포넌트를 강제로 remount 시키려면, setState 시에 리마운트할 컴포넌트의 key 값을 변경시켜야 한다.

