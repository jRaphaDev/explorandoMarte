
function enviarPosicao() {

	if ($('#posicao').val() === "") {
		$("#retorno").text("O campo posicao deve ser preenchido");
		return false;
	}
	$.get( "rest/sonda/posicao/"+$('#posicao').val(), function( data ) {
		alert( "Posicao iniciada.");
		$("#retorno").text("");
	}).fail(function (data){
		$("#retorno").text(data.responseText);
	});
}

function enviarInstrucao() {
	
	if ($('#instrucao').val() === "") {
		$("#retorno").text("O campo instrucao deve ser preenchido");
		return false;
	}
	
	$.get( "rest/sonda/instrucao/"+$('#instrucao').val(), function( data ) {
		$("#retorno").text(data.responseText);
	}).fail(function (data){
		$("#retorno").text(data.responseText);
	});
}