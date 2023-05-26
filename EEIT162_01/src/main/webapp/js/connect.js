document.write('<script src="/UM-app/js/stomp.js"></script>');
document.write('<script src="/UM-app/js/sockjs-0.3.4.js"></script>');
document.write('<script src="/UM-app/js/jquery-3.6.4.min.js"></script>');

var stompClient = null;

function connect(username) {
	stompClient.over(new SockJS('/UM-app/chatroom'));
	stompClient.connect({ user: username }, function(frame) {
		console.log('Connected: ' + frame);

		// 廣播
		stompClient.subscribe('/topic/messages', function(messageOutput) {
			showMessageOutput(JSON.parse(messageOutput.body));
		});

		// 私人
		stompClient.subscribe('/user/' + userName + "/subscribe", function(messageOutput) {
			showMessageOutput(JSON.parse(messageOutput.body));
		});

	});
}


function showMessageOutput(messageOutput){
	var response = 	document.getElementById('response');
	
}