$(document).ready(function() {
	refreshTableData();
});

function refreshTableData() {
	$.get("/reserve/all", function(data, status) {
		if (status == 'success') {
			loadTable('data-table', [ 'id', 'reservationName' ], data);
		} else {
			console.log("Error calling all reservation service");
		}
	});
	return true;
}

function loadTable(tableId, fields, data) {
	// $('#' + tableId).empty(); //not really necessary
	var rows = '<tr>';
	$.each(fields, function(index, field) {
		rows += '<th>' + field + '</th>';
	});
	rows += '</tr>';
	$.each(data, function(index, item) {
		var row = '<tr>';
		$.each(fields, function(index, field) {
			row += '<td>' + item[field + ''] + '</td>';
		});
		rows += row + '</tr>';
	});
	$('#' + tableId).html(rows);
}

function addReservation() {
	var nameVal = $('#nameBox').val();
	if(!nameVal){
		alert('Please enter name');
		return false;
	}
	var dataToPost = {'name': nameVal};
	$.ajax({
		  type: "POST",
		  contentType: "application/json; charset=utf-8", //If proper conenttype is not set in header, the 415 error happens
		  url: "/reserve/save",
		  dataType: "json",
		  data: JSON.stringify(dataToPost), //Data should be stringified, else you will get 400 bad exception
		  success: refreshTableData
		});
}