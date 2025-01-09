// TEMPORARY - change this to check from database when a connection is established
const validCredentials = {
    username: "testuser",
    password: "12345"
};

// Reference to the form and error container
const loginForm = document.getElementById("loginForm");
const errorContainer = document.getElementById("errorContainer");

loginForm.addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent the default form submission

    // Get user inputs
    const username = document.getElementById("first").value.trim();
    const password = document.getElementById("password").value;

    // Clear previous error messages
    errorContainer.textContent = "";

    // Validate credentials
    if (username !== validCredentials.username) {
        errorContainer.textContent = "The username does not exist.";
    } else if (password !== validCredentials.password) {
        errorContainer.textContent = "The password is incorrect.";
    } else {
        // If valid, proceed with successful login (you can redirect or submit here)
        alert("Login successful!");
        window.location.href = "dashboard.html"; // Example redirect to a dashboard page
    }
});