<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>

<style>
body {
	font-size:12px;
	font-family:돋음;
	color:000000;
}
table {
	width:600px;
	border:1px solid #cccccc;
	border-collapse:collapse; /*셀과 셀사이의 간격을 0*/
}
th,td {
	border:1px solid #cccccc;
	padding : 5px;
}
.button_area {
	width:600px;
	text-align:center;
	margin-top:10px;
}
.caption1 {
	font-size:24px;
	font-weigth:bold;
	color:skyblue;
	padding:5px;
}
input,textarea,button {
	font-size:12px;
}
.input1 {
	width:98%;
}
.textarea1 {
	width:98%;
	height:50px;
}
.button1 {
	padding:10px;
	background-color:#99ff00;
	border:1px solid green;
}
</style>

<script>
/*ajax 활용 코드*/
function fn_save() {
	
	if( $("#title").val() == "" ) { // 화면에 ID값이 title로 되어있는 오브젝트
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
	  }
	if( $("#pass").val() == "") {
		alert("암호를 입력해주세요.")
		$("#pass").focus();
		return false;
	}
	
	var form = $("#frm").serialize();
	
	$.ajax({ 
		type : "POST",
		data : form,
		url : "/myproject1/boardWriteSave.do",
		
		datatype : "text",
		success : function(data){
			if( data == "ok") {
				alert("저장완료");
				location = "/myproject1/boardList.do";
			}
		},
		error   : function() {
			alert("전송실패");

		}		
	});
	
/* 	if( document.frm.title.value == "" ) {
		alert("제목을 입력해주세요.");
		document.frm.title.focus();
		return false;
	}
	if( document.frm.pass.value == "" ) {
		alert("암호를 입력해주세요.");
		document.frm.pass.focus();
		return false;
	}
	document.frm.submit(); 일반 jsp 코드 */
}

function fn_action() {
	location = "/myproject1/boardList.do";
}


</script>

<body>

<!-- <form name="frm" method="post" action="/myproject1/boardWriteSave.do"> -->
<form name="frm" id="frm">
<div>
<table>
	<caption class="caption1">게시판 입력화면</caption>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" id="title" class="input1" 
				   placeholder="제목을 입력해주세요." autofocus></td>
	</tr>
	<tr>
		<th>암호</th>
		<td><input type="password" name="pass" id="pass" class="input1"
		           placeholder="암호를 입력해주세요."></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" name="name" id="name" class="input1"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" class="textarea1"></textarea></td>
	</tr>
</table>
</div>
<div class="button_area">
	<button type="submit" onclick="fn_save();return false;" class="button1">저장</button>
	<button type="reset" class="button1">취소</button>
	<button type="button" onclick="fn_action()">목록</button>
</div>
<!-- </form> -->
</body>
</html>



