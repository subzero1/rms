<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				<c:choose>
					<c:when test="${module_id==101}">����ƽ��������</c:when>
					<c:otherwise>����ƽ������</c:otherwise>
				</c:choose>				
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���Ĳ���</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.bdbh}</p></td>
		</tr>
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�绰</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td11_jfpmsq.cjrq}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ����</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jsxz}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��װ�ֵ�����</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jdmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����רҵ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.sszy}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��ʹ�û���</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.jfmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�豸���緽ʽ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.sbgdfs}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.xzjjsl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���ܳߴ�</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_xqs.jjcc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"></p></td>
		</tr>
		<tr height="40mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�� ע</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td11_jfpmsq.bz}</p></td>
		</tr>
	 </table>
	<table width="165mm">
		<tr height="15mm">
			<td width="165mm">
				<p font="Simhei" font-size="14pt" color="black" align="center">�豸��ϸ</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="8mm">
			<td width="29mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�豸����</p></td>
			<td width="35mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�豸�ͺ�</p></td>
			<td width="20mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����</p></td>
			<td width="20mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���ܳߴ�</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���緽ʽ</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����רҵ</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��װλ��</p></td>
			<td width="25mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��������</p></td>
			<td width="36mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��װ����</p></td>
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