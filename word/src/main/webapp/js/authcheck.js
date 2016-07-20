/**
 * 
 */

$.ajax({
	url: "../auth/log.do", 
	async: false,
	dataType: 'json',
	success: function(result) {
		if (result.status != "success") {
		  alert('로그인이 필요합니다.');
		  location.href = "../main/main.html";		  
		}
	}
});
