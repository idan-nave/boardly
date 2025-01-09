// מאזינים לכפתורים
const add = document.getElementById('buttonAdd');
const finish = document.getElementById('buttonFinish');

// לוגיקה להוספת שלב חדש
add.addEventListener('click', () => {
    const stagesContainer = document.getElementById('stages');
    const currentStages = stagesContainer.querySelectorAll('input').length;
    const newStageNumber = currentStages + 1;

    // יצירת label ו-input עבור השלב החדש
    const newLabel = document.createElement('label');
    newLabel.setAttribute('for', `stage-${newStageNumber}`);
    newLabel.innerText = `Stage ${newStageNumber}:`;

    const newInput = document.createElement('input');
    newInput.setAttribute('type', 'text');
    newInput.setAttribute('id', `stage-${newStageNumber}`);
    newInput.setAttribute('placeholder', `Enter your stage ${newStageNumber}`);

    // הוספת האלמנטים החדשים לתוך מיכל הסטייג'ים
    stagesContainer.appendChild(newLabel);
    stagesContainer.appendChild(newInput);
});

// לוגיקה לשליחת הנתונים
finish.addEventListener('click', () => {
    const inputs = document.querySelectorAll('#stages input');
    const stagesData = {};

    // איסוף נתונים מכל השדות
    inputs.forEach((input, index) => {
        const key = `stage-${index + 1}`;
        stagesData[key] = input.value;
    });

    // יצירת JSON ושליחת POST
    const jsonData = JSON.stringify({ stages: stagesData });

    fetch('/api/stages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: jsonData,
    })
    .then(response => response.json())
    .then(data => {
        console.log('Server Response:', data);
        alert('Stages sent successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while sending stages.');
    });
});
