# heStudio MFA for Desktop

## 介绍
可能在很多人的印象中，MFA应用基本上都是手机或者手表上的比较方便。然而，当手机和手表不在身边时，我们又需要完成一些需要MFA认证的时候，那么这个工具就起到了一些作用。

## 软件版本
我们使用Java对这个程序进行重构，并以Java为主要的更新平台。Java版本和Python版本对于令牌文件的目录要求不一样，你可以使用我们提供的脚本`hmfa_gotojava.py`从Python版本迁移到Java版本。

我们在Java版本中进行优化，你可以使用能运行Java 17及以上版本的任何设备（没有图形界面也可以）运行这个程序。

但是，你无法从Java版本自动迁移到Python版本。

## Java版本
### 环境安装
Java 17及以上版本

### 运行
```
java -jar hmfa.jar
```

### 备份
复制根目录下的`heStudio/.hmfa_token`文件夹到其他地方。

### Secure获取
部分账户不提供Secure，而是一个二维码。可以使用一些解码工具提取Secure

## 从Python版本迁移到Java版本
将`hmfa_gotojava.py`下载到Python版本的程序同一个目录，然后运行。

## Python版本
### 环境安装
本程序为了适配多种操作系统，未对其进行编译，你可以通过运行源码直接运行本程序。
- Python 3.6 及以上版本
- pyotp (可以通过pip安装)
- heframework (可以通过pip安装)

### 运行
#### Linux/MacOS
```
python3 hmfa.py
```
#### Windows
```
python hmfa.py
```

### 备份
直接复制目录下的`.hmfa_token.json`到其他位置即可。

### Secure获取
部分账户不提供Secure，而是一个二维码。可以使用一些解码工具提取Secure

### 使用的第三方模块

#### 快速安装
```bash
pip install pyotp heframework
```

#### 详细信息
- pyotp：
   - 用途：获取令牌
   - 主页：https://github.com/pyotp/pyotp
   - 作者：PyOTP contributors
   - 最低支持版本号：2.8.0
   - License：MIT License
- heframework：
   - 用途：主要显示模块
   - 主页：https://gitee.com/hestudio-framework/main-windows/
   - 作者：heStudio
   - 最低支持版本号：0.5.1
   - License：MIT License