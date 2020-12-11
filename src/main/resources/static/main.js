var client = null;
var hours = Array.prototype.slice.call(document.getElementsByClassName('hour'));
for (var i = 0; i < hours.length; i++) {
    hours[i].addEventListener("click", reserve.bind(null, Math.floor(i / 8), i % 8));
    hours[i].setAttribute("day", Math.floor(i / 8));
    hours[i].setAttribute("hour", i % 8);
}

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
    var reservations = timetable.reservations;
    clearCalendar();
    for (var i = 0; i < reservations.length; i++) {
        var day = reservations[i].day;
        var hour = reservations[i].hour;

        setReserved(day, hour);
    }
}

function setReserved(day, hour) {
    for (var i = 0; i < hours.length; i++) {
        if (hours[i].getAttribute('day') == day && hours[i].getAttribute('hour') == hour) {
            hours[i].classList.add('reserved');
        }
    }
}

function clearCalendar() {
    for (var i = 0; i < hours.length; i++) {
        hours[i].classList.remove('reserved');
    }
}

function reserve(day, hour) {
    if(confirm("Potwierdź")){
        client.send("/app/reserve", {}, JSON.stringify({
            "day": day,
            "hour": hour
        }));
    }
}

function loadTimetable() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/timetable", false);
    xmlHttp.send(null);
    return printTimetable(JSON.parse(xmlHttp.responseText));
}

