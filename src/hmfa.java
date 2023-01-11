package src;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Scanner;

public class hmfa {
    // 关于
    static void About() throws IOException, InvalidKeyException {
        System.out.println("\nheStudio MFA Tool for Desktop\n");
        System.out.println("仓库：https://gitee.com/hestudio/hmfa");
        System.out.println("heStudio 博客：https://www.hestudio.org\n");
        hmfa.main(null);
    }

    // 主要函数
    public static void main(String[] aStrings) throws IOException, InvalidKeyException {
        System.out.println("\nheStudio MFA Desktop\n");
        System.out.println("1. 获取令牌\n2. 管理令牌\n3. 关于\n4. 退出\n");
        System.out.print("选择操作：");
        Scanner choose = new Scanner(System.in);
        int choose_return = choose.nextInt();
        if (choose_return == 1) {
            Getkey.main(null);
        } else if (choose_return == 2) {
            Console.main(null);
        } else if (choose_return == 3) {
            hmfa.About();
        } else if (choose_return == 4) {
            System.exit(0);
        } else {
            System.err.println("输入错误！");
            hmfa.main(null);
        }
        choose.close();
    }
}