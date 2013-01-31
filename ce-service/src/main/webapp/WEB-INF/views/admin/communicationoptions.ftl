<script type="text/javascript">
$(function(){
	$("#new_communication").bind("click", function(){
		saveCommuncationType();
	});
	
	$.ajax({
		url: "http://" + location.host + "/CommunicationType/getAll",
		type: "POST",
		dataType: "json",
		success: function(d) {
			$.map(d, function(jCommunicationTypeOption){
				addCommunicationTypeListing(jCommunicationTypeOption);
			});
		}
	});
})
</script>
<div class="headline">
	<div class="headline_title">Communication option</div>
</div>
<div id="vseperator"></div>
<div style="height:100%;width:100%;background:#ffffff;">
	<div class="contentwrapper" style="padding:5px;">
		<div class="left_col">
			<label>Communication Type</label>
			<input type="text" value="" id="comType" name="comType"/>
		</div>
		<div class="right_col">
			<label>Communication Description</label>
			<textarea rows="5" cols="10" id="comDesc" name="comDesc"></textarea>
		</div>
		<div id="button_container">
			<a id="new_communication">Save</a>
		</div>
	</div>
</div>
<div id="vseperator"></div>
<div class="category_list">
	<div class="headline">
		<div class="headline_title">Communication Type List</div>
	</div>
	<div id="vseperator"></div>
	<div id="categorylist">
		<div style="margin:10px;">
			<table id="typelist" width="100%" height="100%" cellspadding="0" cellspacing="0">
				<tr><th>Communcation Type</th><th>Communication Type Description</th></tr>
			</table>
		</div>
	</div>
</div>