<#macro navbar>
    <nav class="navbar"
         style="background-color: #2196f3;height: 64px;padding-left:15px;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12)">
        <a class="navbar-brand text-white" href="/">
            <img style="max-height: 32px;margin-right: 10px;margin-top: -8px;"
                 src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAAB+CAYAAADsphmiAAAHSklEQVR42u2da2gcVRTHN++UIrVQX2hNdlMtWqSJkrR+UyGp+KAV/FQsUhELItpEESEpBfGRVKUg+MBqP/mgakVNqn2JJa1VUvFJilr7rVa0oIlN+kp2rv+TuYUYk+zu7NnduTP/A//OFtqZuff87p17zr1zJ5FQMmNMJXQldCu00fO8j3D8EToGDUGnoHHIo2ZUGjqLujsJ/YnfR6B+6BXofmgZdDFUngiL4WaqoBTUCnVDA9bZNF2TOj0E9UBtUIPUfSkdXw0thdpB6y5omD4qjkldQ7vx81GoEaoppu/LcME6qBP6AhqlS0rXKwCEgzh2QfXim2K0/Dtw0f22xXv0QRg6hAlfSGNcWUjHyyDvKei0HazQwmVp65tu1bGBjDhlwAHKPqbjnQGhD1qUd7RgW30znL8Tx3OsW2dsTAbmNmysDOp8GezdAO2RE7JO3YMA2isNOFBPgP+UtC2fzne7J5BwMZmr8yugXnb7kTDx4Y6cHgX4x0+z3iJn3VnH+Yap3EgmjTLmCYyf4dvHcC+y4WH/jOMB4+f2O5nTj65Z30rauGY6AGRS4YBhejfSDBg/Zdw0XcLnEeOnEmnRNvHxejM5XYy/pGzmSPuZM0apSHVMZnMDqcmtXxZz/K0Ydx61sedWXOw1KrikDqFPbJ1q5WVkhVbrxPQxfiyEnlUcZLwP3WJKuVolYmYb6c2o3/es81TyAhODQfyxwvjLuPK1s/YGk3RZwUCoQx2/I3Wt4C/x+Tw56QaFxI9nu6gWuqngEDQZf7GopzAYbEjYeX6N8OJtqIwuKjgAMkv7hlK4vkYA+EEJgC66p2gQdCgB8Jyc7JgSAB10TdEAWKcUGvYmlEaVBMBNAAYTSjN/BMBBAPD4/z1hdFb8EAA3ARhJKA0mCICbjwCPAMQbAEMACAABIAAEgAAQAAJAAAgAASAADgFgF0pcAC2ArjD+HkVh0SXQXAJQmAqQjZJWQ5uhLdCb0HbP8z4Mi3A/24y/udPtUAUB0Cn4XOgB47+x/JsDS9hl945vcGwjAPkX+lJpUajQf4xbby6N216qigAEL7A8T7c7vBZfVkhfTgCCFbYWetFh58tz4FMZGBKAYIVdbRx/WdUCsJAA5F7Qy6DDxm3z7HL5+QQg94KuNTpr4EvpfXlZ5gmjuGI6NgDYluNy9y8RwAdGXsBgGJhzIedD32bRvUoLO7+j9uYQSbbSvZaZwOCFlN2wBzNcV16WfB2aw7mA6AHQiNb9c4bryhvND3EyKJoAyCaVv2S47l+SGiYABIAAEAACQAAIAAEgAASAABAAAkAACAABIAAEgAAQAAJAAAgAASAABIAAEAACQAAIAAEgAASAABAAAkAACAABIAAEgAAQAAJAAAgAASAABIAAEAACEBYAlkA/zXpRzxvBYaOJ2dfK4gKA7A7yfYbrpu02bGuM/z29JSHS1UZ5f8C4AVAF536exf2N498dtyAcCpG+st9kfByqJQDBCvqk0fmmUalMeqgTOLYTgGAFXYYKPGkcN5RhJw71BCBYYbca9+1ro/hd5bgBkMxiMBj2HkDGJzcSgOAFvg2V+KvjACwnAHlEBNBdqMjvHGVgH3QNAciv0BXQdYBgl/H333PFZCezl6BqAqDXG6yQblW+fWvDrCGJFiQzGCLJ/ZzQ3ig69gBMqQj5csj10N3Qg9DDIdI6e29zClBuAhBnIwAEgAAQAAJAAAgAASAABIAAEAACQAAIAAEICkCaAMQWAE9OdoYAxBaA0YTSkisC4CAAMpkmJ/uDAMS2BxiUkx0hALEFoFceAfuVAOika4oGQIdS9LZJTvayEgBvmZi9olUi55cZ/1uJGgDcIye8DzqlAMBRqJkuKjgAS43/0my+AJyGGuSELdCAAk3ykedtRvEFCNr/nF9ne1qN0F18Pk9OehHUrbT8Wb7j+y50E1RJl6k5vtLWqTSwIaXFquLzGjl5OdRq/M+wqqyCtWv4+6At0KtUXpI67LN1ek7JR0PW52XnCUvhAruVl0NLmDJGqSit6Rjr69TkLkaWWq+3AwNatE183C4+n/qcaQIZB5XCC1o4zbM+bppuoFENddmBHC2a3hffbpgY/M0w2pQ3cfu1nzm0UFja+jY5W8QhWaaVCokhWvhMfLpKfJxN3PkM6yty1pNr4mGHYtxJK52N2RdUK3PNPiVtvDjGOnTX+dCe/8T8OQAgGcJmaC8hcNb5nxl/rqc8nxx0i92UgY8Dt7p96b2Xm3znZGxPsAjqZXjoTLgn47erArf8WXoDiQ7OEITQOl58s8lMTfMqT03eCR2wWSWmjUtvkt4dtineVcWYnpZkUZ3x08Zy0VH6oKTJnS+Nn96tN8VckoeL1UCNUIcMODiHUNQmP2zDu8eMvw9RTSlXq8hUcsr4CwxklcmAYSq5UK1ddiHvgdqghoI+6/PoFS6EFkP3Qs8bf2XQYbul+wjHDRmf57LF3HGpMxnN4/cLti4X27qtzSqfn6X9C0l4ylhXD2rJAAAAAElFTkSuQmCC"
                 style="max-height:32px;">
            <span style="font-weight: 400; font-size: 1.1em; -webkit-font-smoothing: antialiased;">TurnOnline.biz</span>
        </a>

        <#--        <#if !loggedIn>-->
        <a class="nav-link text-white float-right" href="${firebaseConfig.signInUrl}">
            <i class="material-icons">lock_open</i>
            <span style="position: relative;top: -5px;text-transform: none;">${messages["label.signin"]}</span>
        </a>
        <#--        <#else>-->
        <#--            <a class="nav-link text-white float-right" href="/logout">-->
        <#--                <i class="material-icons">power_settings_new</i>-->
        <#--                <span style="position: relative;top: -5px;text-transform: none;">${messages["label.logout"]}</span>-->
        <#--            </a>-->
        <#--        </#if>-->
    </nav>
