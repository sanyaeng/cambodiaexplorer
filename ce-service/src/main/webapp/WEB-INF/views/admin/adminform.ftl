<script type="text/javascript">
	$(function(){
		$.ajax({
			url: "http://" + location.host + "/BusinessCategory/getAllBusinesses",
			type: "POST",
			dataType: "json",
			success: function(d) {
				$.map(d, function(jBusinessCategory){
					updateCategoryListing(jBusinessCategory);
				});
			}
		});
	});
</script>
	
<div class="headline">
	<div class="headline_title">
		Bussiness Categories
	</div>
	<div class="newitem_button">
		<a id="newitem" href="">New Category</a>
	</div>
</div>
<div id="vseperator" class="vseperator"></div>
<div class="new_category toggleable">
	<div class="left_col">
		<label>Category Key</label>
		<input type="text" value="" id="catKey" name="catKey"/>
	</div>
	<div class="right_col">
		<label>Category Description</label>
		<textarea rows="5" cols="10" id="catDesc" name="catDesc"></textarea>
	</div>
	<div id="button_container">
		<a id="new_category" onclick="saveCategory();return false;">Save</a>
	</div>
</div>
<div id="vseperator"></div>
<div class="category_list">
	<div class="headline">
		<div class="headline_title">category list</div>
	</div>
	<div id="categorylist">
		<div style="margin:10px;">
			<table id="cateList" width="100%" height="100%" cellspadding="0" cellspacing="0">
				<tr><th>Category Key</th><th>Category Description</th></tr>
			</table>
		</div>
	</div>
</div>