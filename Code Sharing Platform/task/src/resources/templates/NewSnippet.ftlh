<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
</head>
<body>
    <div class="form-floating">
        <textarea id="code_snippet" rows="30" cols="70" placeholder="Write your code here!" required></textarea>
        <label for="code_snippet" class="form-label">Code</label>
    </div>

    <div>
    <div class="col">
        <div class="form-floating">
            <input id="time_restriction" type="text"/>
            <label for="time_restriction" class="form-label">Time limit (seconds)</label>
        </div>
    </div>
    <div class="col">
        <div class="form-floating">
            <input id="views_restriction" type="text"/>
            <label for="views_restriction" class="form-label">View limit</label>
        </div>
    </div>
    </div>

    <button id="send_snippet" type="submit" onclick="send()">Submit</button>

    <script>
        function send() {
            let object = {
                "code": document.getElementById("code_snippet").value,
                "time": document.getElementById("time_restriction").value,
                "views": document.getElementById("views_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status === 200) {
                alert("Success!");
            }
        }
    </script>
</body>
</html>