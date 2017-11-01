# javascript. x-tag를 이용해서 웹컴포넌트 구현하기

20171101



UI 구성요소를 웹컴포넌트로 만들어서 라이브러리화하고 재상용성을 높일 수 있습니다.

http://d2.naver.com/helloworld/188655



하지만 아직은 웹컴포넌트 표준스팩을 모든 브라우저가 지원하지 않기 때문에

[x-tag 라이브러리](https://x-tag.github.io/)를 사용해서 웹컴포넌트를 구성해 보았습니다.



다음과 같은 형식으로 웹컴포넌트를 구성하고 사용할 수 있습니다.

```
<!DOCTYPE HTML>
<html lang="en">

<head>
    <script language="javascript" src="./node_modules/x-tag/dist/x-tag-core.js"></script>
    <script language="javascript" charset="utf-8">
        xtag.register('custom-input', {
            content: function() {
                /*
                    <input type="text" style='background:red;width:100%;height:100%;'></input>
                */
            },
            lifecycle: {
                created: function() {
                    this.style.display = 'block';
                    this.xtag.input = this.querySelector('input');
                },
                inserted: function() {

                },
                removed: function() {

                },
                attributeChanged: function(attrName, oldValue, newValue) {

                }
            },
            methods: {
                notify: function() {
                    alert(this.xtag.input.value);
                }
            },
            accessors: {
                value: {
                    attribute: {},
                    set: function(value) {
                        this.xtag.input.value = value;
                    },
                    get: function() {
                        return this.xtag.input.value;
                    }
                }
            }
        });
    </script>
</head>

<body>
    <custom-input style="width:100px;height:100px;" value="흠냐"></custom-input>
    <script langeuage="javascript" charset="utf-8">
        var i = document.createElement('custom-input');
        i.style.width = '200px';
        i.style.height = '200px';
        i.value = '긁적';
        document.body.appendChild(i);
        i.addEventListener('click', function() {
            i.notify();
        });
    </script>
</body>

</html>
```





