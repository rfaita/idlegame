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
        url: "/mail/loggedUser",
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

                stompClient.subscribe('/queue/' + user + '#mail.private.update', function (m) {
                    mailMessageUpdate(m.body);
                });
                stompClient.subscribe('/queue/' + user + '#mail.private.delete', function (m) {
                    mailMessageDelete(m.body);
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
        $("#mails").prepend("<tr id='" + message.id + "'><td>" + message.fromNickName
                + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td><td>" + message.reward
                + "</td><td>" + (!!message.readed ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "READ'>Mask as readed</button>")
                + "</td><td>" + (!!message.collected ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "COLLECT'>Collect</button>")
                + "</td><td><button idMsg='" + message.id + "' id='" + message.id + "DEL'>del</button>"
                + "</td></tr>");

        $("#mails #" + message.id + "READ").click(function (e) {
            stompClient.send("/mail/markAsReadPrivateMail/" + $(e.target).attr("idMsg"), {}, null);
        });
        $("#mails #" + message.id + "DEL").click(function (e) {
            stompClient.send("/mail/deletePrivateMail/" + $(e.target).attr("idMsg"), {}, null);
        });
        $("#mails #" + message.id + "COLLECT").click(function (e) {
            $.get("/collectReward/" + $(e.target).attr("idMsg"));
        });
    }
}

function mailMessage(message) {
    message = JSON.parse(message);
    $("#mails").prepend("<tr id='" + message.id + "'><td>" + message.fromNickName
            + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td><td>" + message.reward
            + "</td><td>" + (!!message.readed ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "READ'>Mask as readed</button>")
            + "</td><td>" + (!!message.collected ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "COLLECT'>Collect</button>")
            + "</td><td><button idMsg='" + message.id + "' id='" + message.id + "DEL'>del</button>"
            + "</td></tr>");

    $("#mails #" + message.id + "READ").click(function (e) {
        stompClient.send("/mail/markAsReadPrivateMail/" + $(e.target).attr("idMsg"), {}, null);
    });
    $("#mails #" + message.id + "DEL").click(function (e) {
        stompClient.send("/mail/deletePrivateMail/" + $(e.target).attr("idMsg"), {}, null);
    });
    $("#mails #" + message.id + "COLLECT").click(function (e) {
        $.get("/collectReward/" + $(e.target).attr("idMsg"));
    });
}

function mailMessageUpdate(message) {
    message = JSON.parse(message);
    $("#mails #" + message.id).html("<td>" + message.fromNickName
            + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td><td>" + message.reward
            + "</td><td>" + (!!message.readed ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "READ'>Mask as readed</button>")
            + "</td><td>" + (!!message.collected ? "S" : "<button idMsg='" + message.id + "' id='" + message.id + "COLLECT'>Collect</button>")
            + "</td><td><button idMsg='" + message.id + "' id='" + message.id + "DEL'>del</button>"
            + "</td>");

    $("#mails #" + message.id + "READ").unbind('click');
    $("#mails #" + message.id + "READ").click(function (e) {
        stompClient.send("/mail/markAsReadPrivateMail/" + $(e.target).attr("idMsg"), {}, null);
    });
    $("#mails #" + message.id + "DEL").unbind('click');
    $("#mails #" + message.id + "DEL").click(function (e) {
        stompClient.send("/mail/deletePrivateMail/" + $(e.target).attr("idMsg"), {}, null);
    });

    $("#mails #" + message.id + "COLLECT").unbind('click');
    $("#mails #" + message.id + "COLLECT").click(function (e) {
        $.get("/collectReward/" + $(e.target).attr("idMsg"));
    });

}

function mailMessageDelete(message) {
    message = JSON.parse(message);
    $("#mails #" + message.id).remove();
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
