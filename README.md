# WL-Kits
[![使用 Spigot-API](https://img.shields.io/badge/使用-Spigot%20API-green)](https://hub.spigotmc.org/javadocs/bukkit/)
[![使用 Maven](https://img.shields.io/badge/使用-Maven-blue)](https://hub.spigotmc.org/javadocs/bukkit/)
![当前版本](https://img.shields.io/badge/当前版本-0.0.4%20pre3-orange)
[![使用 IDE](https://img.shields.io/badge/使用%20IDE-JetBrains%20IntelliJ%20IDEA-red)](https://www.jetbrains.com/idea/)
![完成进度](https://img.shields.io/badge/完成进度-14%25-red)

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
- `RecipeAdder`: 允许服务器管理员直接通过编辑 yml 文件来加载 / 修改自定义配方 (使用权限控制)
- `MineBoard`: 挖掘榜 (可关闭)

## 待办 (TODOS)
- 添加新的功能: `CuteLogin`
- ~~修复 `Suicide` 的 Bug~~ **已修复**
- ~~修复 `SimpleTpa` 中 Bug: `tpa 后重复 /tpaccept 会一直将对方 tp 过来`~~ **已修复**
- ~~修复 `MineBoard` 引发的一系列 `NullPointerException` 的 bug~~ **已修复**
- 修复 `MineBoard` 会出现两个第一名的问题

## Wiki

### RecipeAdder

注意! 如果格式不规范将可能抛出 `NullPointerException`, 故请严格使用此格式!

#### 有序合成
例子: [exampleRecipe.yml](https://github.com/WindLeaf233/WL-Kits/blob/cab50fbb44d10c1974c6a22de30cd5533a5340dd/src/main/resources/exampleRecipe.yml)

格式:
```yaml
shape: true # 表示是否为有序
to: 'DIAMOND' # 合成出的东西 (英文大写, 如 DIAMOND_SWORD)
amount: 1 # 合成出的数量, 最大 64
recipe:
  - '###' # 每一行用不同的符号表示不同的物品
  - '#@#' # 可以用字母、数字、符号
  - '###' # 甚至可以用文字
format:
  '#': 'DIR' # 解释上面的符号代替的是什么物品
  '@': 'IRON_INGOT' # 也要英文大写
```
#### 无序合成
例子: [exampleRecipeShapeless.yml](https://github.com/WindLeaf233/WL-Kits/blob/cab50fbb44d10c1974c6a22de30cd5533a5340dd/src/main/resources/exampleRecipeShapeless.yml)

格式:
```yaml
  
shape: false # 表示是否为有序
to: 'IRON_INGOT' # 合成出的东西 (英文大写, 如 DIAMOND_SWORD)
amount: 1 # 合成出的数量, 最大 64
requires:
  - 'DIRT' # 将所有需要的东西都写在这
  - 'DIAMOND' # 如果没写全可能会报错
count:
  DIRT: 3 # 表示每个物品需要多少个, 不能超过 9
  DIAMOND: 1 # 加起来 = 4: 背包合成; 加起来 = 9: 工作台合成
```
