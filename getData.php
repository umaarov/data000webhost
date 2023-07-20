<?php 

include_once "connection.php"

class user {}

$username=$_POST['username'];
$password=$_POST['password'];

$query=mysqli_query($con, "INSERT INTO user(username,password) values ('$username, $password)");
if($query) {
    $response=new user();
    $response->success=1;
    $response->message="Successfully uploaded";
    die(json_encode($response));
    
}else {
    $response=new user();
    $response->success=0;
    $response->message="Error occured";
    die(json_encode($response));
}

mysqli_close($con);

?>