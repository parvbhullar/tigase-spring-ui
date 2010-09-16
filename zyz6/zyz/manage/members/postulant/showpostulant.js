function setintentionid(intentionid,id)
{
	//如果是民政局管的"社区志愿服务" 加技能
	if(intentionid==4)
	{
		$("#jntd").show();
	}
	else
	{
		$("#jntd").hide();
		$('input[name="jn"]').attr("checked",false);
	}
	
	//$("#intentionidid").val(intentionid);
	$("#i"+id.substring(1,id.length)).attr("checked",true);
	$("#i"+id.substring(1,id.length)).click();
	alert("17");
}