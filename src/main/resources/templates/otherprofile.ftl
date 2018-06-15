<#import "parts/common.ftl" as c>
<#import "parts/bookEdit.ftl" as addBook>
<#import "parts/bookList.ftl" as bl>
<@c.page>
<div>
    <h1>@${userView.usernam?html}</h1>

    <div class="alert-success">
        <h1 class="text-primary">Добаленные</h1>
    <@bl.booklist booksView=myBooks></@bl.booklist>
    </div>

    <div class="alert-danger">
        <h1 class="text-primary">Читаемые</h1>
    <@bl.booklist booksView=favouriteBook></@bl.booklist>
    </div>
</div>
</@c.page>