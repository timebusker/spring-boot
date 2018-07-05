/*!
 * 页面目录生成器.
 * 
比如，我们有如下代码：

<article class="abc">
	<h1>OAuth 1.0 的认证流程</h1>
	<h2>OAuth 2.0 的认证流程</h2>
	<h3>OAuth 3.0 的认证流程</h3>
</article>
<div id="catalog"></div>

要生成目录，按如下方式初始化：
$.catalog("#catalog", ".abc");

 * @since: 1.0.0 2017-03-26
 * @author Way Lau <https://waylau.com>
 */
(function($) {
	
	"use strict";
 
	$.catalog = function(selector, target) {
		
		$(target + " :header").each(function(i,item){
	        var tag = $(item).get(0).localName;
	        $(item).attr("id","wow"+i);
	        $(selector).append('<a class="new'+tag+'" href="#wow'+i+'">'+$(this).text()+'</a></br>');
	        $(".newh1").css("margin-left",0);
	        $(".newh2").css("margin-left",5);
	        $(".newh3").css("margin-left",10);
	        $(".newh4").css("margin-left",15);
	        $(".newh5").css("margin-left",20);
	        $(".newh6").css("margin-left",25);
	      });
	};

})(jQuery);