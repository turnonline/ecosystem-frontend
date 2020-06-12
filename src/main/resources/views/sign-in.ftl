<#import "skeleton.ftl" as skeleton>
<#import "components.ftl" as components>

<@skeleton.head>
    <@components.style url="https://www.gstatic.com/firebasejs/ui/${firebaseConfig.uiVersion}/firebase-ui-auth.css"/>

    <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-app.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-auth.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/ui/${firebaseConfig.uiVersion}/firebase-ui-auth__${locale}.js"/>

    <@components.firebase_init_script/>
    <@components.firebase_ui_init_script/>
</@skeleton.head>

<@skeleton.body>

    <div style="margin: 100px 0 0 0">
        <div class="card col-md-6 col-sm-10 m-auto">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <h5 class="card-title text-info text-center">
                            <i class="material-icons">lock</i>
                            <span style="position:relative;top: -3px;">${messages["label.signin"]}</span>
                        </h5>

                        <@components.firebase_auth_container/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@skeleton.body>