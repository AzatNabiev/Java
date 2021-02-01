<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <#include "./head.ftlh">
</head>
<body>
        <div class="container">
                <h1>Sign In</h1>

        <div class="errors-list">
            <#list errors as error>
                <div class="errors-list-item alert alert-danger m-2" role="alert">
                        ${error.getMessage()}
                </div>
            </#list>
        </div>


        <form method="post" action="/signInn">
            <div class="form-group">
                <label for="firstName">First name</label>
                <input name="firstName" class="form-control" type="text" id="firstName"
                       placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="lastName">Last name</label>
                <input name="lastName" type="text" class="form-control" id="lastName"
                    placeholder="Enter lastname">
            </div>
            <div class="form-group">
                <label for="age">Age</label>
                <input name="age" type="number" class="form-control" id="age"
                    placeholder="Enter age">
            </div>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input name="email" type="email" class="form-control" id="email"
                   placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input name="password" type="password" class="form-control" id="password"
                   placeholder="Enter password">
                <small id="emailHelp" class="form-text text-muted">We wont share your password anyone else.</small>
            </div>

            <button type="submit" class="btn btn-primary">
                Sign in
            </button>
            </form>

        </div>
</body>
</html>