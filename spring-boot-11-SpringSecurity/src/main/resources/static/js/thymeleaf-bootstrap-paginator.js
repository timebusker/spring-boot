/*!
 * 分页处理.
 * 
 * @since: 1.0.0
 * @author Way Lau <https://waylau.com>
 */
(function($) {
	
	"use strict";
	
	/**
	 * handler:pageIndex 所选页面的索引，从0开始；pageSize 页面的大小，这里默认是10。
	 */
	$.tbpage = function(selector, handler) {
		
		$(selector).off("click", ".tbpage-item").on("click", ".tbpage-item", function() {
		 
			var pageIndex = $(this).attr("pageIndex");
 
			var pageSize = $('.tbpage-size option:selected').val();
			// 判断所选元素是否为当前页面
			// 若不是当前页面才需要处理
			if($(this).parent().attr("class").indexOf("active")>0){ 
				//console.log("为当前页面");
			}else{
				handler(pageIndex, pageSize);
			}
 
		});
		
		
		$(selector).off("change", ".tbpage-size").on("change", ".tbpage-size", function() {
			 
			var pageIndex = $(this).attr("pageIndex");
 
			var pageSize = $('.tbpage-size option:selected').val();
 
			handler(pageIndex, pageSize);
		});
	};

})(jQuery);