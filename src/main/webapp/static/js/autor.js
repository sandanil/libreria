$(document).ready(function() {	
	$('#btn-borrar').on('click', function(){
		var id = $('#id-borrar').val();
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "autor/"+id,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
		    success: function(result) {
		    	$('tr[data-id="'+id+'"]').remove();
				var numautores = parseInt( $('#cantidades-autores').text() );
		    	$('#cantidades-autores').text(numautores - 1);
		    	$('#modal-confirmar-borrar').modal('hide');
		    }
		});
	});

	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'autor/'+id;
		
		$.get(url)
			.done(function(autor){
				
				$('#id').val(autor.id);
				$('#nombre').val(autor.nombre);
				$('#nacionalidad').val(autor.nacionalidad);
				$('#form-autor .modal-title').text ("Editando....");
				
				$('#modal-autor').modal('show');
			});
	});
	

	$('.btn-confirmar-borrar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'autor/'+id;
				
		$.get(url)
			.done(function(autor){
				
				$('#id-borrar').val(autor.id);
				$('#form-confirmar-borrar .modal-title').text ("borrando....autor");				
				$('#modal-confirmar-borrar').modal('show');
			});
});

$('#modal-autor').on('hide.bs.modal', limpiarModalEditar);

});

var limpiarModalEditar = function(){
    $('#id').val('');
    $('#nombre').val('');
    $('#nacionalidad').val('');
    
};