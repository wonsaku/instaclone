// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault();
	console.log(event);
	alert("update");
	
	let data = $("#profileUpdate").serialize();
	
	console.log(data);
	
	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded;charset=utf-8",
		dataType: "json"
	}).done(res=>{ // http status 상태코드 200번대
		console.log("update 성공", res);
		location.href=`/user/${userId}`;
	}).fail(error=>{ //http status 상태코드 200 아닐때
		if(error.data ==null){
			alert(error.responseJSON.message);
		}else {
			alert(JSON.stringify(error.responseJSON.data));
		}
		console.log("update 실패");
	});
}