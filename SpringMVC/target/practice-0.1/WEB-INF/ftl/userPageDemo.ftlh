<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
</head>
<script>
    function searchUsers(emailText) {
        $.get("/testSearch?email=" + emailText, function(data, status) {
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
<input id="search_input" name="search_input" onkeyup="searchUsers(document.getElementById('search_input').value)">



</div>
</body>
</html>