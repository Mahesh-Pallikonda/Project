<?php
include "db.php";

$msg = "";

if (isset($_POST['submit'])) {
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $confirm_password = md5($_POST['confirm_password']);

    if ($password == $confirm_password) {
        $sql = "insert into users (username, email, password) values ('$username', '$email', '$password')";
        $result = mysqli_query($conn, $sql);

        if ($result) {
            $msg = "registration successful. <a href='login.php'>login here</a>";
            header("Location:login.php");
        }
    } else {
        $msg = "passwords do not match!";
    }
}
?>

<link rel="stylesheet" href="style.css">

<h2>Register</h2>

<form method="post" action="">
    <input type="text" name="username" placeholder="Username" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="password" placeholder="Password" required><br>
    <input type="password" name="confirm_password" placeholder="Confirm Password" required><br>
    <input type="submit" name="submit" value="register">
</form>

<p><?php echo $msg; ?></p>
