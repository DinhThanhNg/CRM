$(document).ready(function(){
	$('.btn-xoa').click(function(){
		id = $(this).attr('id-task')
		This = $(this)
		$.ajax({
		  method: "GET",
		  url: "http://localhost:8080/crmap05/api/task?id=" + idTask,
		})
		  .done(function( result ) {
		    if(result.data){
				This.closest('tr').remove()
			}else{
				alert(result.message)
			}
		});
	})
})