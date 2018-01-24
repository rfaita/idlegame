var stompClient = null;
var subs = {};
var activeChat = null;
var activePmUser = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#mail").show();
    } else {
        $("#mail").hide();
    }
    $("#mails").html("");
}

function connect() {
    $.ajax({
        url: "/loggedUser",
        success: function (user) {

            stompClient = Stomp.client('ws://localhost:8084/ws');
            stompClient.connect({}, function (frame) {
                setConnected(true);
                console.log('Connected: ' + frame);

                stompClient.subscribe('/mail/mail.old.messages', function (m) {
                    mailMessages(m.body);
                });

                stompClient.subscribe('/queue/' + user + '#mail.private', function (m) {
                    mailMessage(m.body);
                });

            });
        }
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function mailMessages(messages) {
    messages = JSON.parse(messages);
    $("#mails").empty();
    for (var i = 0; i < messages.length; i++) {
        var message = messages[i];
        $("#mails").prepend("<tr><td>" + message.fromNickName + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td><td>" + message.reward + "</td></tr>");
    }
}

function mailMessage(message) {
    message = JSON.parse(message);
    $("#mails").prepend("<tr><td>" + message.fromNickName + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td><td>" + message.reward + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });


    connect();
});
