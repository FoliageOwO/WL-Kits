# WL-Kits
[![使用 Spigot-API](https://img.shields.io/badge/使用-Spigot%20API-green)](https://hub.spigotmc.org/javadocs/bukkit/)
[![使用 Maven](https://img.shields.io/badge/使用-Maven-blue)](https://hub.spigotmc.org/javadocs/bukkit/)
![当前版本](https://img.shields.io/badge/当前版本-0.0.4-orange)
[![使用 IDE](https://img.shields.io/badge/使用%20IDE-JetBrains%20IntelliJ%20IDEA-red)](https://www.jetbrains.com/idea/)
![完成进度](https://img.shields.io/badge/完成进度-12%25-red)

一个使用 **Spigot API** 编写的适用于 **Minecraft 1.16.x `Spigot`** 的服务端集成小工具插件。

## 功能 (子插件)
- `AntiCreeper`: 可以防止 `Creeper` 爆炸破坏地形, 但又不影响 `mobGriefing` 引起的一系列问题 (可关闭) (使用权限控制)
- `JoinInfo`: 可以在玩家进入 / 离开服务器时向其他玩家 / CONSOLE 发送对应消息
- `BackDeath`: 允许玩家使用 `/backdeath (简写 /backd)` 返回上次死亡的地点
- `PlayerTag`: 允许玩家设置称号 (使用权限控制)
- `SkipNight`: 可以让玩家睡觉直接跳过夜晚 (可关闭) (使用权限控制)
- `Suicide`: 允许玩家自杀
- `SimpleWarp`: 允许玩家设置 / 传送地标点 (可使用中文名)
- `SimpleTpa`: 允许玩家使用 `/tpa [player]` 互相传送
- `SimpleBack`: 允许玩家返回上一位置

## 待办 (TODOS)
- ~~修复 `Suicide` 的 Bug~~ 已修复
- 添加新的功能: `CuteLogin`
- ~~修复 `SimpleTpa` 中 Bug: `tpa 后重复 /tpaccept 会一直将对方 tp 过来`~~ 已修复
