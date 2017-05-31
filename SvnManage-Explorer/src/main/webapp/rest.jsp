<%@page import="com.assassin.common.Global "%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<%
	String path = request.getContextPath();
	String appPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	String basePath = appPath + path + "/";
	String restPath = Global.getConfig("realestate.rest.baseurl");
	/*
	if(username == "" || "null".equalsIgnoreCase(username)){
		response.getWriter().write("请先登录，再打开此页面");
		return;
	}
	*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>REST服务测试</title>
<meta name="decorator" content="default"/>

<script src="resource/json/json2.js"></script>
<script src="resource/json/jsonlint.js"></script>
<script src="resource/jquery/jquery-1.9.1.js"></script>
<script language="JavaScript" type="text/javascript" src="http://code.jquery.com/jquery-latest.js"> </script>
	<script type="text/javascript">


	function callRest() {
		try {
			var baseurl = $("#baseurl").val();
			var url = $("#url").val();
			var username = $("#username").val();
			var password = $("#password").val();
			var method = $("#method").val();
			var jsoninput = $("#jsoninput").val();
			if (method == "POST" || method == "PUT") {
				if (jsoninput == "") {
					alert("选择POST或PUT方法时，请确认JSON参数是否需要填写");
					//return;
				}
			}
			$("#status").val("");
			$("#source").val("");

			if(method == "GET"){
				$.ajax({
					url: baseurl+url,
					type: 'GET',
					contentType: "application/json; charset=utf-8",
					processData: false,
					success: function (result, status, xhr) {
						$("#source").val(JSON.stringify(
								result, null, "  "));


					},
					error: function (xhr, status, err) {
						$("#source").val(xhr.responseText);
					}
				});
			}else if(method == "POST"){
				$.ajax({
					url: baseurl+url,
					type: 'POST',
					contentType: "application/json; charset=utf-8",
					data: jsoninput,
					processData: false,
					success: function (result, status, xhr) {
						$("#source").val(JSON.stringify(result));

						var result = jsonlint
								.parse(document.getElementById("source").value);
						if (result) {
							document.getElementById("source").value = JSON.stringify(
									result, null, "  ");
						}
						else{
							try{
								$('source').val(new XML(document.getElementById("source").value).toXMLString());
							}
							catch(e){}
						}

					},
					error: function (xhr, status, err) {
						$("#source").val(xhr.responseText);
					}
				});

			}else {
				alert(method + "方法未知");
			}
		} catch (e) {
			alert(e.message);
		}
	}

	//result分两部分:status和json字符串，用♀分隔，例如：200♀{"data":"123456"}
	//其中200是status的值，♀后面的是response body(json字符串)
	function callback(result) {
		var array = result.split("♀");
		var status = array[0];
		var message = array[1];

		$("#status").val(status);
		$("#source").val(message);
		try {
			if(status == 200 || status == 201){
				var result = jsonlint
						.parse(document.getElementById("source").value);
				if (result) {
					document.getElementById("source").value = JSON.stringify(
							result, null, "  ");
				}
				else{
					try{
						$('source').val(new XML(document.getElementById("source").value).toXMLString());
					}
					catch(e){}
				}
				return;

				document.getElementById("divTest").style.display="";
				var objJson=result.data;
				var jsonLength=objJson.length;
				var innerhtml="";

				var tableterra=document.getElementById("tableterra");
				var rowNum=tableterra.rows.length;
				for (i=0;i<rowNum;i++)
				{
					tableterra.deleteRow(i);
					rowNum=rowNum-1;
					i=i-1;
				}


				var mytrTerra=tableterra.insertRow();
				mytrTerra.setAttribute("style","text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold")
				mytrTerra.appendChild(CreateTD("序号"));
				mytrTerra.appendChild(CreateTD("宗地代码"));
				mytrTerra.appendChild(CreateTD("不动产单元号"));
				mytrTerra.appendChild(CreateTD("业务号"));
				mytrTerra.appendChild(CreateTD("权利类型"));
				mytrTerra.appendChild(CreateTD("登记类型"));
				mytrTerra.appendChild(CreateTD("使用权面积"));
				mytrTerra.appendChild(CreateTD("权利开始时间"));
				mytrTerra.appendChild(CreateTD("权利结束时间"));
				mytrTerra.appendChild(CreateTD("权利人名称"));
				mytrTerra.appendChild(CreateTD("权利人代码"));



				for(var i=0;i<jsonLength;i++) {
					var id=objJson[i].id
					var zddm=objJson[i].zddm;
					var bdcdyh=objJson[i].bdcdyh;
					var ywh=objJson[i].ywh;
					var qllx=objJson[i].qllx;
					var djlx=objJson[i].djlx
					var syqmj=objJson[i].syqmj
					var syqqssj=objJson[i].syqqssj;
					var syqjssj=objJson[i].syqjssj;
					var qlrmc=objJson[i].qlrmc
					var zjh=objJson[i].zjh;


					var mytrTerra=tableterra.insertRow();
					mytrTerra.setAttribute("style","text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold")

					mytrTerra.appendChild(CreateTD((id)));
					mytrTerra.appendChild(CreateTD(zddm));
					mytrTerra.appendChild(CreateTD(bdcdyh));
					mytrTerra.appendChild(CreateTD(ywh));
					mytrTerra.appendChild(CreateTD(qllx));
					mytrTerra.appendChild(CreateTD(djlx));
					mytrTerra.appendChild(CreateTD(syqmj));
					mytrTerra.appendChild(CreateTD(syqqssj));
					mytrTerra.appendChild(CreateTD(syqjssj));
					mytrTerra.appendChild(CreateTD(qlrmc));
					mytrTerra.appendChild(CreateTD(zjh));
				}
			}
		} catch (e) {
			alert(e.message);
		}
	}
	function Lpad(text,length,charValue){
		debugger;
		var textlength = (text+"").length;
		var otherlength=length-textlength;
		for(var i=0;i<otherlength;i++){
			text= charValue + text;
		}
		return text;
	}

	function CreateTD(text,isNotNowrap,width)
	{
		var mytd=document.createElement("td");
		if(!isNotNowrap) {
			mytd.setAttribute("nowrap", "true");
		}
		else{
			mytd.setAttribute("width", width+"px");
		}
		mytd.setAttribute("align" ,"center")

		mytd.innerText=text;
		return mytd;
	}
</script>
</head>
<body>
	<h3>REST服务测试</h3>
	<table>
		<tr>
			<td valign="top">
				<table>
					<tr>
						<td class="tableblock halign-left valign-top"><label>BASE_URL：</label>
						</td>
						<td colspan="3" class="tableblock halign-left valign-top"><input
								type="text" style="width:100%" id="baseurl"
								value="<%=restPath %>">
						</td>
					</tr>
					<tr>
						<td><label>用户名：</label></td>
						<td><input type="text" id="username" value="admin"></td>
						<td><label>密码：</label></td>
						<td><input type="password" id="password" value="admin">
						</td>
					</tr>
					<tr>
						<td height="50px"><label>服务Path：</label></td>
						<td colspan="5"><textarea rows="10" cols="83" id="url">/build/buildings</textarea>

						</td>
					</tr>
					<tr>
						<td><label>方法：</label></td>
						<td><select id="method">
							<option value="GET">GET</option>
							<option value="POST">POST</option>
							<option value="PUT">PUT</option>
							<option value="DELETE">DELETE</option>
						</select></td>

					</tr>
					<tr>
						<td><label>JSON参数：</label></td>
						<td colspan="3"><textarea rows="10" cols="83" id="jsoninput"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="right" style="padding-top: 10px;width: 200px">
							<input class="btn_blue" id="validate" type="button" onclick="callRest()"
								   value="调  用">
						</td>
					</tr>
					<tr>
						<td><label>响应代码:</label></td>
						<td><input type="text" id="status"></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><label class="title">响应内容:</label></td>
						<td colspan="3"><textarea rows="18" cols="83" id="source"></textarea>
						</td>
					</tr>

				</table>

			</td>
			<td align="center" valign="top">
				<div id="divTest" style="display:none">
					<table>

						<tr>

							<td>
								<table id="tableterra" border="0" cellpadding="3" cellspacing="1" width="100%"
									   align="center" style="background-color: #b9d8f3;">
								</table>

							</td>
						</tr>


					</table>

				</div>
			</td>

		</tr>

	</table>


</body>
</html>
