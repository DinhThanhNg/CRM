
$(document).ready(function(){
	$('.btn-xoa').click(function(){
		
		id = $(this).attr('id-project')
		
		This = $(this)
		
		$.ajax({
		  method: "GET",
		  url: "http://localhost:8080/crmap05/api/project?id=" + id,
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