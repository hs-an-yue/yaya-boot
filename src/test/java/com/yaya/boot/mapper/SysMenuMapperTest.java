package com.yaya.boot.mapper;

import cn.hutool.json.JSONUtil;
import com.yaya.boot.YayaBootApplicationTests;
import com.yaya.boot.entity.SysMenu;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.List;

class SysMenuMapperTest extends YayaBootApplicationTests {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Test
    void getSysMenuTree() {
        sysMenuMapper.getSysMenuTree(null, null).forEach(System.out::println);
    }

    @Test
    void getAuthTreeSysMenuByRoleId(){
        sysMenuMapper.getAuthSysMenuByRoleId("1").forEach(System.out::println);
    }

    @Test
    void add(){

        String json="[\n" +
                "    {\n" +
                "      \"menuId\": '1',\n" +
                "      \"menuTitle\": \"工作台\",\n" +
                "      \"menuIcon\": \"layui-icon layui-icon-bot\",\n" +
                "      \"menuType\": '2',\n" +
                "      \"perms\": null,\n" +
                "      \"menuUrl\": \"views/workbench.html\",\n" +
                "      \"menuLevel\": 1,\n" +
                "      \"parentId\": '0',\n" +
                "      \"orderNum\": 1,\n" +
                "      \"createById\": '1',\n" +
                "      \"updateById\": '1',\n" +
                "      \"children\": []\n" +
                "    },\n" +
                "    {\n" +
                "      \"menuId\": '2',\n" +
                "      \"menuTitle\": \"系统配置\",\n" +
                "      \"menuIcon\": \"layui-icon layui-icon-set\",\n" +
                "      \"menuType\": 1,\n" +
                "      \"perms\": null,\n" +
                "      \"menuUrl\": \"\",\n" +
                "      \"menuLevel\": 1,\n" +
                "      \"parentId\": '0',\n" +
                "      \"orderNum\": 2,\n" +
                "      \"createById\": '1',\n" +
                "      \"updateById\": '1',\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"menuId\": '3',\n" +
                "          \"menuTitle\": \"用户管理\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-friends\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/user-list.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '2',\n" +
                "          \"orderNum\": 1,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        },\n" +
                "        {\n" +
                "          \"menuId\": '4',\n" +
                "          \"menuTitle\": \"部门管理\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-component\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/dept-list.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '2',\n" +
                "          \"orderNum\": 2,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"menuId\": '5',\n" +
                "      \"menuTitle\": \"日志监控\",\n" +
                "      \"menuIcon\": \"layui-icon layui-icon-engine\",\n" +
                "      \"menuType\": 1,\n" +
                "      \"perms\": null,\n" +
                "      \"menuUrl\": \"\",\n" +
                "      \"menuLevel\": 1,\n" +
                "      \"parentId\": '0',\n" +
                "      \"orderNum\": 3,\n" +
                "      \"createById\": '1',\n" +
                "      \"updateById\": '1',\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"menuId\": '6',\n" +
                "          \"menuTitle\": \"登陆日志\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-form\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/login-log.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '5',\n" +
                "          \"orderNum\": 1,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        },\n" +
                "        {\n" +
                "          \"menuId\": '7',\n" +
                "          \"menuTitle\": \"系统日志\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-table\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/system-log.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '5',\n" +
                "          \"orderNum\": 2,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"menuId\": '8',\n" +
                "      \"menuTitle\": \"多级菜单\",\n" +
                "      \"menuIcon\": \"layui-icon layui-icon-layer\",\n" +
                "      \"menuType\": 1,\n" +
                "      \"perms\": null,\n" +
                "      \"menuUrl\": \"\",\n" +
                "      \"menuLevel\": 1,\n" +
                "      \"parentId\": '0',\n" +
                "      \"orderNum\": 4,\n" +
                "      \"createById\": '1',\n" +
                "      \"updateById\": '1',\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"menuId\": '9',\n" +
                "          \"menuTitle\": \"二级菜单-1\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-form\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/test1.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '8',\n" +
                "          \"orderNum\": 1,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        },\n" +
                "        {\n" +
                "          \"menuId\": '10',\n" +
                "          \"menuTitle\": \"二级菜单-2\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-list\",\n" +
                "          \"menuType\": 1,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '8',\n" +
                "          \"orderNum\": 2,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": [\n" +
                "            {\n" +
                "              \"menuId\": '11',\n" +
                "              \"menuTitle\": \"二级菜单-2-1\",\n" +
                "              \"menuIcon\": \"layui-icon layui-icon-menu-fill\",\n" +
                "              \"menuType\": 2,\n" +
                "              \"perms\": null,\n" +
                "              \"menuUrl\": \"views/test2.html\",\n" +
                "              \"menuLevel\": 1,\n" +
                "              \"parentId\": '10',\n" +
                "              \"orderNum\": 1,\n" +
                "              \"createById\": '1',\n" +
                "              \"updateById\": '1',\n" +
                "              \"children\": []\n" +
                "            },\n" +
                "            {\n" +
                "              \"menuId\": '12',\n" +
                "              \"menuTitle\": \"二级菜单-2-2\",\n" +
                "              \"menuIcon\": \"layui-icon layui-icon-reply-fill\",\n" +
                "              \"menuType\": 2,\n" +
                "              \"perms\": null,\n" +
                "              \"menuUrl\": \"views/test3.html\",\n" +
                "              \"menuLevel\": 1,\n" +
                "              \"parentId\": '10',\n" +
                "              \"orderNum\": 2,\n" +
                "              \"createById\": '1',\n" +
                "              \"updateById\": '1',\n" +
                "              \"children\": []\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"menuId\": '13',\n" +
                "          \"menuTitle\": \"二级菜单-3\",\n" +
                "          \"menuIcon\": \"layui-icon layui-icon-form\",\n" +
                "          \"menuType\": 2,\n" +
                "          \"perms\": null,\n" +
                "          \"menuUrl\": \"views/test4.html\",\n" +
                "          \"menuLevel\": 1,\n" +
                "          \"parentId\": '8',\n" +
                "          \"orderNum\": 3,\n" +
                "          \"createById\": '1',\n" +
                "          \"updateById\": '1',\n" +
                "          \"children\": []\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"menuId\": '14',\n" +
                "      \"menuTitle\": \"关于\",\n" +
                "      \"menuIcon\": \"layui-icon layui-icon-about\",\n" +
                "      \"menuType\": 2,\n" +
                "      \"perms\": null,\n" +
                "      \"menuUrl\": \"views/about.html\",\n" +
                "      \"menuLevel\": 1,\n" +
                "      \"parentId\": '0',\n" +
                "      \"orderNum\": 5,\n" +
                "      \"createById\": '1',\n" +
                "      \"updateById\": '1',\n" +
                "      \"children\": []\n" +
                "    }\n" +
                "  ]";
        List<SysMenu> list = JSONUtil.toList(json, SysMenu.class);
        list.forEach(x->{

            sysMenuMapper.insert(x);

            List<SysMenu> children = x.getChildren();
            children.forEach(x2->{
                sysMenuMapper.insert(x2);
                List<SysMenu> children2 = x2.getChildren();
                children2.forEach(x3->{
                    sysMenuMapper.insert(x3);
                });
            });

        });

    }
}