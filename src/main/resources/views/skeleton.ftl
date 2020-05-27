<#import "components.ftl" as components>

<#macro head>
    <head>
        <title>Home</title>

        <link href="/favicon.ico" rel="icon" type="image/ico"/>

        <!-- Material Design for Bootstrap fonts and icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

        <!-- Material Design for Bootstrap CSS -->
        <link rel="stylesheet"
              href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
              integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
              crossorigin="anonymous">

        <#nested>
    </head>
</#macro>

<#macro body>
    <!DOCTYPE html>
    <html>

    <body>
    <@components.navbar/>
    <#nested >
    </body>

    </html>
</#macro>

<#macro gwt_widget widgetSrc>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Home</title>

        <link href="/favicon.ico" rel="icon" type="image/ico"/>

        <meta content="width=device-width, initial-scale=1.0,maximum-scale=1.0" name="viewport">
        <meta content="text/html; charset=UTF-8" http-equiv="content-type">
        <meta name="gwt:property" content="locale=en">

        <style>
            :focus {
                outline: none;
            }
        </style>

        <@components.style url="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.css"/>
    </head>

    <body>
    <noscript>
        <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
            Your web browser must have JavaScript enabled
            in order for this application to display correctly.
        </div>
    </noscript>

    <div id="splashscreen">
        <!-- Material Design for Bootstrap fonts and icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

        <!-- Material Design for Bootstrap CSS -->
        <link rel="stylesheet"
              href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
              integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
              crossorigin="anonymous">

        <nav class="navbar"
             style="background-color: #2196f3;height: 64px;padding-left:15px;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12)">
            <a class="navbar-brand text-white" href="/">
                <span style="font-weight: 400; font-size: 1.1em; -webkit-font-smoothing: antialiased;"></span>
            </a>
        </nav>

        <div class="spinner-layer spinner-green">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
    </div>

    <div id="widget-content"></div>

    <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-app.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/7.14.4/firebase-auth.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth__en.js"/>

    <@components.firebase_init_script/>
    <@components.gwt_init_script/>

    <script type="text/javascript">
        firebase.auth().onAuthStateChanged( function ( user ) {
            if ( user )
            {
                link = document.createElement( 'script' );
                link.type = 'text/javascript';
                link.language = 'javascript';
                link.setAttribute( 'language', 'javascript' );
                link.src = '${widgetSrc}';
                document.body.appendChild( link );
            }
        } );
    </script>

    </body>

    </html>
</#macro>