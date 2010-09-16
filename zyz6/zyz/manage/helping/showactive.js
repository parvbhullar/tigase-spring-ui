function getInputNum() {
		var inputLen = $("#messageId").text().length;
		if (inputLen <= 140) {
			var contentStr = "您还可以输入 <span>" + (140-inputLen) +"</span> 字 &nbsp; &nbsp; &nbsp;";
			$("#inputNumDiv").html(contentStr);
		} else {
			var contentStr = "已超出 <span>" + (inputLen - 140) +"</span> 字 &nbsp; &nbsp; &nbsp;";
			$("#inputNumDiv").html(contentStr);
		}
	}
