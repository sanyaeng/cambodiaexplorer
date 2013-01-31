$(function(){
	$("#newitem").bind("click", function(){
		if($(".toggleable").is(":hidden")){
			$(".vseperator").show();
			$(".toggleable").slideDown("slow",function(){
				//alert("done");
			});
		}
		else {
			$(".toggleable").slideUp("slow",function(){
				$(".vseperator").hide();
			});
		}
		return false;
	});
})

function saveCommuncationType() {
	$.ajax({
		url: "http://" + location.host + "/CommunicationType/new",
		type: "POST",
		dataType: "json",
		data:{
			"type": $("#comType").val(),
			"description": $("#comDesc").val(),
		},
		success: function(d) {
			addCommunicationTypeListing(d);
			$("#comType").val("");
			$("#comDesc").val("");
		}
	});
}

function updateCommunicationType(selectedItem) {
	$.ajax({
		url: "http://" + location.host + "/CommunicationType/update",
		type: "POST",
		dataType: "json",
		data:{
			"type": $("#comType").val(),
			"description": $("#comDesc").val(),
		},
		success: function(d) {
			updateCommunicationTypeListing(d, selectedItem);
			$("#comType").val("");
			$("#comDesc").val("");
		}
	});
}

function findSelectedRow(searchTerm, tableObject) {
	var rows = tableObject.find("tr");
	var selectedRow;
	rows.each(function(){
		 var hiddenVal = $(this).find("td").eq(0).find("input[type=hidden]").val();
		 console.log(hiddenVal + "|" + searchTerm);
		if(hiddenVal == searchTerm) {
			selectedRow = $(this);
		}
	});
	return selectedRow;
}

function updateCommunicationTypeListing(jCommunicationTypeOption, selectedItem) {
	var rows = findSelectedRow(selectedItem, $("#typelist"));
	console.log("found row: " + rows.find("td").eq(0).text());
	rows.find("td").eq(0).text(jCommunicationTypeOption.comType).find("input[type=hidden]").val(jCommunicationTypeOption).attr("id", jCommunicationTypeOption);
	rows.find("td").eq(1).text(jCommunicationTypeOption.comDesc);
	$("#new_communication").unbind("click");
	$("#new_communication").bind("click", function(){
		saveCommuncationType();
	});
	
}
/**
 * Function to update the business listing table after saved
 * @param jBusinessCategory
 */
function addCommunicationTypeListing(jCommunicationTypeOption) {
	console.log("add a row");
	$("#typelist").append("<tr onclick='selectedComTypeItem(this);' onmouseover='updateRowColor(this, \"rowOver\");' onmouseout='updateRowColor(this, \"rowOut\");'><td><input type='hidden' id='" +jCommunicationTypeOption.comType+ "' value='" + jCommunicationTypeOption.comType + "'>"+jCommunicationTypeOption.comType + "</td><td>" + jCommunicationTypeOption.comTypeDesc + "</td></tr>");
}

/**
 * Save the new business category
 */
function saveCategory() {
	$.ajax({
		url: "http://" + location.host + "/BusinessCategory/new",
		type: "POST",
		dataType: "json",
		data:{
			"catKey": $("#catKey").val(),
			"catDesc": $("#catDesc").val(),
		},
		success: function(d) {
			updateCategoryListing(d);
			$("#catKey").val("");
			$("#catDesc").val("");
		}
	});
}

function selectedComTypeItem(selectedRow) {
	$("#comType").val($(selectedRow).find("td").eq(0).text());
	$("#comDesc").val($(selectedRow).find("td").eq(1).text());
	//$("#new_communication").attr("onclick", 'updateCommunicationType($(selectedRow).find("td").eq(0).find("input[type=hidden]").val())');
	$("#new_communication").unbind("click");
	$("#new_communication").bind('click', function() {
		updateCommunicationType($(selectedRow).find("td").eq(0).find("input[type=hidden]").val());
	});
}

function selectedItem(selectedRow) {
	$("#catKey").val($(selectedRow).find("td").eq(0).text());
	$("#catDesc").val($(selectedRow).find("td").eq(1).text());
}
/**
 * update the class of each td once mouse is over
 * @param selectedRow
 * @param classes
 */
function updateRowColor(selectedRow, classes) {
	$(selectedRow).find("td").each(function(){
		$(this).attr("class", classes);
	});
}
/**
 * Function to update the business listing table after saved
 * @param jBusinessCategory
 */
function updateCategoryListing(jBusinessCategory) {
	$("#cateList").append("<tr onclick='selectedItem(this);' onmouseover='updateRowColor(this, \"rowOver\");' onmouseout='updateRowColor(this, \"rowOut\");'><td>"+jBusinessCategory.catKey + "</td><td>" + jBusinessCategory.catDesc + "</td></tr>");
}