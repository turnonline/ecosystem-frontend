<#import "skeleton.ftl" as skeleton>

<@skeleton.head/>

<@skeleton.body>
    <div class="d-flex justify-content-center align-content-center mt-5">
        <div class="col-md-6 card p-5">
            <h1 class="text-info">
                <i class="material-icons text-info display-3">error_outline</i>
                <span style="position: relative; top: -8px;">${messages["title.notFound"]}</span>
            </h1>

            ${messages["label.notFound"]}
        </div>
    </div>
</@skeleton.body>