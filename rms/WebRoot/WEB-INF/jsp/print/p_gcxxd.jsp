<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pages>
	<table width="165mm">
		<tr height="20mm">
			<td width="165mm">
				<p font="Simhei" font-size="18pt" color="black" align="center">
				������Ϣ��			
				</p>
			</td>
		</tr>
	</table>
	<table width="165mm">
		<tr height="10mm">
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.xqbm}</p></td>
			<td width="23mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���������</p></td>
			<td width="32mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.cjr}</p></td>
			<td width="22mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td width="33mm" border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${Td02_xmbgd.cjrq}" pattern="yyyy-MM-dd HH:mm"/></p></td>
		</tr>
	    <tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��������</p></td>
			<td border="1px solid black" colspan="3"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcmc}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���̱��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ssdq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">Ҫ�����ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.yqwcsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ���</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td01_xmxx.xmbh}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gclb}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����רҵ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.zydl}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">רҵϸ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.zyxx}</p></td>
		</tr>
		<tr height="40mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����˵��</p></td>
			<td width="142mm" border="1px solid black" colspan="5"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.gcsm}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ƶ�λ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���췴��ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.kcfkzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjsx}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʩ����λ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgdw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgjdtbzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�ƻ�����ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.jhjgsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����λ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jldw}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��־�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jlrjtbzq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����ɷ�ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjpgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʩ���ɷ�ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sgpfsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����ɷ�ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.jlpfsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����Ա</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sjry}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʩ������Ա</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.sgfzr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������ʦ</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.jlgcs}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ŀ����Ա</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.xmgly}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʵ�ʿ���ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjkgsj}" pattern="yyyy-MM-dd"/></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʵ�ʿ���ʱ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px"><fmt:formatDate value="${td00_gcxx.sjjgsj}" pattern="yyyy-MM-dd"/></p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black" colspan="6" align="center"><p font="Simsun" font-size="10pt" color="black" align="center">Ԥ����ã����õ�λ��Ԫ��</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ͷ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_je}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jaf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�Ǳ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_ybf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�˹���</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_rgf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���Ϸ�</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_clf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_qtf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��������</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�豸��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_sbf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��Ʒ�</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_sjf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�չ�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_pggr}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">��е��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jxf}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">�����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" padding-left="8px">${td00_gcxx.ys_jlf}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center"></p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">Ҫ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">����</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">���</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sjyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sjfk}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">ʩ��</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sgyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.sgfk}</p></td>
		</tr>
		<tr height="10mm">
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center">����</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.jlyq}</p></td>
			<td border="1px solid black"><p font="Simsun" font-size="10pt" color="black" align="center" colspan="2">${td00_gcxx.jlfk}</p></td>
		</tr>
	</table>	
</pages>