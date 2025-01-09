// Select the form and error message element
const form = document.getElementById('registrationForm');
const errorMessage = document.getElementById('errorMessage');

// Add a submit event listener to the form
form.addEventListener('submit', function (event) {
  // Get values of the password and confirm password fields
  const password = document.getElementById('password').value.trim();
  const confirmPassword = document.getElementById('confirmPassword').value.trim();

  // Check if passwords match
  if (password !== confirmPassword) {
    // Prevent form submission
    event.preventDefault();

    // Display error message
    errorMessage.textContent = "Passwords do not match!";

    // Clear the password fields
    document.getElementById('password').value = "";
    document.getElementById('confirmPassword').value = "";
  } else {
    // Clear any previous error message
    errorMessage.textContent = "";

    // Show success message (optional, can be replaced with actual submission logic)
    alert("Registration Successful!");
  }
});
