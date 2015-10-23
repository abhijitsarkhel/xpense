<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>View Expense Details</title>
</head>
<body>
	<h3>View Expense Details</h3>
	<table>
		<c:forEach items="${expenses}" var="expense" varStatus="ctr">
			<tr>
				<td>${ctr.index + 1}.</td>
			</tr>
			<tr>
				<td>Expense for date ${expense.date}, total ${expense.total}</td>
			</tr>
			<tr>
				<td>
					<table>
						<c:forEach items="${expense.expenseLineItems}"
							var="expenseLineItem" varStatus="ctr1">
							<tr>
								<td>${ctr1.index + 1}</td>
							</tr>
							<tr>
								<td>Category:</td>
								<td>${expenseLineItem.categoryName}</td>
							</tr>
							<tr>
								<td>Sub Category:</td>
								<td>${expenseLineItem.subCategoryName}</td>
							</tr>
							<tr>
								<td>Amount:</td>
								<td>${expenseLineItem.amount}</td>
							</tr>
							<tr>
								<td>Remarks:</td>
								<td>${expenseLineItem.remarks}</td>
							</tr>
						</c:forEach>

					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>