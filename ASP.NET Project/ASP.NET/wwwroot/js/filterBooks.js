prevAuthor = ""
prevStartDate = ""
prevEndDate = ""

function generateBooks(newsData) {
    return `<div class=\"news\">
                <h2>${newsData.title}</h2>
                <p>${newsData.author}</text>
                <p>By ${newsData.author} on ${newsData.date.split("T")[0]}</p>
                <p>${newsData.text}</p>
            </div>`
}

function refreshBooks(news) {
    $("#newsContainer").html("")
    news.map(generateBooks).forEach(element => {
        $("#newsContainer").append(element)
    });
}

$("#filterButton").click(
    (e) => {
        $.ajax({
            type: "GET",
            url: "/Main/GetBooks",
            data: {
                author: $("#author").val(),
                startDate: $("#startDate").val(),
                endDate: $("#endDate").val()
            },
            success: function (data, status) {
                refreshBooks(data)
                $("#prevAuthor").text((i, oldText) => {
                    return "Author: " + prevAuthor
                })
                $("#prevStartDate").text((i, oldText) => {
                    return "Start Date: " + prevStartDate
                })
                $("#prevEndDate").text((i, oldText) => {
                    return "End Date: " + prevEndDate
                })

                prevAuthor = $("#author").val()
                prevStartDate = $("#startDate").val()
                prevEndDate = $("#endDate").val()
            }
        });
    }
)

$.ajax({
    type: "GET",
    url: "/Main/GetBooks",
    data: {},
    success: function (data, status) {
        refreshBooks(data)
    }
});