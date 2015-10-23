<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Add Expense</title>
<link href="static/css/style.css" rel="stylesheet" />
<script src="static/js/jquery-2.1.4.min.js"></script>
<c:url var="findSubCategoriesURL" value="/subCategories"/>
<script type="text/javascript">
	$(document).ready(function() {
		$('#category').change(
				function() {
					$.ajax({
						url: '${findSubCategoriesURL}',
						type: 'GET',
						dataType: 'json',
						data: {
							categoryId: $(this).val()
						},
						success: function(data) {
							var html = '';
							for(var i =0; i < data.length; i++) {
								html += '<option value="' + data[i].subCategoryId + '">'
									+ data[i].subCategoryName + '</option>';
							}
							$('#subCategory').html(html);
						}
				});
		});
	});
</script>
</head>
<body>
	<h3>Add Expense</h3>
	<form:form method="POST" commandName="expense">
		<table>
			<tr>
				<td>Select category</td>
				<td>
					<form:select path="category">
						<form:options items="${categoryList}" itemValue="categoryId" itemLabel="categoryName" />
					</form:select>
				</td>
				<td>
					<form:input path="newCategory" placeholder="add new category"/>
				</td>
			</tr>
			<tr>
				<td>Select sub category</td>
				<td>
					<form:select path="subCategory">
						<form:options items="${subCategoryList}" itemValue="subCategoryId" itemLabel="subCategoryName" />
					</form:select>
				</td>
				<td>
					<form:input path="newSubCategory" placeholder="add new sub category"/>
				</td>
			</tr>
			<tr>
				<td>Add amount</td>
				<td>
					<form:input path="amount" placeholder="add amount"/>
				</td>
			</tr>
			<tr>
				<td> Remarks </td>
				<td>
					<form:textarea path="remarks"/>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;"><input
					type="submit" value="Add Expense" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>