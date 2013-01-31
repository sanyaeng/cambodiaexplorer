<div id="businessbody">
	<div id="header" class="header inf_header">Business Information</div>
		<div id="navWrapper">
			<div id="business">
				<form method="POST" action="<@spring.url '/User/Business/addnewbusiness.html'/>">
					<div class="itemwrapper">
						Business Category<br/>
							<select id="businesstype" name="businesstype" style="height:28px;width:291px;">
								<option value="-1">Select business type</option>
								<#list BusinessCat as type>
									<option value="${type.catKey}">${type.catDesc}</option>
								</#list>
							</select>
					</div>
					<div class="itemwrapper">
						Latitude<br/>
						<input type="text" id="lat" class="lat" value="" name="lat"/>
					</div>
					<div class="itemwrapper">
						Lontitude<br/>
						<input type="text" id="lon" class="lon" value="" name="lon"/>
					</div>
					<div class="itemwrapper">
						Business Name<br/>
						<input type="text" id="businessname" name="business" value=""/>
					</div>
					<div class="itemwrapper_text">
						Business Description<br/>
						<textarea id="businessDescription" name="businessDescription" rows="11" cols="35"></textarea>
					</div>
					<div class="itemwrapper">
						Business Address<br/>
						<input type="text" name="businessaddress" id="businessaddress" class="businessaddress"/>
					</div>
					
					<div class="itemwrapper">
						<input type="submit" class="savebutton" value="Save">
					</div>
				</form>
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
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBeOMafbyRYXO7V1oFTQ_4El2IDG3hBjho&sensor=true"></script>