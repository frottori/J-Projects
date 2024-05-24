<!DOCTYPE html>
<html>

<head>
<title>Bank Login Page</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="main.css">
</head>

<body>
<div><h1>Bank Login System</h1></div>

<form action="login" method="post">
username:<input type="text" name="userName"/><br/>
password:<input type="password" name="userPass"/><br/>
<input type="submit" value="Sign In"/>
<button type="button" onclick="location.href='signup.jsp'">Sign Up</button>
</form>

</body>

</html>