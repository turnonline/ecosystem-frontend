<#import "skeleton.ftl" as skeleton>

<@skeleton.head/>

<@skeleton.body>
    <div class="col-md-12 card p-5">
        <h3 class="text-info">
            ${messages["home.title.why"]}
        </h3>

        <p>${messages["home.text.why"]}</p>
    </div>
    <div class="col-md-12 card p-5">
        <h3 class="text-info">
            ${messages["home.title.how"]}
        </h3>

        <p>${messages["home.text.how"]}</p>
    </div>
    <div class="col-md-12 card p-5">
        <h3 class="text-info">
            ${messages["home.title.what"]}
        </h3>

        <p>${messages["home.text.what"]}</p>
        <p>${messages["home.text.what.2"]}</p>
    </div>
    <div class="col-md-12 card p-5">
        <h3 class="text-info">
            ${messages["home.title.forWhom"]}
        </h3>

        <p>${messages["home.text.forWhom"]}</p>
        <a href="${firebaseConfig.signInUrl}">${messages["home.kickOff"]}</a>
    </div>
    <div class="col-md-12 card p-5">
        <h3 class="text-info">
            ${messages["home.title.oneMore"]}
        </h3>

        <p>${messages["home.text.oneMore"]}</p>
    </div>

</@skeleton.body>