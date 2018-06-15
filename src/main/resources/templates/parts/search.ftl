<div class="form-row">
    <div class="form-group col-md-8">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by title">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
    <div class="form-group col-md-8">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filterAuthor" class="form-control" value="${filterAuthor?ifExists}" placeholder="Search by title">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>

</div>