<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

   
<!--  추가  -->
<%@ page errorPage = "error.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>insertBoard.jsp</title>
</head>
<body>
   <center>
   <h1>새 게시글 등록 </h1>
   <a href = "logout.do">로그아웃</a>
   <hr>
   <form name = "insertForm" method = "POST" action = "insertBoard.do">
      <table border = "1" cellpadding = "0" cellspacing = "0">
         <tr>
            <td bgcolor= "orange" width = "70">제목</td>
            <td align = "left"><input type = "text" name = "title" />
         </tr>
         
         <tr>
            <td bgcolor= "orange">작성자</td>
            <td align = "left"><input type = "text" name = "writer" />
         </tr>
         
         <tr>
            <td bgcolor= "orange">내용</td>
            <td align = "left">
            	<textarea rows="10" cols="40" name = "content"></textarea>
            </td>
         </tr>
         
         <tr>
            <td colspan = "2" align = "center">
               <input type = "submit" value = "글 등록"/>
         </tr>
      </table>
   </form>
   <hr>
   <a href = "getBoardList.do">전체 게시글 목록 보기</a>
   </center>
</body>
</html>