function log(tid)
{
	$.ajax( {
		type : "POST",
		url : "/zyz/manage/log/insert.action",
		data : {
			tid:tid
		},
		success : function(data) {
			
		},
		error:function(data)
		{
			alert("日志记录失败!");
		}
	});
}