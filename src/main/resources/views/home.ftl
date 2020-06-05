<#import "skeleton.ftl" as skeleton>

<@skeleton.head/>

<@skeleton.body>
    <h1>${messages["welcome"]}</h1>

    <a href="${firebaseConfig.signInUrl}">${messages["label.signin"]}</a>
</@skeleton.body>