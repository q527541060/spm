
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String contextPath = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
  String staticPath = basePath + "/static";
  request.setAttribute("basePath", basePath);
  request.setAttribute("staticPath", staticPath);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${staticPath}/styles/base.css"/>
<script src="${staticPath}/jquery/jQuery-2.2.0.min.js"></script>
<script>
  // 将基础路径写入js变量，用于js获取
  window.basePath = '${basePath}'
</script>
