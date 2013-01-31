<script type="text/javascript">
	var user;
	$(function(){
		$.ajaxSetup({
			cache: false,
			data: null,
		});
		
		$("#content").load("static/business.html", function(){
			setTimeout(function(){
				$("div").remove("#registersuccess");
				$("div").remove(".signup");
				var availableHeight = $(window).height() - $("#header").height() - $(".bar").height();
				$("#map").css("height", availableHeight + "px");
				//scrollNav.refresh();
			}, 100);
		});
	});
</script>