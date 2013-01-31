<script type="text/javascript">
	$.ajax({
		url: "http://" + location.host + "/User/Business/getAllBusiness",
		type: "POST",
		dataType: "json",
		success: function(d) {
			console.log(d.JBussiness);
		}
	});
</script>
<div id="businessbody">
	<div id="header" class="header inf_header">Business Information</div>
		<div id="navWrapper">
			<div id="business">
				<#list businesses as business>
					<div class="item">
						<div id="businessname">${business.businessName}</div>
						<div id="businessaddress">${business.businessLongAddress}</div>
					</div>
				</#list>
			</div>
		</div>
	</div>
	
	<div class="map" id="map">
		<div id="header" class="header map_header">Business Location</div>
		<div style="width:100%;height:100%;">
			<div class="mapcanvas" id="mapcanvas" style="width:100%; height:100%"/>
		</div>
	</div>
</div>
