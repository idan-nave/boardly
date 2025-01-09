document.getElementById('new-worker-form').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the default form submission

    // Gather form data
    const workerData = {
        name: document.getElementById('name').value,
        position: document.getElementById('position').value,
        email: document.getElementById('email').value,
        department: document.getElementById('department').value,
        startDate: document.getElementById('start-date').value
    };

    // For now, just log the data to the console
    console.log("New Worker Data:", workerData);

    // Ideally, send this data to the server to be saved in a database
    // Example: 
    // fetch('/add-worker', {
    //     method: 'POST',
    //     body: JSON.stringify(workerData),
    //     headers: { 'Content-Type': 'application/json' }
    // }).then(response => response.json())
    //   .then(data => console.log("Worker added:", data));

    // After form submission, redirect to the team edit page
    alert("New worker added successfully!");
    window.location.href = "../../views/teamWorkersEdit.html";  // Redirect to the team edit page
});
