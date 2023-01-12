package src;

import java.util.Scanner;

import java.io.*;
import java.security.InvalidKeyException;

public class Getkey {
    public static void main(String[] args) throws IOException, InvalidKeyException {
        File heStudioDir = new File("/", "heStudio");
        boolean RootDirExists = heStudioDir.exists();
        if (RootDirExists) {
            boolean DirExistsIsDir = heStudioDir.isDirectory();
            if (DirExistsIsDir != true) {
                System.err.println("heStudio应该是文件夹, 而不是文件");
                System.exit(-1);
            }
        } else {
            System.out.println("什么都没有！");
        }
        File TokenDir = new File("/heStudio", ".hmfa_token");
        boolean DirExists = TokenDir.exists();
        if (DirExists) {
            boolean DirExistsIsDir = TokenDir.isDirectory();
            if (DirExistsIsDir != true) {
                System.err.println(".hmfa_token应该是文件夹, 而不是文件");
                System.exit(-1);
            }
        } else {
            System.out.println("什么都没有！");
        }
        String[] NamesList = TokenDir.list();
        System.out.println("\n请选择令牌\n");
        for (int count = 0; count < NamesList.length; count++) {
            System.out.println(count + ". " + NamesList[count]);
        }
        System.out.print("\n选择令牌: ");
        Scanner choose = new Scanner(System.in);
        int choose_return = choose.nextInt();
        if (choose_return >= 0) {
            if (choose_return < NamesList.length) {
                String NameReady = NamesList[choose_return];
                File ReadyFile = new File("/heStudio/.hmfa_token", NameReady);
                FileInputStream TokenRead = new FileInputStream(ReadyFile);
                InputStreamReader inputStreamReader = new InputStreamReader(TokenRead);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String securelast = bufferedReader.readLine();
                TokenRead.close();
                // System.out.println("\nSecure为 "+bufferedReader.readLine());
                while (true) {
                    System.out.print(
                            "\r令牌：" + TotpUtils.generateTOTP(securelast) + "\t剩余时间：" + TotpUtils.getRemainingSeconds());
                }

            } else {
                System.err.println("非法的数字！");
                hmfa.main(null);
            }
        } else {
            System.err.println("非法的数字！");
            hmfa.main(null);
        }
        choose.close();
    }
}