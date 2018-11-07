# typescript. customElements.define 사용하기

20181107



커스텀 웹컴포넌트를 만드는 customElements.define 메소드( [참고 - 사용자설정 요소 v1: 재사용 가능한 웹 구성 요소](https://developers.google.com/web/fundamentals/web-components/customelements?hl=ko) )는 파라미터로 es6 클래스를 전달받는다.

typescript 빌드타겟을 es5로 설정할 경우에는 이때문에 오류가 발생하게 된다.



이를 회피하기 위해서는 es5에서도 customElements.define 을 사용할 수 있도록 해주는 폴리필을 사용해야 한다.

<https://stackoverflow.com/questions/41085635/typescript-2-1-custom-elements>



아래와 같은 typescript 코드를 컴파일해보면 크롬 브라우저에서 동작하는 것을 확인할 수 있다.

```javascript
(function() {
  if (
    // No Reflect, no classes, no need for shim because native custom elements
    // require ES2015 classes or Reflect.
    window[ 'Reflect' ] === undefined ||
    window.customElements === undefined ||
    // The webcomponentsjs custom elements polyfill doesn't require
    // ES2015-compatible construction (`super()` or `Reflect.construct`).
    window.customElements.hasOwnProperty('polyfillWrapFlushCallback')
  ) {
    return;
  }
  const BuiltInHTMLElement = HTMLElement;
  window[ 'HTMLElement' ] = /** @this {!Object} */ function HTMLElement() {
    return Reflect.construct(
        BuiltInHTMLElement, [], /** @type {!Function} */ (this.constructor));
  };
  HTMLElement.prototype = BuiltInHTMLElement.prototype;
  HTMLElement.prototype.constructor = HTMLElement;
  Object.setPrototypeOf(HTMLElement, BuiltInHTMLElement);
})();

class AppDrawer extends HTMLElement {
    constructor() {
        super();
    }
}

customElements.define('app-drawer', AppDrawer);

var a = document.createElement('app-drawer');
a.style.width = '100px';
a.style.height = '100px';
a.style.backgroundColor = 'red';
a.style.display = 'block';

document.body.appendChild(a);
```

