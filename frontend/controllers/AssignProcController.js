// /frontend/js/teamController.js
document
  .getElementById("assignProcessBtn")
  .addEventListener("click", function () {
    const processId = document.getElementById("processSelect").value;
    const teamId = 1; // Example team ID

    // Sending POST request to the backend API to assign the process to the team
    fetch("/assignProcess", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        teamId: teamId,
        processId: processId,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        // Show success message
        alert(data.message);
      })
      .catch((error) => {
        // Show error message
        console.error("Error:", error);
      });
  });
