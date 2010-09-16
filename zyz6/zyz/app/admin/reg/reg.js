$(document).ready(function(){
	for(var i=0;i<arrayOrgType0.length;i++)
	{
		 $("#orgdiv").append(arrayOrgType0[i]);
	}

	$("#orgdiv").change(function() {
        var value = $("option:selected", this).val();
        $('#orgtyp').find('option').remove();
        $("#orgtyp").append("<option value='' label='请选择'>请选择</option>");
       
        if(value==100)
        {
        	for(var i=0;i<arrayOrgType1.length;i++)
        	{
        		 $("#orgtyp").append(arrayOrgType1[i]);
        	}
        }
        if(value==200)
        {
        	for(var i=0;i<arrayOrgType2.length;i++)
        	{
        		 $("#orgtyp").append(arrayOrgType2[i]);
        	}
        }
    });

	for(var i=0;i<arrayAreaCity.length;i++)
	{
		$("#loccitid").append(arrayAreaCity[i]);
	}

	$('#loccitid').change(function() {
		changeCity($(this).val());
	});

	$('#locareid').change(function() {
		changeArea($(this).val());
	});
});

//切换城市
function changeCity(areaid)
{
	$('#locareid').find('option').remove();
	$.ajax({
		   type: "POST",
		   url: "load.action",
		   data: { "areaid": areaid },
		   dataType: "text",
		   success: function(data){
			   $("#locareid").append(data);
		   }
		 });
	
	//切换市的时候市下属的组织也切换
	$('#regorgid').find('option').remove();
	$.ajax({
		   type: "POST",
		   url: "loadAreaOrg.action",
		   data: { "locareid": areaid },
		   dataType: "text",
		   success: function(data){
			   $("#regorgid").append(data);
		   }
		 });
}

//切换区域
function changeArea(locareid)
{
	$('#regorgid').find('option').remove();
	$.ajax({
		   type: "POST",
		   url: "loadAreaOrg.action",
		   data: { "locareid": locareid },
		   dataType: "text",
		   success: function(data){
			   $("#regorgid").append(data);
		   }
		 });
}