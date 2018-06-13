<#include "security.ftl">

<div class="card-columns">
    <#list books as book>
        <div class="card my-3">
            <#if book.fileName??>
                <img src="/img/${book.fileName}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${book.text?html}</span><br/>
                <i>#${book.description?html}</i>
            </div>
            <div class="card-footer text-muted">
                <a><#if book.author??>${book.author.username?if_exists}</#if></a>
            </div>
        </div>
    <#else>
        No book
    </#list>
</div>
