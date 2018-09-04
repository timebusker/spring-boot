//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

var vm = new Vue({
	el:'#rrapp',
	data:{
        user:{},
        menuList:{},
        main:"main.html",
        password:'',
        newPassword:'',
        navTitle:"欢迎页"
	},
	methods: {
		getMenuList: function () {
			//路由
            var router = new Router();
			routerList(router);
			router.start();
		}
	},
	created: function(){
		this.getMenuList();
	}
});

function routerList(router) {
    $("#all_memu").find("a").each(function () {
        var href = $(this).attr("href").toString();
        if (href.indexOf('#' == 0)) {
            router.add(href, function () {
                var url = window.location.hash;
                console.log(url);
                //替换iframe的url
                vm.main = url.replace('#', '');
                //导航菜单展开
                $(".treeview-menu li").removeClass("active");
                $(".sidebar-menu li").removeClass("active");
                $("a[href='" + url + "']").parents("li").addClass("active");
                vm.navTitle = $("a[href='" + url + "']").text();
            });
        }
    });
}