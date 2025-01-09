document.addEventListener("DOMContentLoaded", function () {
    const workerList = document.getElementById("worker-list");
    const addWorkerBtn = document.getElementById("add-worker-btn");
    const saveBtn = document.getElementById("save-btn");
    
    // Simulated data for workers
    const workers = [
        { id: 1, name: "Alice", stage: "Interview", status: "Pending", team: "Dev Team", note: "" },
        { id: 2, name: "Bob", stage: "Quiz", status: "Pending", team: "Design Team", note: "" },
        { id: 3, name: "Charlie", stage: "Laptop Acquiring", status: "Pending", team: "HR Team", note: "" }
    ];

    // Render workers dynamically
    function renderWorkers(workers) {
        workerList.innerHTML = "";
        workers.forEach(worker => {
            const li = document.createElement("li");
            li.classList.add("worker-item");
            li.innerHTML = `
                <p><strong>${worker.name}</strong> (${worker.team}) - ${worker.stage}</p>
                <p>Status: ${worker.status}</p>
                <textarea id="note-${worker.id}" placeholder="Add a note">${worker.note}</textarea>
                <button class="approve-btn" data-id="${worker.id}">Approve</button>
            `;
            workerList.appendChild(li);
        });
    }

    // Event listener for approve/terminate actions
    workerList.addEventListener("click", function (event) {
        if (event.target.classList.contains("approve-btn")) {
            const workerId = event.target.getAttribute("data-id");
            const note = document.getElementById(`note-${workerId}`).value;
            updateWorkerStatus(workerId, "Approved", note);
        } else if (event.target.classList.contains("terminate-btn")) {
            const workerId = event.target.getAttribute("data-id");
            const note = document.getElementById(`note-${workerId}`).value;
            updateWorkerStatus(workerId, "Terminated", note);
        }
    });

    // Function to update worker status
    function updateWorkerStatus(workerId, status, note) {
        const worker = workers.find(worker => worker.id === parseInt(workerId));
        if (worker) {
            worker.status = status;
            worker.note = note;
            renderWorkers(workers);
        }
    }

    // Add worker functionality (simulate adding a worker)
    addWorkerBtn.addEventListener("click", function () {
        const newWorker = {
            id: Date.now(), // Using timestamp as worker ID for demo
            name: `Worker ${Date.now()}`,
            stage: "Interview",
            status: "Pending",
            team: "New Team",
            note: ""
        };
        workers.push(newWorker);
        renderWorkers(workers);
    });

    // Save button functionality
    saveBtn.addEventListener("click", function () {
        alert("Changes saved!");
    });

    // Initial render of workers
    renderWorkers(workers);
});
