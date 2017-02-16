$(document).ready(function() {	
	$('#btn-borrar').on('click', function(){
		var id = $('#id-borrar').val();
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "libro/"+id,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
		    success: function(result) {
		    	$('tr[data-id="'+id+'"]').remove();
				var numlibros = parseInt( $('#cantidades-libros').text() );
		    	$('#cantidades-libros').text(numlibros - 1);
		    	$('#modal-confirmar-borrar').modal('hide');
		    }
		});
	});

	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'libro/'+id;
		
		$.get(url)
			.done(function(libro){
				
				$('#id').val(libro.id);
				$('#nombre').val(libro.nombre);
				$('#categoria').val(libro.categoria);
				$('#autor').val(libro.autor);
				$('#form-libro .modal-title').text ("Editando....");
				
				$('#modal-libro').modal('show');
			});
	});
	

	$('.btn-confirmar-borrar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'libro/'+id;
				
		$.get(url)
			.done(function(libro){
				
				$('#id-borrar').val(libro.id);
				$('#form-confirmar-borrar .modal-title').text ("borrando....libro");				
				$('#modal-confirmar-borrar').modal('show');
			});
});

$('#modal-libro').on('hide.bs.modal', limpiarModalEditar);

});

var limpiarModalEditar = function(){
    $('#id').val('');
    $('#nombre').val('');
    $('#categoria').val('');
    $('#autor').val('');
    
};