set d=%date:~0,10%
set t=%time:~0,8%
set /p var=请输入提交标签：
git add .
git commit -am"%d% %t% ____ %var%"
git push  origin master