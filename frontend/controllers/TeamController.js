document.getElementById("assignProcessBtn").addEventListener("click", function () {
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

// Add Worker Button
document.getElementById("add-worker-btn").addEventListener("click", function () {
  const workerName = prompt("Enter worker's name:");

  if (workerName) {
      const teamId = 1; // Example team ID

      // Sending POST request to add the worker to the team
      fetch(`/teams/${teamId}/workers/add`, {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
          },
          body: JSON.stringify({
              workerName: workerName,
          }),
      })
      .then((response) => response.json())
      .then((data) => {
          alert(data.message);
      })
      .catch((error) => {
          console.error("Error:", error);
      });
  } else {
      alert("Please enter a worker's name.");
  }
});

// Save Changes Button
document.getElementById("save-btn").addEventListener("click", function () {
  const teamName = prompt("Enter team name:");
  const manager = prompt("Enter manager's name:");
  const processId = document.getElementById("processSelect").value;

  const teamId = 1; // Example team ID

  // Sending POST request to save changes to the team
  fetch(`/teams/${teamId}/save`, {
      method: "POST",
      headers: {
          "Content-Type": "application/json",
      },
      body: JSON.stringify({
          teamName: teamName,
          manager: manager,
          processId: processId,
      }),
  })
  .then((response) => response.json())
  .then((data) => {
      alert(data.message);
  })
  .catch((error) => {
      console.error("Error:", error);
  });
});
