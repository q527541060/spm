<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <%@include file="common/head.jsp" %>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>用户列表</title>
  <link rel="stylesheet" href="${staticPath}/styles/user/manage.css">
</head>
<body>

<script>
  $(function () {
    $.ajax({
      url: basePath + "/threePointClose/getTestJson",
      dataType: "json",
      async: true,
      //data: {path: pcbPath, heigthPath: heigthPath},
      type: "GET",
      beforeSend: function () {
      },
      success: function (req) {
        var v = req;
        //alert(v.data.CompData_AOIF.arrShape[0].JudgeRes);
      },
    });


  })
</script>
</body>
</html>