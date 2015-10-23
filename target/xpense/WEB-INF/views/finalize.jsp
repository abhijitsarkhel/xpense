<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Finalize Monthly expense statement</title>
<link href="static/css/style.css" rel="stylesheet" />
</head>
<body>
	<h3>Finalize Monthly expense statement</h3>
	<form:form method="POST" commandName="finalMonthlyExpense">
		<table>
			<tr>
				<td> Enter current purse money </td>
				<td> <form:input path="purseMoney"/> </td>
			</tr>
			<tr>
				<td> Enter current bank account balance </td>
				<td> <form:input path="accountMoney"/>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit" value="Finalize"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
