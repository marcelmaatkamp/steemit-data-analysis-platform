function post_cypherquery() {
    // while busy, show we're doing something in the messageArea.
    $('#messageArea').html('<h3>(loading)</h3>');

    // get the data from neo4j
    $.ajax({
        url: "/db/data/transaction/commit",
        type: 'POST',
        data: JSON.stringify({ "statements": [{ "statement": $('#cypher-in').val() }] }),
        contentType: 'application/json',
        accept: 'application/json; charset=UTF-8',
        success: function () { },
        error: function (jqXHR, textStatus, errorThrown) { $('#messageArea').html('<h3>' + textStatus + ' : ' + errorThrown + '</h3>') },
        complete: function () { }
    }).then(function (data) {
        console.log(data)
    }
}
