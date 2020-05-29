<#import "skeleton.ftl" as skeleton>

<@skeleton.head/>

<@skeleton.body>
    <div class="d-flex justify-content-center align-content-center mt-5">
        <div class="col-md-6 card p-5">
            <h1 class="text-info">
                <i class="material-icons text-info display-3">error_outline</i>
                <span style="position: relative; top: -8px;">500 - Internal server error</span>
            </h1>

            <p><b>Un unexpected error occurred during processing request. Try again later.</b></p>
        </div>
    </div>
</@skeleton.body>