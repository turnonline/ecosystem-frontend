<#import "skeleton.ftl" as skeleton>
<#import "components.ftl" as components>

<@skeleton.head>
    <@components.style url="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.css"/>
</@skeleton.head>

<@skeleton.body>
    <div style="margin: 100px 0 0 20px;">
        <div class="card col-md-6 m-auto">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 border-right border-secondary">
                        <h5 class="card-title text-info">
                            <i class="material-icons">lock</i>
                            <span style="position:relative;top: -3px;">Sign up</span>
                        </h5>
                        <p class="card-text">Sign up to TurnOnline.biz application via following sign up methods:</p>
                    </div>
                    <div class="col-md-6">
                        <div style="margin-bottom: 40px;" id="firebaseui-auth-container"></div>
                    </div>
                </div>
            </div>
        </div>

        <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-app.js"/>
        <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-auth.js"/>
        <@components.script url="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth__en.js"/>

        <@components.firebase_init_script config=firebaseConfig/>
        <@components.firebase_ui_init_script config=firebaseConfig/>
    </div>
</@skeleton.body>