<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <meta http-equiv="Pragma" content="no-cache">

	<script type="text/javascript" src="/eRedG4/resource/pushlets/js-pushlet-client.js"></script>

     <script type="text/javascript">
       	// Initialization
		function init() {
			p_join_listen('/system');
  		}

		// Event Callback: display all events
		function onEvent(event) {
			document.eventDisplay.EVENT.value = event.toString();
		}
	</script>

</head>
<body bgcolor="#cccccc" onLoad="init()">
<h4>Event Monitor</h4>

<form name="eventDisplay">
  <table border="2" bordercolor="white" cellpadding="4" cellspacing="0" >
    <tr>
     <td>
       <textarea cols="32" rows="16" name="EVENT">
        WAITING FOR EVENTS
       </textarea>
     </td>
    </tr>
  </table>
</form>

<script type="text/javascript">p_embed()</script>

</body>
</html>