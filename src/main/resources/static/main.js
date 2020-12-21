var client = null;

function connect() {
    loadTimetable();
    client = Stomp.client('ws://localhost:8080/fryzjer');
    client.connect({}, function () {
            client.subscribe("/topic/timetable", function (timetable) {
                printTimetable(JSON.parse(timetable.body));
            });
        }
    )
}

function printTimetable(timetable) {
    let reservations = timetable.reservations;
    clearCalendar();
    for (let i = 0; i < reservations.length; i++) {
        let day = reservations[i].day;
        let hour = reservations[i].hour;
        let username = reservations[i].username;
        setReserved(day, hour, username);
    }
}

function setReserved(day, hour, username) {
    const rows = document.getElementById("timetable").rows;
    const cell = rows[hour + 1].cells[day + 1];
    cell.classList.add("reserved");
    cell.innerHTML = username;
    cell.onclick = function() {dialog("Czy na pewno chcesz anulować ten termin?", function() {sendCancel(day, hour)})};
}

function clearCalendar() {
    const rows = document.getElementById("timetable").rows;
    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].cells;
        for (let j = 1; j < cells.length; j++) {
            cells[j].classList.remove("reserved");
            cells[j].innerHTML = '&nbsp;';
            cells[j].onclick = function () {
                dialog("Czy na pewno chcesz zarezerwować ten termin?", function () {
                    sendReservation(j-1, i-1)
                })
            };
        }
    }
}

function sendCancel(day, hour) {
    $.ajax({
        url: '/timetable/cancel',
        type: 'POST',
        data: {
            "day": day,
            "hour": hour
        },
        success: function (data) {
            showModal("Twoja wizyta została anulowana", true);
        },
        error: function (xhr, status, error) {
            showModal(JSON.parse(xhr.responseText).message, false);
        }
    })
}

function sendReservation(day, hour) {
    $.ajax({
        url: '/timetable/reserve',
        type: 'POST',
        data: {
            "day": day,
            "hour": hour
        },
        success: function (data) {
            showModal("Twoja wizyta została zarezerwowana", true);
        },
        error: function (xhr, status, error) {
            showModal(JSON.parse(xhr.responseText).message, false);
        }
    })
}

function loadTimetable() {
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/timetable", false);
    xmlHttp.send(null);
    return printTimetable(JSON.parse(xmlHttp.responseText));
}

function closeModal() {
    document.getElementById("modal").style.display = "none";
}

function showModal(text, success) {
    document.getElementById("modal").style.display = "block";
    document.getElementById("message").innerHTML = text;
    let bar = document.getElementById("bar");
    bar.className = '';
    if (success) {
        bar.classList.add("success");
    } else {
        bar.classList.add("error");
    }
}

function dialog(message, yesCallback) {
    $('#confirm-modal').show();
    $('#btnYes').unbind("click");
    $('#confirm-message').text(message);
    $('#btnYes').click(function () {
        yesCallback();
        $('#confirm-modal').hide();
    });
    $('#btnNo').click(function () {
        $('#confirm-modal').hide();
    });
}
