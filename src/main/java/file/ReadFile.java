package file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadFile {

    /*
     * 读取指定路径下的文件名和目录名
     */
    public void getFileList() {
        File file = new File("/Users/Johnson/test");

        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                System.out.println("文件：" + fileName);
            }

            if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                System.out.println("目录：" + fileName);
            }
        }
    }

    /*
     * 读取指定路径下的文件名和目录名
     */
    public void renameFileList() {
        File file = new File("/Users/Johnson/test");
        String newName = "";
        File parentPath = new File("");
        File newDir = null;
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                parentPath = fileList[i].getParentFile();
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                System.out.println("修改前文件名：" + fileName);
                if (fileName.contains("20191001")) {
                    newName = "newName"+dateString;
                    newDir = new File(parentPath + "/" + newName);
                    fileList[i].renameTo(newDir);
                    System.out.println("修改后文件名：" + newDir.getName());
                }
            }
        }


    }

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        readFile.getFileList();
        readFile.renameFileList();
    }
}
