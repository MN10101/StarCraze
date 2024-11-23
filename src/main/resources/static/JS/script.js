document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    // Event listener for login form
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission


        // Redirect to the main page after successful login
        window.location.href = 'mn.html';
    });

    // Event listener for register form
    registerForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission


        // Redirect to the main page after successful registration
        window.location.href = 'Login.html';
    });
});


const bcrypt = require('bcrypt');
const plainPassword = 'user_password';
const saltRounds = 10;
bcrypt.hash(plainPassword, saltRounds, function(err, hash) {
    if (err) throw err;
    // Store the 'hash' in the database
    console.log(hash);
});

const mysql = require('mysql');
const bcrypt = require('bcrypt');
const express = require('express');
const app = express();
const connection = mysql.createConnection({
    host: 'Mamo',
    user: 'root',
    password: 'Mamo',
    database: 'starcrazedb'
});
app.post('/register', (req, res) => {
    const { username, password } = req.body;
    bcrypt.hash(password, saltRounds, function(err, hash) {
        if (err) throw err;
        const query = 'INSERT INTO users (username, password) VALUES (?, ?)';
        connection.query(query, [username, hash], (error, results) => {
            if (error) throw error;
            res.send('User registered successfully!');
        });
    });
});


// Attach the logout function to the button click event
document.getElementById('logout-btn').addEventListener('click', logout);
