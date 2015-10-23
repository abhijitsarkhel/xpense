<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Add Income</title>
</head>
<body>
	<h3> Add Income</h3>
	<form:form method="POST" commandName="income">
		<c:if test="${not empty msg}">
			<div style="color:green"> ${msg}</div>
		</c:if>
		<c:if test="${not empty errorMsg}">
			<div style="color:red"> ${errorMsg}</div>
		</c:if>
		<table>
			<tr>
				<td>Select Income Option</td>
				<td><form:select path="incomeOption">
						<option value="salary">From Salary</option>
						<option value="misc">Misc</option>
					</form:select></td>
			</tr>
			<tr>
				<td>Enter Amount</td>
				<td><form:input path="amount" /></td>
			</tr>
			<tr>
				<td> Remarks </td>
				<td> <form:textarea path="remarks"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit" value="Add Income" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>