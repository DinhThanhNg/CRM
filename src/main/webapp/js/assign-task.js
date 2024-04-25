$(document).ready(function(){
	$('.btn-xoa').click(function(){
		idUser = $(this).attr('id-user')
		idTask = $(this).attr('id-task')
		idStatus = $(this).attr('id-status')
		This = $(this) // 
		
		$.ajax({
		  method: "GET",
		  url: "http://localhost:8080/crmap05/api/assignTask?idUser=" + idUser + "&idTask=" + idTask + "&idStatus=" +idStatus,
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