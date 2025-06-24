<?php
include "session_check.php";
include "db.php";

$email = $_SESSION['email'];

$sql = "select * from users where email='$email'";
$result = mysqli_query($conn, $sql);
$row = mysqli_fetch_assoc($result);

?>

<link rel="stylesheet" href="style.css">

<h2>Welcome <?php echo $row['username']; ?>!</h2>

<a href="profile.php">View profile</a> |
<a href="edit_profile.php">Edit profile</a> |
<a href="logout.php">Logout</a>
