document.addEventListener("DOMContentLoaded", function () {
    const workerList = document.getElementById("worker-list");
    const addWorkerBtn = document.getElementById("add-worker-btn");
    const saveBtn = document.getElementById("save-btn");
    
    // Fetch workers current status out of all the teams stages. (stage id)
    fetch("/worker/getCurrentStatus")
        .then(response => response.json())
        .then(data => renderWorkers(data))
        .catch(error => console.error("Error fetching workers:", error));

    // Function to render workers dynamically
    function renderWorkers(workers) {
        workerList.innerHTML = "";
        workers.forEach(worker => {
            const li = document.createElement("li");
            li.classList.add("worker-item");
            li.innerHTML = `
                <p><strong>${worker.name}</strong> (${worker.team}) - ${worker.currentStage}</p>
                <p>Status: ${worker.status}</p>
                <textarea id="note-${worker.id}" placeholder="Add a note">${worker.note}</textarea>
                <button class="approve-btn" data-id="${worker.id}">Approve</button>
                <button class="terminate-btn" data-id="${worker.id}">Terminate</button>
            `;
            workerList.appendChild(li);
        });
    }

    // Event listener for approving or terminating the worker's process
    workerList.addEventListener("click", function (event) {
        if (event.target.classList.contains("approve-btn")) {
            const workerId = event.target.getAttribute("data-id");
            const note = document.getElementById(`note-${workerId}`).value;
            changeWorkerStatus(workerId, "Approved", note);
        } else if (event.target.classList.contains("terminate-btn")) {
            const workerId = event.target.getAttribute("data-id");
            const note = document.getElementById(`note-${workerId}`).value;
            changeWorkerStatus(workerId, "Terminated", note);
        }
    });

    // Change worker status and add a note
    function changeWorkerStatus(workerId, status, note) {
        fetch("/worker/updateStatus", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                workerId: workerId,
                status: status,
                note: note,
            }),
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            fetch("/worker/getWorkers")
                .then(response => response.json())
                .then(data => renderWorkers(data));
        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred.");
        });
    }

    // Terminate Worker - DELETES IT FROM DB
    addWorkerBtn.addEventListener("click", function () {
        const newWorker = {
            id: Date.now(),  // Temporary ID generation
            name: `Worker ${Date.now()}`,
            team: "Example Team",
            currentStage: "Interview",
            status: "Pending",
            note: ""
        };

        fetch("/worker/deleteWorker", {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newWorker),
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            fetch("/worker/getWorkers")
                .then(response => response.json())
                .then(data => renderWorkers(data));
        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred while adding the worker.");
        });
    });

    // Save button functionality to simulate saving
    saveBtn.addEventListener("click", function () {
        const workers = [];
        document.querySelectorAll(".worker-item").forEach(item => {
            const workerId = item.querySelector("button").getAttribute("data-id");
            const noteField = document.getElementById(`note-${workerId}`);
            workers.push({
                workerId: workerId,
                note: noteField.value
            });
        });

        // update a workers designated team and concats currentNote of approval to the worker notes field.
        fetch("/worker/saveChanges", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ workers: workers })
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred while saving.");
        });
    });
});
