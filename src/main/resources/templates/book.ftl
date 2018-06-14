<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">
    <div class="row align-items-center justify-content-center">
        <div class="col-md-6"><#if book.getFileName()??>
            <#if book.fileName??>
            <img src="/img/${book.fileName}" alt="id = ${book.id}">
            </#if>
        </#if>
        </div>
    </div>
    <div class="row align-items-center justify-content-center">
        <div class="col-6">
            <div class="text-central"><h1>${book.title?html}</h1></div>
        </div>
    </div>
    <div class="row align-items-center justify-content-center">
        <div class="col-md-3"><strong>Автор: </strong> <i>${book.author.username?html}</i></div>
        <div class="col-md-9"><strong>Описание</strong> <i>${book.description?html}</i></div>
    </div>
    <div class="row align-items-center justify-content-center">
        <div class="col-md-3">${book.text?html}</i></div>
    </div>
</div>
</@c.page>