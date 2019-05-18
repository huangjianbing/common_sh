#!/bin/bash
#centos mysql一键式安装
#检查是否安装mysql

MYSQL_NMAE=$(rpm -qa |grep -i mysql);
#安装检查
check(){
	if [ -n "$MYSQL_NMAE" ]; then

		echo -e "\e[1;32m 系统已存在Mysql,当前版本为:${MYSQL_NMAe} \e[0m";
		rpm -e --nodeps ${MYSQL_NMAE}
		echo -e "\e[1;32m 删除Mysql成功... \e[0m";

	fi
}
#初始化目录
init(){
	if [ ! -d "/usr/local/mysql/" ];then

		mkdir - p /usr/local/mysql

		echo -e "\e[1;32m 创建/usr/local/mysql成功... \e[0m";
	fi
}
#mysql-5.5.49 下载
downLoad(){
	cd /usr/local/mysql;

	echo -e "\e[1;32m 开始下载Mysql... \e[0m";

    wget http://dev.mysql.com/get/Downloads/MySQL-5.5/MySQL-5.5.49-1.linux2.6.i386.rpm-bundle.tar --no-check-certificate


	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 下载成功... \e[0m";
		tar -xvf MySQL-5.5.49-1.linux2.6.i386.rpm-bundle.tar
	else
		echo -e "\033[37;31;5m下载失败...\033[39;49;0m"
		exit 1;
	fi
}
#安装服务端，客户端 启动Mysql
installProductSoftWare(){
	rpm -ivh MySQL-server-5.5.49-1.linux2.6.i386.rpm
	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 安装服务端成功... \e[0m";

	else
		echo -e "\033[37;31;5m安装服务端失败...\033[39;49;0m"
		exit 1;
	fi
	rpm -ivh MySQL-client-5.5.49-1.linux2.6.i386.rpm
	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 安装客户端成功... \e[0m";

	else
		echo -e "\033[37;31;5m安装客户端失败...\033[39;49;0m"
		exit 1;
	fi
	service mysql start
	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 启动Mysql成功... \e[0m";

	else
		echo -e "\033[37;31;5m启动Mysql失败...\033[39;49;0m"
		exit 1;
	fi
}
#修改密码,放行端口
updateService(){
	/usr/bin/mysqladmin -u root password '123456'
	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 修改密码成功... \e[0m";
	else
		echo -e "\033[37;31;5m修改密码失败...\033[39;49;0m"
		exit 1;
	fi
	#放行3306端口
	iptables -I INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT


	service iptables save;

	service iptables restart
}
#允许远程连接授权
authorize(){
	mysql -uroot -p123456 -e "grant all privileges on *.* to 'root'@'%' identified by '123456' with grant option;"
   #刷新权限
    mysql -uroot -p123456 -e "flush privileges;"
	if [ $? -eq 0 ]; then
		echo -e "\e[1;32m 刷新权限成功... \e[0m";
	else
		echo -e "\033[37;31;5m刷新权限失败...\033[39;49;0m"
		exit 1;
	fi
}
main(){
	check;
	init
	downLoad;
	installProductSoftWare;
	updateService;
	authorize;
	echo "Mysql 安装成功"
}
main;
