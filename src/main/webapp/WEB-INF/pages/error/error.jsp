<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="../common/head.jsp" %>
    <title>出错啦!</title>
    <link rel="stylesheet" href="${staticPath}/styles/error.css"/>
</head>
<body>
<div class="error">
    <h1>${code}</h1>
    <p>${message}</p>
</div>
</body>
</html>
