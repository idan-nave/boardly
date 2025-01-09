document.addEventListener("DOMContentLoaded", function () {
    // Fetch processes from the backend when the page loads
    fetch("/getProcesses")
      .then((response) => response.json())
      .then((data) => {
        const processDropdown = document.getElementById("process");
  
        // Check if any processes are returned
        if (data.processes && data.processes.length > 0) {
          // Populate the dropdown
          data.processes.forEach((process) => {
            const option = document.createElement("option");
            option.value = process.id; // Use process ID as the value
            option.textContent = process.name; // Use process name as the text
            processDropdown.appendChild(option);
          });
        } else {
          // Keep the dropdown empty if no processes exist
          console.log("No processes found.");
        }
      })
      .catch((error) => {
        console.error("Error fetching processes:", error);
      });
  });