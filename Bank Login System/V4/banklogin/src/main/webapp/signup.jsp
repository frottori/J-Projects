<!DOCTYPE html>
<html>

<head>
<title>Bank Login Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="main.css">
</head>

<body>
<div><h1>Sign Up</h1></div>

<form action="signupServ" method="post">
    <label for="username" style="display: inline-block; width: 100px;">Username:</label>
    <input type="text" name="user" id="username" /><br/>
    <label for="password" style="display: inline-block; width: 100px;">Password:</label>
    <input type="password" name="pass" id="password" /><br/>
    <label for="firstName" style="display: inline-block; width: 100px;">First Name:</label>
    <input type="text" name="fname" id="firstName" /><br/>
    <label for="lastName" style="display: inline-block; width: 100px;">Last Name:</label>
    <input type="text" name="lname" id="lastName" /><br/>
    <label for="gender" style="display: inline-block; width: 100px;">Gender:</label>
    <select name="gender" id="gender" class="textbox">
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br/>
    <input type="submit" value="Sign Up"/>
</form>
</body>

</html>