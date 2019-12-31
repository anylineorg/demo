<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.anyline.org/core" prefix="al"%>
list size:${set.size}
<c:forEach var="row" items="${set }" varStatus="status">
<div>
${status.index }
${row }
</div>
</c:forEach>
<table>
	<tr>
		<td>序号</td>
		<td>ID</td>
		<td>NM</td>
	</tr>
	<tbody id="listBody">
	</tbody>
</table>
<div id="listPage"></div>
<script>
	function fnGetParam(){
		var params = {"id":"${param.id}","nm":"${param.nm}"};
		return params;
	}
</script>
<al:navi url="/web/app/navi" param="fnGetParam" page="listPage" body="listBody" intime="true"></al:navi>