import sys
import os
import pyotp
import json
import time
import heframework

# 令牌调取模块


def get():
    print("\n选择令牌 - heStudio MFA Tool for Desktop\n")
    if not os.path.exists(".hmfa_token.json"):
        heframework.show(title="heStudio MFA Tool for Desktop",
                         message="没有令牌文件")
    else:
        read_only_file = open(".hmfa_token.json")
        dicts = json.load(read_only_file)
        read_only_file.close()
        if not dicts["name"]:
            heframework.show(title="heStudio MFA Tool for Desktop",
                             message="没有可以调取的令牌！")
        else:
            secure = heframework.choose(
                mode="json", json_file=".hmfa_token.json", info="选择令牌：")
            storage = pyotp.TOTP(str(secure))
            win = heframework.refresh_show(title="令牌", geometry="200x100")
            while True:
                t = 30
                token_now = storage.now()
                for i in range(30):
                    code = win.refresh(str(token_now+"\n剩余时间"+str(t)+"秒"))
                    t -= 1
                    time.sleep(1)
                    if code == 21099:
                        sys.exit()
                    elif code == 20000:
                        pass
                    else:
                        sys.exit()


# 控制台模块


def add():
    print("\n添加令牌 - heStudio MFA Tool for Desktop\n")
    name = input("令牌名称：")
    secure = input("令牌Secure：")
    if not os.path.exists(".hmfa_token.json"):
        file = open(".hmfa_token.json", "w")
        dicts = {"name": [name], "return_text": [secure]}
        file.write(json.dumps(dicts))
        time.sleep(1)
        file.close()
        heframework.show(
            title="heStudio MFA Tool for Desktop", message="添加成功！")
    else:
        read_only_file = open(".hmfa_token.json")
        dicts = json.load(read_only_file)
        read_only_file.close()
        if name in dicts["name"]:
            heframework.show(
                title="heStudio MFA Tool for Desktop", message="此名称已存在！")
        else:
            if secure in dicts["return_text"]:
                heframework.show(
                    title="heStudio MFA Tool for Desktop", message="此Secure已存在！")
            else:
                file_after = open(".hmfa_token.json", "w")
                names = dicts["name"]
                secures = dicts["return_text"]
                names.append(name)
                secures.append(secure)
                dict_after = {"name": names, "return_text": secures}
                file_after.write(json.dumps(dict_after))
                time.sleep(1)
                file_after.close()
                heframework.show(
                    title="heStudio MFA Tool for Desktop", message="添加成功！")


def delete():
    if not os.path.exists(".hmfa_token.json"):
        heframework.show(
                    title="heStudio MFA Tool for Desktop", message="没有令牌文件")
    else:
        read_only_file = open(".hmfa_token.json")
        dicts = json.load(read_only_file)
        read_only_file.close()
        if not dicts["name"]:
            heframework.show(title="heStudio MFA Tool for Desktop",
                            message="没有可以删除的令牌了！")
        else:
            num = 0
            for i in dicts["name"]:
                num += 1
                print(num, ": ", i)
            choose = int(input("请输入你要删除的项目："))
            if choose >= 1 and choose <= num:
                names = dicts["name"]
                secures = dicts["return_text"]
                del names[int(choose-1)]
                del secures[int(choose-1)]
                heframework.show(
                    title="heStudio MFA Tool for Desktop", message="删除成功！")
                file_after = open(".hmfa_token.json", "w")
                dict_after = {"name": names, "return_text": secures}
                file_after.write(json.dumps(dict_after))
                time.sleep(1)
                file_after.close()
            else:
                heframework.show(title="heStudio MFA Tool for Desktop",
                                message="输入的数字错误！")


def console():
    print("\n管理令牌 - heStudio MFA Tool for Desktop\n")
    while True:
        console = heframework.choose(mode="list", info="请选择功能：", name=[
                                     "新增令牌", "删除令牌", "返回上一层"], return_text=["211", "212", "213"])
        if console == "211":
            add()
        elif console == "212":
            delete()
        elif console == "213":
            break

# 关于模块


def about():
    print("""
heStudio MFA Tool for Desktop

仓库：https://gitee.com/hestudio/hmfa
heStudio 博客：https://www.hestudio.org

本程序依赖于以下第三方模块：
    pyotp：
        用途：获取令牌
        主页：https://github.com/pyotp/pyotp
        作者：PyOTP contributors
        最低支持版本号：2.8.0
        License：MIT License
    heframework：
        用途：主要显示模块
        主页：https://gitee.com/hestudio-framework/main-windows/
        作者：heStudio
        最低支持版本号：0.5.1
        License：MIT License
    """)

# 主模块


def main():
    print("\nheStudio MFA Tool for Desktop\n")
    while True:
        home = heframework.choose(mode="list", info="请选择功能：", name=[
                                  "获取令牌", "管理令牌", "关于程序", "退出"], return_text=["201", "202", "203", "204"])
        if home == "201":
            get()
        elif home == "202":
            console()
        elif home == "203":
            about()
        elif home == "204":
            sys.exit()
        else:
            print("输入错误，请重新选择")


if __name__ == "__main__":
    main()
