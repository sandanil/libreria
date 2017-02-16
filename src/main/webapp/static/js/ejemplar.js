$(document).ready(function() {	
	$('#btn-borrar').on('click', function(){
		var id = $('#id-borrar').val();
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "ejemplar/"+id,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
		    success: function(result) {
		    	$('tr[data-id="'+id+'"]').remove();
				var numejemplares = parseInt( $('#cantidades-ejemplares').text() );
		    	$('#cantidades-ejemplares').text(numejemplares - 1);
		    	$('#modal-confirmar-borrar').modal('hide');
		    }
		});
	});

	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'ejemplar/'+id;
		
		$.get(url)
			.done(function(ejemplar){
				
				$('#id').val(ejemplar.id);
				$('#fecha_impresion').val(ejemplar.fecha_impresion);
				$('#form-ejemplar .modal-title').text ("Editando....");
				
				$('#modal-ejemplar').modal('show');
			});
	});
	

	$('.btn-confirmar-borrar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'ejemplar/'+id;
				
		$.get(url)
			.done(function(ejemplar){
				
				$('#id-borrar').val(ejemplar.id);
				$('#form-confirmar-borrar .modal-title').text ("borrando....ejemplar");				
				$('#modal-confirmar-borrar').modal('show');
			});
});

$('#modal-ejemplar').on('hide.bs.modal', limpiarModalEditar);

});

var limpiarModalEditar = function(){
    $('#id').val('');
    $('#fecha_impresion').val('');
    
};