</#macro>

<#macro script url>
    <script src="${url}" type="text/javascript"></script>
</#macro>

<#macro style url>
    <link href="${url}" rel="stylesheet" type="text/css">
</#macro>

<#macro firebase_init_script>
    <script type="text/javascript" id="firebase_init">
        // Initialize Firebase
        var config = {
            apiKey: '${firebaseConfig.apiKey}',
            authDomain: '${firebaseConfig.projectId}.firebaseapp.com',
            databaseURL: 'https://${firebaseConfig.projectId}.firebaseio.com',
            projectId: '${firebaseConfig.projectId}',
            storageBucket: '${firebaseConfig.projectId}.appspot.com',
            messagingSenderId: '${firebaseConfig.senderId}'
        };
        firebase.initializeApp( config );
    </script>
</#macro>

<#macro firebase_ui_init_script>
    <script type="text/javascript" id="firebase_ui_init">
        function setupAccount( resp )
        {
            let account = JSON.parse( resp.response );

            let id = account.id;
            let domicile = "SK";
            let currency = "EUR";
            let logo = "";
            let vat = "STANDARD";
            let locale = account.locale;

            if ( account.business )
            {
                domicile = account.business.domicile;
            }
            if ( account.invoicing )
            {
                currency = account.invoicing.currency;
            }
            if ( account.business && account.business.logo && account.business.logo.servingUrl )
            {
                logo = account.business.logo.servingUrl;
            }

            window.localStorage.setItem( "turnonline::account::id", id );
            window.localStorage.setItem( "turnonline::account::domicile", domicile );
            window.localStorage.setItem( "turnonline::account::currency", currency );
            window.localStorage.setItem( "turnonline::account::logo", logo );
            window.localStorage.setItem( "turnonline::account::vat", vat );

            document.cookie = "locale=" + locale + ";path=/";

            window.location.href = "${firebaseConfig.signInSuccessUrl}";
        }

        function retrieveAccount( email, accessToken )
        {
            let request = new XMLHttpRequest();
            request.addEventListener( "loadend", function ( data ) {
                let resp = data.target;

                if ( resp.status === 200 )
                {
                    setupAccount(resp);
                }
                else if ( resp.status === 404 )
                {
                    createAccount(email, accessToken);
                }
                else
                {
                    console.error( "Error occur during loading account: " + resp.statusText );
                    window.location.href = "${firebaseConfig.signInUrl}";
                }
            } );

            request.open( "GET", "${gwtConfig.accountStewardApiRoot}/accounts/" + email );
            request.setRequestHeader( "Authorization", "Bearer " + accessToken );
            request.send();
        }

        function createAccount( email, accessToken )
        {
            jwtObject = JSON.parse(atob(accessToken.split(".")[1]));

            let account = {
                email: email,
                company: false,
                firstName: jwtObject.name,
                identityId: jwtObject.iss
            };

            let request = new XMLHttpRequest();
            request.addEventListener( "loadend", function ( data ) {
                let resp = data.target;

                if ( resp.status === 200 )
                {
                    setupAccount(resp);
                }
                else
                {
                    console.error( "Error occur during creating account: " + resp.statusText );
                    window.location.href = "${firebaseConfig.signInUrl}";
                }
            } );

            request.open( "POST", "${gwtConfig.accountStewardApiRoot}/accounts" );
            request.setRequestHeader( "Authorization", "Bearer " + accessToken );
            request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            request.send(JSON.stringify(account));
        }

        // FirebaseUI config.
        var uiConfig = {
            callbacks: {
                // Called when the user has been successfully signed in.
                signInSuccessWithAuthResult: function ( authResult, redirectUrl ) {
                    document.getElementById("account-loader").classList.remove("d-none");

                    let currentUser = authResult.user;

                    currentUser.getIdToken().then( function ( accessToken ) {
                        retrieveAccount( currentUser.email, accessToken );
                    } );

                    return false;
                }
            },
            credentialHelper: [${firebaseConfig.credentialHelper}],
            signInFlow: '${firebaseConfig.signInFlow}',
            signInSuccessUrl: '${firebaseConfig.signInSuccessUrl}',
            signInOptions: [
                ${firebaseConfig.signInOptionsAsString}
            ],
            // Terms of service url.
            tosUrl: '${firebaseConfig.termsUrl}'
        };

        // Initialize the FirebaseUI Widget using Firebase.
        var ui = new firebaseui.auth.AuthUI( firebase.auth() );
        // The start method will wait until the DOM is loaded.
        ui.start( '#firebaseui-auth-container', uiConfig );
    </script>
</#macro>

<#macro gwt_init_script>
    <script type="text/javascript" id="gwt_init">
        var Configuration = {
            ACCOUNT_STEWARD_STORAGE: '${gwtConfig.accountStewardStorage}',
            PRODUCT_BILLING_STORAGE: '${gwtConfig.productBillingStorage}',
            BILLING_PROCESSOR_STORAGE: '${gwtConfig.billingProcessorStorage}',
            ACCOUNT_STEWARD_API_ROOT: '${gwtConfig.accountStewardApiRoot}',
            PRODUCT_BILLING_API_ROOT: '${gwtConfig.productBillingApiRoot}',
            BILLING_PROCESSOR_API_ROOT: '${gwtConfig.billingProcessorApiRoot}',
            PAYMENT_PROCESSOR_API_ROOT: '${gwtConfig.paymentProcessorApiRoot}',
            SEARCH_API_ROOT: '${gwtConfig.searchApiRoot}',
            MAPS_API_KEY: '${gwtConfig.mapsApiKey}'
        };
    </script>
</#macro>