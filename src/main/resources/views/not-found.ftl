<#import "skeleton.ftl" as skeleton>

<@skeleton.head/>

<@skeleton.body>
    <div class="d-flex justify-content-center align-content-center mt-5">
        <div class="col-md-6 card p-5">
            <h1 class="text-info">
                <i class="material-icons text-info display-3">error_outline</i>
                <span style="position: relative; top: -8px;">404 - Page not found</span>
            </h1>

            <p><b>The page you were looking for appears to have been moved, deleted or does not exist.</b></p>

            <p>This is most likely due to:</p>

            <ul>
                <li>An outdated link on another site</li>
                <li>A typo in the address / URL</li>
            </ul>
        </div>
    </div>
</@skeleton.body>