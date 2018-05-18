color 0a
@echo off & setlocal
set var=":sun_with_face::sunflower::palm_tree::house_with_garden::office::octocat::guitar::meat_on_bone:"
set d=%date:~0,10%
set t=%time:~0,8%
git add .
git commit -am"%d% %t% ____ %var%"
git push  origin master

:: tree _posts
:: ECHO ================ MENU ===============
:: ECHO    
:: ECHO    D:.                           .....  
:: ECHO    ├─centOS                      .....  1
:: ECHO    ├─Git                         .....  2
:: ECHO    ├─hadoop                      .....  3
:: ECHO    ├─java-book-Effective-Java    .....  4
:: ECHO    ├─java-book-Thinking-In-Java  .....  5
:: ECHO    ├─java-coding                 .....  6
:: ECHO    ├─mysql                       .....  7
:: ECHO    ├─nginx                       .....  8
:: ECHO    ├─oracle                      .....  9
:: ECHO    ├─Python                      .....  10
:: ECHO    ├─scala                       .....  11
:: ECHO    ├─spring-boot                 .....  12
:: ECHO    └─spring-security             .....  13
:: ECHO    
:: ECHO ==========PRESS '0' TO QUIT==========
:: set /p input=请输入提交标签目录编号：
:: 
:: set var = ;
:: if /i '%input%'=='0' (goto end)
:: if /i '%input%'=='1' ( set var="centOS")
:: if /i '%input%'=='2' ( set var="Git")
:: if /i '%input%'=='3' ( set var="hadoop")
:: if /i '%input%'=='4' ( set var="java-book-Effective-Java")
:: if /i '%input%'=='5' ( set var="java-book-Thinking-In-Java")
:: if /i '%input%'=='6' ( set var="java-coding")
:: if /i '%input%'=='7' ( set var="mysql")
:: if /i '%input%'=='8' ( set var="nginx")
:: if /i '%input%'=='9' ( set var="oracle")
:: if /i '%input%'=='10'( set var="Python")
:: if /i '%input%'=='11'( set var="scala")
:: if /i '%input%'=='12'( set var="spring-boot")
:: if /i '%input%'=='13'( set var="spring-security")
