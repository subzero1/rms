<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				��Ŀ�����			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���Ĳ���</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.fwbm}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bdbh}</p></td>
		</tr>
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�� �� ��</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.cjr}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�绰</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.cjrdh}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td02_xmbgd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ����</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ���</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.gclb}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgzl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ�ȼ�</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmdj}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��ƽ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.ys_je}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.lxje}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgje}</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border-left="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">ԭ��ƹ�������</p></td>
			<td colspan="3" border-left="1px solid black" border-right="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">�����������</p></td>
		</tr>
		<tr height="10mm">
		    <td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.ysjnr}</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bghnr}</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">ԭ��ƹ�����</p></td>
			<td colspan="3" border="1px solid black" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">���������</p></td>
		</tr>
		<tr height="10mm">
		    <td colspan="3" border="1px solid black" ><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.ysjgzl}</p></td>
			<td colspan="3" border="1px solid black" ><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bghgzl}</p></td>
		</tr>
		<tr height="20mm">
			<td border="1px solid black" border-top="0px solid black" width="23mm"><p font="Simsun" font-size="10pt" color="black" align="center">���ԭ��</p></td>
			<td border="1px solid black" border-top="0px solid black" colspan="5" width="142mm"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td02_xmbgd.bgyysm}</p></td>
		</tr>
	</table>	
</pages>