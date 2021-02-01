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

<div>User: ${friend.getFirstName()}!</div>
<div>Email: ${friend.getEmail()}</div>
<div>LastName: ${friend.getLastName()}</div>
<div>Age: ${friend.getAge()}</div>

    <div class="row m-2">
        <img class="img-fluid rounded mx-auto" alt="Responsive image" src="http://localhost:8080/showFriendAvatar" width="300" height="500">
    </div>

    <div id="accordion">
        <#list posts as post>
            <div class="card mb-3">
                <div class="card-header" id="${friend.getFirstName()}_heading">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#${friend.getFirstName()}_collapse" aria-expanded="true" aria-controls="${user.getFirstName()}_collapse">
                            ${friend.getFirstName()}
                        </button>
                    </h5>
                </div>

                <div id="${friend.getFirstName()}_collapse" class="collapse show" aria-labelledby="${friend.getFirstName()}_heading" data-parent="#accordion">
                    <div class="card-body">
                        <div class="d-flex flex-column">
                            <p>${post.getText()}</p>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
</body>
</html>