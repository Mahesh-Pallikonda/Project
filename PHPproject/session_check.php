<?php
session_start();

if (!isset($_SESSION['email'])) {
    header("location: login.php");
    exit();
}

if (isset($_SESSION['last_time']) && (time() - $_SESSION['last_time'] > 600)) {
    session_unset();
    session_destroy();
    header("location: login.php?timeout=1");
    exit();
}

$_SESSION['last_time'] = time();
?>
