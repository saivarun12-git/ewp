const API = (window.APP_CONFIG && window.APP_CONFIG.API_BASE_URL) || "http://localhost:8080/api";

// BOOKS
async function loadBooks() {
    const res = await fetch(`${API}/books`);
    const books = await res.json();

    document.getElementById("bookTable").innerHTML = books.map(b => `
        <tr>
            <td>${b.id}</td>
            <td>${b.title}</td>
            <td>${b.author}</td>
            <td>${b.category}</td>
            <td>${b.quantity}</td>
            <td>
                <button onclick="deleteBook(${b.id})">Delete</button>
            </td>
        </tr>
    `).join("");
}

async function addBook() {
    const book = {
        title: document.getElementById("title").value,
        author: document.getElementById("author").value,
        category: document.getElementById("category").value,
        quantity: parseInt(document.getElementById("quantity").value)
    };

    await fetch(`${API}/books`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(book)
    });

    document.getElementById("title").value = "";
    document.getElementById("author").value = "";
    document.getElementById("category").value = "";
    document.getElementById("quantity").value = "";

    loadBooks();
}

async function deleteBook(id) {
    await fetch(`${API}/books/${id}`, {
        method: "DELETE"
    });

    loadBooks();
}


// STUDENTS
async function loadStudents() {
    const res = await fetch(`${API}/students`);
    const students = await res.json();

    document.getElementById("studentTable").innerHTML = students.map(s => `
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.department}</td>
            <td>${s.phone}</td>
            <td>
                <button onclick="deleteStudent(${s.id})">Delete</button>
            </td>
        </tr>
    `).join("");
}

async function addStudent() {
    const student = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        department: document.getElementById("department").value,
        phone: document.getElementById("phone").value
    };

    await fetch(`${API}/students`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(student)
    });

    loadStudents();
}

async function deleteStudent(id) {
    await fetch(`${API}/students/${id}`, {
        method: "DELETE"
    });

    loadStudents();
}


// ISSUE / RETURN
async function loadIssues() {
    const res = await fetch(`${API}/issues`);
    const issues = await res.json();

    document.getElementById("issueTable").innerHTML = issues.map(i => `
        <tr>
            <td>${i.id}</td>
            <td>${i.book ? i.book.title : ""}</td>
            <td>${i.student ? i.student.name : ""}</td>
            <td>${i.issueDate || ""}</td>
            <td>${i.returnDate || ""}</td>
            <td>${i.returned}</td>
            <td>
                ${i.returned ? "" : `<button onclick="returnBook(${i.id})">Return</button>`}
            </td>
        </tr>
    `).join("");
}

async function issueBook() {
    const bookId = document.getElementById("bookId").value;
    const studentId = document.getElementById("studentId").value;

    await fetch(
        `${API}/issues/issue?bookId=${bookId}&studentId=${studentId}`,
        {
            method: "POST"
        }
    );

    loadIssues();
}

async function returnBook(id) {
    await fetch(`${API}/issues/return/${id}`, {
        method: "PUT"
    });

    loadIssues();
}