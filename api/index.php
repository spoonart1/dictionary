<?php

define( 'INCLUDE_DIR', dirname( __FILE__ ) . '/api/' );

$rules = array( 
    'scrapper'   => "/scrapper/(?'text'[^/]+)/(?'id'\d+)",   
);

$uri = rtrim( dirname($_SERVER["SCRIPT_NAME"]), '/' );
$uri = '/' . trim( str_replace( $uri, '', $_SERVER['REQUEST_URI'] ), '/' );
$uri = urldecode( $uri );
$uri = str_replace('/', '', $uri);

header("Location:http://192.168.8.102:8989/kamus/api/scrapper.php?word=".$uri);
//$page = file_get_contents("http://www.domain.com/filename");

// nothing is found so handle the 404 error
//include( INCLUDE_DIR . '404.php' );

?>