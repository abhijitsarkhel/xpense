<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><decorator:title></decorator:title></title>
    <decorator:head></decorator:head>
</head>
<body>
	<table width="100%">
		<tr>
			<td><h2>Xpense</h2></td>
		</tr>
		<tr>
			<td><a href="<c:url value="/index.jsp" />"> Home </a></td>
		</tr>
    	<tr>
    		<td><hr /></td>
    	</tr>
    	<tr>
    		<td><decorator:body /></td>
    	</tr>
    	<tr>
    		<td><hr /></td>
    	</tr>
    	<tr>
    		<td><div style="float: right;font-size: 8pt;">&copy; Indigenous &reg;, 2015.</div></td>
    	</tr>
    </table>
</body>
</html>