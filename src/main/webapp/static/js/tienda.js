$(document).ready(function() {	
	$('#btn-borrar').on('click', function(){
		var id = $('#id-borrar').val();
		var csrf = $('#csrf').val();
		
		$.ajax({
			url : "tienda/"+id,
			type: 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
		    success: function(result) {
		    	$('tr[data-id="'+id+'"]').remove();
				var numtiendas = parseInt( $('#cantidades-tiendas').text() );
		    	$('#cantidades-tiendas').text(numtienda - 1);
		    	$('#modal-confirmar-borrar').modal('hide');
		    }
		});
	});

	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'tienda/'+id;
		
		$.get(url)
			.done(function(tienda){
				
				$('#id').val(tienda.id);
				$('#direccion').val(tienda.direccion);
				$('#cuidad').val(tienda.cuidad);
				$('#form-tienda .modal-title').text ("Editando....");
				
				$('#modal-tienda').modal('show');
			});
	});
	

	$('.btn-confirmar-borrar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'tienda/'+id;
				
		$.get(url)
			.done(function(autor){
				
				$('#id-borrar').val(autor.id);
				$('#form-confirmar-borrar .modal-title').text ("borrando....tienda");				
				$('#modal-confirmar-borrar').modal('show');
			});
});

$('#modal-tienda').on('hide.bs.modal', limpiarModalEditar);

});

var limpiarModalEditar = function(){
    $('#id').val('');
    $('#direccion').val('');
    $('#cuidad').val('');
    
};