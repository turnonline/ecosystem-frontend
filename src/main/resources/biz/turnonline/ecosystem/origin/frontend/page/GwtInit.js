firebase.auth().onAuthStateChanged( function ( user ) {
    if ( user )
    {
        link = document.createElement( 'script' );
        link.type = 'text/javascript';
        link.language = 'javascript';
        link.setAttribute( 'language', 'javascript' );
        link.src = '${gwt.src}';
        document.body.appendChild( link );
    }
} );