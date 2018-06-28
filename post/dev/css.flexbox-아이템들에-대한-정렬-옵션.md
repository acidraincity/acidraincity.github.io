# css. flexbox 아이템들에 대한 정렬 옵션

20180628

<style type="text/css">
    .container{
        width:100px;
        height:100px;
        background-color: blue;
        display:flex;
        margin : 5px;
    }
    .item{
        margin: 5px;
        background-color: red;
    }
</style>

justify-content 속성은 아이템들이 진행방향으로 어떻게 정렬되어야 하는지를 지정합니다.

flex-start (default)
<div class="container" style="justify-content: flex-start;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
    <div class="item">
        3
    </div>
</div>
flex-end
<div class="container" style="justify-content: flex-end;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
    <div class="item">
        3
    </div>
</div>
center
<div class="container" style="justify-content: center;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
    <div class="item">
        3
    </div>
</div>
space-between
<div class="container" style="justify-content: space-between;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
    <div class="item">
        3
    </div>
</div>
space-around
<div class="container" style="justify-content: space-around;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
    <div class="item">
        3
    </div>
</div>

align-items 속성은 아이템이 진행방향과 무관한 축에 대해 어떻게 정렬되어야 하는지를 지정합니다.

stretch (default)
<div class="container" style="align-items: stretch;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
</div>
flex-start
<div class="container" style="align-items: flex-start;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
</div>
center
<div class="container" style="align-items: center;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
</div>
flex-end
<div class="container" style="align-items: flex-end;">
    <div class="item">
        1
    </div>
    <div class="item">
        2
    </div>
</div>