<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title>View Expense</title>
</head>
<body>
	<h3> View Expense </h3>
	<table>
	<c:forEach items="${categorisedExpenses}" var="categorisedExpense" varStatus="ctr">
		<tr><td> ${categorisedExpense.category}</td></tr>
		<tr><td><table>
		<tr><td>Account Balance:</td><td>${categorisedExpense.accountBalance}</td></tr>
		<tr><td>Purse Money:</td><td>${categorisedExpense.purseMoney}</td></tr>
		<tr><td>Total Expense:</td><td>${categorisedExpense.totalExpense} &nbsp; <a href="viewExpenseDetails?index=${ctr.index}"> View details </a></td>
		<tr><td>Unaccounted Expense:</td><td>${categorisedExpense.unaccountedExpense}</td>
		</table></td></tr>
	</c:forEach>
	</table>
</body>
</html>