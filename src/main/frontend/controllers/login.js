const login = document.getElementById('login');
login.addEventListener('click', () => {
    const username = document.querySelectorAll('#first input');
    const password = document.querySelectorAll('#password input');

    const loginData = {
        usernameObject : username,
        passwordObject : password
    }

    fetch('http://localhost:8080/test', {  // Change '/api/login' to your actual server URL
        method: 'POST',  // POST method
        headers: {
            'Content-Type': 'application/json'  // Indicate the request contains JSON data
        },
        body: JSON.stringify(loginData)  // Convert the login data to a JSON string
    })
    .then(response => response.json())  // Parse the JSON response from the server
    .then(data => {
        console.log('Login Response:', data);  // Log the response from the server
        alert('Login successful!');
    })
    .catch(error => {
        console.error('Error:', error);  // Handle any errors
        alert('Login failed!');
    });


});