<?php
include "session_check.php";
include "db.php";

$email = $_SESSION['email'];

$sql = "select * from users where email='$email'";
$result = mysqli_query($conn, $sql);
$row = mysqli_fetch_assoc($result);
?>

<link rel="stylesheet" href="style.css">

<h2>Profile details</h2>
<p>Username: <?php echo $row['username']; ?></p>
<p>Email: <?php echo $row['email']; ?></p>

<a href="dashboard.php">Back to dashboard</a>
