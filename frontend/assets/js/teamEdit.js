// Fictional data for workers
let workers = [
    { id: 1, name: "Alice Johnson", position: "Software Engineer" },
    { id: 2, name: "Bob Smith", position: "UI/UX Designer" },
    { id: 3, name: "Charlie Brown", position: "Project Manager" },
    { id: 4, name: "Diana Prince", position: "Marketing Specialist" },
    { id: 5, name: "Edward Walker", position: "QA Tester" }
];

// Mock process options
const processes = ["Process 1", "Process 2", "Process 3"];

// Function to render the workers list
function renderWorkers() {
    const workerListElement = document.getElementById("worker-list");
    workerListElement.innerHTML = ''; // Clear the list before rendering
    workers.forEach(worker => {
        const li = document.createElement("li");
        li.innerHTML = `
            <span>${worker.name} - ${worker.position}</span>
            <div>
                <button class="edit-btn" onclick="editWorker(${worker.id})">Edit</button>
                <button onclick="deleteWorker(${worker.id})">Delete</button>
            </div>
        `;
        workerListElement.appendChild(li);
    });
}

// Function to handle editing a worker
function editWorker(workerId) {
    const worker = workers.find(w => w.id === workerId);
    const newName = prompt("Edit name:", worker.name);
    const newPosition = prompt("Edit position:", worker.position);
    if (newName && newPosition) {
        worker.name = newName;
        worker.position = newPosition;
        renderWorkers(); // Re-render the list
    }
}

// Function to handle deleting a worker
function deleteWorker(workerId) {
    const confirmDelete = confirm("Are you sure you want to delete this worker?");
    if (confirmDelete) {
        workers = workers.filter(worker => worker.id !== workerId);
        renderWorkers(); // Re-render the list after deletion
    }
}

// Function to handle adding a new worker
document.getElementById("add-worker-btn").addEventListener("click", () => {
    const newName = prompt("Enter worker name:");
    const newPosition = prompt("Enter worker position:");
    if (newName && newPosition) {
        const newWorker = { id: workers.length + 1, name: newName, position: newPosition };
        workers.push(newWorker);
        renderWorkers(); // Re-render the list with new worker
    }
});

// Function to handle saving changes (this could be extended to save to a database)
document.getElementById("save-btn").addEventListener("click", () => {
    alert("Changes saved!");
});

// Initial render
renderWorkers();
