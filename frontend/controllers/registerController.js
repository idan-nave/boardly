// Select the form and error message element
const form = document.getElementById('registrationForm');
const errorMessage = document.getElementById('errorMessage');

// Add a submit event listener to the form
form.addEventListener('submit', function (event) {

  // Get values of the password and confirm password fields
  const password = document.getElementById('password').value.trim();
  const confirmPassword = document.getElementById('confirmPassword').value.trim();
  const companyName = document.getElementById('companyName').value.trim();
  const address = document.getElementById("address").value.trim();
  const email = document.getElementById("email").value.trim();
  const username = document.getElementById("username").value.trim();

  // check if passwords are equal
  if (password == confirmPassword){
      
        // Sending username to server for availability check
        fetch("/findUsername", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            password: password,
            confirmPassword: confirmPassword,
            companyName: companyName,
            address: address,
            email: email,
            username: username,
          }),
        })
          .then((response) => response.json())
          .then((data) => {
            // Show success message
            errorMessage.textContent = data;
          })
          .catch((error) => {
            // Show error message
            console.error("Error:", error);
          });
  }
  else{
        // Prevent form submission
        event.preventDefault();

        // Display error message
        errorMessage.textContent = "Passwords do not match!";    
  }

});