<?php
// Database connection
$conn = new mysqli('localhost', 'root', '', 'student_grade_tracker');

// Check connection
if ($conn->connect_error) {
    die("Database connection failed: " . $conn->connect_error);
}

// Check if POST data is set
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['studentName'], $_POST['course'], $_POST['grade'])) {
    $studentName = trim($_POST['studentName']);
    $course = trim($_POST['course']);
    $grade = trim($_POST['grade']);

    // Prepared statement to prevent SQL injection
    $stmt = $conn->prepare("INSERT INTO grades (student_name, course, grade) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $studentName, $course, $grade);

    if ($stmt->execute()) {
        echo json_encode(["status" => "success", "message" => "Grade added successfully"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Error adding grade"]);
    }

    $stmt->close();
} else {
    echo json_encode(["status" => "error", "message" => "Invalid request"]);
}

$conn->close();
?>
