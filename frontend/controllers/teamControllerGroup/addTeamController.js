document.getElementById("add-team").addEventListener("click", function () {
    // Collect input values
    const teamName = document.getElementById("companyName").value;
    const teamManager = document.getElementById("companyManager").value;
    const processId = document.getElementById("process").value;
  
    // Validate input
    if (!teamName || !teamManager) {
      alert("Please enter both team name and manager!");
      return;
    }
  
    // Send POST request to backend API
    fetch("/addTeam", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        teamName: teamName,
        teamManager: teamManager,
        processId: processId,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle success response
        if (data.success) {
          alert("Team added successfully!");
        } else {
          alert("Failed to add team: " + data.message);
        }
      })
      .catch((error) => {
        // Handle error
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
      });
  });