package src;

import java.util.Scanner;

import java.io.*;
import java.security.InvalidKeyException;

public class Console {
    static void Add() throws IOException, InvalidKeyException {
        File heStudioDir = new File("/", "heStudio");
        boolean RootDirExists = heStudioDir.exists();
        if (RootDirExists) {
            boolean DirExistsIsDir = heStudioDir.isDirectory();
            if (DirExistsIsDir != true) {
                System.err.println("heStudio应该是文件夹, 而不是文件");
                System.exit(-1);
            }
        } else {
            heStudioDir.mkdir();
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
            TokenDir.mkdir();
        }
        System.out.print("请输入令牌名称: ");
        Scanner name = new Scanner(System.in);
        String TokenName = name.nextLine();
        System.out.print("请输入令牌Secure: ");
        Scanner secure = new Scanner(System.in);
        String TokenSecure = secure.nextLine();
        File NameFile = new File("/heStudio/.hmfa_token", TokenName);
        if (NameFile.exists()) {
            System.err.println("该名称已经存在！");
            Console.main(null);
        } else {
            NameFile.createNewFile();
            FileWriter SecureWrite = new FileWriter(NameFile);
            SecureWrite.append(TokenSecure);
            SecureWrite.close();
        }
        System.out.println("添加成功！");
        Console.main(null);
        name.close();
        secure.close();
    }

    static void Delete() throws IOException, InvalidKeyException {
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
        System.out.println("\n请选择要删除的令牌\n");
        for (int count = 0; count < NamesList.length; count++) {
            System.out.println(count + ". " + NamesList[count]);
        }
        System.out.print("\n删除哪一个: ");
        Scanner choose = new Scanner(System.in);
        int choose_return = choose.nextInt();
        if (choose_return >= 0) {
            if (choose_return < NamesList.length) {
                String NameDelete = NamesList[choose_return];
                File DeleteFile = new File("/heStudio/.hmfa_token", NameDelete);
                DeleteFile.delete();
                System.out.println("令牌 " + NameDelete + " 已删除");
                Console.main(null);
            } else {
                System.err.println("非法的数字！");
                Console.main(null);
            }
        } else {
            System.err.println("非法的数字！");
            Console.main(null);
        }
        choose.close();
    }

    public static void main(String[] args) throws IOException, InvalidKeyException {
        System.out.println("\n管理令牌 - heStudio MFA Tool for Desktop\n");
        System.out.println("\n1. 新增令牌\n2. 删除令牌\n3. 返回上一层\n");
        System.out.print("选择操作：");
        Scanner choose = new Scanner(System.in);
        int choose_return = choose.nextInt();
        if (choose_return == 1) {
            Console.Add();
        } else if (choose_return == 2) {
            Console.Delete();
        } else if (choose_return == 3) {
            hmfa.main(null);
        } else {
            System.err.println("输入错误！");
            Console.main(null);
        }
        choose.close();
    }
}
