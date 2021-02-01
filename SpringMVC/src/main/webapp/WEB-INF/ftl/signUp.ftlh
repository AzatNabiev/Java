<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <#include "./head.ftlh"/>
</head>
<body>
    <div class="container">
        <div>Sign Up</div>

        <div class="errors-list">
            ${error}
        </div>

        <form method="post" action="/signUpp">
            <div class="form-group">
                <label for="email">E-mail</label>
                <input name="email" type="email" class="form-control" id="email"
                   placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control"
                       placeholder="Enter password">
                <small id="emailHelp" class="form-text text-muted">We'll never share your password with anyone else.</small>
            </div>
            <button type="submit" class="btn btn-primary">
                Sign up
            </button>
        </form>
    </div>
</body>
</html>
