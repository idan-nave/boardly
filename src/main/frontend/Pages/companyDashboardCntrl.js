const addTeam = document.getElementById('btnAddTeam');
addTeam.addEventListener('click', () => {
    // Sending GET request to fetch team names
    fetch('http://localhost:8080/Dashboard', {  // Replace with your actual server endpoint
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())  // Parse the JSON response from the server
    .then(data => {
        console.log('Fetched Data:', data);  // Log the entire response for debugging

        // Extract the teams array from the JSON object
        const teams = data.teams;

        // Update the Teams list
        const teamsList = document.querySelector('.left-container ul');

        // Clear the existing list
        teamsList.innerHTML = '';

        // Populate the list with new team names
        teams.forEach(team => {
            const listItem = document.createElement('li');
            listItem.textContent = team;
            teamsList.appendChild(listItem);
        });

        alert('Teams updated successfully!');
    })
    .catch(error => {
        console.error('Error fetching teams:', error);  // Handle any errors
        alert('Failed to fetch teams!');
    });
});
