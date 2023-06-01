#  云享--云笔记系统

## 介绍
 云享--云笔记系统

## 软件架构
前后端分离项目


## 运行环境

前端运行环境

> Yarn 1.22.17、Node.js v14.16.1、Vue-cli 2.9.6

后端运行环境

> Java11， MySQL8.0，IPFS 0.12.2， go 1.18.1
>
> （开发阶段采用内网穿透进行对接，IPFS暂时只在本地搭建节点，未连通其他网络节点，如需测试，需要在本地搭建节点。）

## 配置说明

前端

> 在vue.config.js中修改proxy中的target路径

后端

> 在application.yaml中修改MySQL数据库路径和账号密码
>
> 在src/main/java/com/cyj/utils/ipfs/IPFSUtils.java中修改IPF的服务器地址和端口
>
> 在src/main/java/com/cyj/utils/constants/AddressConstants.java修改相关地址配置：发下地址前缀，IPFS请求地址，分享社交平台的默认图片

## 运行说明

前端

Project setup

```
yarn install
```

Compiles and hot-reloads for development

```
yarn serve
```

Compiles and minifies for production

```
yarn build
```

Lints and fixes files	

```
yarn lint
```

后端

配置Mysql数据库，IPFS环境，修改配置说明中相关路径后

启动IPFS环境

```
ipfs daemon
```

将项目导入至IDEA点击运行

## 效果图

（见项目说明文档）


