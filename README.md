# WL-Kits
[![使用 Spigot-API](https://img.shields.io/badge/使用-Spigot%20API-green)](https://hub.spigotmc.org/javadocs/bukkit/)
[![使用 Maven](https://img.shields.io/badge/使用-Maven-blue)](https://hub.spigotmc.org/javadocs/bukkit/)
![当前版本](https://img.shields.io/badge/当前版本-0.0.5%20pre1-orange)
[![使用 IDE](https://img.shields.io/badge/使用%20IDE-JetBrains%20IntelliJ%20IDEA-red)](https://www.jetbrains.com/idea/)
![完成进度](https://img.shields.io/badge/完成进度-25%25-red)

一个使用 **Spigot API** 编写的适用于 **Minecraft 1.16.x `Paper/Spigot`** 的服务端集成小工具插件。

## 目录
- [目录](#目录)
- [功能 (子插件)](#功能-子插件)
- [待办 (TODOS)](#待办-todos)
  - [总体](#总体)
  - [MineBoard](#mineboard)
  - [SimpleHome](#simplehome)

## 功能 (子插件)
- `AntiCreeper`: 可以防止 `Creeper` 爆炸破坏地形, 但又不影响 `mobGriefing` 引起的一系列问题 (可关闭) (使用权限控制)
- `JoinInfo`: 可以在玩家进入、离开服务器时向其他玩家、CONSOLE 发送对应消息
- `BackDeath`: 允许玩家使用 `/backdeath (简写 /backd)` 返回上次死亡的地点
- `PlayerTag`: 允许玩家设置称号 (使用权限控制)
- `SkipNight`: 可以让玩家睡觉直接跳过夜晚 (可关闭) (使用权限控制)
- `Suicide`: 允许玩家自杀
- `SimpleWarp`: 允许玩家设置、传送地标点 (可使用中文名)
- `SimpleTpa`: 允许玩家使用 `/tpa [player]` 互相传送
- `SimpleBack`: 允许玩家返回上一位置
- `MineBoard`: 挖掘榜 (可关闭)
- `SimpleHome`: 允许玩家设置家、回家
- `Disenchant`: 允许玩家可以将物品上的附魔转移到附魔书上
- `WebSocket`: 可以通过连接 WebSocket 服务器来监听服务器中的事件 (如玩家聊天, 玩家加入退出等)

## 待办 (Todos)
### 总体
- ~~修复 `Suicide` 的 Bug~~ **(已修复: v0.0.3)**
- ~~修复 `SimpleTpa` 中 Bug: `tpa 后重复 /tpaccept 会一直将对方 tp 过来`~~ **(已修复: v0.0.3)**
- ~~修复 `MineBoard` 引发的一系列 `NullPointerException` 的 bug~~ **(已修复: v0.0.4)**
- ~~修复 *README.md*、*exampleRecipe.yml* 中的 **typo**: `DIR` -> `DIRT`~~ **(已修复: v0.0.4-pre4)**
- ~~修复 help 帮助的横线太长的问题~~ **(已修复: v0.0.5)**
- ~~修改文件结构: `plugins/WL-Kits/plugin_name/plugin_file` -> `plugins/WL-kits/plugin_file`~~ **(已修改: v0.0.5)**
- ~~将一些多余的空格参数删除~~ **(取消)**
- ~~添加命令: `/wlkits plugins [name] on/off/check`~~ **(已添加: v0.0.5)**
- ~~完善 `/wlkits plugins [name]` 的相关配置问题~~ **(已完善: v0.0.5)**
- ~~完善 `YmlConfig`~~ **(已完善: v0.0.5)**
- ~~去掉 `anticreeper`、`skipnight` 的命令, 直接用 `/wlkits plugins [name] on/off/check 管理`~~ **(已修改: v0.0.5)**
- ~~优化一些代码~~ **(取消)**
- ~~将所有的消息都移植到 `message.yml`~~ **(已迁移: v0.0.5)**
- ~~添加新的功能: `Disenchant`~~ **(已添加: v0.0.5)**
- ~~添加新的功能: `WebSocket`~~ **(已添加: v0.0.5-pre1)**
- ~~添加新的功能: `ColorMOTD`~~ **(已添加: v0.0.5-pre1)**
### MineBoard
- ~~修复两个第一的 bug~~ **(已修复: v0.0.5)**
- ~~修复玩家进入服务器有时候没有计分板, 需要手动破坏一个方块才能显示的问题~~ **(已修复: v0.0.5)**
- ~~修复玩家进入服务器没有其他玩家数据的问题~~ **(已修复: v0.0.5)**
### SimpleHome
- ~~修改存储 bin 为 .yml~~ **(取消)**
- ~~添加 /home 提示~~ **(已添加: v0.0.5)**
