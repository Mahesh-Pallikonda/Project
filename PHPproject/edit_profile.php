<?php
include "session_check.php";
include "db.php";

$email = $_SESSION['email'];

$sql = "select * from users where email='$email'";
$result = mysqli_query($conn, $sql);
$row = mysqli_fetch_assoc($result);

$msg = "";

if (isset($_POST['update'])) {
    $new_username = $_POST['username'];
    $new_email = $_POST['email'];
    $new_password = md5($_POST['password']);

    $update_sql = "update users set username='$new_username', email='$new_email', password='$new_password' where email='$email'";
    $update_result = mysqli_query($conn, $update_sql);

    if ($update_result) {
        $_SESSION['email'] = $new_email;
        $msg = "profile updated successfully.";
        header("Location:login.php");
    } else {
        $msg = "error updating profile: " . mysqli_error($conn);
    }
}
?>

<link rel="stylesheet" href="style.css">

<h2>Edit profile</h2>

<form method="post" action="">
    <input type="text" name="username" value="<?php echo $row['username']; ?>" required><br>
    <input type="email" name="email" value="<?php echo $row['email']; ?>" required><br>
    <input type="password" name="password" placeholder="New password" required><br>
    <input type="submit" name="update" value="update profile">
</form>

<p><?php echo $msg; ?></p>

<a href="dashboard.php">Back to dashboard</a>
