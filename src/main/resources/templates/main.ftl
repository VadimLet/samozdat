<#import "parts/common.ftl" as c>
<#import "parts/bookList.ftl" as boo>

<@c.page>

<#include "parts/search.ftl"/>
    <@boo.booklist booksView=books></@boo.booklist>

</@c.page>
