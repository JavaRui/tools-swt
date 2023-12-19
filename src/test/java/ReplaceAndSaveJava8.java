import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ReplaceAndSaveJava8 {
    public static void main(String[] args) {
        // 指定文件夹路径
        String folderPath = "E:\\wu-kafka-client";

        // 替换字符串
        String searchString = "bluemoon";
        String replacementString = "wujin";

        try {
            // 获取文件夹下所有的文件
            Files.walk(new File(folderPath).toPath())
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        try {
                            // 读取文件内容
                            String content = new String(Files.readAllBytes(path));

                            // 替换字符串
                            content = content.replace(searchString, replacementString);

                            // 将修改后的内容写回文件
                            Files.write(path, content.getBytes());

                            System.out.println("文件 " + path.getFileName() + " 中的字符串已替换并保存。");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            // 或者，如果你想将原文件夹改名
//             renameFolder(folderPath, "newFolderName");
             System.out.println("文件夹已重命名。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将文件夹改名
    private static void renameFolder(String folderPath, String newFolderName) {
        File sourceFolder = new File(folderPath);
        File targetFolder = new File(sourceFolder.getParent(), newFolderName);

        if (sourceFolder.renameTo(targetFolder)) {
            System.out.println("文件夹已重命名为 " + newFolderName);
        } else {
            System.out.println("文件夹重命名失败。");
        }
    }
}
