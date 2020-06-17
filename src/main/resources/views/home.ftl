<#import "skeleton.ftl" as skeleton>
<#import "components.ftl" as components>

<@skeleton.head>
    <style>
        .firebaseui-idp-list {
            margin: 0 !important;
        }
    </style>
</@skeleton.head>

<@skeleton.body>
    <div class="row p-4">
        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <@components.card title=messages["home.title.why"] content=messages["home.text.why"] icon="help" />
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <@components.card title=messages["home.title.how"] content=messages["home.text.how"] icon="trending_down" color="#f44336"/>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <@components.card title=messages["home.title.what"] content=messages["home.text.what"] icon="insert_link" color="#4caf50"/>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <@components.card title=messages["home.title.forWhom"] content=messages["home.text.forWhom"] icon="corporate_fare" color="#ffc107"/>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <@components.card title=messages["home.title.oneMore"] content=messages["home.text.oneMore"] icon="lock_open" color="#17a2b8"/>
        </div>

        <div class="col-lg-4 col-md-6 col-sm-12 mb-4">
            <div class="card">
                <div class="card-img-top text-white p-4 text-center" style="background-color: #ff5722">
                    <i class="material-icons display-1">person</i>
                </div>
                <div class="card-body">
                    <h5 class="card-title" style="color: #ff5722">${messages["label.signin"]}</h5>
                    <div class="card-text" style="height: 100px;overflow: auto;">
                        <a href="${firebaseConfig.signInUrl}">${messages["home.kickOff"]}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@skeleton.body>