<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.anyline.org/core" prefix="al"%>
<c:forEach var="item" items="${set }" varStatus="status">
<tr id="tr_${item.ID }">
	<td><al:serial index="${status.index }"/></td>
	<td>${item.ID }</td>
	<td>${item.NM }</td>
</tr>
</c:forEach>
