<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 复文本控件 -->
<script language="Javascript" src="/mice/htmlBox/htmlbox.min.js" type="text/javascript"></script>
<textarea id='hb'></textarea>
<script language="Javascript" type="text/javascript">
$("#hb").css("width",220).css("height",120).htmlbox()
   .separator("dots").button("cut").button("copy").button("paste")
   .separator("dots").button("bold").button("italic").button("underline")
   .separator("dots",2).button("undo",2).button("redo",2)
   .separator("dots",2).button("left",2).button("center",2).button("right",2).button("justify",2)
   .init();
</script>
