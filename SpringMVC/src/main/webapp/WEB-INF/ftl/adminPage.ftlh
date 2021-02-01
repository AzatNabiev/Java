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
    <h1>Admin page</h1>

    <div>
        <div>Users</div>
        <div>
            <#list users as user>
                <tr>
                    <td><a href="/showFriend/${user.getId()}">${user.getEmail()}</a></td>
                    <td><form method="post" action="/deleteUser/${user.getId()}"><input type="submit" value="Delete"> </form></td>
                </tr>
            </#list>
        </div>
    </div>
</div>
</body>
</html>