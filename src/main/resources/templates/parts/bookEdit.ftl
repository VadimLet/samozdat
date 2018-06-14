<#macro editBook message>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    ${message}
</a>
<div class="collapse <#if book??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" action="/addbook">
            <div class="form-group">
                <textarea name="text" type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                          placeholder="Введите текст"><#if book??>${book.text?html}</#if></textarea>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.description?html}</#if>" name="description" placeholder="Введите описание" />
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.title?html}</#if>" name="title" placeholder="Название">
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if book??>${book.id?if_exists}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save book</button>
            </div>
        </form>
    </div>
</div>
</#macro>
