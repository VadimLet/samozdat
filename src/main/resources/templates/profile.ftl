<#import "parts/common.ftl" as c>
<#import "parts/bookEdit.ftl" as addBook>
<@c.page>
<div>
<@addBook.editBook message="add book"></@addBook.editBook>
</div>
</@c.page>