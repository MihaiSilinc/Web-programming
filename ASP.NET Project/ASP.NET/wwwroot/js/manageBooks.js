function refreshBooks() {
    $.ajax({
        type: "GET",
        url: "/Main/GetTitlesOfUser",
        data: {},
        success: function (data, status) {
            $("#newsContainer").html("")
            data.forEach(title => {
                $("#newsContainer").append(
                    $('<button/>',
                        {
                            text: title,
                            class: 'news',
                            click: function () {
                                $.ajax(
                                    {
                                        type: "GET",
                                        url: "/Main/GetBooksWithTitle",
                                        data: {
                                            title: $(this).text()
                                        },
                                        success: function (data, status) {
                                            $("#title").val(data.title)
                                            $("#author").val(data.author)
                                            $('#date').val(data.date.split("T")[0])
                                            $('#text').val(data.text)
                                        }
                                    }
                                )

                            }
                        }
                    )
                )
            });
        }
    });
}

$("#addBooksButton").click(
    (e) => {
        title = $("#title").val()
        author = $("#author").val()
        date = $("#date").val()
        text = $("#text").val()
        $.ajax({
            type: "GET",
            url: "/Main/AddBooks",
            data: {
                title: title,
                author: author,
                date: date,
                text: text
            },
            success: function (data, status) {
                $("#message").html(data)
                refreshBooks()
            }
        });
    }
)

$("#deleteBooksButton").click(
    (e) => {
        console.log()
        if (confirm("Are you sure you want to delete news?"))
            $.ajax({
                type: "GET",
                url: "/Main/DeleteBooks",
                data: {
                    title: $("#title").val(),
                },
                success: function (data, status) {
                    $("#message").html(data)
                    refreshBooks()
                }
            });
    }
)

$("#updateBooksButton").click(
    (e) => {
        $.ajax({
            type: "GET",
            url: "/Main/UpdateBooks",
            data: {
                title: $("#title").val(),
                author: $("#author").val(),
                date: $("#date").val(),
                text: $("#text").val()
            },
            success: function (data, status) {
                $("#message").html(data)
                refreshBooks()
            }
        });
    }
)

refreshBooks()