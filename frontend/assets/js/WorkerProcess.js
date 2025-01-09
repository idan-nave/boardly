document.addEventListener("DOMContentLoaded", function () {
    const workerList = document.getElementById("worker-list");
    const addWorkerBtn = document.getElementById("add-worker-btn");
    const saveBtn = document.getElementById("save-btn");
    
    // Sample data to show in the list
    const workers = [
        {
            id: 1,
            name: "John Doe",
            team: "Development",
            currentStage: "Interview",
            status: "Pending",
            note: ""
        },
        {
            id: 2,
            name: "Jane Smith",
            team: "Marketing",
            currentStage: "Quiz",
            status: "Pending",
            note: ""
        },
    ];

    // Render the workers list
    function renderWorkers() {
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
            changeWorkerStatus(workerId, "Approved", "Move to next stage");
        } else if (event.target.classList.contains("terminate-btn")) {
            const workerId = event.target.getAttribute("data-id");
            changeWorkerStatus(workerId, "Terminated", "Terminated from process");
        }
    });

    // Change worker status and add a note
    function changeWorkerStatus(workerId, status, note) {
        const worker = workers.find(w => w.id === parseInt(workerId));
        if (worker) {
            worker.status = status;
            worker.note = note;
            renderWorkers(); // Re-render the list after status change
        }
    }

    // Add new worker functionality (fake data for now)
    addWorkerBtn.addEventListener("click", function () {
        const newWorker = {
            id: workers.length + 1,
            name: `Worker ${workers.length + 1}`,
            team: "Example Team",
            currentStage: "Interview",
            status: "Pending",
            note: ""
        };
        workers.push(newWorker);
        renderWorkers(); // Re-render list with the new worker
    });

    // Save button functionality to simulate saving
    saveBtn.addEventListener("click", function () {
        const notes = {};
        workers.forEach(worker => {
            const noteField = document.getElementById(`note-${worker.id}`);
            if (noteField) {
                notes[worker.id] = noteField.value;
            }
        });
        console.log("Saving workers data:", workers);
        console.log("Notes:", notes);
        alert("Changes saved!");
    });

    // Initial rendering of the workers list
    renderWorkers();
});
