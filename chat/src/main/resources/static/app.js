var stompClient = null;
var subs = {};
var activeChat = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#online").show();
    } else {
        $("#conversation").hide();
        $("#online").hide();
    }
    $("#messages").html("");
}

function connect() {
    //var socket = new SockJS('https://service1.brkambiental.com.br:8083/notificacao');
    //stompClient = Stomp.over(socket);
    stompClient = Stomp.client('ws://localhost:8083/ws');
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/ping', function (m) {
//            chatMessage(m.body);
//        });
        stompClient.subscribe('/queue/c723fc50-639d-4eec-a449-cebda70599c9#messages.private', function (m) {
            chatMessage(m.body);
        });

        stompClient.subscribe('/chat/chats', function (m) {
            chatsJoined(m.body);
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendChatMessage() {
    stompClient.send("/chat/sendChatMessage/" + activeChat, {}, JSON.stringify({"text": $("#message").val()}));
}

function joinChat() {
    stompClient.send("/chat/join/" + $("#chat").val(), {}, null);
    setTimeout(function () {
        stompClient.subscribe('/chat/chats', function (m) {
            chatsJoined(m.body);
        });
    }, 500);
}

function subChat(chat) {

    if (subs[chat] !== undefined) {
        subs[chat].unsubscribe();
        subs[chat] = null;
    }
    if (subs[chat + "users"] !== undefined) {
        subs[chat + "users"].unsubscribe();
        subs[chat + "users"] = null;
    }

    stompClient.subscribe('/chat/chat.old.messages/' + chat, function (m) {
        chatMessages(m.body);
    });
    stompClient.subscribe('/chat/chat.users/' + chat, function (m) {
        usersOnLine(m.body);
    });
    subs[$("#chat").val()] = stompClient.subscribe('/topic/' + chat + '#messages.public', function (m) {
        chatMessage(m.body);
    });
    subs[$("#chat").val() + "users"] = stompClient.subscribe('/topic/' + chat + '#users.connected', function (m) {
        usersOnLine(m.body);
    });
}

function leaveChat(chat) {
    stompClient.send("/chat/leave/" + chat, {}, null);
    if (subs[chat] !== undefined) {
        subs[chat].unsubscribe();
        subs[chat] = null;
    }
    if (subs[chat + "users"] !== undefined) {
        subs[chat + "users"].unsubscribe();
        subs[chat + "users"] = null;
    }
}

function chatMessages(messages) {
    messages = JSON.parse(messages);
    $("#messages").empty();
    for (var i = 0; i < messages.length; i++) {
        var message = messages[i];
        $("#messages").prepend("<tr><td>" + message.fromNickName + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td></tr>");
    }
}

function chatMessage(message) {
    message = JSON.parse(message);
    $("#messages").prepend("<tr><td>" + message.fromNickName + "</td><td>" + new Date(message.date) + "</td><td>" + message.text + "</td></tr>");
}

function chatsJoined(chats) {
    chats = JSON.parse(chats);
    $("#chats").empty();
    for (var i = 0; i < chats.length; i++) {
        $("#chats").prepend("<tr><td id='" + chats[i].chatRoom + "'>" + chats[i].chatRoom
                + "</td><td><button class='btn btn-default' id='rm" + chats[i].chatRoom + "'>Leave</button></td></tr>");

        $("#rm" + chats[i].chatRoom).click(function (e) {
            leaveChat($(e.target).parent().parent().find('td').first().text());
            $(e.target).parent().parent().remove();
        });

        $("#" + chats[i].chatRoom).click(function (e) {
            activeChat = $(e.target).text();
            $("td").removeClass("active");
            $(e.target).addClass('active');
            subChat(activeChat);
        });
    }
}

function usersOnLine(users) {
    users = JSON.parse(users);
    $("#users").empty();
    for (var i = 0; i < users.length; i++) {
        $("#users").prepend("<tr><td>" + users[i].nickName + "</td></tr>");
    }
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
    $("#send").click(function () {
        sendChatMessage();
    });
    $("#joinChat").click(function () {
        joinChat();
    });
    $("#leaveChat").click(function () {
        leaveChat();
    });


    connect();
});
