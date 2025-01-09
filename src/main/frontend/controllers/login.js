const login = document.getElementById('login');
login.addEventListener('click', (event) => {
    event.preventDefault();  // מונע רענון הדף בעת לחיצה על כפתור ה-login

    const username = document.querySelector('#first').value;   // לא צריך לשנות
const password = document.querySelector('#password').value;  // גם לא צריך לשנות


    const loginData = {
        username: username,  // השתמש ב-username כ-string
        password: password   // השתמש ב-password כ-string
    };

    fetch('http://localhost:8080/login', {  // כתובת ה-URL של השרת
        method: 'POST',  // שיטה POST
        headers: {
            'Content-Type': 'application/json'  // הצהרה על כך שהנתונים הם בפורמט JSON
        },
        body: JSON.stringify(loginData)  // המרת הנתונים ל-JSON
    })
    .then(response => response.json())  // פענוח התגובה כ-JSON
    .then(data => {
        console.log('Login Response:', data);  // הדפסת התגובה ב-console
        if (data.success) {
            alert('Login successful!');  // אם ההתחברות הצליחה
        } else {
            alert('Login failed!');  // אם ההתחברות נכשלה
        }
    })
    .catch(error => {
        console.error('Error:', error);  // טיפול בשגיאות
        alert('Login failed!');
    });
});
