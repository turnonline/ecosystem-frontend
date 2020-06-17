<#import "components.ftl" as components>

<#macro head>
    <head>
        <title>Ecosystem Admin</title>

        <link href="/favicon.ico" rel="icon" type="image/ico"/>

        <!-- Material Design for Bootstrap fonts and icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

        <!-- Material Design for Bootstrap CSS -->
        <link rel="stylesheet"
              href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
              integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX"
              crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.5.0/css/flag-icon.min.css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <#nested>
    </head>
</#macro>

<#macro body>
    <!DOCTYPE html>
    <html>

    <body>
    <@components.navbar/>
    <div style="min-height: 100%;">
        <#nested >
    </div>

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
        <meta name="gwt:property" content="locale=${locale}">

        <style>
            :focus {
                outline: none;
            }
        </style>

        <@components.style url="https://www.gstatic.com/firebasejs/ui/${firebaseConfig.uiVersion}/firebase-ui-auth.css"/>
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

        <nav class="navbar"
             style="background-color: #2196f3;height: 64px;padding-left:15px;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12)">
            <a class="navbar-brand text-white" href="/">
                <span style="font-weight: 400; font-size: 1.1em; -webkit-font-smoothing: antialiased;"></span>
            </a>
        </nav>

        <div class="mt-5 text-center">
            <div class="spinner-border"></div>
        </div>
    </div>

    <div id="widget-content"></div>

    <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-app.js"/>
    <@components.script url="https://www.gstatic.com/firebasejs/${firebaseConfig.version}/firebase-auth.js"/>

    <@components.firebase_init_script/>
    <@components.gwt_init_script/>

    <script type="text/javascript">
        firebase.auth().onAuthStateChanged( function ( user ) {
            if ( user )
            {
                script = document.createElement( 'script' );
                script.type = 'text/javascript';
                script.language = 'javascript';
                script.setAttribute( 'language', 'javascript' );
                script.src = '${widgetSrc}';

                document.body.appendChild( script );
            }
            else
            {
                window.location.href = "${firebaseConfig.signInUrl}";
            }
        } );
    </script>

    </body>

    </html>
</#macro>