#!/bin/bash
#备份数据库
	USER=root
    PASSWORD=123456
    DATABASE1=backup
    BACKUP_DIR=/data/backup/database/  #备份数据库文件的路径
    LOGFILE=/data/backup/database/data_backup.log    #备份数据库脚本的日志文件
    DATE=`date +%Y%m%d-%H%M -d -3minute`     #获取当前系统时间-3分钟
    DUMPFILE1=$DATE-zblog.sql                #需要备份的数据库名称
    ARCHIVE1=$DUMPFILE1-tar.gz                #备份的数据库压缩后的名称

    if [ ! -d $BACKUP_DIR ];                 #判断备份路径是否存在，若不存在则创建该路径
    then
    mkdir -p "$BACKUP_DIR"
    fi

    echo -e "\n" >> $LOGFILE
    echo "------------------------------------" >> $LOGFILE
    echo "BACKUP DATE:$DATE">> $LOGFILE
    echo "------------------------------------" >> $LOGFILE

    cd $BACKUP_DIR                           #跳到备份路径下
    mysqldump -h127.0.0.1  -u$USER -p$PASSWORD $DATABASE1 > $DUMPFILE1    #使用mysqldump备份数据库
    if [[ $? == 0 ]]; then
    tar czvf $ARCHIVE1 $DUMPFILE1 >> $LOGFILE 2>&1                               #判断是否备份成功，若备份成功，则压缩备份数据库，否则将错误日志写入日志文件中去。
    echo "$ARCHIVE1 BACKUP SUCCESSFUL!" >> $LOGFILE
    rm -f $DUMPFILE1
    else
    echo “$ARCHIVE1 Backup Fail!” >> $LOGFILE
    fi

