
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <%@ include file="../common/head.jsp" %>
  <title>出错啦～</title>
  <link rel="stylesheet" href="${staticPath}/styles/error.css"/>
</head>
<body>
<div class="error">
  <h1>500</h1>
  <p>错误详情: ${error}</p>
</div>
</body>
</html>
