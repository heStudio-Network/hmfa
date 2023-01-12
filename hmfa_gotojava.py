import json
import os
import time

print("heStudio MFA for Desktop Python Version to Java Version")
print("正在扫描令牌...")
if not os.path.exists(".hmfa_token.json"):
    print("\rNo file!")
else:
    read_only_file = open(".hmfa_token.json")
    dicts = json.load(read_only_file)
    read_only_file.close()
    if not os.path.exists("/heStudio/.hmfa_token/"):
        os.makedirs("/heStudio/.hmfa_token/")
    if not dicts["name"]:
        print("\rNo Token!")
    else:
        print("\r正在迁移...")
        count = 0
        secure = dicts["return_text"]
        for i in dicts["name"]:
            print("\r正在迁移第", int(count+1), "个：", i)
            file = open(str("/heStudio/.hmfa_token/"+i),"w")
            file.write(secure[count])
            time.sleep(1)
            file.close()
            count+=1
        print("\r迁移完成！")