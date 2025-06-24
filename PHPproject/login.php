<?php
session_start();
include "db.php";

$msg = "";

if (isset($_POST['login'])) {
    $email = $_POST['email'];
    $password = md5($_POST['password']);

    $sql = "select * from users where email='$email' and password='$password'";
    $result = mysqli_query($conn, $sql);

    if (mysqli_num_rows($result) > 0) {
        $_SESSION['email'] = $email;
        $_SESSION['last_time'] = time();
        header("location: dashboard.php");
    } else {
        $msg = "invalid email or password";
    }
}

if (isset($_GET['timeout'])) {
    $msg = "session expired. please login again.";
}
?>

<link rel="stylesheet" href="style.css">

<h2>Login</h2>

<form method="post" action="">
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <input type="submit" name="login" value="login">
</form>

<p><?php echo $msg; ?></p>
