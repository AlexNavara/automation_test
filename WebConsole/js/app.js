var server_host = 'http://localhost',
    server_port = '8080',
    app_name = 'Calculator';

var BASE_URL = server_host + ":" + server_port + "/" + app_name;

var lastHistoryResponseBody;

function doRequest() {
    var expression = document.getElementById("expression").value;
    var equation = new Equation(expression);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', BASE_URL, false);
    xhr.send(JSON.stringify(equation));

    if (xhr.status !== 200) {
        alert(xhr.status + " : " + xhr.statusText);
    }
    else {
        var result = JSON.parse(xhr.responseText)["result"];
        alert(expression + " = " + result);
    }
}

function showTable() {
    console.log("Show table");
    document.getElementById("History").removeAttribute("hidden");
}

function hideTable() {
    console.log("Hide table");
    document.getElementById("History").setAttribute("hidden", "");
}

function refreshTable() {
    var table = document.getElementById("History").getElementsByTagName("tbody")[0];

    while(table.firstChild) {
        table.removeChild(table.firstChild);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("GET", BASE_URL, false);
    xhr.send();

    if (xhr.status !== 200) return;

    lastHistoryResponseBody = JSON.parse(xhr.responseText).map(function (obj) {
        var eq = new Equation();
        eq.id = obj["id"];
        eq.expression = obj["expression"];
        eq.result = obj["result"];
        return eq.buildRow();
    });

    for(var i = 0; i < lastHistoryResponseBody.length; i++) {
        table.appendChild(lastHistoryResponseBody[i]);
    }

}

function clearHistory() {
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", BASE_URL);
    xhr.send();

}

function Equation(expression) {
    this.id = null;
    this.expression = expression ? expression : null;
    this.result = null;

    this.getResult = function() {
        return this.result;
    };

    this.getExpression = function () {
        return this.expression;
    };

    this.buildRow = function() {
        var row = document.createElement('tr');
        var expressionCell = document.createElement('td');
        var resultCell = document.createElement('td');
        row.appendChild(expressionCell);
        row.appendChild(resultCell);

        expressionCell.innerHTML = this.expression;
        resultCell.innerHTML = this.result;

        return row;
    }
}
