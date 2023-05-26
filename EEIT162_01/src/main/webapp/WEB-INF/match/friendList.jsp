<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>好友名單</title>

		<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
		<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
		<!-- mdbootstrap -->
		<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.css" rel="stylesheet" />

		<style>
			/* 個人資料大頭照 */
			#memberProfile img {
				object-fit: cover;
				border-radius: 75px;
				max-width: 100%;
				height: 150px;
				width: 150px;
			}

			.name-margin {
				margin-left: 20px;
				margin-right: 30vmax;
			}

			#fl-bar .nav-link {
				font-size: medium;
			}

			/* 好友列表圖片大頭照 */
			.friend-avatar img {
				object-fit: cover;
				border-radius: 25px;
				max-width: 100%;
				height: 50px;
				width: 50px;
			}
		</style>

	</head>

	<body style="background-color: rgb(253, 252, 246);">
		<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />

		<div id="topDiv">
			<div id="memberProfile"  style="padding-top:  150px;">
				<table>
					<tbody>
						<tr>
							<td><img src="${root}/showphoto/${member.id}"></td>
							<td>
								<h2 class="name-margin text-secondary">${member.username}</h2>
							</td>
							<td><a href="${root}/members/date" class="btn btn-outline-primary" type="button">編輯資料</a></td>
							<td><a href="${root}/match/mdp" class="btn btn-outline-success" type="button">編輯配對資料</a></td>
						</tr>
					</tbody>
				</table>
			</div>
	
	
	
			<div id="fl-list">
				<nav id="fl-bar">
					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<div style="display: flex;">
							<div class="nav-link active" id="nav-checked-tab" data-bs-toggle="tab"
								data-bs-target="#nav-checked" type="button" role="tab" aria-controls="nav-checked"
								aria-selected="true">好友列表
								<span id="checkednum" class="badge text-bg-light rounded-pill align-text-bottom"></span>
							</div>
						</div>
						<div style="display: flex;">
							<div class="nav-link" id="nav-uncheck-tab" data-bs-toggle="tab" data-bs-target="#nav-uncheck"
								type="button" role="tab" aria-controls="nav-uncheck" aria-selecte4d="false">已收到的邀請
								<span id="unchecknum" class="badge text-bg-light rounded-pill align-text-bottom"></span>
							</div>
						</div>
						<div style="display: flex;">
							<div class="nav-link" id="nav-invite-tab" data-bs-toggle="tab" data-bs-target="#nav-invite"
								type="button" role="tab" aria-controls="nav-invite" aria-selected="false">已送出的邀請
								<span id="invitenum" class="badge text-bg-light rounded-pill align-text-bottom"></span>
							</div>
						</div>
						<!-- <button class="nav-link" id="nav-blacklist-tab" data-bs-toggle="tab" data-bs-target="#nav-blacklist"
							type="button" role="tab" aria-controls="nav-blacklist" aria-selected="false">封鎖名單</button> -->
					</div>
				</nav>
				<div class="tab-content" id="nav-tabContent">
					<!-- nav-checked -->
					<div class="tab-pane fade show active" id="nav-checked" role="tabpanel"
						aria-labelledby="nav-checked-tab">
	
					</div>
					<!-- nav-uncheck -->
					<div class="tab-pane fade" id="nav-uncheck" role="tabpanel" aria-labelledby="nav-uncheck-tab"></div>
					<!-- nav-invite -->
					<div class="tab-pane fade" id="nav-invite" role="tabpanel" aria-labelledby="nav-invite-tab"></div>
					<!-- nav-blacklist -->
					<div class="tab-pane fade" id="nav-blacklist" role="tabpanel" aria-labelledby="nav-blacklist-tab">
					</div>
				</div>
			</div>
		</div>

		
		<jsp:include page="/WEB-INF/layout/footer.jsp" />


		<script>
			//拿登入id去controller查好友
			var memberId = "${member.id}";
			//分辨是否審核
			$(document).ready(function () {
				$('#nav-checked-tab').on('click', function () {
					getfriendListByStatus(memberId, 'checked');
				});
				$('#nav-invite-tab').on('click', function () {
					getfriendListByStatus(memberId, 'invite');
				});
				$('#nav-uncheck-tab').on('click', function () {
					getfriendListByStatus(memberId, 'uncheck');
				});
				$('#nav-blacklist-tab').on('click', function () {
					// getfriendListByStatus(memberId);
				});
				$('#nav-checked-tab').trigger('click');
			});


			function getfriendListByStatus(memberId1, check) {
				$.ajax({
					url: '${root}/friend/get/' + memberId + '/' + check,
					type: 'GET',
					dataType: 'json',
					success: function (friendList) {
						displayFriendList(friendList, check);						
					},
					error: function () {
						alert('Error loading friend list.');
					}
				});
			}
			var friendID = 0;
			var friendName = '';

			function deleteFriend(memberId, friendId) {
				$.ajax({
					url: '${root}/friend/delete/' + memberId + '/' + friendId,
					type: 'DELETE',
					success: function (response) {
						//重新獲取好友列表
						getfriendListByStatus(memberId, 'checked');
						getfriendListByStatus(memberId, 'invite');
						getfriendListByStatus(memberId, 'uncheck');
					}
				});
			}
			function updateFriend(memberId, friendId) {
				$.ajax({
					url: '${root}/friend/update/' + friendId + '/' + memberId,
					type: 'PUT',
					success: function (response) {
						//重新獲取好友列表
						getfriendListByStatus(memberId, 'checked');
						getfriendListByStatus(memberId, 'invite');
						getfriendListByStatus(memberId, 'uncheck');
					}
				});
			}

			function chatFriend(){
				$(location).prop("href", "${root}/chat/");
			}

			function displayFriendList(friendList, check) {
				var friendListThead = `<div><table class="table table-striped table-sm">` + `<thead><tr>
                            <th scope="col">大頭貼</th>
                            <th scope="col">name</th>
                            <th scope="col">操作</th>
                                    </tr></thead>`
				var friendListHtml = '<tbody>';

				friendList.forEach(function (friend) {
					switch (check) {
						case 'checked':
							$("#checkednum").text(friendList.length);
							if (memberId == friend.member1.id) {
								friendID = friend.member2.id;
								friendName = friend.member2.username;
							} else if (memberId == friend.member2.id) {
								friendID = friend.member1.id;
								friendName = friend.member1.username;
							}
							friendListHtml += createRow(friendID, friendName, `<button onclick="deleteFriend(` + memberId + `,` + friendID + `)" class="btn btn-danger">刪除好友</button><button class="btn btn-primary" onclick="chatFriend()"">開始聊天</button>`);
							break;
						case 'invite':
							$("#invitenum").text(friendList.length);
							friendID = friend.member2.id;
							friendName = friend.member2.username;
							friendListHtml += createRow(friendID, friendName, `<button onclick="deleteFriend(` + memberId + `,` + friendID + `)" class="btn btn-danger">收回邀請</button>`);
							break;
						case 'uncheck':
							$("#unchecknum").text(friendList.length);
							friendID = friend.member1.id;
							friendName = friend.member1.username;
							friendListHtml += createRow(friendID, friendName, `<button onclick="updateFriend(` + memberId + `,` + friendID + `)" class="btn btn-success">接受</button><button onclick="deleteFriend(` + memberId + `,` + friendID + `)" class="btn btn-danger">拒絕</button>`);
							break;
						default:
							console.log('default');
					}
				});

				friendListHtml += '</tbody></table></div>';

				var html123 = friendListThead + friendListHtml;
				// console.log(html123);
				$('#nav-checked').html(html123);
				$('#nav-invite').html(html123);
				$('#nav-uncheck').html(html123);
			}


			function createRow(friendID, friendName, buttonHTML) {
				return '<tr>' +
					'<div class="friend-item">' +
					'<td><div class="friend-avatar">' +
					'<img src="${root}/showphoto/' + friendID + '" alt="avatar">' +
					'</div></td>' +
					'<div class="friend-info">' +
					'<td><p>' + friendName + '</p>' +
					'</div></td>' +
					'<td>' + buttonHTML + '</td>' +
					'</div>' +
					'</tr>';
			}
		</script>

	</body>

	</html>