<?php
// Database connection
$conn = new mysqli('localhost', 'root', '', 'student_grade_tracker');

if ($conn->connect_error) {
    die(json_encode(["status" => "error", "message" => "Database connection failed"]));
}

// Validate input
if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['id']) && filter_var($_GET['id'], FILTER_VALIDATE_INT)) {
    $id = $_GET['id'];

    // Use prepared statement to prevent SQL injection
    $stmt = $conn->prepare("DELETE FROM grades WHERE id = ?");
    $stmt->bind_param("i", $id);

    if ($stmt->execute()) {
        echo json_encode(["status" => "success", "message" => "Grade deleted successfully"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Error deleting grade"]);
    }

    $stmt->close();
} else {
    echo json_encode(["status" => "error", "message" => "Invalid ID"]);
}

$conn->close();
?>
