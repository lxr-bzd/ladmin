-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.19-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5186
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 ladmin 的数据库结构
CREATE DATABASE IF NOT EXISTS `ladmin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ladmin`;

-- 导出  表 ladmin.t_sys_resource 结构
CREATE TABLE IF NOT EXISTS `t_sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '资源名称',
  `parentid` int(11) DEFAULT NULL COMMENT '上级id',
  `level` int(11) DEFAULT NULL COMMENT '层级（1-系统,2-模块, 3-菜单，4-按钮）',
  `ckey` varchar(50) DEFAULT NULL COMMENT '名称',
  `path` varchar(500) DEFAULT NULL COMMENT '资源路径',
  `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
  `rdesc` varchar(500) DEFAULT NULL COMMENT '资源描述',
  `permission` varchar(100) COMMENT '权限字符串',
  `sort` int(11) COMMENT '排序',
  `status` int(11) DEFAULT NULL COMMENT '是否启用',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `createtime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=523 DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- 正在导出表  ladmin.t_sys_resource 的数据：~17 rows (大约)
DELETE FROM `t_sys_resource`;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
INSERT INTO `t_sys_resource` (`id`, `name`, `parentid`, `level`, `ckey`, `path`, `icon`, `rdesc`, `permission`, `sort`, `status`, `creator`, `createtime`, `updatetime`) VALUES
	(1, '资源根目录', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, '系统管理', 1, 1, NULL, '', 'icon-nav-06.png', '', 'sysManager', 18, 1, NULL, NULL, 20180110161233),
	(20, '权限管理', 19, 2, NULL, '', '', '11111', 'test', 1, 1, NULL, NULL, NULL),
	(40, '角色管理', 20, 3, NULL, 'sys/auth/role.do', '', '', 'SysRole:list', 1, 1, NULL, NULL, NULL),
	(59, '资源管理', 20, 3, NULL, 'sys/auth/res.do', '', '', 'SysResource:list', 2, 1, NULL, NULL, NULL),
	(64, '账号管理', 20, 3, NULL, 'sys/auth/user.do', '', '', 'SysUser:list', 3, 1, NULL, NULL, 20170921112717),
	(419, '开发', 19, 2, NULL, '', '', '', 'development', 2, 1, '3', 20171220153044, NULL),
	(420, '资源', 419, 3, NULL, 'sys/dev/res.do', '', '', 'developmentRes:list', 1, 1, '3', 20171220153215, 20171220154019),
	(421, '资源-添加', 420, 4, NULL, '/', NULL, '资源添加操作', 'developmentRes:add', 1, 1, '3', 20171220153215, NULL),
	(422, '资源-删除', 420, 4, NULL, '/', NULL, '资源删除操作', 'developmentRes:remove', 2, 1, '3', 20171220153215, NULL),
	(423, '资源-编辑', 420, 4, NULL, '/', NULL, '资源编辑操作', 'developmentRes:edit', 3, 1, '3', 20171220153215, NULL),
	(424, '资源-查看', 420, 4, NULL, '/', NULL, '资源查看操作', 'developmentRes:info', 4, 1, '3', 20171220153215, NULL),
	(460, 'druid', 419, 3, NULL, 'druid/index.html', '', '', 'druid', 2, 1, '28', 20180113095856, NULL),
	(518, '个人中心', 1, 1, NULL, '', '', '', 'my', 7, 1, '44', 20180227145841, NULL),
	(519, '主页', 518, 2, NULL, '', '', '', 'my/main', 1, 1, '44', 20180227150011, NULL),
	(520, '首页', 519, 3, NULL, 'my/main/index.do', '', '', 'my/main/index', 1, 1, '44', 20180227150138, NULL),
	(521, '管理员首页', 520, 4, NULL, '', '', '', 'my/main/index?$sysView=admin', 1, 1, '44', 20180227150318, NULL);
/*!40000 ALTER TABLE `t_sys_resource` ENABLE KEYS */;

-- 导出  表 ladmin.t_sys_role 结构
CREATE TABLE IF NOT EXISTS `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '角色名称',
  `rdesc` varchar(500) DEFAULT NULL COMMENT '说明',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '状态(1-启用 2-禁用)',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `createtime` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `updatetime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='角色管理';

-- 正在导出表  ladmin.t_sys_role 的数据：~1 rows (大约)
DELETE FROM `t_sys_role`;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` (`id`, `name`, `rdesc`, `status`, `creator`, `createtime`, `updatetime`) VALUES
	(18, '超级管理员', NULL, 1, NULL, 44, 20180328144613);
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;

