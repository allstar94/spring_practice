<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>login.jsp ������</title>
<style>
#div_box {
	position: absolute;
	top: 10%;
	left: 40%;
}
</style>
</head>
<body>
	<div id="div_box">
		<h1>�α���</h1>
		<form name="LoginForm" action="login.do">
			<table border="1" cellpading="0" cellspacing="0">
				<tr>
					<td bgcolor="orange">���̵�</td>
					<td><input type="text" name="id"/></td>
				</tr>
				
				<tr>
					<td bgcolor="orange">�н�����</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="�α���"></td>
				</tr>
				
			</table>
		</form>
	</div>

</body>
</html>