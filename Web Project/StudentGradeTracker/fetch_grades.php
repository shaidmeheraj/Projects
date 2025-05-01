<?php
// Database connection
$conn = new mysqli('localhost', 'root', '', 'student_grade_tracker');

if ($conn->connect_error) {
    die(json_encode(["status" => "error", "message" => "Database connection failed"]));
}

// Fetch all grades
$sql = "SELECT * FROM grades";
$result = $conn->query($sql);

$grades = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $grades[] = $row;
    }
}

// Return data as JSON
echo json_encode(["status" => "success", "data" => $grades]);

$conn->close();
?>
