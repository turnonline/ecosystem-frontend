<#import "components.ftl" as components>

<!DOCTYPE html>
<html>
<head>
    <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-app.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-auth.js"/>

    <@components.firebase_init_script/>
</head>
<body>

<script type="text/javascript" id="firebase_logout">
    firebase.auth().signOut().then( function () {
        window.location.href = "${firebaseConfig.signInUrl}";
    } );
</script>
</body>
</html>


