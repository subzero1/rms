<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				�����ܽ�			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="10mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���Ĳ���</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.bdbh}</p></td>
		</tr>
		<tr height="10mm">
	    	<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�� �� ��</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�绰</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td03_jlzj.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ����</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${Td01_xmxx.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ���</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${Td01_xmxx.xmbh}</p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����λ</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jldw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ί������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td01_xmxx.jlpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������ʦ</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlgcs}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��ϵ�绰</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.jlgcsdh}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����ܽ�</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.jlzj}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����ע</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td03_jlzj.bz}</p></td>
		</tr>
	</table>	
</pages>