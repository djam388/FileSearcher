import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.SizeFileFilter;

import java.io.File;
import java.util.Collection;
import java.util.Scanner;

public class FileSearcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке:");

        while (true) {
            String directoryPath = scanner.nextLine();
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
                long sizeLimit = 10 * FileUtils.ONE_MB; // Файлы больше 10 MB

                Collection<File> files = FileUtils.listFiles(
                        directory,
                        new SizeFileFilter(sizeLimit, true),
                        TrueFileFilter.TRUE
                );

                if (files.isEmpty()) {
                    System.out.println("Файлы больше 10 MB.");
                } else {
                    System.out.println("Найденные файлы:");
                    for (File file : files) {
                        System.out.println(file.getAbsolutePath() + " (" + file.length() + " bytes)");
                    }
                }
                break;
            } else {
                System.out.println("Папка не существует, попробуйте еще раз:");
            }
        }

        scanner.close();
    }
}

