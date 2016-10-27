    CREATE TABLE `jxc_kind` (
      `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
      `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类别名称',
      `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
      `create_date` datetime NOT NULL COMMENT '创建时间',
      `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
      `update_date` datetime NOT NULL COMMENT '更新时间',
      `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
      `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='进销存类别表';


树装SQL

    CREATE TABLE `jxc_incomekind` (
      `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
      `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '收入类别',
      `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
      `create_date` datetime NOT NULL COMMENT '创建时间',
      `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
      `update_date` datetime NOT NULL COMMENT '更新时间',
      `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
      `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
      `parent_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '父级编号',
      `parent_ids` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT 'VARCHAR(2000)',
      `sort` decimal(10,0) NOT NULL COMMENT '排序',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='收入类别表';
