function showPuzzle(){
    var user_name = $('#username').text();
    $.get("/Puzzle",
        {"username": user_name}, function(response) {
            $.each(response, function (key, value) {
                if (key !== "-1") {
                    console.log("<img src='" +value + "'/>");
                    document.getElementById(key.toString()).innerHTML = "<img src='" +value + "'/>";

                }
                else{
                    document.getElementById("moves").innerText = value;
                }
            });
        });
}

$(document).ready(function(){
    $(document).on('click', 'img', function (){
        var source = $(this).attr('src');
        var position = $(this).parent().attr('id');
        $.post("/Puzzle", {"position": position, "source": source},
            function (response) {
                $.each(response, function (key, value) {
                    if (key!== "-1"){
                        document.getElementById(key.toString()).innerHTML = "<img src='" +value+ "'/>";
                    }
                    else{
                        document.getElementById("moves").innerText = value;
                    }
                })})
            .done(function () {
                checkWin();
            });
    });
});

function checkWin() {
    var countCorrect = 0;
    var images = document.getElementsByTagName('img');
    var len=images.length;

    for (var i=0; i<len;i++){
        console.log(images[i]);
        var src = images[i].getAttribute('src').split(".")[0];
        var id = images[i].parentElement.getAttribute('id');
        if (src === id) countCorrect+=1;
    }

    if (countCorrect === len) {
        var username = document.getElementById("username").innerText;
        var moves = document.getElementById("moves").innerText;
        $.post("/Done", {"username": username, "moves": moves, "status": "won"});
    }
}