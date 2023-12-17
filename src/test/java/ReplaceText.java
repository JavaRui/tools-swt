import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceText {
    public static void main(String[] args) {
        String folderPath = "E:\\IdeaProjects\\tools-swt\\src"; // 文件夹路径
        String targetString = ".wujin."; // 需要替换的字符串
        String replacementString = ".wujin."; // 替换后的字符串

        try {
            replaceStringInFolderAndFiles(folderPath, targetString, replacementString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceStringInFolderAndFiles(String folderPath, String targetString, String replacementString) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            throw new IOException("文件夹不存在");
        }

        if (folder.isFile()) {
            replaceStringInFile(folder, targetString, replacementString);
        } else {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        replaceStringInFolderAndFiles(file.getAbsolutePath(), targetString, replacementString);
                    } else {
                        replaceStringInFile(file, targetString, replacementString);
                    }
                }
            }
        }
    }

    public static void replaceStringInFile(File file, String targetString, String replacementString) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        Pattern pattern = Pattern.compile(targetString);
        Matcher matcher = pattern.matcher(content);
        String replacedContent = matcher.replaceAll(replacementString);
        Files.write(filePath, replacedContent.getBytes(StandardCharsets.UTF_8));
    }
}
