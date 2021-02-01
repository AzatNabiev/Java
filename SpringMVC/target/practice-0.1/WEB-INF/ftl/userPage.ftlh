<#ftl encoding='UTF-8'>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./styles.css">
    <title>Document</title>
</head>
<script>
    function searchUsers(emailText) {
        $.get("/findUser?email=" + emailText, function(data, status) {
            let resultList = "<ul>";
            data.forEach(function (value, index, array) {
                resultList += '<li><a href="/showFriend/'+value["id"]+'">' + value["email"] +  '</a></li>';
            });
            resultList += '</ul>';
            document.getElementById("searchResult").innerHTML = resultList;
        });
    }
</script>
<body>

<div class="container">
    <h1>
        Hi user ${user.getFirstName()} with ID: ${user.getId()}!
    </h1>
    <div>
        <input id="search_input" name="search_input" onkeyup="searchUsers(document.getElementById('search_input').value)">
        <div>
            <div id="searchResult">
        </div>
    </div>

    <div class="row m-2">
        <img class="img-fluid rounded mx-auto" alt="Responsive image" src="http://localhost:8080/showUserAvatar" width="300" height="500">
    </div>

    <form method="post" action="/saveFile" enctype="multipart/form-data">
        <div class="custom-file mb-1">
            <input class="form-control custom-file-input" type="file" name="file" placeholder="Choose the file">
            <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
        <button class="btn btn-primary" type="submit">Change photo</button>
    </form>

    <form action="/sendNewPost" method="post">
        <h3>Make new post</h3>
        <div class="form-group">
            <label for="textarea">Text</label>
            <textarea class="form-control" name="text" id="textarea" rows="3" required></textarea>
        </div>
        <button class="btn btn-primary" type="submit">Create post</button>
    </form>

    <div id="accordion">
        <#list posts as post>
            <div class="card mb-3">
                <div class="card-header" id="${user.getFirstName()}_heading">
                    <h5 class="mb-0">
                        <button class="btn btn-link" data-toggle="collapse" data-target="#${user.getFirstName()}_collapse" aria-expanded="true" aria-controls="${user.getFirstName()}_collapse">
                            ${user.getFirstName()}
                        </button>
                    </h5>
                </div>

                <div id="${user.getFirstName()}_collapse" class="collapse show" aria-labelledby="${user.getFirstName()}_heading" data-parent="#accordion">
                    <div class="card-body">
                        <div class="d-flex flex-column">
                            <p>${post.getText()}</p>
                            <form method="post" action="/deletePost/${post.getPostId()}">
                                <button type="submit" class="btn btn-danger">
                                    Delete
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>





</body>
</html>