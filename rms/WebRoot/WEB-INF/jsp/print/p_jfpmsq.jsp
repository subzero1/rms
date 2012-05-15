<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				<c:choose>
					<c:when test="${module_id==101}">机房平面申请书</c:when>
					<c:otherwise>机房平面变更单</c:otherwise>
				</c:choose>				
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">发文部门</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">表单编号</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.bdbh}</p></td>
		</tr>
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">起草人</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">电话</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">提出日期</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td11_jfpmsq.cjrq}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">项目名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">建设性质</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jsxz}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">安装局点名称</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jdmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">所属专业</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.sszy}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">拟使用机房</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jfmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设备供电方式</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.sbgdfs}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">新增机架数量</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.xzjjsl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">机架尺寸</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_xqs.jjcc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"></p></td>
		</tr>
		<tr height="40mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">备 注</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.bz}</p></td>
		</tr>
	 </table>
	<table width="165mm">
		<tr height="15mm">
			<td width="165mm">
				<p font="Simhei" font-size="14pt" color="black" align="center">设备明细</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="8mm">
			<td width="29mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设备名称</p></td>
			<td width="35mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">设备型号</p></td>
			<td width="20mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">厂家</p></td>
			<td width="20mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">机架尺寸</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">供电方式</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">所属专业</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">安装位置</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">建设性质</p></td>
			<td width="36mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">安装日期</p></td>
		</tr>
		<c:forEach var="obj" items="${td12_sq_sbmx}">
			<tr height="8mm">
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.sbmc}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.sbxh}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.cj}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.jjcc}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.gdfs}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.sszy}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.azwz}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px">${obj.jsxz}</p></td>
				<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="12px"><fmt:formatDate value="${obj.azrq}" pattern="yyyy-MM-dd"/></p></td>
			</tr>
		</c:forEach>
	</table>
</pages>