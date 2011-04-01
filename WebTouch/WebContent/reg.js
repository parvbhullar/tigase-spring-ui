$(document).ready(function() {
	var validator = $("#signupform").validate({
		rules: {
			username: "required"
			
		},
		messages: {
			username: "请填写用户名"
			
		},
		// the errorPlacement has to take the table layout into account
		errorPlacement: function(error, element) {
			console.info("36")
			if ( element.is(":radio") )
				error.appendTo( element.parent().next().next() );
			else if ( element.is(":checkbox") )
				error.appendTo ( element.next() );
			else
			{
				//$"<span class='form-tip tip-error'>"+error.label+"</br></span>".appendTo( element.next() );
				
				element.next("").append("<span class='form-tip tip-error'><br></span>")
				error.appendTo( element.next() );
			}
		},
		// specifying a submitHandler prevents the default submit, good for the demo
		submitHandler: function() {
			alert("submitted!");
		},
		// set this class to error-labels to indicate valid fields
		success: function(label) {
			// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("checked");
		}
	});
})