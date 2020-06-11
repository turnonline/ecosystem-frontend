<#import "skeleton.ftl" as skeleton>
<#import "components.ftl" as components>

<@skeleton.head>
    <@components.style url="https://www.gstatic.com/firebasejs/ui/${firebaseConfig.uiVersion}/firebase-ui-auth.css"/>
</@skeleton.head>

<@skeleton.body>
    <style type="text/css">
        @keyframes spinner-border {
            to {
                transform: rotate(360deg);
            }
        }

        .spinner-border {
            display: inline-block;
            width: 2rem;
            height: 2rem;
            vertical-align: text-bottom;
            border: 0.25em solid #2196f3;
            border-right-color: transparent;
            border-radius: 50%;
            animation: spinner-border .75s linear infinite;
        }
    </style>

    <div style="margin: 100px 0 0 20px;">
        <div class="card col-md-6 m-auto">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 border-right border-secondary">
                        <h5 class="card-title text-info">
                            <i class="material-icons">lock</i>
                            <span style="position:relative;top: -3px;">${messages["label.signin"]}</span>
                        </h5>
                    </div>
                    <div class="col-md-6">
                        <div class="mt-5 text-center d-none" id="account-loader">
                            <div class="spinner-border"></div>
                        </div>

                        <div style="margin-bottom: 40px;" id="firebaseui-auth-container"></div>
                    </div>
                </div>
            </div>
        </div>

        <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-app.js"/>
        <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-auth.js"/>
        <@components.script url="https://www.gstatic.com/firebasejs/ui/${firebaseConfig.uiVersion}/firebase-ui-auth__${locale}.js"/>

        <@components.firebase_init_script/>
        <@components.firebase_ui_init_script/>
    </div>
</@skeleton.body>