set d=%date:~0,10%
set t=%time:~0,8%
git add .
git commit -am"%d% %t% _________"
git push  origin master