-- 导出  表 ladmin.t_sys_role_res 结构
CREATE TABLE IF NOT EXISTS `t_sys_role_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  `resid` int(11) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=18616 DEFAULT CHARSET=utf8 COMMENT='角色_资源关系表';

-- 正在导出表  ladmin.t_sys_role_res 的数据：~17 rows (大约)
DELETE FROM `t_sys_role_res`;
/*!40000 ALTER TABLE `t_sys_role_res` DISABLE KEYS */;
INSERT INTO `t_sys_role_res` (`id`, `roleid`, `resid`) VALUES
	(18571, 18, 1),
	(18572, 18, 518),
	(18573, 18, 519),
	(18574, 18, 520),
	(18575, 18, 521),
	(18576, 18, 19),
	(18577, 18, 20),
	(18578, 18, 40),
	(18584, 18, 59),
	(18589, 18, 64),
	(18595, 18, 419),
	(18596, 18, 420),
	(18597, 18, 421),
	(18598, 18, 422),
	(18599, 18, 423),
	(18600, 18, 424),
	(18601, 18, 460);
/*!40000 ALTER TABLE `t_sys_role_res` ENABLE KEYS */;

-- 导出  表 ladmin.t_sys_user 结构
CREATE TABLE IF NOT EXISTS `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(50) NOT NULL COMMENT '账号',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `islock` int(2) NOT NULL DEFAULT '1' COMMENT '1：未锁定，2：已锁定',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(1启用 2禁用 )',
  `creator` varchar(50) DEFAULT NULL,
  `createtime` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `updatetime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  ladmin.t_sys_user 的数据：~1 rows (大约)
DELETE FROM `t_sys_user`;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` (`id`, `account`, `name`, `password`, `islock`, `status`, `creator`, `createtime`, `updatetime`) VALUES
	(44, 'admin', 'admin', '202cb962ac59075b964b07152d234b70', 1, 0, '', NULL, NULL),
	(47, 'admin3432', '32543', '202cb962ac59075b964b07152d234b70', 1, 0, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;

-- 导出  表 ladmin.t_sys_user_role 结构
CREATE TABLE IF NOT EXISTS `t_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8 COMMENT='人员_角色表';

-- 正在导出表  ladmin.t_sys_user_role 的数据：~2 rows (大约)
DELETE FROM `t_sys_user_role`;
/*!40000 ALTER TABLE `t_sys_user_role` DISABLE KEYS */;
INSERT INTO `t_sys_user_role` (`id`, `uid`, `roleid`) VALUES
	(25, 25, 18),
	(208, 44, 18);
/*!40000 ALTER TABLE `t_sys_user_role` ENABLE KEYS */;

-- 导出  表 ladmin._log 结构
CREATE TABLE IF NOT EXISTS `_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `msg` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `date` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `file` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `line` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  ladmin._log 的数据：~0 rows (大约)
DELETE FROM `_log`;
/*!40000 ALTER TABLE `_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `_log` ENABLE KEYS */;

-- 导出  表 ladmin._pk 结构
CREATE TABLE IF NOT EXISTS `_pk` (
  `pkey` varchar(50) DEFAULT NULL,
  `val` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ladmin._pk 的数据：~13 rows (大约)
DELETE FROM `_pk`;
/*!40000 ALTER TABLE `_pk` DISABLE KEYS */;
INSERT INTO `_pk` (`pkey`, `val`, `time`, `version`) VALUES
	('f', '95', '2017-08-28 17:13:19', 315),
	('plo_tickets', '1', '2017-09-27 14:43:29', 0),
	('plo_ticketsparam', '6', '2017-09-11 11:31:46', 0),
	('plo_info', '1', '2017-09-22 09:52:25', 0),
	('com_pricingstrategy', '4', '2017-09-02 08:50:13', 0),
	('com_pricingmethod', '17', '2017-09-02 08:51:09', 0),
	('com_pricingrate', '10', '2017-09-02 08:55:58', 0),
	('com_monthcard', '1', '2017-09-21 15:21:02', 0),
	('com_monthcardtype', '1', '2017-09-09 08:50:13', 0),
	('com_monthcardorder', '1', '2017-09-21 15:21:02', 0),
	('com_monthcard_carnumber', '1', '2017-09-21 15:21:02', 0),
	('com_section', '1', '2017-09-25 17:38:56', 0),
	('posWater', '495', NULL, 0);
/*!40000 ALTER TABLE `_pk` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
